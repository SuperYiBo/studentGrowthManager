package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeqifu.growth.entity.Rule;
import com.yeqifu.growth.service.IRuleService;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/growth/rule")
public class RuleController {
    @Autowired
    private IRuleService ruleService;
    /**
     * 评分细则的查询
     * @param rule
     * @return
     */
    @RequestMapping("loadRule")
    public DataGridView loadAllNotice(Rule rule){
        QueryWrapper<Rule> queryWrapper = new QueryWrapper<Rule>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(rule.getType()),"type",rule.getType());
        //根据公告创建时间进行排序
        queryWrapper.orderByAsc("id");
        List<Rule> list = ruleService.list(queryWrapper);
        return new DataGridView(list.stream().count(),list);
    }
    /**
     * 修改评分细则
     * @param rule
     * @return
     */
    @RequestMapping("updateRule")
    public ResultObj updateNotice(Rule rule){
        rule.setCreatetime(new Date());
        try {
            ruleService.updateById(rule);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}

