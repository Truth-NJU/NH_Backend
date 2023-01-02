package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhuserDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_user")
public class Nhuser implements Serializable, PO {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("create_date")
    Date createDate;

    @TableField("user_address")
    String userAddress;

    @TableField("user_email")
    String userEmail;

    @TableField("user_mobile")
    String userMobile;

    @TableField("user_password")
    String userPassword;

    @TableField("realName")
    String realName;

    @TableField("user_status")
    String userStatus;

    @TableField("user_name")
    String userName;

    @TableField("user_pic")
    String userPic;

    @TableField("team_id")
    String teamId;

    @Override
    public NhuserDTO toDTO() {
        NhuserDTO nhuserDTO = new NhuserDTO();
        nhuserDTO.setCreateDate(createDate);
        nhuserDTO.setUserAddress(userAddress);
        nhuserDTO.setUserEmail(userEmail);
        nhuserDTO.setUserMobile(userMobile);
        nhuserDTO.setUserPassword(userPassword);
        nhuserDTO.setRealName(realName);
        nhuserDTO.setUserStatus(userStatus);
        nhuserDTO.setUserName(userName);
        nhuserDTO.setUserPic(userPic);
        nhuserDTO.setTeamId(teamId);
        return nhuserDTO;
    }
}
