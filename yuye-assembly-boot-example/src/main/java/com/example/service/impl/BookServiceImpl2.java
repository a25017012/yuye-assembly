package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damain.Book;
import com.example.dao.BookDao;
import com.example.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl2 extends ServiceImpl<BookDao, Book> implements IBookService {

    //delete方法变成remove方法，getAll方法变成list方法，getByPage方法变成page方法
    //自己定义的方法还是要实现
//    @Autowired
//    private BookDao bookDao;
//    @Override
//    public Book get(Integer id) {
//        return bookDao.selectById(id);
//    }
    @Autowired
    private BookDao bookDao;

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        return bookDao.selectPage(page,null);
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, Book queryBook) {
        IPage page = new Page(currentPage,pageSize);
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(Strings.isNotEmpty(queryBook.getName()),Book::getName,queryBook.getName());
        lqw.like(Strings.isNotEmpty(queryBook.getType()),Book::getType,queryBook.getType());
        lqw.like(Strings.isNotEmpty(queryBook.getDescription()),
                Book::getDescription,queryBook.getDescription());
        return bookDao.selectPage(page,lqw);
    }


}
