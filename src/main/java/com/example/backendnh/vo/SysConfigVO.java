package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SysConfigVO implements VO{
    String baseDn_ldap;

    String pwd_ldap;

    String url_ldap;

    String user_ldap;

    String filter_ldap;

    String ldapType;

    String pageShowCount;

    String archPath;

    String archIndexPath;

    String txtArchPath;

    String localSrcPath;

    String localTxtPath;

    @Override
    public DTO toDTO() {
        return null;
    }
}
