package com.yeqifu.growth.mapper;

import com.yeqifu.growth.entity.Work;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxc-
 * @since 2022-03-01
 */
public interface WorkMapper extends BaseMapper<Work> {
    Integer countWork();
}
