package com.example.backendnh.service.impl;

import com.example.backendnh.bean.Doc;
import com.example.backendnh.bean.DocModel;
import com.example.backendnh.mapper.NharchivesMapper;
import com.example.backendnh.mapper.NhfullquerylogMapper;
import com.example.backendnh.mapper.NhuserMapper;
import com.example.backendnh.po.Nharchives;
import com.example.backendnh.po.Nhfullquerylog;
import com.example.backendnh.po.StatisticQuery;
import com.example.backendnh.service.FullQueryService;
import com.example.backendnh.util.LuceneUtil;
import com.example.backendnh.vo.NharchivesVO;
import com.example.backendnh.vo.ResponseVO;
import com.example.backendnh.vo.StatisticQueryVO;
import com.example.backendnh.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FullQueryServiceImpl implements FullQueryService {

    @Autowired
    NhfullquerylogMapper nhfullquerylogMapper;

    @Autowired
    NharchivesMapper nharchivesMapper;


    @Autowired
    NhuserMapper nhuserMapper;

    @Override
    public ResponseVO fullQuery(String keyword, Integer exactMatch, String username) {
        DocModel dm = null;
        List<Doc> nharchives = null;

        if (keyword != null && keyword.length() > 0 && exactMatch != null && exactMatch == 1) {  //全文检索(模糊检索)
            try {
                dm = LuceneUtil.getInstance().search(keyword);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            nharchives = dm.getDocList();

            //记录全文检索关键字
            String[] strKeywords = keyword.split(" ");
            if (strKeywords != null && strKeywords.length > 0) {
                Date dNow = new Date();
                String strBatch = "" + dNow.getTime();
                Nhfullquerylog nhfullquerylog = null;
                for (int i = 0; i < strKeywords.length; i++) {
                    if (strKeywords[i] != null && strKeywords[i].trim().length() > 0) {
                        nhfullquerylog = new Nhfullquerylog();
                        nhfullquerylog.setHotword(strKeywords[i]);
                        nhfullquerylog.setQuerybatch(strBatch);
                        nhfullquerylog.setQuerytime(dNow);
                        if (username != null && username.length() > 0) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("user_name", username);
                            nhfullquerylog.setQueryman(nhuserMapper.selectByMap(map).get(0).getRealName());
                        } else {
                            nhfullquerylog.setQueryman("");
                        }
                        nhfullquerylogMapper.insert(nhfullquerylog);
                    }
                }
            }

        } else if (keyword != null && keyword.length() > 0 && exactMatch != null && exactMatch == 0) {  // 全文检索,精确检索
            List<Nharchives> lMatchArch = nhfullquerylogMapper.findByArchtext(keyword);

            List<Doc> lDoc = null;
            if (lMatchArch != null && lMatchArch.size() > 0) {
                lDoc = new ArrayList<Doc>(lMatchArch.size());
                for (int i = 0; i < lMatchArch.size(); i++) {
                    Nharchives obTemp = lMatchArch.get(i);

                    Doc docTemp = new Doc();
                    docTemp.setBh(obTemp.getBh());
                    docTemp.setDamc(obTemp.getDamc());
                    docTemp.setWb(obTemp.getWb());
                    docTemp.setFlh(obTemp.getFlh());
                    docTemp.setCsrq(obTemp.getCsrq());
                    lDoc.add(docTemp);
                }
            }
            nharchives = lDoc;
        }
        List<VO> queryRes = new ArrayList<>();
        if (nharchives != null) {
            for (Doc doc : nharchives) {
                NharchivesVO nharchivesVO = new NharchivesVO();
                Map<String, Object> map = new HashMap<>();
                map.put("damc", doc.getDamc());
                map.put("wb", doc.getWb());
                map.put("flh", doc.getFlh());
                map.put("csrq", doc.getCsrq());
                nharchivesVO.setId(nharchivesMapper.selectByMap(map).get(0).getId());
                nharchivesVO.setDamc(doc.getDamc());
                nharchivesVO.setWb(doc.getWb());
                nharchivesVO.setFlh(doc.getFlh());
                nharchivesVO.setCsrq(doc.getCsrq());
                queryRes.add(nharchivesVO);
            }
        }
        return ResponseVO.succeed(queryRes);
    }

    @Override
    public ResponseVO statisticQuery() {
        List<StatisticQuery> statisticQueries = nhfullquerylogMapper.statisticQuery();
        List<VO> res = new ArrayList<>();
        for (StatisticQuery statisticQuery : statisticQueries) {
            StatisticQueryVO statisticQueryVO = new StatisticQueryVO();
            statisticQueryVO.setStatistic(statisticQuery.getStatistic());
            statisticQueryVO.setStatisticCount(statisticQuery.getStatisticCount());
            res.add(statisticQueryVO);
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO statisticArchives(String strStatisticType) {
        List<StatisticQuery> statisticQueries = new ArrayList<>();
        if (strStatisticType.equals("wb")) statisticQueries = nhfullquerylogMapper.statisticArchivesWB();
        if (strStatisticType.equals("xgdy")) statisticQueries = nhfullquerylogMapper.statisticArchivesXGDY();
        if (strStatisticType.equals("gcdw")) statisticQueries = nhfullquerylogMapper.statisticArchivesGCDW();
        List<VO> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (StatisticQuery statisticQuery : statisticQueries) {
            String statistic = statisticQuery.getStatistic();
            statistic = statistic.replaceAll("；", ";");
            String[] statistics = statistic.split(";");
            for (String tmp : statistics) {
                if (map.containsKey(tmp)) {
                    map.put(tmp, map.get(tmp) + statisticQuery.getStatisticCount());
                } else {
                    map.put(tmp, statisticQuery.getStatisticCount());
                }
            }
        }
        for (String key : map.keySet()) {
            if (key.equals("") || key.equals(" ")) continue;
            StatisticQueryVO statisticQueryVO = new StatisticQueryVO();
            statisticQueryVO.setStatistic(key);
            statisticQueryVO.setStatisticCount(map.get(key));
            res.add(statisticQueryVO);
        }
        return ResponseVO.succeed(res);
    }
}

