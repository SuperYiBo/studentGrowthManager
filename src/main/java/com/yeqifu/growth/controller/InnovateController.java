package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Innovate;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IInnovateService;
import com.yeqifu.growth.vo.InnovateVo;
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
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/growth/innovate")
public class InnovateController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IInnovateService innovateService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addInnovateScore")
    public ResultObj addModuleScore(Innovate innovate){
        User user = (User) getSession().getAttribute("user");
        innovate.setUserid(user.getId());
        innovate.setYpass("2");
        //获取当前时间
        innovate.setInnovatedate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Innovate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",innovate.getYear());
        int count = innovateService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (innovate.getImgpath()!=null&&innovate.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(innovate.getImgpath());
                    innovate.setImgpath(newName);
                }
                innovateService.save(innovate);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param innovate
     * @return
     */
    @RequestMapping("loadInnovateScore")
    public DataGridView loadinnovate(Innovate innovate){
        QueryWrapper<Innovate> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Innovate> list = innovateService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交项目成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deleteInnovate")
    public ResultObj deleteinnovate(Integer id){
        try {
            innovateService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版项目信息
     * @param innovate
     * @return
     */
    @RequestMapping("updateInnovate")
    public ResultObj updateGoods(Innovate innovate){
        User user = (User) getSession().getAttribute("user");
        innovate.setUserid(user.getId());
        innovate.setYpass("2");
        //获取当前时间
        innovate.setInnovatedate(new Date());
        try {
            //商品图片不是默认图片
            if (!(innovate.getImgpath()!=null&&innovate.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (innovate.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(innovate.getImgpath());
                    innovate.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = innovateService.getById(innovate.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            innovateService.updateById(innovate);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除出版项目提交记录
     * @param innovateVo
     * @return
     */
    @RequestMapping("batchDeleteInnovate")
    public ResultObj batchDeleteinnovate(InnovateVo innovateVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : innovateVo.getIds()) {
                idList.add(id);
            }
            innovateService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param innovateVo
     * @return
     */
    @RequestMapping("loadAllInnovateScore")
    public DataGridView loadAllinnovate(InnovateVo innovateVo){
        //1.声明一个分页page对象
        IPage<Innovate> page = new Page<Innovate>(innovateVo.getPage(),innovateVo.getLimit());
        QueryWrapper<Innovate> queryWrapper = new QueryWrapper<Innovate>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(innovateVo.getYear()),"year",innovateVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(innovateVo.getYpass()),"ypass",innovateVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        innovateService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Innovate> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Innovate innovate : list) {
            Integer studentId = innovate.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                innovate.setStudentname(student.getName());
                innovate.setSnumber(student.getLoginname());
                //设置学生年级
                innovate.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Innovate> innovates = new ArrayList<>();
        if(StringUtils.isNotBlank(innovateVo.getGrades())&&StringUtils.isBlank(innovateVo.getStudentname())){
            innovates.clear();
            for (Innovate innovate : list) {
                if(innovate.getGrades().equals(innovateVo.getGrades())){
                    innovates.add(innovate);
                }
            }
            return new DataGridView(innovates.stream().count(),innovates);
        }
        if(StringUtils.isNotBlank(innovateVo.getStudentname())&&StringUtils.isBlank(innovateVo.getGrades())){
            innovates.clear();
            for (Innovate innovate : list) {
                if(innovate.getStudentname().indexOf(innovateVo.getStudentname())>=0){
                    innovates.add(innovate);
                }
            }
            return new DataGridView(innovates.stream().count(),innovates);
        }
        if(StringUtils.isNotBlank(innovateVo.getStudentname())&&StringUtils.isNotBlank(innovateVo.getGrades())){
            innovates.clear();
            for (Innovate innovate : list) {
                if(innovate.getStudentname().indexOf(innovateVo.getStudentname())>=0&&innovate.getGrades().equals(innovateVo.getGrades())){
                    innovates.add(innovate);
                }
            }
            return new DataGridView(innovates.stream().count(),innovates);
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
            Innovate innovate=innovateService.getById(id);
            innovate.setYpass("1");
            innovateService.updateById(innovate);
            //审核通过后更新综合成绩表单创新课题研究分数
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                innovate.setYpass("2");
                innovateService.updateById(innovate);
                return ResultObj.VERIFY_ERROR;
            }
            one.setKinnovate(doscore);
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
            //拒绝通过
            Innovate innovate=innovateService.getById(id);
            innovate.setYpass("0");
            innovate.setWhy(why);
            innovateService.updateById(innovate);
            //拒绝通过后，将综合成绩表单，创新课题研究分数清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setKinnovate(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param innovate
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Innovate innovate){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Innovate> innovateQueryWrapper = new QueryWrapper<>();
        innovateQueryWrapper.eq("userid",user.getId());
        innovateQueryWrapper.eq("year",innovate.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Innovate one = innovateService.getOne(innovateQueryWrapper);
            //修改word文件路径
            one.setWordpath(innovate.getWordpath());
            one.setYpass("2");
            innovateService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

