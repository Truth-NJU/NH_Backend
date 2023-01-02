package com.example.backendnh.dto;

import com.example.backendnh.po.Nhfullquerylog;
import com.example.backendnh.po.PO;
import com.example.backendnh.vo.NhfullquerylogVO;
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
public class NhfullquerylogDTO implements DTO {
    Integer id;

    String querybatch;

    String hotword;

    Date querytime;

    String queryman;

    @Override
    public Nhfullquerylog toPO() {
        Nhfullquerylog nhfullquerylog = new Nhfullquerylog();
        nhfullquerylog.setId(id);
        nhfullquerylog.setQuerybatch(querybatch);
        nhfullquerylog.setHotword(hotword);
        nhfullquerylog.setQuerytime(querytime);
        nhfullquerylog.setQueryman(queryman);
        return nhfullquerylog;
    }

    @Override
    public NhfullquerylogVO toVO() {
        NhfullquerylogVO nhfullquerylogVO = new NhfullquerylogVO();
        nhfullquerylogVO.setId(id);
        nhfullquerylogVO.setQuerybatch(querybatch);
        nhfullquerylogVO.setHotword(hotword);
        nhfullquerylogVO.setQuerytime(querytime);
        nhfullquerylogVO.setQueryman(queryman);
        return nhfullquerylogVO;
    }
}
