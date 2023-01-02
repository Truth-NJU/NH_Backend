package com.example.backendnh.ServiceTest;

import com.example.backendnh.dto.NhuserDTO;
import com.example.backendnh.service.UserService;
import com.example.backendnh.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testLogin() {
        NhuserDTO nhuserDTO = new NhuserDTO(null, null, null, null, null, "123456", "tzh", "", "klein", null, null);
        ResponseVO responseVO = userService.login(nhuserDTO);
        assert responseVO.getCode() == 4000;
    }

    @Test
    public void testGetAllUsers() {
        ResponseVO responseVO = userService.getAllUsers();
        System.out.println(responseVO);
    }
}
