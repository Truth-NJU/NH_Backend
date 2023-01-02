package com.example.backendnh.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backendnh.dto.NhuserDTO;
import com.example.backendnh.mapper.NhuserMapper;
import com.example.backendnh.po.Nhuser;
import com.example.backendnh.service.UserService;
import com.example.backendnh.vo.NhuserVO;
import com.example.backendnh.vo.ResponseVO;
import com.example.backendnh.vo.VO;
import com.example.backendnh.vo.http.UserHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author taozehua
 * @since 2022-11-25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    NhuserMapper nhuserMapper;


    @Override
    public ResponseVO addUser(NhuserDTO userDTO) {
        String username = userDTO.getUserName();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("user_name", username);
        List<Nhuser> list = nhuserMapper.selectByMap(searchMap);

        if (!(list != null && list.size() > 0)) {
            Nhuser nhuser = new Nhuser();
            nhuser.setUserName(username);
            nhuser.setRealName(userDTO.getRealName() != null ? userDTO.getRealName() : "");
            nhuser.setUserEmail(userDTO.getUserEmail() != null ? userDTO.getUserEmail() : "");
            nhuser.setUserMobile(userDTO.getUserMobile() != null ? userDTO.getUserMobile() : "");
            nhuser.setUserStatus(userDTO.getUserStatus() != null ? userDTO.getUserStatus() : "");
            nhuser.setUserPassword(userDTO.getUserPassword() != null ? userDTO.getUserPassword() : "");
            nhuser.setCreateDate(new Date());
            if (nhuserMapper.insert(nhuser) == 1) {
                return ResponseVO.succeed();
            }
        } else {
            return ResponseVO.fail(UserHttpStatus.USER_AlREADY_EXIST);
        }
        return null;
    }

    @Override
    public ResponseVO deleteUser(String userName) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", userName);
        if (nhuserMapper.deleteByMap(map) == 1) {
            return ResponseVO.succeed();
        }
        return null;
    }

    @Override
    public ResponseVO login(NhuserDTO userDTO) {
        String username = userDTO.getUserName();
        String password = userDTO.getUserPassword();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("user_name", username);
        List<Nhuser> res = nhuserMapper.selectByMap(searchMap);
        if (res.size() == 0) {
            return ResponseVO.fail(UserHttpStatus.USER_NOT_EXIST);
        }
        Nhuser user = res.get(0);
        if (!user.getUserPassword().equals(password)) {
            return ResponseVO.fail(UserHttpStatus.PASSWD_ERROR);
        }
        return ResponseVO.succeed();
    }

    @Override
    public ResponseVO getAllUsers() {
        List<Nhuser> users = nhuserMapper.selectList(null);
        List<VO> userVOs = new ArrayList<>();
        for (Nhuser nhuser : users) {
            NhuserVO nhuserVO = new NhuserVO();
            nhuserVO.setRealName(nhuser.getRealName());
            nhuserVO.setUserName(nhuser.getUserName());
            nhuserVO.setUserMobile(nhuser.getUserMobile());
            nhuserVO.setCreateDate(nhuser.getCreateDate());
            userVOs.add(nhuserVO);
        }
        return ResponseVO.succeed(userVOs);
    }

    @Override
    public ResponseVO getUserIdentity(String userName) {
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("user_name", userName);
        List<Nhuser> res = nhuserMapper.selectByMap(searchMap);
        Nhuser user = res.get(0);
        return ResponseVO.succeed(user.toDTO().toVO());
    }

    @Override
    public ResponseVO changePassword(String userName, String oldPassword, String newPassword) {
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("user_name", userName);
        List<Nhuser> res = nhuserMapper.selectByMap(searchMap);
        Nhuser user = res.get(0);
        // 原密码错误
        if (!Objects.equals(user.getUserPassword(), oldPassword)) {
            return ResponseVO.fail(UserHttpStatus.OLD_PASSWD_ERROR);
        } else {
            // 更新
            UpdateWrapper<Nhuser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_name", userName);
            Nhuser newUser = new Nhuser();
            newUser.setUserPassword(newPassword);

            if (nhuserMapper.update(newUser, updateWrapper) == 1)
                return ResponseVO.succeed();
        }
        return null;
    }
}
