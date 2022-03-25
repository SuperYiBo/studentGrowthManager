package com.yeqifu.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeqifu.sys.common.ActiverUser;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.Loginfo;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.ILoginfoService;
import com.yeqifu.sys.service.IUserService;
import com.yeqifu.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("login")
public class LoginController {
    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginfoService loginfoService;

    @PostMapping("sendEmail")
    public Map<String, Object> sendEmail(String loginname, String email, HttpSession httpSession) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("loginname", loginname);
        User one = userService.getOne(userQueryWrapper);
        if (one == null) {
            map.put("success", false);
            map.put("errorInfo", "账户不存在!");
            return map;
        } else if (!email.equals(one.getEmail())) {
            map.put("success", false);
            map.put("errorInfo", "邮箱错误!");
            return map;
        } else {
            String mailCode = WebUtils.getSixRandom();
            //发邮件
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1844246071@qq.com");                        //发件人
            message.setTo(email);                                       //收件人
            message.setSubject("学生成长管理评价系统-用户找回密码");         //主题
            message.setText("您本次的验证码是：" + mailCode);            //正文内容
            try {
                mailSender.send(message);
                System.out.println(mailCode);
                //将密码存到session
                httpSession.setAttribute("mailCode", mailCode);
                httpSession.setAttribute("loginname", loginname);
                map.put("success", true);
                return map;
            } catch (MailException e) {
                e.printStackTrace();
                map.put("success", false);
                map.put("errorInfo", "发送失败，请稍后重试!");
                return map;
            }
        }
    }

    @PostMapping("checkYzm")
    public Map<String, Object> checkYzm(String yzm, HttpSession httpSession) {
        HashMap<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(yzm)) {
            map.put("success", false);
            map.put("errorInfo", "验证码不能为空");
            return map;
        }
        String mailCode = (String) httpSession.getAttribute("mailCode");
        String loginname = (String) httpSession.getAttribute("loginname");
        if (!yzm.equals(mailCode)) {
            map.put("success", false);
            map.put("errorInfo", "验证码错误");
            return map;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("loginname", loginname);
        User one = userService.getOne(userQueryWrapper);
        try {
            //设置盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            one.setSalt(salt);
            //设置默认密码
            one.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());
            userService.updateById(one);
            map.put("success", true);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errorInfo", "重置密码出错了，请稍后再尝试！!");
            return map;
        }

    }

    @RequestMapping("login")
    public ResultObj login(UserVo userVo, String code, HttpSession session) {
        //userVo  用户的登录信息
        // code    验证码信息
        // session  给用户返回登陆页面时，登录验证码的的具体数据存到了session中

        //获得存储在session中的验证码
        System.out.println("login/login执行了");
        String sessionCode = (String) session.getAttribute("code");
        if (code != null && sessionCode.equals(code)) {
            Subject subject = SecurityUtils.getSubject();
            System.out.println(subject);
            // UsernamePasswordToken
            AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(), userVo.getPwd());
            try {
                //对用户进行认证登陆
                subject.login(token);
                //通过subject获取以认证活动的user
                ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
                //将user存储到session中
                WebUtils.getSession().setAttribute("user", activerUser.getUser());
                //记录登陆日志
                Loginfo entity = new Loginfo();
                entity.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());
                entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
                entity.setLogintime(new Date());
                loginfoService.save(entity);

                return ResultObj.LOGIN_SUCCESS;
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return ResultObj.LOGIN_ERROR_PASS;
            }
        } else {
            return ResultObj.LOGIN_ERROR_CODE;
        }

    }

    /**
     * 得到登陆验证码
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 5);
        session.setAttribute("code", lineCaptcha.getCode());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
