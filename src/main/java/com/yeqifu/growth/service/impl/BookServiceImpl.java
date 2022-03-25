package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Book;
import com.yeqifu.growth.mapper.BookMapper;
import com.yeqifu.growth.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-18
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    BookMapper bookMapper;
    @Override
    public Integer countBook() {
        return bookMapper.countBook();
    }
}
