package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.damain.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {

}
