package com.yeqifu.growth.service;

import com.yeqifu.growth.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-18
 */
public interface IBookService extends IService<Book> {
    Integer countBook();
}
