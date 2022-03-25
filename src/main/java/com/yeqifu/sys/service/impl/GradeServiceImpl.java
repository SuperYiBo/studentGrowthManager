package com.yeqifu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yeqifu.sys.entity.Grade;
import com.yeqifu.sys.mapper.GradeMapper;
import com.yeqifu.sys.service.IGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements IGradeService {

    @Override
    public Grade getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean update(Grade entity, Wrapper<Grade> updateWrapper){
        return super.update(entity,updateWrapper);
    }

    @Override
    public boolean updateById(Grade entity){
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id){
        return super.removeById(id);
    }

    @Override
    public boolean save(Grade entity) {
        return super.save(entity);
    }


}
