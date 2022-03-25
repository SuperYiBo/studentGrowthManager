package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Innovate;
import com.yeqifu.growth.mapper.InnovateMapper;
import com.yeqifu.growth.service.IInnovateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-22
 */
@Service
public class InnovateServiceImpl extends ServiceImpl<InnovateMapper, Innovate> implements IInnovateService {

    @Autowired
    private InnovateMapper innovateMapper;
    @Override
    public Integer countInnovate() {
        return innovateMapper.countInnovate();
    }
}
