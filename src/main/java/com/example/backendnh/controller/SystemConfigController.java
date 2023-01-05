package com.example.backendnh.controller;


import com.example.backendnh.util.SystemConfig;
import com.example.backendnh.vo.ResponseVO;
import com.example.backendnh.vo.SysConfigVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nh/config")
@CrossOrigin(origins = "http://124.222.139.8:8081", maxAge = 3600, allowCredentials="true",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET})
public class SystemConfigController {

    @PostMapping("/getConfig")
    public ResponseVO getConfig() {
        // 得到现有的配置，配置暂时以properties文件的形式存在util文件夹下
        // 借助SystemConfig类进行解析
        SystemConfig systemConfig = new SystemConfig();
        SysConfigVO sysConfigVO = new SysConfigVO();
        sysConfigVO.setUrl_ldap(systemConfig.getUrl_ldap());
        sysConfigVO.setBaseDn_ldap(systemConfig.getBaseDn_ldap());
        sysConfigVO.setUser_ldap(systemConfig.getUser_ldap());
        sysConfigVO.setPwd_ldap(systemConfig.getPawd_ldap());
        sysConfigVO.setFilter_ldap(systemConfig.getFilter_ldap());
        // ldap type应该没有设置
        sysConfigVO.setArchPath(systemConfig.getArchPath());
        sysConfigVO.setArchIndexPath(systemConfig.getArchIndexPath());
        sysConfigVO.setTxtArchPath(systemConfig.getTxtArchPath());
        sysConfigVO.setPageShowCount(String.valueOf(systemConfig.getPageShowCount()));
        return ResponseVO.succeed(sysConfigVO);
    }

    @PostMapping("/updateConfig")
    public ResponseVO updateConfig(@RequestBody SysConfigVO sysConfigVO) {
        /*LDAP 源码中未对这几项进行更新*/
//        sysConfigVO.getBaseDn_ldap();
//        sysConfigVO.getUrl_ldap();
//        sysConfigVO.getPwd_ldap();
//        sysConfigVO.getFilter_ldap();
//        sysConfigVO.getLdapType();
        // 更新
        // System.out.println(sysConfigVO.toString());
        SystemConfig systemConfig=new SystemConfig();
        systemConfig.setArchPath(sysConfigVO.getArchPath());
        systemConfig.setArchIndexPath(sysConfigVO.getArchIndexPath());
        systemConfig.setTxtArchPath(sysConfigVO.getTxtArchPath());
//        systemConfig.setPageShowCount(sysConfigVO.getPageShowCount());
        // 存储
        systemConfig.storeProperty();
        return ResponseVO.succeed();
    }
}
