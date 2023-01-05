package com.example.backendnh.controller;

import com.example.backendnh.service.ClassService;
import com.example.backendnh.vo.NhclassesVO;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nh/class")
@CrossOrigin(origins = "http://124.222.139.8:8081", maxAge = 3600, allowCredentials="true",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET})
public class ClassController {

    @Autowired
    ClassService classService;


    @PostMapping("/addClass")
    public ResponseVO addClass(@RequestBody NhclassesVO nhclassesVO) {
        return classService.addClass(nhclassesVO.toDTO());
    }


    @PostMapping("/getAllClasses")
    public ResponseVO getAllClasses() {
        return classService.getAllClasses();
    }

    @PostMapping("/getClassesByParentId")
    public ResponseVO getClassesByParentId(@RequestParam Integer parentId) {
        return classService.getClassesByParentId(parentId);
    }


    @PostMapping("/getParentByParentId")
    public ResponseVO getParentByParentId(@RequestParam Integer parentId) {
        return classService.getParentByParentId(parentId);
    }

    @PostMapping("/deleteClassById")
    public ResponseVO deleteClassById(@RequestParam Integer id) {
        return classService.deleteClassById(id);
    }

    @PostMapping("/getClassWithoutSubClass")
    public ResponseVO getClassWithoutSubClass() {
        return classService.getClassWithoutSubClass();
    }

    @PostMapping("/getClassWithClassId")
    public ResponseVO getClassWithClassId(@RequestParam Integer id) {
        return classService.getClassWithClassId(id);
    }

    @PostMapping("/getSubClassByClassId")
    public ResponseVO getSubClassByClassId(@RequestParam String classId) {
        return classService.getSubClassByClassId(classId);
    }

}
