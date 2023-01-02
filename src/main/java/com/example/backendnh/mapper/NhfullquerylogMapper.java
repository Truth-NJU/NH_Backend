package com.example.backendnh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendnh.po.Nharchives;
import com.example.backendnh.po.Nhfullquerylog;
import com.example.backendnh.po.StatisticQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NhfullquerylogMapper extends BaseMapper<Nhfullquerylog> {
    List<Nharchives> findByArchtext(String keyword);

    List<StatisticQuery> statisticQuery();

    List<StatisticQuery> statisticArchivesWB();

    List<StatisticQuery> statisticArchivesXGDY();

    List<StatisticQuery> statisticArchivesGCDW();
}
