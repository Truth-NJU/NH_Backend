package com.example.backendnh.service.impl;

import com.example.backendnh.dto.NhclassesDTO;
import com.example.backendnh.mapper.NhclassesMapper;
import com.example.backendnh.po.Nhclasses;
import com.example.backendnh.service.ClassService;
import com.example.backendnh.vo.ResponseVO;
import com.example.backendnh.vo.VO;
import com.example.backendnh.vo.http.ClassHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    NhclassesMapper nhclassesMapper;

    @Override
    public ResponseVO addClass(NhclassesDTO nhclassesDTO) {
        Nhclasses nhclasses = nhclassesDTO.toPO();
        if (nhclassesMapper.insert(nhclasses) == 1) {
            return ResponseVO.succeed();
        }
        return ResponseVO.fail(ClassHttpStatus.ADD_CLASS_FAIL);
    }

    @Override
    public ResponseVO getAllClasses() {
        List<Nhclasses> nhclassesList = nhclassesMapper.selectByMap(null);
        List<VO> res = new ArrayList<>();
        for (Nhclasses nhclasses : nhclassesList) {
            res.add(nhclasses.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO getClassesByParentId(Integer parentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentid", parentId);
        List<Nhclasses> nhclassesList = nhclassesMapper.selectByMap(map);
        List<VO> res = new ArrayList<>();
        for (Nhclasses nhclasses : nhclassesList) {
            res.add(nhclasses.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO getParentByParentId(Integer parentId) {
        Nhclasses nhclasses = nhclassesMapper.selectById(parentId);
        return ResponseVO.succeed(nhclasses.toDTO().toVO());
    }

    @Override
    public ResponseVO deleteClassById(Integer id) {
        // 如果有以id为parentId的数据，就代表是该分类的子分类，也需要删除
        Map<String, Object> map = new HashMap<>();
        map.put("parentid", id);
        nhclassesMapper.deleteByMap(map);
        // 删除分类
        nhclassesMapper.deleteById(id);
        return ResponseVO.succeed();
    }

    @Override
    public ResponseVO getClassWithoutSubClass() {
        Set<Integer> allParentId = new HashSet<>(); // 所有当过父类id的分类id
        Set<Integer> allClassId = new HashSet<>(); // 所有分类id
        List<Nhclasses> nhclassesList = nhclassesMapper.selectByMap(null);
        for (Nhclasses nhclasses : nhclassesList) {
            allParentId.add(nhclasses.getParentid());
            allClassId.add(nhclasses.getId());
        }
        List<VO> res = new ArrayList<>();
        for (Integer id : allClassId) {
            if (!allParentId.contains(id)) {
                // 没有子分类的分类
                res.add(nhclassesMapper.selectById(id).toDTO().toVO());
            }
        }

        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO getClassWithClassId(Integer id) {
        return ResponseVO.succeed(nhclassesMapper.selectById(id).toDTO().toVO());
    }

    @Override
    public ResponseVO getSubClassByClassId(String classId) {
        Map<String, Object> map = new HashMap<>();
        map.put("classid", classId);
        Integer id = nhclassesMapper.selectByMap(map).get(0).getId();

        map = new HashMap<>();
        map.put("parentid", id);
        List<Nhclasses> nhclassesList = nhclassesMapper.selectByMap(map);
        List<VO> res = new ArrayList<>();
        for (Nhclasses nhclasses : nhclassesList) {
            res.add(nhclasses.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }
}
