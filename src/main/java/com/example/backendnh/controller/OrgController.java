package com.example.backendnh.controller;

import com.example.backendnh.service.OrgService;
import com.example.backendnh.vo.NhorgVO;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nh/org")
public class OrgController {

    @Autowired
    OrgService orgService;

    @PostMapping("/addOrg")
    public ResponseVO addOrg(@RequestBody NhorgVO nhorgVO) {
        return orgService.addOrg(nhorgVO.toDTO());
    }


    @PostMapping("/deleteOrgById")
    public ResponseVO deleteOrgById(@RequestParam Integer id) {
        return orgService.deleteOrgById(id);
    }

    @PostMapping("/getOrgsByParentId")
    public ResponseVO getOrgsByParentId(@RequestParam Integer parentId) {
        return orgService.getOrgsByParentId(parentId);
    }

    @PostMapping("/getParentOrgByParentId")
    public ResponseVO getParentByParentId(@RequestParam Integer parentId) {
        return orgService.getParentOrgByParentId(parentId);
    }

    @PostMapping("/getSubOrgByOrgId")
    public ResponseVO getSubOrgByOrgId(@RequestParam Integer orgId) {
        return orgService.getSubOrgByOrgId(orgId);
    }

    @PostMapping("/getOrgById")
    public ResponseVO getOrgById(@RequestParam Integer orgId) {
        return orgService.getOrgById(orgId);
    }

    @PostMapping("/getAllOrgs")
    public ResponseVO getAllOrgs() {
        return orgService.getAllOrgs();
    }
}
