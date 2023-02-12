package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.utils.R;
import com.example.damain.Book;
import com.example.metadata.mapper.TestMetadataMapper;
import com.example.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metadata")
public class MetadataTestController {

    @Autowired
    private TestMetadataMapper testMetadataMapper;


    @GetMapping("/test")
    public Object test(){


        return testMetadataMapper.test();
    }




}
