package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeqifu.growth.entity.Year;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IYearService;
import com.yeqifu.growth.vo.AllScoreVo;
import com.yeqifu.growth.vo.YearVo;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxc-
 * @since 2022-02-17
 */
@RestController
@RequestMapping("/growth/year")
public class YearController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IYearService yearService;
    @Autowired
    private IAllscoreService allscoreService;

    @RequestMapping("loadAllYear")
    public DataGridView loadAllProviderForSelect(){
        List<Year> list = yearService.list();
        return new DataGridView(list);
    }

    /**
     * 添加学年
     * @param yearVo
     * @return
     */
    @RequestMapping("addYear")
    public ResultObj addYear(YearVo yearVo){
        try {
            //防止学年重复
            QueryWrapper<Year> yearQueryWrapper = new QueryWrapper<>();
            yearQueryWrapper.eq("year",yearVo.getYear());
            Year one = yearService.getOne(yearQueryWrapper);
            if(one!=null){
                return ResultObj.YEAR_ERROR;
            }
            //添加学年
            yearVo.setYeardate(new Date());
            yearService.save(yearVo);
            //在allScore表单中生成该学年所有学生默认信息
            List<User> list = userService.list();
            AllScoreVo allScoreVo = new AllScoreVo();
            for (User student:list) {
                if(student.getType()==1){
                    allScoreVo.setUserid(student.getId());
                    allScoreVo.setYear(yearVo.getYear());
                    allscoreService.save(allScoreVo);
                }else{
                    //不执行任何操作
                }
            }
            return ResultObj.YEAR_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新学年
     * @param yearVo
     * @return
     */
    @RequestMapping("updateYear")
    public ResultObj updateYear(YearVo yearVo){
        try {
            yearVo.setYeardate(new Date());
            yearService.updateById(yearVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除学年数据
     * @param id
     * @return
     */
    @RequestMapping("deleteYear")
    public ResultObj deleteYear(Integer id){
        try {
            yearService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

