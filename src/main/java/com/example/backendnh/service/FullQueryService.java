package com.example.backendnh.service;

import com.example.backendnh.vo.ResponseVO;

public interface FullQueryService {

    /**
     * 全文检索
     *
     * @param keyword
     * @param exactMatch 0 精确匹配，1模糊匹配
     * @param username
     * @return
     */
    ResponseVO fullQuery(String keyword, Integer exactMatch, String username);

    /**
     * 查询词频统计
     * @return
     */
    ResponseVO statisticQuery();

    /**
     * 数据统计
     * @param strStatisticType
     * @return
     */
    ResponseVO statisticArchives(String strStatisticType);
}
