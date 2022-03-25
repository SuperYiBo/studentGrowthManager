package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.vo.AllScoreVo;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IGradeService;
import com.yeqifu.sys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cxc-
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/growth/allscore")
public class AllscoreController {
    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IAllscoreService allscoreService;

    @Autowired
    private IUserService userService;

    /**
     * 查询全部提交记录
     *
     * @param allScoreVo
     * @return
     */
    @RequestMapping("loadAllScore")
    public DataGridView loadAllScore(AllScoreVo allScoreVo) {
        //实现成绩的统计
        List<Allscore> list1 = allscoreService.list();
        for(Allscore allscore:list1){
            allscore.setK(allscore.getKbook()+allscore.getKcertificate()+allscore.getKcompetition()+
                    allscore.getKinnovate()+allscore.getKpaper()+allscore.getKpatent());
            allscore.setS(allscore.getSvolunteer()+allscore.getSwork());
            allscore.setAllscore(allscore.getModule()*0.5+allscore.getK()*0.25+allscore.getS()*0.15+allscore.getRhonor()*0.1);
            allscoreService.updateById(allscore);
        }
        //实现分页展示
        IPage<Allscore> page = new Page<Allscore>(allScoreVo.getPage(), allScoreVo.getLimit());
        QueryWrapper<Allscore> queryWrapper = new QueryWrapper<Allscore>();
        //模糊查询
        queryWrapper.like(StringUtils.isNotBlank(allScoreVo.getYear()), "year", allScoreVo.getYear());
        queryWrapper.orderByDesc("allscore");
        allscoreService.page(page, queryWrapper);
        //将所有用户数据放入list中
        List<Allscore> list = page.getRecords();
        //根据userid设置学生的姓名和学号、年级
        for (Allscore allscore : list) {
            User student = userService.getById(allscore.getUserid());
            allscore.setStudentname(student.getName());
            allscore.setSnumber(student.getLoginname());
            allscore.setGrades(gradeService.getById(student.getGradeid()).getName());
        }
        ArrayList<Allscore> allScores = new ArrayList<>();
        if(StringUtils.isNotBlank(allScoreVo.getGrades())&&StringUtils.isBlank(allScoreVo.getStudentname())){
            allScores.clear();
            for (Allscore Allscore : list) {
                if(Allscore.getGrades().equals(allScoreVo.getGrades())){
                    allScores.add(Allscore);
                }
            }
            return new DataGridView(allScores.stream().count(),allScores);
        }
        if(StringUtils.isNotBlank(allScoreVo.getStudentname())&&StringUtils.isBlank(allScoreVo.getGrades())){
            allScores.clear();
            for (Allscore Allscore : list) {
                if(Allscore.getStudentname().indexOf(allScoreVo.getStudentname())>=0){
                    allScores.add(Allscore);
                }
            }
            return new DataGridView(allScores.stream().count(),allScores);
        }
        if(StringUtils.isNotBlank(allScoreVo.getStudentname())&&StringUtils.isNotBlank(allScoreVo.getGrades())){
            allScores.clear();
            for (Allscore Allscore : list) {
                if(Allscore.getStudentname().indexOf(allScoreVo.getStudentname())>=0&&Allscore.getGrades().equals(allScoreVo.getGrades())){
                    allScores.add(Allscore);
                }
            }
            return new DataGridView(allScores.stream().count(),allScores);
        }

        return new DataGridView(page.getTotal(), list);
    }
}

