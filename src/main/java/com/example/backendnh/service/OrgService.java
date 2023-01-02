package com.example.backendnh.service;

import com.example.backendnh.dto.NhorgDTO;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrgService {
    ResponseVO addOrg(NhorgDTO nhorgDTO);

    ResponseVO deleteOrgById(Integer id);

    /**
     * 通过parentId找到该机构下的所有子机构
     * @param parentId
     * @return
     */
    ResponseVO getOrgsByParentId(Integer parentId);

    /**
     * 通过某一个机构的parentId查询该机构的上一级机构的parentId
     * @param parentId
     * @return
     */
    ResponseVO getParentOrgByParentId(Integer parentId);


    /**
     * 通过机构id得到该机构下的所有子机构
     * @param orgId
     * @return
     */
    ResponseVO getSubOrgByOrgId(Integer orgId);

    /**
     * 通过id获得org的详细信息
     * @param orgId
     * @return
     */
    ResponseVO getOrgById(Integer orgId);

    /**
     * 获得所有的机构
     * @return
     */
    ResponseVO getAllOrgs();

}
