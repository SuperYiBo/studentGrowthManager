package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Competition;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.ICompetitionService;
import com.yeqifu.growth.vo.CompetitionVo;
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
@RequestMapping("/growth/competition")
public class CompetitionController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private ICompetitionService competitionService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addCompetitionScore")
    public ResultObj addModuleScore(Competition competition){
        User user = (User) getSession().getAttribute("user");
        competition.setUserid(user.getId());
        competition.setYpass("2");
        //获取当前时间
        competition.setCompetitiondate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",competition.getYear());
        int count = competitionService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (competition.getImgpath()!=null&&competition.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(competition.getImgpath());
                    competition.setImgpath(newName);
                }
                competitionService.save(competition);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param competition
     * @return
     */
    @RequestMapping("loadCompetitionScore")
    public DataGridView loadCompetition(Competition competition){
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Competition> list = competitionService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交项目成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deleteCompetition")
    public ResultObj deleteCompetition(Integer id){
        try {
            competitionService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版项目信息
     * @param competition
     * @return
     */
    @RequestMapping("updateCompetition")
    public ResultObj updateGoods(Competition competition){
        User user = (User) getSession().getAttribute("user");
        competition.setUserid(user.getId());
        competition.setYpass("2");
        //获取当前时间
        competition.setCompetitiondate(new Date());
        try {
            //商品图片不是默认图片
            if (!(competition.getImgpath()!=null&&competition.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (competition.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(competition.getImgpath());
                    competition.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = competitionService.getById(competition.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            competitionService.updateById(competition);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除出版项目提交记录
     * @param competitionVo
     * @return
     */
    @RequestMapping("batchDeleteCompetition")
    public ResultObj batchDeleteCompetition(CompetitionVo competitionVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : competitionVo.getIds()) {
                idList.add(id);
            }
            competitionService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param competitionVo
     * @return
     */
    @RequestMapping("loadAllCompetitionScore")
    public DataGridView loadAllCompetition(CompetitionVo competitionVo){
        //1.声明一个分页page对象
        IPage<Competition> page = new Page<Competition>(competitionVo.getPage(),competitionVo.getLimit());
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<Competition>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(competitionVo.getYear()),"year",competitionVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(competitionVo.getYpass()),"ypass",competitionVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        competitionService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Competition> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Competition competition : list) {
            Integer studentId = competition.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                competition.setStudentname(student.getName());
                competition.setSnumber(student.getLoginname());
                //设置学生年级
                competition.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Competition> competitions = new ArrayList<>();
        if(StringUtils.isNotBlank(competitionVo.getGrades())&&StringUtils.isBlank(competitionVo.getStudentname())){
            competitions.clear();
            for (Competition competition : list) {
                if(competition.getGrades().equals(competitionVo.getGrades())){
                    competitions.add(competition);
                }
            }
            return new DataGridView(competitions.stream().count(),competitions);
        }
        if(StringUtils.isNotBlank(competitionVo.getStudentname())&&StringUtils.isBlank(competitionVo.getGrades())){
            competitions.clear();
            for (Competition competition : list) {
                if(competition.getStudentname().indexOf(competitionVo.getStudentname())>=0){
                    competitions.add(competition);
                }
            }
            return new DataGridView(competitions.stream().count(),competitions);
        }
        if(StringUtils.isNotBlank(competitionVo.getStudentname())&&StringUtils.isNotBlank(competitionVo.getGrades())){
            competitions.clear();
            for (Competition competition : list) {
                if(competition.getStudentname().indexOf(competitionVo.getStudentname())>=0&&competition.getGrades().equals(competitionVo.getGrades())){
                    competitions.add(competition);
                }
            }
            return new DataGridView(competitions.stream().count(),competitions);
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
            Competition competition=competitionService.getById(id);
            competition.setYpass("1");
            competitionService.updateById(competition);
            //审核通过后更新综合成绩表单，竞赛模块分数
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                competition.setYpass("2");
                competitionService.updateById(competition);
                return ResultObj.VERIFY_ERROR;
            }
            one.setKcompetition(doscore);
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
            Competition competition=competitionService.getById(id);
            competition.setYpass("0");
            competition.setWhy(why);
            competitionService.updateById(competition);
            //审核拒绝后，清空综合成绩表单竞赛的成绩
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setKcompetition(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param competition
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Competition competition){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Competition> competitionQueryWrapper = new QueryWrapper<>();
        competitionQueryWrapper.eq("userid",user.getId());
        competitionQueryWrapper.eq("year",competition.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Competition one = competitionService.getOne(competitionQueryWrapper);
            //修改word文件路径
            one.setYpass("2");
            one.setWordpath(competition.getWordpath());
            competitionService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

