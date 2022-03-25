package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Certificate;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.ICertificateService;
import com.yeqifu.growth.vo.CertificateVo;
import com.yeqifu.sys.common.AppFileUtils;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IGradeService;
import com.yeqifu.sys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.yeqifu.sys.common.WebUtils.getSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
@RestController
@RequestMapping("/growth/certificate")
public class CertificateController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private ICertificateService certificateService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addCertificateScore")
    public ResultObj addModuleScore(Certificate certificate){
        User user = (User) getSession().getAttribute("user");
        certificate.setUserid(user.getId());
        certificate.setYpass("2");
        //获取当前时间
        certificate.setCertificatedate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Certificate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",certificate.getYear());
        int count = certificateService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (certificate.getImgpath()!=null&&certificate.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(certificate.getImgpath());
                    certificate.setImgpath(newName);
                }
                certificateService.save(certificate);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param certificate
     * @return
     */
    @RequestMapping("loadCertificateScore")
    public DataGridView loadCertificate(Certificate certificate){
        QueryWrapper<Certificate> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Certificate> list = certificateService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交证书数据
     * @param id
     * @return
     */
    @RequestMapping("deleteCertificate")
    public ResultObj deleteCertificate(Integer id){
        try {
            certificateService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版证书信息
     * @param certificate
     * @return
     */
    @RequestMapping("updateCertificate")
    public ResultObj updateGoods(Certificate certificate){
        User user = (User) getSession().getAttribute("user");
        certificate.setUserid(user.getId());
        certificate.setYpass("2");
        //获取当前时间
        certificate.setCertificatedate(new Date());
        try {
            //商品图片不是默认图片
            if (!(certificate.getImgpath()!=null&&certificate.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (certificate.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(certificate.getImgpath());
                    certificate.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = certificateService.getById(certificate.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            certificateService.updateById(certificate);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除证书提交记录
     * @param certificateVo
     * @return
     */
    @RequestMapping("batchDeleteCertificate")
    public ResultObj batchDeleteCertificate(CertificateVo certificateVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : certificateVo.getIds()) {
                idList.add(id);
            }
            certificateService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param certificateVo
     * @return
     */
    @RequestMapping("loadAllCertificateScore")
    public DataGridView loadAllcertificate(CertificateVo certificateVo){
        //1.声明一个分页page对象
        IPage<Certificate> page = new Page<Certificate>(certificateVo.getPage(),certificateVo.getLimit());
        QueryWrapper<Certificate> queryWrapper = new QueryWrapper<Certificate>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(certificateVo.getYear()),"year",certificateVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(certificateVo.getYpass()),"ypass",certificateVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        certificateService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Certificate> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Certificate certificate : list) {
            Integer studentId = certificate.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                certificate.setStudentname(student.getName());
                certificate.setSnumber(student.getLoginname());
                //设置学生年级
                certificate.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Certificate> certificates = new ArrayList<>();
        if(StringUtils.isNotBlank(certificateVo.getGrades())&&StringUtils.isBlank(certificateVo.getStudentname())){
            certificates.clear();
            for (Certificate certificate : list) {
                if(certificate.getGrades().equals(certificateVo.getGrades())){
                    certificates.add(certificate);
                }
            }
            return new DataGridView(certificates.stream().count(),certificates);
        }
        if(StringUtils.isNotBlank(certificateVo.getStudentname())&&StringUtils.isBlank(certificateVo.getGrades())){
            certificates.clear();
            for (Certificate certificate : list) {
                if(certificate.getStudentname().indexOf(certificateVo.getStudentname())>=0){
                    certificates.add(certificate);
                }
            }
            return new DataGridView(certificates.stream().count(),certificates);
        }
        if(StringUtils.isNotBlank(certificateVo.getStudentname())&&StringUtils.isNotBlank(certificateVo.getGrades())){
            certificates.clear();
            for (Certificate certificate : list) {
                if(certificate.getStudentname().indexOf(certificateVo.getStudentname())>=0&&certificate.getGrades().equals(certificateVo.getGrades())){
                    certificates.add(certificate);
                }
            }
            return new DataGridView(certificates.stream().count(),certificates);
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 审核通过
     * @param id
     * @return
     */
    @RequestMapping("updateVerify1")
    public ResultObj updateVerify1(Integer id,String snumber,String year,Double doscore){
        try {
            //审核通过
            Certificate certificate=certificateService.getById(id);
            certificate.setYpass("1");
            certificateService.updateById(certificate);
            //审核通过后，更新综合成绩表单中的证书成绩
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                certificate.setYpass("2");
                certificateService.updateById(certificate);
                return ResultObj.VERIFY_ERROR;
            }
            one.setKcertificate(doscore);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ERROR;
        }
    }

    /**
     * 审核不通过
     * @param id
     * @return
     */
    @RequestMapping("updateVerify0")
    public ResultObj updateVerify0(Integer id,String snumber,String year,String why){
        try {
            //审核拒绝
            Certificate certificate=certificateService.getById(id);
            certificate.setYpass("0");
            certificate.setWhy(why);
            certificateService.updateById(certificate);
            //审核拒绝后，将综合成绩表单中的证书成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setKcertificate(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param certificate
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Certificate certificate){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Certificate> certificateQueryWrapper = new QueryWrapper<>();
        certificateQueryWrapper.eq("userid",user.getId());
        certificateQueryWrapper.eq("year",certificate.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Certificate one = certificateService.getOne(certificateQueryWrapper);
            //修改word文件路径
            one.setYpass("2");
            one.setWordpath(certificate.getWordpath());
            certificateService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

