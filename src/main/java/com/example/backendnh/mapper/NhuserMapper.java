package com.example.backendnh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendnh.po.Nhuser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NhuserMapper extends BaseMapper<Nhuser> {
}
