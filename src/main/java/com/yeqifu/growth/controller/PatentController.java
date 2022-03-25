package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Patent;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IPatentService;
import com.yeqifu.growth.vo.PatentVo;
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
@RequestMapping("/growth/patent")
public class PatentController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IPatentService patentService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addPatentScore")
    public ResultObj addModuleScore(Patent patent){
        User user = (User) getSession().getAttribute("user");
        patent.setUserid(user.getId());
        patent.setYpass("2");
        //获取当前时间
        patent.setPatentdate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Patent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",patent.getYear());
        int count = patentService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (patent.getImgpath()!=null&&patent.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(patent.getImgpath());
                    patent.setImgpath(newName);
                }
                patentService.save(patent);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param patent
     * @return
     */
    @RequestMapping("loadPatentScore")
    public DataGridView loadPatent(Patent patent){
        QueryWrapper<Patent> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Patent> list = patentService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交专利成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deletePatent")
    public ResultObj deletePatent(Integer id){
        try {
            patentService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版专利信息
     * @param patent
     * @return
     */
    @RequestMapping("updatePatent")
    public ResultObj updateGoods(Patent patent){
        User user = (User) getSession().getAttribute("user");
        patent.setUserid(user.getId());
        patent.setYpass("2");
        //获取当前时间
        patent.setPatentdate(new Date());
        try {
            //商品图片不是默认图片
            if (!(patent.getImgpath()!=null&&patent.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (patent.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(patent.getImgpath());
                    patent.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = patentService.getById(patent.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            patentService.updateById(patent);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除出版专利提交记录
     * @param patentVo
     * @return
     */
    @RequestMapping("batchDeletePatent")
    public ResultObj batchDeletePatent(PatentVo patentVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : patentVo.getIds()) {
                idList.add(id);
            }
            patentService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param patentVo
     * @return
     */
    @RequestMapping("loadAllPatentScore")
    public DataGridView loadAllPatent(PatentVo patentVo){
        //1.声明一个分页page对象
        IPage<Patent> page = new Page<Patent>(patentVo.getPage(),patentVo.getLimit());
        QueryWrapper<Patent> queryWrapper = new QueryWrapper<Patent>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(patentVo.getYear()),"year",patentVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(patentVo.getYpass()),"ypass",patentVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        patentService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Patent> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Patent patent : list) {
            Integer studentId = patent.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                patent.setStudentname(student.getName());
                patent.setSnumber(student.getLoginname());
                //设置学生年级
                patent.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Patent> patents = new ArrayList<>();
        if(StringUtils.isNotBlank(patentVo.getGrades())&&StringUtils.isBlank(patentVo.getStudentname())){
            patents.clear();
            for (Patent patent : list) {
                if(patent.getGrades().equals(patentVo.getGrades())){
                    patents.add(patent);
                }
            }
            return new DataGridView(patents.stream().count(),patents);
        }
        if(StringUtils.isNotBlank(patentVo.getStudentname())&&StringUtils.isBlank(patentVo.getGrades())){
            patents.clear();
            for (Patent patent : list) {
                if(patent.getStudentname().indexOf(patentVo.getStudentname())>=0){
                    patents.add(patent);
                }
            }
            return new DataGridView(patents.stream().count(),patents);
        }
        if(StringUtils.isNotBlank(patentVo.getStudentname())&&StringUtils.isNotBlank(patentVo.getGrades())){
            patents.clear();
            for (Patent patent : list) {
                if(patent.getStudentname().indexOf(patentVo.getStudentname())>=0&&patent.getGrades().equals(patentVo.getGrades())){
                    patents.add(patent);
                }
            }
            return new DataGridView(patents.stream().count(),patents);
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
            Patent patent=patentService.getById(id);
            patent.setYpass("1");
            patentService.updateById(patent);
            //审核通过后更新综合成绩表单的专利成绩
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                patent.setYpass("2");
                patentService.updateById(patent);
                return ResultObj.VERIFY_ERROR;
            }
            one.setKpatent(doscore);
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
            Patent patent=patentService.getById(id);
            patent.setYpass("0");
            patent.setWhy(why);
            patentService.updateById(patent);
            //审核拒绝后，将综合成绩表单中专利成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setKpatent(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param patent
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Patent patent){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Patent> patentQueryWrapper = new QueryWrapper<>();
        patentQueryWrapper.eq("userid",user.getId());
        patentQueryWrapper.eq("year",patent.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Patent one = patentService.getOne(patentQueryWrapper);
            //修改word文件路径
            one.setYpass("2");
            one.setWordpath(patent.getWordpath());
            patentService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

