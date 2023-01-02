package com.example.backendnh.dto;


import com.example.backendnh.po.PO;
import com.example.backendnh.vo.VO;

/**
 * @author taozehua
 * @since 2022-11-25
 */
public interface DTO {
    PO toPO();

    VO toVO();
}