package com.example.backendnh.dto;

import com.example.backendnh.po.Archmark;
import com.example.backendnh.po.PO;
import com.example.backendnh.vo.ArchmarkVO;
import com.example.backendnh.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ArchmarkDTO implements DTO{

    Integer id;

    Integer archid; // 是序号不是编号

    String archname;

    String remarkname;

    Date remarktime;

    String remarkcontent;

    String isview;

    @Override
    public Archmark toPO() {
        Archmark archmark = new Archmark();
        archmark.setId(id);
        archmark.setArchid(archid);
        archmark.setArchname(archname);
        archmark.setRemarkname(remarkname);
        archmark.setRemarktime(remarktime);
        archmark.setRemarkcontent(remarkcontent);
        archmark.setIsview(isview);
        return archmark;
    }

    @Override
    public ArchmarkVO toVO() {
        ArchmarkVO archmarkVO = new ArchmarkVO();
        archmarkVO.setId(id);
        archmarkVO.setArchid(archid);
        archmarkVO.setArchname(archname);
        archmarkVO.setRemarkname(remarkname);
        archmarkVO.setRemarktime(remarktime);
        archmarkVO.setRemarkcontent(remarkcontent);
        archmarkVO.setIsview(isview);
        return archmarkVO;
    }
}
