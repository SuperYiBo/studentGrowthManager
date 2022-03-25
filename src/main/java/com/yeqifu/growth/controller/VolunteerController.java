package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Volunteer;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IVolunteerService;
import com.yeqifu.growth.vo.VolunteerVo;
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
@RequestMapping("/growth/volunteer")
public class VolunteerController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IVolunteerService volunteerService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addVolunteerScore")
    public ResultObj addModuleScore(Volunteer volunteer){
        User user = (User) getSession().getAttribute("user");
        volunteer.setUserid(user.getId());
        volunteer.setYpass("2");
        //获取当前时间
        volunteer.setVolunteerdate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Volunteer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",volunteer.getYear());
        int count = volunteerService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (volunteer.getImgpath()!=null&&volunteer.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(volunteer.getImgpath());
                    volunteer.setImgpath(newName);
                }
                volunteerService.save(volunteer);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param volunteer
     * @return
     */
    @RequestMapping("loadVolunteerScore")
    public DataGridView loadVolunteer(Volunteer volunteer){
        QueryWrapper<Volunteer> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Volunteer> list = volunteerService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交志愿数据
     * @param id
     * @return
     */
    @RequestMapping("deleteVolunteer")
    public ResultObj deleteVolunteer(Integer id){
        try {
            volunteerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版志愿信息
     * @param volunteer
     * @return
     */
    @RequestMapping("updateVolunteer")
    public ResultObj updateGoods(Volunteer volunteer){
        User user = (User) getSession().getAttribute("user");
        volunteer.setUserid(user.getId());
        volunteer.setYpass("2");
        //获取当前时间
        volunteer.setVolunteerdate(new Date());
        try {
            //商品图片不是默认图片
            if (!(volunteer.getImgpath()!=null&&volunteer.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (volunteer.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(volunteer.getImgpath());
                    volunteer.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = volunteerService.getById(volunteer.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            volunteerService.updateById(volunteer);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除志愿提交记录
     * @param volunteerVo
     * @return
     */
    @RequestMapping("batchDeleteVolunteer")
    public ResultObj batchDeleteVolunteer(VolunteerVo volunteerVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : volunteerVo.getIds()) {
                idList.add(id);
            }
            volunteerService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param volunteerVo
     * @return
     */
    @RequestMapping("loadAllVolunteerScore")
    public DataGridView loadAllVolunteer(VolunteerVo volunteerVo){
        //1.声明一个分页page对象
        IPage<Volunteer> page = new Page<Volunteer>(volunteerVo.getPage(),volunteerVo.getLimit());
        QueryWrapper<Volunteer> queryWrapper = new QueryWrapper<Volunteer>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(volunteerVo.getYear()),"year",volunteerVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(volunteerVo.getYpass()),"ypass",volunteerVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        volunteerService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Volunteer> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Volunteer volunteer : list) {
            Integer studentId = volunteer.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                volunteer.setStudentname(student.getName());
                volunteer.setSnumber(student.getLoginname());
                //设置学生年级
                volunteer.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Volunteer> volunteers = new ArrayList<>();
        if(StringUtils.isNotBlank(volunteerVo.getGrades())&&StringUtils.isBlank(volunteerVo.getStudentname())){
            volunteers.clear();
            for (Volunteer volunteer : list) {
                if(volunteer.getGrades().equals(volunteerVo.getGrades())){
                    volunteers.add(volunteer);
                }
            }
            return new DataGridView(volunteers.stream().count(),volunteers);
        }
        if(StringUtils.isNotBlank(volunteerVo.getStudentname())&&StringUtils.isBlank(volunteerVo.getGrades())){
            volunteers.clear();
            for (Volunteer volunteer : list) {
                if(volunteer.getStudentname().indexOf(volunteerVo.getStudentname())>=0){
                    volunteers.add(volunteer);
                }
            }
            return new DataGridView(volunteers.stream().count(),volunteers);
        }
        if(StringUtils.isNotBlank(volunteerVo.getStudentname())&&StringUtils.isNotBlank(volunteerVo.getGrades())){
            volunteers.clear();
            for (Volunteer volunteer : list) {
                if(volunteer.getStudentname().indexOf(volunteerVo.getStudentname())>=0&&volunteer.getGrades().equals(volunteerVo.getGrades())){
                    volunteers.add(volunteer);
                }
            }
            return new DataGridView(volunteers.stream().count(),volunteers);
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
            Volunteer volunteer=volunteerService.getById(id);
            volunteer.setYpass("1");
            volunteerService.updateById(volunteer);
            //审核通过后，去更新综合成绩表单中志愿成绩分数
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                volunteer.setYpass("2");
                volunteerService.updateById(volunteer);
                return ResultObj.VERIFY_ERROR;
            }
            one.setSvolunteer(doscore);
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
            Volunteer volunteer=volunteerService.getById(id);
            volunteer.setYpass("0");
            volunteer.setWhy(why);
            volunteerService.updateById(volunteer);
            //审核拒绝后，将综合成绩表单中志愿成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setSvolunteer(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param volunteer
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Volunteer volunteer){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Volunteer> volunteerQueryWrapper = new QueryWrapper<>();
        volunteerQueryWrapper.eq("userid",user.getId());
        volunteerQueryWrapper.eq("year",volunteer.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Volunteer one = volunteerService.getOne(volunteerQueryWrapper);
            //修改word文件路径
            one.setYpass("2");
            one.setWordpath(volunteer.getWordpath());
            volunteerService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

