package com.example.backendnh.service;

import com.example.backendnh.dto.NhuserDTO;
import com.example.backendnh.vo.ResponseVO;

/**
 * @author taozehua
 * @since 2022-11-25
 */
public interface UserService {
    ResponseVO addUser(NhuserDTO userDTO);

    ResponseVO deleteUser(String userName);

    ResponseVO login(NhuserDTO userDTO);

    ResponseVO getAllUsers();

    ResponseVO getUserIdentity(String userName);

    ResponseVO changePassword(String userName,String oldPassword,String newPassword);
}