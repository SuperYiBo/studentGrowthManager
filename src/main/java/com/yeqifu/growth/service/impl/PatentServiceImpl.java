package com.yeqifu.growth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeqifu.growth.entity.Patent;
import com.yeqifu.growth.mapper.PatentMapper;
import com.yeqifu.growth.service.IPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
@Service
public class PatentServiceImpl extends ServiceImpl<PatentMapper, Patent> implements IPatentService {

    @Autowired
    private PatentMapper patentMapper;
    @Override
    public Integer countPatent() {
        return patentMapper.countPatent();
    }
}
