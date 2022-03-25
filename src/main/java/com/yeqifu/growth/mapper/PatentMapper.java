package com.yeqifu.growth.mapper;

import com.yeqifu.growth.entity.Patent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
public interface PatentMapper extends BaseMapper<Patent> {
    Integer countPatent();
}
