package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Competition;
import com.yeqifu.growth.mapper.CompetitionMapper;
import com.yeqifu.growth.service.ICompetitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements ICompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;
    @Override
    public Integer countCompetition() {
        return competitionMapper.countCompetition();
    }
}
