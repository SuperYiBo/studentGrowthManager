package com.yeqifu.growth.mapper;

import com.yeqifu.growth.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxc-
 * @since 2022-02-18
 */
public interface BookMapper extends BaseMapper<Book> {
    Integer countBook();
}
