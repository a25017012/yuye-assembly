package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damain.Book;

public interface IBookService extends IService<Book> {

    //通用方法IService已经提供了，在这里你可以写自己的业务方法
//    Book get(Integer id);


    IPage<Book> getPage(int currentPage, int pageSize);

    IPage<Book> getPage(int currentPage, int pageSize,Book queryBook);




}
