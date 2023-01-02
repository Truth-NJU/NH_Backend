package com.example.backendnh.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.backendnh.dto.ArchmarkDTO;
import com.example.backendnh.dto.DTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ArchmarkVO implements VO {

    Integer id;

    Integer archid; // 是序号不是编号

    String archname;

    String remarkname;

    Date remarktime;

    String remarkcontent;

    String isview;


    @Override
    public ArchmarkDTO toDTO() {
        ArchmarkDTO archmarkDTO = new ArchmarkDTO();
        archmarkDTO.setId(id);
        archmarkDTO.setArchid(archid);
        archmarkDTO.setArchname(archname);
        archmarkDTO.setRemarkname(remarkname);
        archmarkDTO.setRemarktime(remarktime);
        archmarkDTO.setRemarkcontent(remarkcontent);
        archmarkDTO.setIsview(isview);
        return archmarkDTO;
    }
}
