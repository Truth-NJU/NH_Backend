package com.example.backendnh.dto;

import com.example.backendnh.po.PO;
import com.example.backendnh.vo.NhuserVO;
import com.example.backendnh.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author taozehua
 * @since 2022-11-25
 */
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhuserDTO implements DTO {
    Integer id;

    Date createDate;

    String userAddress;

    String userEmail;

    String userMobile;

    String userPassword;

    String realName;

    String userStatus;

    String userName;

    String userPic;

    String teamId;


    @Override
    public PO toPO() {
        return null;
    }

    @Override
    public NhuserVO toVO() {
        NhuserVO nhuserVO = new NhuserVO();
        nhuserVO.setCreateDate(createDate);
        nhuserVO.setUserAddress(userAddress);
        nhuserVO.setUserEmail(userEmail);
        nhuserVO.setUserMobile(userMobile);
        nhuserVO.setUserPassword(userPassword);
        nhuserVO.setRealName(realName);
        nhuserVO.setUserStatus(userStatus);
        nhuserVO.setUserName(userName);
        nhuserVO.setUserPic(userPic);
        nhuserVO.setTeamId(teamId);
        return nhuserVO;
    }
}
