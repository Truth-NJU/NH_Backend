package com.example.backendnh.controller;

import com.example.backendnh.dto.NhuserDTO;
import com.example.backendnh.service.UserService;
import com.example.backendnh.vo.NhuserVO;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author taozehua
 * @since 2022-11-25
 */
@RestController
@RequestMapping("/nh/user")
@CrossOrigin(origins = {"http://124.222.139.8:8081","http://localhost:8080"}, maxAge = 3600, allowCredentials="true",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseVO addUser(@RequestBody NhuserVO nhuserVO) {
        NhuserDTO userDTO = nhuserVO.toDTO();
        return userService.addUser(userDTO);
    }


    @PostMapping("/getAllUsers")
    public ResponseVO getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/deleteUser")
    public ResponseVO deleteUser(@RequestParam String userName) {
        return userService.deleteUser(userName);
    }


    @PostMapping("/getUserIdentity")
    public ResponseVO getUserIdentity(@RequestParam String userName) {
        return userService.getUserIdentity(userName);
    }

    @PostMapping("/login")
    public ResponseVO login(@RequestBody NhuserVO nhuserVO) {
        NhuserDTO userDTO = nhuserVO.toDTO();
        return userService.login(userDTO);
    }


    @PostMapping("/changePassword")
    public ResponseVO changePassword(@RequestParam String userName, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return userService.changePassword(userName, oldPassword, newPassword);
    }

}
