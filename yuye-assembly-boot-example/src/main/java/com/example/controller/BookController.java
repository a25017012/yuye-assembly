package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.damain.Book;
import com.example.service.IBookService;
import com.example.controller.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("/{currentPage}/{pageSize}")
    public R getAll(@PathVariable int currentPage,@PathVariable int pageSize,Book book){

        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if(currentPage > page.getPages()){
            page = bookService.getPage((int) page.getPages(),pageSize,book);
        }
        return new R(null != page, page);
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
//        R r = new R();
//        Book book = bookService.getById(id);
//        r.setData(book);
//        if(book!=null){
//            r.setFlag(true);
//        }else{
//            r.setFlag(false);
//        }

        return new R(true,bookService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody Book book){
//        R r = new R();
//        boolean b = bookService.save(book);
//        r.setFlag(b);
        boolean flag = bookService.save(book);
        return new R(flag , flag ? "添加成功" : "添加失败");
    }

    @PutMapping
    public R update(@RequestBody Book book){//用@RequestBody用于接收json格式的数据
//        R r = new R();
//        boolean b = bookService.updateById(book);
//        r.setFlag(b);

        return  new R(bookService.updateById(book));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
//        R r = new R();
//        boolean b = bookService.removeById(id);
//        r.setFlag(b);

        return new R(bookService.removeById(id));
    }




}
