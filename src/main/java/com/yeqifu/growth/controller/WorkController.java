package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Work;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IWorkService;
import com.yeqifu.growth.vo.WorkVo;
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
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/growth/work")
public class WorkController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IWorkService workService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addWorkScore")
    public ResultObj addModuleScore(Work work){
        User user = (User) getSession().getAttribute("user");
        work.setUserid(user.getId());
        work.setYpass("2");
        //获取当前时间
        work.setWorkdate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",work.getYear());
        int count = workService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (work.getImgpath()!=null&&work.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(work.getImgpath());
                    work.setImgpath(newName);
                }
                workService.save(work);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param work
     * @return
     */
    @RequestMapping("loadWorkScore")
    public DataGridView loadWork(Work work){
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Work> list = workService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交工作数据
     * @param id
     * @return
     */
    @RequestMapping("deleteWork")
    public ResultObj deleteWork(Integer id){
        try {
            workService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版工作信息
     * @param work
     * @return
     */
    @RequestMapping("updateWork")
    public ResultObj updateGoods(Work work){
        User user = (User) getSession().getAttribute("user");
        work.setUserid(user.getId());
        work.setYpass("2");
        //获取当前时间
        work.setWorkdate(new Date());
        try {
            //商品图片不是默认图片
            if (!(work.getImgpath()!=null&&work.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (work.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(work.getImgpath());
                    work.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = workService.getById(work.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            workService.updateById(work);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除工作提交记录
     * @param workVo
     * @return
     */
    @RequestMapping("batchDeleteWork")
    public ResultObj batchDeleteWork(WorkVo workVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : workVo.getIds()) {
                idList.add(id);
            }
            workService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param workVo
     * @return
     */
    @RequestMapping("loadAllWorkScore")
    public DataGridView loadAllWork(WorkVo workVo){
        //1.声明一个分页page对象
        IPage<Work> page = new Page<Work>(workVo.getPage(),workVo.getLimit());
        QueryWrapper<Work> queryWrapper = new QueryWrapper<Work>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(workVo.getYear()),"year",workVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(workVo.getYpass()),"ypass",workVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        workService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Work> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Work work : list) {
            Integer studentId = work.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                work.setStudentname(student.getName());
                work.setSnumber(student.getLoginname());
                //设置学生年级
                work.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Work> works = new ArrayList<>();
        if(StringUtils.isNotBlank(workVo.getGrades())&&StringUtils.isBlank(workVo.getStudentname())){
            works.clear();
            for (Work work : list) {
                if(work.getGrades().equals(workVo.getGrades())){
                    works.add(work);
                }
            }
            return new DataGridView(works.stream().count(),works);
        }
        if(StringUtils.isNotBlank(workVo.getStudentname())&&StringUtils.isBlank(workVo.getGrades())){
            works.clear();
            for (Work work : list) {
                if(work.getStudentname().indexOf(workVo.getStudentname())>=0){
                    works.add(work);
                }
            }
            return new DataGridView(works.stream().count(),works);
        }
        if(StringUtils.isNotBlank(workVo.getStudentname())&&StringUtils.isNotBlank(workVo.getGrades())){
            works.clear();
            for (Work work : list) {
                if(work.getStudentname().indexOf(workVo.getStudentname())>=0&&work.getGrades().equals(workVo.getGrades())){
                    works.add(work);
                }
            }
            return new DataGridView(works.stream().count(),works);
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
            Work work=workService.getById(id);
            work.setYpass("1");
            workService.updateById(work);
            //审核通过后，更新综合成绩中社会工作成绩
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                work.setYpass("2");
                workService.updateById(work);
                return ResultObj.VERIFY_ERROR;
            }
            one.setSwork(doscore);
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
            Work work=workService.getById(id);
            work.setYpass("0");
            work.setWhy(why);
            workService.updateById(work);
            //审核拒绝后，将综合成绩表单中社会工作成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setSwork(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param work
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Work work){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Work> workQueryWrapper = new QueryWrapper<>();
        workQueryWrapper.eq("userid",user.getId());
        workQueryWrapper.eq("year",work.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Work one = workService.getOne(workQueryWrapper);
            //修改word文件路径
            one.setYpass("2");
            one.setWordpath(work.getWordpath());
            workService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

