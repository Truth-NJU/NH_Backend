package com.example.backendnh;

import com.example.backendnh.mapper.NharchivesMapper;
import com.example.backendnh.po.Nharchives;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendNhApplicationTests {

    @Autowired
    NharchivesMapper nharchivesMapper;

    @Test
    void contextLoads() {

        Nharchives nharchives= nharchivesMapper.selectById(4);
        System.out.println(nharchives);

    }

}
