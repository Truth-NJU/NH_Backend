package com.example.backendnh.dto;

import com.example.backendnh.po.Nharchives;
import com.example.backendnh.po.PO;
import com.example.backendnh.vo.NharchivesVO;
import com.example.backendnh.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NharchivesDTO implements DTO{
    Integer id;
    String bh; // 编号
    String damc; // 档案名称
    String jm; // 卷名
    String wb; // 文别
    String flh; // 分类号
    String sy; // 事由
    String gjc; // 关键词
    String zrh; // 责任号
    String ywdt; //  有无地图
    String yslh; // 原始类号
    String xgdy; // 相关岛屿
    String gjrw; // 关键人物
    String szdajh; // 所在档案夹号
    String fwrq; // 发文日期
    String swrq; // 收文日期
    String csrq; // 产生日期
    String fwjg; // 发文机构
    String swjg; // 收文机构
    String gcdw; // 馆藏单位
    Integer hf; // 画幅
    String bclj; // 保存路径
    String wzjg; // 文中机构

    @Override
    public Nharchives toPO() {
        Nharchives nharchives=new Nharchives();
        nharchives.setId(id);
        nharchives.setBh(bh);
        nharchives.setDamc(damc);
        nharchives.setJm(jm);
        nharchives.setWb(wb);
        nharchives.setFlh(flh);
        nharchives.setSy(sy);
        nharchives.setGjc(gjc);
        nharchives.setZrh(zrh);
        nharchives.setYwdt(ywdt);
        nharchives.setYslh(yslh);
        nharchives.setXgdy(xgdy);
        nharchives.setGjrw(gjrw);
        nharchives.setSzdajh(szdajh);
        nharchives.setFwrq(fwrq);
        nharchives.setSwrq(swrq);
        nharchives.setCsrq(csrq);
        nharchives.setFwjg(fwjg);
        nharchives.setSwjg(swjg);
        nharchives.setGcdw(gcdw);
        nharchives.setHf(hf);
        nharchives.setBclj(bclj);
        nharchives.setWzjg(wzjg);
        return nharchives;
    }

    @Override
    public NharchivesVO toVO() {
        NharchivesVO nharchivesVO=new NharchivesVO();
        nharchivesVO.setId(id);
        nharchivesVO.setBh(bh);
        nharchivesVO.setDamc(damc);
        nharchivesVO.setJm(jm);
        nharchivesVO.setWb(wb);
        nharchivesVO.setFlh(flh);
        nharchivesVO.setSy(sy);
        nharchivesVO.setGjc(gjc);
        nharchivesVO.setZrh(zrh);
        nharchivesVO.setYwdt(ywdt);
        nharchivesVO.setYslh(yslh);
        nharchivesVO.setXgdy(xgdy);
        nharchivesVO.setGjrw(gjrw);
        nharchivesVO.setSzdajh(szdajh);
        nharchivesVO.setFwrq(fwrq);
        nharchivesVO.setSwrq(swrq);
        nharchivesVO.setCsrq(csrq);
        nharchivesVO.setFwjg(fwjg);
        nharchivesVO.setSwjg(swjg);
        nharchivesVO.setGcdw(gcdw);
        nharchivesVO.setHf(hf);
        nharchivesVO.setBclj(bclj);
        nharchivesVO.setWzjg(wzjg);
        return nharchivesVO;
    }
}
