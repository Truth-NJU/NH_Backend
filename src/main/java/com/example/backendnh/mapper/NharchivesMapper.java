package com.example.backendnh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendnh.po.Nharchives;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NharchivesMapper extends BaseMapper<Nharchives> {
}
