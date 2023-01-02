package com.example.backendnh.service;

import com.example.backendnh.dto.NhclassesDTO;
import com.example.backendnh.vo.ResponseVO;

public interface ClassService {
    ResponseVO addClass(NhclassesDTO nhclassesDTO);

    ResponseVO getAllClasses();

    /**
     * 通过parentId找到该类下的所有子分类
     * @param parentId
     * @return
     */
    ResponseVO getClassesByParentId(Integer parentId);

    /**
     * 通过某一个分类的parentId查询该分类的上一级分类的parentId
     * @param parentId
     * @return
     */
    ResponseVO getParentByParentId(Integer parentId);

    /**
     * 通过id删除对应的分类以及所有的子分类
     * @param id
     * @return
     */
    ResponseVO deleteClassById(Integer id);


    /**
     * 得到没有子分类的所有分类
     * @param id
     * @return
     */
    ResponseVO getClassWithoutSubClass();


    /**
     * 通过分类id得到该分类
     * @param id
     * @return
     */
    ResponseVO getClassWithClassId(Integer id);

    /**
     * 通过分类号得到该分类下的所有子分类
     * @param classId
     * @return
     */
    ResponseVO getSubClassByClassId(String classId);
}
