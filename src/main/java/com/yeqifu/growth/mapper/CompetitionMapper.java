package com.yeqifu.growth.mapper;

import com.yeqifu.growth.entity.Competition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
public interface CompetitionMapper extends BaseMapper<Competition> {
    Integer countCompetition();
}
