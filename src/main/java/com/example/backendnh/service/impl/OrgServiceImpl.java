package com.example.backendnh.service.impl;


import com.example.backendnh.dto.NhorgDTO;
import com.example.backendnh.mapper.NhorgMapper;
import com.example.backendnh.po.Nhorg;
import com.example.backendnh.service.OrgService;
import com.example.backendnh.vo.ResponseVO;
import com.example.backendnh.vo.VO;
import com.example.backendnh.vo.http.OrgHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    NhorgMapper nhorgMapper;

    @Override
    public ResponseVO addOrg(NhorgDTO nhorgDTO) {
        Nhorg nhorg = nhorgDTO.toPO();
        if (nhorgMapper.insert(nhorg) == 1) {
            return ResponseVO.succeed();
        } else {
            return ResponseVO.fail(OrgHttpStatus.ADD_ORG_FAIL);
        }
    }

    @Override
    public ResponseVO deleteOrgById(Integer id) {
        // 如果有以id为parentId的数据，就代表是该机构的子机构，也需要删除
        Map<String, Object> map = new HashMap<>();
        map.put("parentid", id);
        nhorgMapper.deleteByMap(map);
        // 删除机构
        nhorgMapper.deleteById(id);
        return ResponseVO.succeed();
    }

    @Override
    public ResponseVO getOrgsByParentId(Integer parentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentid", parentId);
        List<Nhorg> nhorgList = nhorgMapper.selectByMap(map);
        List<VO> res = new ArrayList<>();
        for (Nhorg nhorg : nhorgList) {
            res.add(nhorg.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO getParentOrgByParentId(Integer parentId) {
        Nhorg nhorg = nhorgMapper.selectById(parentId);
        return ResponseVO.succeed(nhorg.toDTO().toVO());
    }

    @Override
    public ResponseVO getSubOrgByOrgId(Integer orgId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentid", orgId);
        List<Nhorg> nhorgList = nhorgMapper.selectByMap(map);
        List<VO> res = new ArrayList<>();
        for (Nhorg nhorg : nhorgList) {
            res.add(nhorg.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO getOrgById(Integer orgId) {
        Nhorg nhorg = nhorgMapper.selectById(orgId);
        return ResponseVO.succeed(nhorg.toDTO().toVO());
    }

    @Override
    public ResponseVO getAllOrgs() {
        List<Nhorg> nhorgList = nhorgMapper.selectByMap(null);
        List<VO> res = new ArrayList<>();
        for (Nhorg nhorg : nhorgList) {
            res.add(nhorg.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }
}
