package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Honor;
import com.yeqifu.growth.mapper.HonorMapper;
import com.yeqifu.growth.service.IHonorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-03-01
 */
@Service
public class HonorServiceImpl extends ServiceImpl<HonorMapper, Honor> implements IHonorService {

    @Autowired
    private HonorMapper honorMapper;
    @Override
    public Integer countHonor() {
        return honorMapper.countHonor();
    }
}
