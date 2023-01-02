package com.example.backendnh.vo;

import com.example.backendnh.vo.http.BaseHttpStatus;
import com.example.backendnh.vo.http.HttpStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author taozehua
 * @since 2022-11-25
 */
@Getter
@Setter
public class ResponseVO {

    private Integer code;

    private String msg;

    private List<VO> data;

    private List<String> strData;

    public static ResponseVO fail(HttpStatus httpStatus) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.code = httpStatus.getCode();
        responseVO.msg = httpStatus.getMessage();
        return responseVO;
    }

    public static ResponseVO fail(HttpStatus httpStatus, @NonNull VO vo) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.code = httpStatus.getCode();
        responseVO.msg = httpStatus.getMessage();
        responseVO.data = new ArrayList<>();
        responseVO.data.add(vo);
        return responseVO;
    }

    public static ResponseVO succeed() {
        ResponseVO responseVO = new ResponseVO();
        HttpStatus httpStatus = BaseHttpStatus.COMMON_OK;
        responseVO.code = httpStatus.getCode();
        responseVO.msg = httpStatus.getMessage();
        return responseVO;
    }

    public static ResponseVO succeed(@NonNull List<VO> voList) {
        ResponseVO responseVO = new ResponseVO();
        HttpStatus httpStatus = BaseHttpStatus.COMMON_OK;
        responseVO.code = httpStatus.getCode();
        responseVO.msg = httpStatus.getMessage();
        responseVO.data = voList;
        return responseVO;
    }

    public static ResponseVO succeed(@NonNull VO vo) {
        ResponseVO responseVO = new ResponseVO();
        HttpStatus httpStatus = BaseHttpStatus.COMMON_OK;
        responseVO.code = httpStatus.getCode();
        responseVO.msg = httpStatus.getMessage();
        responseVO.data = new ArrayList<>();
        responseVO.data.add(vo);
        return responseVO;
    }

    public static ResponseVO succeed(@NonNull String data) {
        ResponseVO responseVO = new ResponseVO();
        HttpStatus httpStatus = BaseHttpStatus.COMMON_OK;
        responseVO.code = httpStatus.getCode();
        responseVO.msg = httpStatus.getMessage();
        responseVO.strData = new ArrayList<>();
        responseVO.strData.add(data);
        return responseVO;
    }
    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}