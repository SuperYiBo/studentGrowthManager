package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Honor;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IHonorService;
import com.yeqifu.growth.vo.HonorVo;
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
@RequestMapping("/growth/honor")
public class HonorController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IHonorService honorService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addHonorScore")
    public ResultObj addModuleScore(Honor honor){
        User user = (User) getSession().getAttribute("user");
        honor.setUserid(user.getId());
        honor.setYpass("2");
        //获取当前时间
        honor.setHonordate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Honor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",honor.getYear());
        int count = honorService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (honor.getImgpath()!=null&&honor.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(honor.getImgpath());
                    honor.setImgpath(newName);
                }
                honorService.save(honor);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param honor
     * @return
     */
    @RequestMapping("loadHonorScore")
    public DataGridView loadHonor(Honor honor){
        QueryWrapper<Honor> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Honor> list = honorService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deleteHonor")
    public ResultObj deleteHonor(Integer id){
        try {
            honorService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改信息
     * @param honor
     * @return
     */
    @RequestMapping("updateHonor")
    public ResultObj updateGoods(Honor honor){
        User user = (User) getSession().getAttribute("user");
        honor.setUserid(user.getId());
        honor.setYpass("2");
        //获取当前时间
        honor.setHonordate(new Date());
        try {
            //商品图片不是默认图片
            if (!(honor.getImgpath()!=null&&honor.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (honor.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(honor.getImgpath());
                    honor.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = honorService.getById(honor.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            honorService.updateById(honor);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除提交记录
     * @param honorVo
     * @return
     */
    @RequestMapping("batchDeleteHonor")
    public ResultObj batchDeleteHonor(HonorVo honorVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : honorVo.getIds()) {
                idList.add(id);
            }
            honorService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param honorVo
     * @return
     */
    @RequestMapping("loadAllHonorScore")
    public DataGridView loadAllHonor(HonorVo honorVo){
        //1.声明一个分页page对象
        IPage<Honor> page = new Page<Honor>(honorVo.getPage(),honorVo.getLimit());
        QueryWrapper<Honor> queryWrapper = new QueryWrapper<Honor>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(honorVo.getYear()),"year",honorVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(honorVo.getYpass()),"ypass",honorVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        honorService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Honor> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Honor honor : list) {
            Integer studentId = honor.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                honor.setStudentname(student.getName());
                honor.setSnumber(student.getLoginname());
                //设置学生年级
                honor.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Honor> honors = new ArrayList<>();
        if(StringUtils.isNotBlank(honorVo.getGrades())&&StringUtils.isBlank(honorVo.getStudentname())){
            honors.clear();
            for (Honor honor : list) {
                if(honor.getGrades().equals(honorVo.getGrades())){
                    honors.add(honor);
                }
            }
            return new DataGridView(honors.stream().count(),honors);
        }
        if(StringUtils.isNotBlank(honorVo.getStudentname())&&StringUtils.isBlank(honorVo.getGrades())){
            honors.clear();
            for (Honor honor : list) {
                if(honor.getStudentname().indexOf(honorVo.getStudentname())>=0){
                    honors.add(honor);
                }
            }
            return new DataGridView(honors.stream().count(),honors);
        }
        if(StringUtils.isNotBlank(honorVo.getStudentname())&&StringUtils.isNotBlank(honorVo.getGrades())){
            honors.clear();
            for (Honor honor : list) {
                if(honor.getStudentname().indexOf(honorVo.getStudentname())>=0&&honor.getGrades().equals(honorVo.getGrades())){
                    honors.add(honor);
                }
            }
            return new DataGridView(honors.stream().count(),honors);
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
            Honor honor=honorService.getById(id);
            honor.setYpass("1");
            honorService.updateById(honor);
            //审核通过后，更新综合成绩表单中荣誉成绩
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                honor.setYpass("2");
                honorService.updateById(honor);
                return ResultObj.VERIFY_ERROR;
            }
            one.setRhonor(doscore);
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
            Honor honor=honorService.getById(id);
            honor.setYpass("0");
            honor.setWhy(why);
            honorService.updateById(honor);
            //审核拒绝后，将综合成绩表单中，荣誉成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setRhonor(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param honor
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Honor honor){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Honor> honorQueryWrapper = new QueryWrapper<>();
        honorQueryWrapper.eq("userid",user.getId());
        honorQueryWrapper.eq("year",honor.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Honor one = honorService.getOne(honorQueryWrapper);
            //修改word文件路径
            one.setYpass("2");
            one.setWordpath(honor.getWordpath());
            honorService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

