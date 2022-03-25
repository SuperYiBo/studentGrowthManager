package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Modulescore;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IModulescoreService;
import com.yeqifu.growth.vo.ModuleScoreVo;
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
 * @since 2022-02-17
 */
@RestController
@RequestMapping("/growth/modulescore")
public class ModulescoreController {

    @Autowired
    private IAllscoreService allscoreService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IModulescoreService modulescoreService;
    /**
     * 添加学年模块成绩表单
     * @return
     */
    @RequestMapping("addModuleScore")
    public ResultObj addModuleScore(Modulescore modulescore){
        //获取该用户id
        User user = (User) getSession().getAttribute("user");
        modulescore.setUserid(user.getId());
        //获取当前时间
        modulescore.setCreateDate(new Date());
        //计算平均分
        Double add = modulescore.getAmbit()+modulescore.getGeneral()
                +modulescore.getPractice()+modulescore.getMajor()+
                modulescore.getPublicBase()+modulescore.getSpacialWeek()+
                modulescore.getSpecialized();
        modulescore.setAveragescore(add/Constast.MODULE_SIZE);
        //条件查询，通过用户id和年份获取该学生的成绩数据
        QueryWrapper<Modulescore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",modulescore.getYear());
        //条件查询是否有相关数据，防止数据同年重复提交
        int count = modulescoreService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                //添加学年成绩
                modulescoreService.save(modulescore);
                //同时更新到综合成绩表单中学年成绩
                QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
                allscoreQueryWrapper.eq("userid",modulescore.getUserid());
                allscoreQueryWrapper.eq("year",modulescore.getYear());
                Allscore one = allscoreService.getOne(allscoreQueryWrapper);
                one.setFail(modulescore.getFail());
                one.setModule(modulescore.getAveragescore());
                allscoreService.updateById(one);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }

    /**
     * 查询学生个人提交记录,加载表单
     * @param modulescore
     * @return
     */
    @RequestMapping("loadModuleScore")
    public DataGridView loadModuleScore(Modulescore modulescore){
        QueryWrapper<Modulescore> queryWrapper = new QueryWrapper<Modulescore>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Modulescore> list = modulescoreService.list(queryWrapper);
        return new DataGridView(list);
    }

    /**
     * 删除学生个人提交学期成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deleteModuleScore")
    public ResultObj deleteModuleScore(Integer id){
        try {
            modulescoreService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 教师批量删除出版书籍提交记录
     * @param moduleScoreVo
     * @return
     */
    @RequestMapping("batchDeleteScore")
    public ResultObj batchDeleteScore(ModuleScoreVo moduleScoreVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : moduleScoreVo.getIds()) {
                idList.add(id);
            }
            modulescoreService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询全部提交记录
     * @param moduleScoreVo
     * @return
     */
    @RequestMapping("loadModuleScoreAll")
    public DataGridView loadModuleScoreAll(ModuleScoreVo moduleScoreVo){

        //1.声明一个分页page对象
        IPage<Modulescore> page = new Page<Modulescore>(moduleScoreVo.getPage(),moduleScoreVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<Modulescore> queryWrapper = new QueryWrapper<Modulescore>();
        //根据用户登录名称以及用户名称模糊查询用户
        queryWrapper.like(StringUtils.isNotBlank(moduleScoreVo.getYear()),"year",moduleScoreVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(moduleScoreVo.getFail()),"fail",moduleScoreVo.getFail());
        queryWrapper.orderByDesc("averagescore");
        modulescoreService.page(page,queryWrapper);
        //将所有用户数据放入list中
        List<Modulescore> list = page.getRecords();
        //级联查询出学生（user)姓名，学号(年级）
        for (Modulescore modulescore : list) {
            Integer studentId = modulescore.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                modulescore.setStudentname(student.getName());
                modulescore.setSnumber(student.getLoginname());
                //设置学生年级
                modulescore.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Modulescore> moduleScores = new ArrayList<>();
        if(StringUtils.isNotBlank(moduleScoreVo.getGrades())&&StringUtils.isBlank(moduleScoreVo.getStudentname())){
            moduleScores.clear();
            for (Modulescore modulescore : list) {
                if(modulescore.getGrades().equals(moduleScoreVo.getGrades())){
                    moduleScores.add(modulescore);
                }
            }
            return new DataGridView(moduleScores.stream().count(),moduleScores);
        }
        if(StringUtils.isNotBlank(moduleScoreVo.getStudentname())&&StringUtils.isBlank(moduleScoreVo.getGrades())){
            moduleScores.clear();
            for (Modulescore modulescore : list) {
                if(modulescore.getStudentname().indexOf(moduleScoreVo.getStudentname())>=0){
                    moduleScores.add(modulescore);
                }
            }
            return new DataGridView(moduleScores.stream().count(),moduleScores);
        }
        if(StringUtils.isNotBlank(moduleScoreVo.getStudentname())&&StringUtils.isNotBlank(moduleScoreVo.getGrades())){
            moduleScores.clear();
            for (Modulescore modulescore : list) {
                if(modulescore.getStudentname().indexOf(moduleScoreVo.getStudentname())>=0&&modulescore.getGrades().equals(moduleScoreVo.getGrades())){
                    moduleScores.add(modulescore);
                }
            }
            return new DataGridView(moduleScores.stream().count(),moduleScores);
        }

        return new DataGridView(page.getTotal(),list);
    }
    /**
     * 学生修改成绩信息
     * @param modulescore
     * @return
     */
    @RequestMapping("updateModuleScore")
    public ResultObj updateModuleScore(Modulescore modulescore){
        User user = (User) getSession().getAttribute("user");
        modulescore.setUserid(user.getId());
        //获取当前时间
        modulescore.setCreateDate(new Date());
        //计算平均分
        Double add = modulescore.getAmbit()+modulescore.getGeneral()
                +modulescore.getPractice()+modulescore.getMajor()+
                modulescore.getPublicBase()+modulescore.getSpacialWeek()+
                modulescore.getSpecialized();
        modulescore.setAveragescore(add/Constast.MODULE_SIZE);
        try {
            //修改后更新成绩
            modulescoreService.updateById(modulescore);
            //修改后，更新综合成绩表单中学年模块成绩
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",modulescore.getUserid());
            allscoreQueryWrapper.eq("year",modulescore.getYear());
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setFail(modulescore.getFail());
            one.setModule(modulescore.getAveragescore());
            allscoreService.updateById(one);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}

