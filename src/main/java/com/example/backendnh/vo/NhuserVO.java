package com.example.backendnh.vo;

import com.example.backendnh.dto.NhuserDTO;
import lombok.*;

import java.util.Date;

/**
 * @author taozehua
 * @since 2022-11-25
 */
@Getter
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhuserVO implements VO {
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
