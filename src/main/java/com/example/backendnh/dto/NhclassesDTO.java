package com.example.backendnh.dto;

import com.example.backendnh.po.Nhclasses;
import com.example.backendnh.po.PO;
import com.example.backendnh.vo.NhclassesVO;
import com.example.backendnh.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhclassesDTO implements DTO{

    Integer id;

    String classid;

    String classcn;

    Integer parentid;

    String classdes;

    @Override
    public Nhclasses toPO() {
        Nhclasses nhclasses=new Nhclasses();
        nhclasses.setId(id);
        nhclasses.setClassid(classid);
        nhclasses.setClasscn(classcn);
        nhclasses.setParentid(parentid);
        nhclasses.setClassdes(classdes);
        return nhclasses;
    }

    @Override
    public NhclassesVO toVO() {
        NhclassesVO nhclassesVO=new NhclassesVO();
        nhclassesVO.setId(id);
        nhclassesVO.setClassid(classid);
        nhclassesVO.setClasscn(classcn);
        nhclassesVO.setParentid(parentid);
        nhclassesVO.setClassdes(classdes);
        return nhclassesVO;
    }
}
