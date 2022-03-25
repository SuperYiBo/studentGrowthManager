package com.yeqifu.sys.controller;

import com.yeqifu.growth.service.IBookService;
import com.yeqifu.sys.common.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("sys")
public class SystemController {
    @Autowired
    private IBookService bookService;

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/index/login";
    }

    @RequestMapping("findPassword")
    public String findPassword(){
        return "system/index/findPassword";
    }


    @RequestMapping("test")
    public String test(){
        return "system/index/test";
    }

    /**
     * 跳转到个人资料页面
     * @return
     */
    @RequestMapping("toUserInfo")
    public String toUserInfo(){
        return "system/user/userInfo";
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @RequestMapping("toChangePassword")
    public String toChangePassword(){
        return "system/user/changePassword";
    }

    /**
     * 退出然后跳转到登陆页面
     * @return
     */
    @RequestMapping("toSignOut")
    public String toSignOut(){
        //销毁session
        WebUtils.getSession().removeAttribute("user");
        return "system/index/login";
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
//       model.addAttribute("countBook", bookService.countBook());
        return "system/index/index";
    }


    /**
     * 跳转到登陆台
     * @return
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/index/deskManager";
    }

    /**
     * 跳转到日志管理
     * @return
     */
    @RequestMapping("toLoginfoManager")
    public String toLoginfoManager(){
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转到公告管理
     * @return
     */
    @RequestMapping("toNoticeManager")
    public String toNoticeManager(){
        return "system/notice/noticeManager";
    }

    /**
     * 跳转到年级管理
     * @return
     */
    @RequestMapping("toGradeManager")
    public String toGradeManager(){
        return "system/grade/gradeManager";
    }

    /**
     * 跳转到年级管理--left
     * @return
     */
    @RequestMapping("toGradeLeft")
    public String toGradeLeft(){
        return "system/grade/gradeLeft";
    }

    /**
     * 跳转到年级管理--right
     * @return
     */
    @RequestMapping("toGradeRight")
    public String toGradeRight(){
        return "system/grade/gradeRight";
    }

    /**
     * 跳转到菜单管理
     * @return
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理--left
     * @return
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }

    /**
     * 跳转到菜单管理--right
     * @return
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }

    /**
     * 跳转到权限管理
     * @return
     */
    @RequestMapping("toPermissionManager")
    public String toPermissionManager(){
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理--left
     * @return
     */
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft(){
        return "system/permission/permissionLeft";
    }

    /**
     * 跳转到权限管理--right
     * @return
     */
    @RequestMapping("toPermissionRight")
    public String toPermissionRight(){
        return "system/permission/permissionRight";
    }

    /**
     * 跳转到角色管理
     * @return
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager(){
        return "system/role/roleManager";
    }

    /**
     * 跳转到用户管理
     * @return
     */
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }

    /**
     * 跳转到缓存管理   /sys/suggest
     * @return
     */
    @RequestMapping("toCacheManager")
    public String toCacheManager(){
        return "system/cache/cacheManager";
    }



}
