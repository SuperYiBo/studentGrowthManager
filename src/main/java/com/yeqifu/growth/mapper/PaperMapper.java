package com.yeqifu.growth.mapper;

import com.yeqifu.growth.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxc-
 * @since 2022-02-21
 */
public interface PaperMapper extends BaseMapper<Paper> {
    Integer countPaper();
}
