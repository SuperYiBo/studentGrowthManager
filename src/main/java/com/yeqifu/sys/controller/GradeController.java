package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.TreeNode;
import com.yeqifu.sys.entity.Grade;
import com.yeqifu.sys.service.IGradeService;
import com.yeqifu.sys.vo.GradeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;

    /**
     * 加载年级左边的菜单树
     * @param gradeVo
     * @return
     */
    @RequestMapping("loadGradeManagerLeftTreeJson")
    public DataGridView loadManagerLeftTreeJson(GradeVo gradeVo){
    
        List<Grade> list = gradeService.list();

        List<TreeNode> treeNodes = new ArrayList<>();
        //将年级放入treeNodes中，组装成json
        for (Grade grade : list) {
            Boolean open = grade.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(grade.getId(), grade.getPid(), grade.getName(),open));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有年级数据
     * @param gradeVo
     * @return
     */
    @RequestMapping("loadAllGrade")
    public DataGridView loadAllGrade(GradeVo gradeVo){
        IPage<Grade> page = new Page<>(gradeVo.getPage(),gradeVo.getLimit());
        //进行模糊查询
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(gradeVo.getName()),"name",gradeVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(gradeVo.getRemark()),"remark",gradeVo.getRemark());
        queryWrapper.like(StringUtils.isNotBlank(gradeVo.getAddress()),"address",gradeVo.getAddress());
        queryWrapper.eq(gradeVo.getId()!=null,"id",gradeVo.getId()).or().eq(gradeVo.getId()!=null,"pid",gradeVo.getId());
        queryWrapper.orderByAsc("id");
        //进行查询
        gradeService.page(page,queryWrapper);
        //返回DataGridView
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加年级
     * @param gradeVo
     * @return
     */
    @RequestMapping("addGrade")
    public ResultObj addgrade(GradeVo gradeVo){
        try {
            gradeVo.setCreatetime(new Date());
            gradeService.save(gradeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }



    /**
     * 更新年级
     * @param gradeVo
     * @return
     */
    @RequestMapping("updateGrade")
    public ResultObj updateGrade(GradeVo gradeVo){
        try {
            gradeService.updateById(gradeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前年级是否有子年级
     * @param gradeVo
     * @return
     */
    @RequestMapping("checkGradeHasChildrenNode")
    public Map<String,Object> checkGradeHasChildrenNode(GradeVo gradeVo){
        Map<String,Object> map = new HashMap<String, Object>();
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",gradeVo.getId());
        List<Grade> list = gradeService.list(queryWrapper);
        if (list.size()>0){
            map.put("value",true);
        }else {
            map.put("value",false);
        }
        return map;
    }

    /**
     * 删除年级
     * @param gradeVo
     * @return
     */
    @RequestMapping("deleteGrade")
    public ResultObj deleteGrade(GradeVo gradeVo){
        try {
            gradeService.removeById(gradeVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

