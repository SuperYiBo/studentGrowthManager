package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Paper;
import com.yeqifu.growth.mapper.PaperMapper;
import com.yeqifu.growth.service.IPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-21
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Override
    public Integer countPaper() {
        return paperMapper.countPaper();
    }
}
