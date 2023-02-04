package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.damain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookService {
    boolean save(Book book);
    boolean delete(Integer id);
    boolean update(Book book);
    Book getById(Integer id);
    List<Book> getAll();
    IPage<Book> getByPage(int currentPage,int pageSize);
}
