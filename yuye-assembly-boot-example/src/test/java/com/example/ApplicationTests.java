package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damain.Book;
import com.example.dao.BookDao;
import com.example.service.BookService;
import com.example.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    @Autowired
    private IBookService bookService2;


    //dao层开发的测试
    @Test
    void BookDaoInsertTest() {
        Book book = new Book();
        book.setId(1);
        book.setName("java");
        book.setType("语言类");
        book.setDescription("java语言");

        int result = bookDao.insert(book);
        System.out.println(result);

        Book book1 = bookDao.selectById(1);
        System.out.println(book1);
    }

    @Test
    void BookDaoUpdateTest(){
        Book book = new Book();
        book.setId(1);
        book.setName("C");

        int result = bookDao.updateById(book);
        System.out.println(result);
    }

    @Test
    void GetPageTest(){
        IPage<Book> page = new Page(1,5);//获取第一页数据，一页5条
        bookDao.selectPage(page,null);

    }

    @Test
    void SelectConditionTest(){
        IPage page = new Page(1,5);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Book::getId,"3");

        bookDao.selectPage(page,wrapper);
    }


    //service层开发的测试
    @Test
    void testSave(){
        Book book = new Book();
        book.setName("微信");
        book.setType("工具类");
        book.setDescription("用好微信");

        bookService.save(book);

    }

    @Test
    void testDelete(){
        bookService.delete(9);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(13);
        book.setName("微信");
        book.setDescription("使用好微信");

        bookService.update(book);
    }

    @Test
    void testGetById(){
        bookService.getById(10);
    }

    @Test
    void testGetAll(){
        bookService.getAll();
    }

    @Test
    void testGetByPage(){
        bookService.getByPage(1,5);
    }


    //service层快速开发的测试
    @Test
    void testGetById2(){
        System.out.println(bookService2.getById(4));
    }

    @Test
    void testSave2(){
        Book book = new Book();
        book.setType("测试数据123");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookService2.save(book);
    }

    @Test
    void testUpdate2(){
        Book book = new Book();
        book.setId(14);
        book.setType("-----------------");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookService2.updateById(book);
    }

    @Test
    void testDelete2(){
        bookService2.removeById(13);
    }

    @Test
    void testGetAll2(){
        bookService2.list();
    }

    @Test
    void testGetPage2(){
        IPage<Book> page = new Page<Book>(2,5);
        bookService2.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }

}
