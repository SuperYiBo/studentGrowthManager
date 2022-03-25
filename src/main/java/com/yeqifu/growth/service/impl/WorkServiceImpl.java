package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Work;
import com.yeqifu.growth.mapper.WorkMapper;
import com.yeqifu.growth.service.IWorkService;
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
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements IWorkService {

    @Autowired
    private WorkMapper workMapper;
    @Override
    public Integer countWork() {
        return workMapper.countWork();
    }
}
