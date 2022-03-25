package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Paper;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IPaperService;
import com.yeqifu.growth.vo.PaperVo;
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
@RequestMapping("/growth/paper")
public class PaperController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IPaperService paperService;
    /**
     * 添加
     * @return
     */
    @RequestMapping("addpaperScore")
    public ResultObj addModuleScore(Paper paper){
        User user = (User) getSession().getAttribute("user");
        paper.setUserid(user.getId());
        paper.setYpass("2");
        //获取当前时间
        paper.setPaperdate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",paper.getYear());
        int count = paperService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (paper.getImgpath()!=null&&paper.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(paper.getImgpath());
                    paper.setImgpath(newName);
                }
                paperService.save(paper);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param paper
     * @return
     */
    @RequestMapping("loadpaperScore")
    public DataGridView loadpaper(Paper paper){
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Paper> list = paperService.list(queryWrapper);
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交论文成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deletepaper")
    public ResultObj deletepaper(Integer id){
        try {
            paperService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版论文信息
     * @param paper
     * @return
     */
    @RequestMapping("updatepaper")
    public ResultObj updateGoods(Paper paper){
        User user = (User) getSession().getAttribute("user");
        paper.setUserid(user.getId());
        paper.setYpass("2");
        //获取当前时间
        paper.setPaperdate(new Date());
        try {
            //商品图片不是默认图片
            if (!(paper.getImgpath()!=null&&paper.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (paper.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(paper.getImgpath());
                    paper.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = paperService.getById(paper.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            paperService.updateById(paper);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除出版论文提交记录
     * @param paperVo
     * @return
     */
    @RequestMapping("batchDeletepaper")
    public ResultObj batchDeletePaper(PaperVo paperVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : paperVo.getIds()) {
                idList.add(id);
            }
            paperService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询学生个人提交记录
     * @param paperVo
     * @return
     */
    @RequestMapping("loadAllpaperScore")
    public DataGridView loadAllpaper(PaperVo paperVo){
        //1.声明一个分页page对象
        IPage<Paper> page = new Page<Paper>(paperVo.getPage(),paperVo.getLimit());
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<Paper>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(paperVo.getYear()),"year",paperVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(paperVo.getYpass()),"ypass",paperVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        paperService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Paper> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Paper paper : list) {
            Integer studentId = paper.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                paper.setStudentname(student.getName());
                paper.setSnumber(student.getLoginname());
                //设置学生年级
                paper.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Paper> papers = new ArrayList<>();
        if(StringUtils.isNotBlank(paperVo.getGrades())&&StringUtils.isBlank(paperVo.getStudentname())){
            papers.clear();
            for (Paper paper : list) {
                if(paper.getGrades().equals(paperVo.getGrades())){
                    papers.add(paper);
                }
            }
            return new DataGridView(papers.stream().count(),papers);
        }
        if(StringUtils.isNotBlank(paperVo.getStudentname())&&StringUtils.isBlank(paperVo.getGrades())){
            papers.clear();
            for (Paper paper : list) {
                if(paper.getStudentname().indexOf(paperVo.getStudentname())>=0){
                    papers.add(paper);
                }
            }
            return new DataGridView(papers.stream().count(),papers);
        }
        if(StringUtils.isNotBlank(paperVo.getStudentname())&&StringUtils.isNotBlank(paperVo.getGrades())){
            papers.clear();
            for (Paper paper : list) {
                if(paper.getStudentname().indexOf(paperVo.getStudentname())>=0&&paper.getGrades().equals(paperVo.getGrades())){
                    papers.add(paper);
                }
            }
            return new DataGridView(papers.stream().count(),papers);
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
            Paper paper=paperService.getById(id);
            paper.setYpass("1");
            paperService.updateById(paper);
            //审核通过后，更新综合成绩表单论文分数
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                paper.setYpass("2");
                paperService.updateById(paper);
                return ResultObj.VERIFY_ERROR;
            }
            one.setKpaper(doscore);
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
            Paper paper=paperService.getById(id);
            paper.setYpass("0");
            paper.setWhy(why);
            paperService.updateById(paper);
            //拒绝后查找综合成绩表单，对应成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setKpaper(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param paper
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Paper paper){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Paper> paperQueryWrapper = new QueryWrapper<>();
        paperQueryWrapper.eq("userid",user.getId());
        paperQueryWrapper.eq("year",paper.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Paper one = paperService.getOne(paperQueryWrapper);
            //修改word文件路径
            one.setWordpath(paper.getWordpath());
            one.setYpass("2");
            paperService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

