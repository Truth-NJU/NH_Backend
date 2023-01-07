package com.example.backendnh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backendnh.bean.ImpArchivesResult;
import com.example.backendnh.dto.ArchmarkDTO;
import com.example.backendnh.dto.NharchivesDTO;
import com.example.backendnh.mapper.*;
import com.example.backendnh.po.*;
import com.example.backendnh.service.ArchService;
import com.example.backendnh.service.ExcelManager;
import com.example.backendnh.util.*;
import com.example.backendnh.vo.*;
import com.example.backendnh.vo.http.ArchHttpStatus;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArchServiceImpl implements ArchService {
    @Autowired
    NharchivesMapper nharchivesMapper;

    @Autowired
    NharchattachMapper nharchattachMapper;

    @Autowired
    NharchivestextMapper nharchivestextMapper;

    @Autowired
    ArchmarkMapper archmarkMapper;

    @Autowired
    NhuserMapper nhuserMapper;


    public ResponseVO getAllChildrenDirs(String appendDir) {
        SystemConfig systemConfig = new SystemConfig();
        String dir = systemConfig.getArchPath() + "/" + appendDir;
        ArrayList<File> alChildrenDirs = FileUtil.getChildrenDirs(dir);
        List<String> filePathList = new ArrayList<>();
        for (File file : alChildrenDirs) {
            filePathList.add(file.getAbsolutePath());
        }
//        System.out.println(filePathList);
        ImportArchVO importArchVO = new ImportArchVO();
        importArchVO.setFilepathList(filePathList);
        return ResponseVO.succeed(importArchVO);
    }

    public ImpArchivesResult impArchivesResult = new ImpArchivesResult();

    public ImpArchivesResult impArchives(String strCurrDir, boolean blnCover) {
        File fCurrDir = new File(strCurrDir);
        if (fCurrDir.exists() && fCurrDir.isDirectory()) {
            if (FileUtil.isArchDir(strCurrDir)) {
                this.impSingleArchive(strCurrDir, blnCover);
            } else {
                File[] fChildren = fCurrDir.listFiles();
                for (int i = 0; fChildren != null && i < fChildren.length; ++i) {
                    if (fChildren[i] != null && fChildren[i].isDirectory()) {
                        this.impArchives(fChildren[i].getAbsolutePath(), blnCover);
                    }
                }
            }
        }
        return impArchivesResult;

    }

    public boolean impSingleArchive(String strArchiveDir, boolean blnCover) {
        if (strArchiveDir != null && strArchiveDir.length() > 0) {
            System.out.println("----------start imp:" + strArchiveDir + "----------");
            Integer iSingleArchiveID = null;
            String strSingleArchName = "";
            strArchiveDir = strArchiveDir.replaceAll("\\\\", "/");
            if (strArchiveDir.endsWith("/")) {
                strArchiveDir = strArchiveDir.substring(0, strArchiveDir.length() - 1);
            }

            String strIndexFile = FileUtil.getIndexFile();
            strSingleArchName = strArchiveDir.substring(strArchiveDir.lastIndexOf("/") + 1, strArchiveDir.length());
            ExcelManager excelManager = new ExcelManager();
            // 通过src目录下的index.xls表格得到对应的信息
            Nharchives nharchives = excelManager.getNharchByArchName(strArchiveDir, strIndexFile, strSingleArchName);
            if (nharchives == null) {
                this.impArchivesResult.getAlErrorArchive().add(strArchiveDir);
                return false;
            } else {
                Map<String, Object> searchMap = new HashMap<>();
                searchMap.put("damc", strSingleArchName);
                List<Nharchives> lNharchive = nharchivesMapper.selectByMap(searchMap);
//                NharchivesDAO nharchivesDAO = new NharchivesDAO();
//                List<Nharchives> lNharchive = nharchivesDAO.findByDamc(strSingleArchName);
//                nharchivesDAO.closeSession();
                List lArch;
                Nharchives archTemp;
                if (lNharchive != null && lNharchive.size() > 0) {
                    Nharchives arch = lNharchive.get(0);
                    nharchives.setId(arch.getId());
                    iSingleArchiveID = arch.getId();
                    if (!blnCover) {
                        this.impArchivesResult.getAlExistArchive().add(strArchiveDir);
                        return false;
                    }

                    if (nharchives != null) {
                        UpdateWrapper<Nharchives> updateWrapper = new UpdateWrapper<>();
                        nharchivesMapper.update(nharchives, updateWrapper);
//                        nharchivesDAO = new NharchivesDAO();
//                        nharchivesDAO.update(nharchives);
//                        nharchivesDAO.commit();
//                        nharchivesDAO.closeSession();
                    }
                } else if (nharchives != null) {
                    System.out.println(nharchives);
                    nharchivesMapper.insert(nharchives);
//                    nharchivesDAO = new NharchivesDAO();
//                    nharchivesDAO.save(nharchives);
//                    nharchivesDAO.commit();
//                    nharchivesDAO.closeSession();
//                    nharchivesDAO = new NharchivesDAO();
//                    lArch = nharchivesDAO.findByDamc(strSingleArchName);
                    Map<String, Object> searchMap2 = new HashMap<>();
                    searchMap2.put("damc", strSingleArchName);
                    lArch = nharchivesMapper.selectByMap(searchMap2);
                    if (lArch != null && lArch.size() > 0) {
                        archTemp = (Nharchives) lArch.get(0);
                        iSingleArchiveID = archTemp.getId();
                    }
                }
                Map<String, Object> searchMap3 = new HashMap<>();
                searchMap3.put("damc", strSingleArchName);
                lArch = nharchivesMapper.selectByMap(searchMap3);
//                nharchivesDAO = new NharchivesDAO();
//                lArch = nharchivesDAO.findByDamc(strSingleArchName);
//                nharchivesDAO.closeSession();
                String strTempFileName;
                if (lArch != null && lArch.size() > 0) {
                    archTemp = (Nharchives) lArch.get(0);
                    if (archTemp != null && archTemp.getId() != null && archTemp.getId() != -1) {
                        ArrayList<File> alArchPics = FileUtil.getArchPics(strArchiveDir);
                        if (alArchPics != null && alArchPics.size() > 0) {
                            for (int i = 0; i < alArchPics.size(); ++i) {
                                File fTemp = (File) alArchPics.get(i);
                                strTempFileName = fTemp.getName();
                                String strTempFileSuffix = FileUtil.getFileSuffixByName(strTempFileName);
                                // 存在附件
                                if (strTempFileSuffix != null && strTempFileSuffix.toLowerCase().equals("pdf")) {
                                    String strPdfPath = strArchiveDir + "/" + fTemp.getName();
                                    String strPreviewImgPath = strArchiveDir + "/" + FileUtil.getFileName(fTemp.getName()) + ".jpg";
                                    FileUtil.makeDFPreviewImg(strPdfPath, strPreviewImgPath);
                                    Nharchattach archAttachTemp = new Nharchattach();
                                    archAttachTemp.setDaid(archTemp.getId());
                                    archAttachTemp.setTpmc(fTemp.getName());
                                    archAttachTemp.setTplstmc(FileUtil.getFileName(fTemp.getName()) + ".jpg");
                                    archAttachTemp.setTplx(FileUtil.getFileSuffixByName(fTemp.getName()));
                                    archAttachTemp.setTplj(strArchiveDir);
                                    Map<String, Object> searchMap4 = new HashMap<>();
                                    searchMap4.put("daid", archTemp.getId());
                                    searchMap4.put("tpmc", fTemp.getName());
                                    List<Nharchattach> lAttach = nharchattachMapper.selectByMap(searchMap4);
//                                    NharchattachDAO nharchattachDAO = new NharchattachDAO();
//                                    List<Nharchattach> lAttach = nharchattachDAO.findByArchIDAndPicName(archTemp.getId(), fTemp.getName());
//                                    nharchattachDAO.closeSession();
                                    if (lAttach != null && lAttach.size() > 0) {
                                        Nharchattach archAttach = (Nharchattach) lAttach.get(0);
                                        archAttachTemp.setId(archAttach.getId());
                                        UpdateWrapper<Nharchattach> updateWrapper = new UpdateWrapper<>();
                                        nharchattachMapper.update(archAttachTemp, updateWrapper);
//                                        nharchattachDAO = new NharchattachDAO();
//                                        nharchattachDAO.update(archAttachTemp);
//                                        nharchattachDAO.closeSession();
                                    } else {
                                        nharchattachMapper.insert(archAttachTemp);
//                                        nharchattachDAO = new NharchattachDAO();
//                                        nharchattachDAO.save(archAttachTemp);
//                                        nharchattachDAO.commit();
//                                        nharchattachDAO.closeSession();
                                    }
                                } else if (!FileUtil.isPreviewImg(alArchPics, strTempFileName)) {
                                    Nharchattach archAttachTemp = new Nharchattach();
                                    archAttachTemp.setDaid(archTemp.getId());
                                    archAttachTemp.setTpmc(fTemp.getName());
                                    archAttachTemp.setTplstmc(fTemp.getName());
                                    archAttachTemp.setTplx(FileUtil.getFileSuffixByName(fTemp.getName()));
                                    archAttachTemp.setTplj(strArchiveDir);
                                    Map<String, Object> searchMap5 = new HashMap<>();
                                    searchMap5.put("daid", archTemp.getId());
                                    searchMap5.put("tpmc", fTemp.getName());
                                    List<Nharchattach> lAttach = nharchattachMapper.selectByMap(searchMap5);
//                                    NharchattachDAO nharchattachDAO = new NharchattachDAO();
//                                    List<Nharchattach> lAttach = nharchattachDAO.findByArchIDAndPicName(archTemp.getId(), fTemp.getName());
//                                    nharchattachDAO.closeSession();
                                    if (lAttach != null && lAttach.size() > 0) {
                                        Nharchattach archAttach = (Nharchattach) lAttach.get(0);
                                        archAttachTemp.setId(archAttach.getId());
                                        UpdateWrapper<Nharchattach> updateWrapper = new UpdateWrapper<>();
                                        nharchattachMapper.update(archAttachTemp, updateWrapper);
//                                        nharchattachDAO = new NharchattachDAO();
//                                        nharchattachDAO.update(archAttachTemp);
//                                        nharchattachDAO.closeSession();
                                    } else {
//                                        nharchattachDAO = new NharchattachDAO();
//                                        nharchattachDAO.save(archAttachTemp);
//                                        nharchattachDAO.commit();
//                                        nharchattachDAO.closeSession();
                                        nharchattachMapper.insert(archAttachTemp);
                                    }
                                }
                            }
                        }
                    }
                }

                boolean blnCreateTxt = true;
                String strArchive = FileUtil.getArchiveByDir(strArchiveDir);
                String strHtmlFile = FileUtil.getHtmlFileByArchFile(strArchive);
                if (strArchive != null && strArchive.length() > 0) {
                    if (strArchive.toLowerCase().endsWith(".doc")) {
                        try {
                            FileUtil.convertDoc2Html(strArchive, strHtmlFile);
                        } catch (Exception var25) {
                            var25.printStackTrace();
                            blnCreateTxt = false;
                        }
                    } else if (strArchive.toLowerCase().endsWith(".docx")) {
                        try {
                            FileUtil.convertDocx2Html(strArchive, strHtmlFile);
                        } catch (Exception var24) {
                            var24.printStackTrace();
                            blnCreateTxt = false;
                        }
                    }
                }

                if (!blnCreateTxt) {
                    return false;
                } else {
                    boolean blnArchText2DB = true;
                    if (iSingleArchiveID == null) {
                        return false;
                    } else {
                        strTempFileName = ArchHtmlUtil.getArchTextFromHtml(strHtmlFile);
                        Map<String, Object> searchMap6 = new HashMap<>();
                        searchMap6.put("daid", iSingleArchiveID);
                        List<Nharchivestext> listNharchivestext = nharchivestextMapper.selectByMap(searchMap6);
//                        NharchivestextDAO nharchivestextDAO = new NharchivestextDAO();
//                        List<Nharchivestext> listNharchivestext = nharchivestextDAO.findByDaid(iSingleArchiveID);
//                        nharchivestextDAO.closeSession();
                        Nharchivestext nat;
                        if (listNharchivestext != null && listNharchivestext.size() > 0) {
                            nat = (Nharchivestext) listNharchivestext.get(0);
                            nat.setDamc(strSingleArchName);
                            nat.setDayw(strTempFileName);
                            UpdateWrapper<Nharchivestext> updateWrapper = new UpdateWrapper<>();
                            nharchivestextMapper.update(nat, updateWrapper);
//                            nharchivestextDAO = new NharchivestextDAO();
//                            nharchivestextDAO.update(nat);
//                            nharchivestextDAO.commit();
//                            nharchivestextDAO.closeSession();
                        } else {
                            nat = new Nharchivestext();
                            nat.setDaid(iSingleArchiveID);
                            nat.setDamc(strSingleArchName);
                            nat.setDayw(strTempFileName);
                            nharchivestextMapper.insert(nat);
//                            NharchivestextDAO nadao = new NharchivestextDAO();
//                            nadao.save(nat);
//                            nadao.commit();
//                            nadao.closeSession();
                        }

                        if (!blnArchText2DB) {
                            return false;
                        } else {
                            boolean blnCreateIndex = true;

                            try {
                                File fHtmlFile = new File(strHtmlFile);
                                if (fHtmlFile != null && fHtmlFile.isFile()) {
                                    LuceneUtil.getInstance().index(nharchives, strHtmlFile);
                                }
                            } catch (Exception var23) {
                                this.impArchivesResult.getAlErrorArchive().add(strArchiveDir);
                                var23.printStackTrace();
                            }

                            if (!blnCreateIndex) {
                                return false;
                            } else {
                                this.impArchivesResult.getAlSucessArchive().add(strArchiveDir);
                                System.out.println("----------end imp:" + strSingleArchName + "----------");
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public ResponseVO getAllArchives() {
        List<Nharchives> nharchivesList = nharchivesMapper.selectByMap(null);
        List<VO> nharchivesVOS = new ArrayList<>();
        for (Nharchives nharchives : nharchivesList) {
            nharchivesVOS.add(nharchives.toDTO().toVO());
        }
        return ResponseVO.succeed(nharchivesVOS);
    }

    @Override
    public ResponseVO addArchive(NharchivesDTO nharchivesDTO) {
        Nharchives nharchives = nharchivesDTO.toPO();
        if (nharchivesMapper.insert(nharchives) == 1) {
            return ResponseVO.succeed();
        }
        return ResponseVO.fail(ArchHttpStatus.ADD_ARCH_FAIL);
    }

    @Override
    public ResponseVO deleteArch(Integer archId) {
        Nharchives nharchives = nharchivesMapper.selectById(archId);
        if (nharchives != null) {
            // 删除档案
            nharchivesMapper.deleteById(archId);
            try {
                // todo 可能会报错
                LuceneUtil.getInstance().delIndex(nharchives.getDamc());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseVO.succeed();
        /*
        原代码没有这么干
        // 删除对应的附件
        Map<String, Object> map = new HashMap<>();
        map.put("daid", archId);
        nharchattachMapper.deleteByMap(map);
        // 删除对应的文本
        map = new HashMap<>();
        map.put("daid", archId);
        nharchivestextMapper.deleteByMap(map);
        */
    }

    @Override
    public ResponseVO getArchContentById(Integer archId) {
        Nharchives nharchives = nharchivesMapper.selectById(archId);
        return ResponseVO.succeed(nharchives.toDTO().toVO());
    }

    @Override
    public ResponseVO getArchFullTextById(Integer archId) {
        Nharchives nharchives = nharchivesMapper.selectById(archId);
        String strContent = Utility.getFileString(nharchives.getBclj());
        // System.out.println(strContent);
        return ResponseVO.succeed(strContent);
    }

    @Override
    public ResponseVO addArchRemark(ArchmarkDTO archmarkDTO) {
        Archmark archmark = archmarkDTO.toPO();
//        Date dRemark = new Date();
//        String strRemarkDate = "";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        strRemarkDate = format.format(dRemark);
//        archmark.setRemarktime(dRemark);
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", archmark.getRemarkname());
        List<Nhuser> nhusers = nhuserMapper.selectByMap(map);
        if (nhusers.size() == 1) {
            archmark.setRemarkname(nhusers.get(0).getRealName());
        } else {
            archmark.setRemarkname("");
        }
        if (archmarkMapper.insert(archmark) == 1) {
            return ResponseVO.succeed();
        }
        return ResponseVO.fail(ArchHttpStatus.ADD_ARCH_REMARK_FAIL);
    }

    @Override
    public ResponseVO findAllRemarks() {
        List<Archmark> archmarkList = archmarkMapper.selectByMap(null);
        List<VO> archmarkVOList = new ArrayList<>();
        for (Archmark archmark : archmarkList) {
            archmarkVOList.add(archmark.toDTO().toVO());
        }
        return ResponseVO.succeed(archmarkVOList);
    }

    @Override
    public ResponseVO findAllMarkByArchId(Integer archId) {
        Map<String, Object> map = new HashMap<>();
        map.put("archid", archId);
        List<Archmark> archmarkList = archmarkMapper.selectByMap(map);
        List<VO> archmarkVOList = new ArrayList<>();
        for (Archmark archmark : archmarkList) {
            archmarkVOList.add(archmark.toDTO().toVO());
        }
        return ResponseVO.succeed(archmarkVOList);
    }

    @Override
    public ResponseVO deleteArchMarkById(Integer markId) {
        if (archmarkMapper.deleteById(markId) == 1) {
            return ResponseVO.succeed();
        }
        return ResponseVO.fail(ArchHttpStatus.DEL_ARCH_REMARK_FAIL);
    }

    @Override
    public ResponseVO getArchIdByMarkId(Integer markId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", markId);
        List<Archmark> archmarkList = archmarkMapper.selectByMap(map);
        return ResponseVO.succeed(archmarkList.get(0).toDTO().toVO());
    }

    @Override
    public ResponseVO getAttachByArchId(Integer archId) {
        Map<String, Object> map = new HashMap<>();
        map.put("daid", archId);
        List<Nharchattach> nharchattachList = nharchattachMapper.selectByMap(map);
        if (nharchattachList.size() > 0) {
            // 做一次排序，方便展示
            TreeSet<Nharchattach> treesetArchAttachs = new TreeSet<>(
                    new Comparator<Nharchattach>() {
                        public int compare(Nharchattach o1, Nharchattach o2) {
                            return o1.getId() - o2.getId();
                        }
                    }
            );
            for (Nharchattach archAttachTemp : nharchattachList) {
                treesetArchAttachs.add(archAttachTemp);
            }
            List<VO> nharchattachVOList = new ArrayList<>();
            Object[] obArchAttachs = treesetArchAttachs.toArray();
            for (int i = 0; i < obArchAttachs.length; i++) {
                Nharchattach archAttachTemp = (Nharchattach) obArchAttachs[i];
                nharchattachVOList.add(archAttachTemp.toDTO().toVO());
            }
            return ResponseVO.succeed(nharchattachVOList);
        } else {
            List<VO> nharchattachVOList = new ArrayList<>();
            return ResponseVO.succeed(nharchattachVOList);
        }
    }

    @Override
    public ResponseVO getAllWb() {
        List<Nharchives> nharchives = nharchivesMapper.selectByMap(null);
        Set<String> wbSet = new HashSet<>();
        List<VO> wb = new ArrayList<>();
        for (Nharchives nharchive : nharchives) {
            if (!wbSet.contains(nharchive.getWb())) {
                wbSet.add(nharchive.getWb());
                NharchivesVO nharchivesVO = new NharchivesVO();
                nharchivesVO.setWb(nharchive.getWb());
                wb.add(nharchivesVO);
            }
        }
        return ResponseVO.succeed(wb);
    }

    @Override
    public ResponseVO quickSearch(String strBH, String strDAMC, String strWB) {
        QueryWrapper<Nharchives> queryWrapper = new QueryWrapper<>();
        if (strBH != null && strBH.length() > 0) {
            queryWrapper.eq("bh", strBH);
            // map.put("bh", strBH);
        }
        if (strDAMC != null && strDAMC.length() > 0) {
            queryWrapper.like("damc", strDAMC);
            // map.put("damc", strDAMC);
        }
        if (strWB != null && strWB.length() > 0) {
            queryWrapper.eq("wb", strWB);
            //map.put("wb", strWB);
        }
        List<Nharchives> nharchivesList = nharchivesMapper.selectList(queryWrapper);
        List<VO> res = new ArrayList<>();
        for (Nharchives nharchives : nharchivesList) {
            res.add(nharchives.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO advancedSearch(String strBH, String strDAMC, String strWB, String strJM, String strFLH, String strGJC, String strGJRW, String strXGDY, String strFWRQStart, String strFWRQEnd, String strSWRQStart, String strSWRQEnd, String strCSRQStart, String strCSRQEnd, String strFWJG, String strSWJG, String strGCDW) {
        QueryWrapper<Nharchives> queryWrapper = new QueryWrapper<>();
        if (strBH != null && strBH.length() > 0) {
            queryWrapper.eq("bh", strBH);
        }
        if (strDAMC != null && strDAMC.length() > 0) {
            queryWrapper.like("damc", strDAMC);
        }
        if (strWB != null && strWB.length() > 0) {
            queryWrapper.eq("wb", strWB);
        }
        if (strJM != null && strJM.length() > 0) {
            queryWrapper.eq("jm", strJM);
        }
        if (strFLH != null && strFLH.length() > 0) {
            queryWrapper.like("flh", strFLH);
        }
        if (strGJC != null && strGJC.length() > 0) {
            queryWrapper.like("gjc", strGJC);
        }
        if (strGJRW != null && strGJRW.length() > 0) {
            queryWrapper.like("gjrw", strGJRW);
        }
        if (strXGDY != null && strXGDY.length() > 0) {
            queryWrapper.like("xgdy", strXGDY);
        }
        if (strFWRQStart != null && strFWRQStart.length() > 0) {
            queryWrapper.gt("fwrq", strFWRQStart);
            // queryWrapper.apply("DATE_FORMAT(fwrq,'%Y.%m.%d')>={0}", strFWRQStart);
        }
        if (strFWRQEnd != null && strFWRQEnd.length() > 0) {
            queryWrapper.lt("fwrq", strFWRQEnd);
            // queryWrapper.apply("DATE_FORMAT(fwrq,'%Y.%m.%d')<={0}", strFWRQEnd);
        }
        if (strSWRQStart != null && strSWRQStart.length() > 0) {
            queryWrapper.gt("swrq", strSWRQStart);
        }
        if (strSWRQEnd != null && strSWRQEnd.length() > 0) {
            queryWrapper.lt("swrq", strSWRQEnd);
        }
        if (strCSRQStart != null && strCSRQStart.length() > 0) {
            queryWrapper.gt("csrq", strCSRQStart);
        }
        if (strCSRQEnd != null && strCSRQEnd.length() > 0) {
            queryWrapper.lt("csrq", strCSRQEnd);
        }
        if (strFWJG != null && strFWJG.length() > 0) {
            queryWrapper.like("fwjg", strFWJG);
        }
        if (strSWJG != null && strSWJG.length() > 0) {
            queryWrapper.like("swjg", strSWJG);
        }
        if (strGCDW != null && strGCDW.length() > 0) {
            queryWrapper.like("gcdw", strGCDW);
        }
        List<Nharchives> nharchivesList = nharchivesMapper.selectList(queryWrapper);
        List<VO> res = new ArrayList<>();
        for (Nharchives nharchives : nharchivesList) {
            res.add(nharchives.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO classSearch(String strFLH, String strGCDW) {
        QueryWrapper<Nharchives> queryWrapper = new QueryWrapper<>();
        if (strFLH != null && strFLH.length() > 0) {
            queryWrapper.like("flh", strFLH);
        }
        if (strGCDW != null && strGCDW.length() > 0) {
            queryWrapper.like("gcdw", strGCDW);
        }
        List<Nharchives> nharchivesList = nharchivesMapper.selectList(queryWrapper);
        List<VO> res = new ArrayList<>();
        for (Nharchives nharchives : nharchivesList) {
            res.add(nharchives.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public ResponseVO orgSearch(String strFWJG) {
        QueryWrapper<Nharchives> queryWrapper = new QueryWrapper<>();
        if (strFWJG != null && strFWJG.length() > 0) {
            queryWrapper.like("fwjg", strFWJG);
        }
        List<Nharchives> nharchivesList = nharchivesMapper.selectList(queryWrapper);
        List<VO> res = new ArrayList<>();
        for (Nharchives nharchives : nharchivesList) {
            res.add(nharchives.toDTO().toVO());
        }
        return ResponseVO.succeed(res);
    }

    @Override
    public String downloadFile(HttpServletResponse response, String strAttachID, String strType) {
        String strFilePath = "";
        String strFileName = "";
        if (strAttachID != null && strAttachID.length() > 0) {
            try {
                if (strType.equals("doc")) {
                    // strType是doc则代表下载的是档案正文，传入的strAttachID对应的则是档案的id
                    Nharchives nharchives = nharchivesMapper.selectById(new Integer(strAttachID));
                    String strBCLJ = nharchives.getBclj();
                    if (strBCLJ != null && strBCLJ.length() > 0) {
                        String strDocLJ = FileUtil.getDocLJByBCLJ(strBCLJ);
                        String strDocName = FileUtil.getDocFileNameByDocLJ(strDocLJ);
                        strFilePath = strDocLJ.substring(0, strDocLJ.lastIndexOf("/"));
                        strFileName = strDocName;
                    }
                } else {
                    // 下载档案的附件，也就是图片
                    Nharchattach nharchattach = nharchattachMapper.selectById(new Integer(strAttachID));
                    if (nharchattach != null) {
                        strFilePath = nharchattach.getTplj();
                        strFileName = nharchattach.getTpmc();
                    }
                }
            } catch (Exception var29) {
                var29.printStackTrace();
            }
            System.out.println(strFilePath + '/' + strFileName);

            File file = new File(strFilePath + '/' + strFileName);
            if (!file.exists()) {
                return "下载文件不存在";
            }

            response.reset();

            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");

            response.setBufferSize(1048576);
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + toUtf8String(strFileName) + "\"");
            FileInputStream fis = null;
            ServletOutputStream out = null;

            try {
                fis = new FileInputStream(new File(strFilePath + "/" + strFileName));
                out = response.getOutputStream();
                byte[] readUnit = new byte[1048576];

                int readLength;
                while ((readLength = fis.read(readUnit)) != -1) {
                    out.write(readUnit, 0, readLength);
                    out.flush();
                }

                fis.close();
                out.close();
            } catch (Exception var26) {
                var26.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception var25) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception var24) {
                    }
                }

            }
        }
        return null;
    }

    @Override
    public ResponseVO uploadFile(MultipartFile[] files, String strType, Integer archId) {
        if (strType.equals("doc")) {
            // 上传档案正文
            Nharchives nharchives = nharchivesMapper.selectById(archId);
            String strBCLJ = nharchives.getBclj();

            SystemConfig sc = new SystemConfig();
            String strArchRoot = sc.getArchPath();
            String strTxtArchRoot = sc.getTxtArchPath();
            strBCLJ = strBCLJ.replaceAll(strTxtArchRoot, strArchRoot);
            String strDocLJ = strBCLJ.substring(0, strBCLJ.lastIndexOf(".html"));
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".")+1);  // 文件名统一与原来的文件保持一致，只取后缀
                System.out.println(strDocLJ + '.' + fileName);
                File dest = new File(strDocLJ + '.' + fileName); // 上传文件的路径
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    files[i].transferTo(dest);
                } catch (Exception e) {
                    return ResponseVO.fail(ArchHttpStatus.UPLOAD_FILE_FAIL);
                }

                try {
                    LuceneUtil.getInstance().delIndex(nharchives.getDamc());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // 更新 doc 对应的html文件什么的？
                String strArchive = strDocLJ + "." + fileName;
                String strHtmlFile = nharchives.getBclj();
                System.out.println("档案路径:" + strArchive);
                System.out.println("文本路径:" + strHtmlFile);
                if (strArchive.length() > 0) {
                    if (strArchive.toLowerCase().endsWith(".doc")) {
                        try {
                            FileUtil.convertDoc2Html(strArchive, strHtmlFile);
                        } catch (Exception var43) {
                            var43.printStackTrace();
                        }
                    } else if (strArchive.toLowerCase().endsWith(".docx")) {
                        try {
                            FileUtil.convertDocx2Html(strArchive, strHtmlFile);
                        } catch (Exception var42) {
                            var42.printStackTrace();
                        }
                    }
                }

                try {
                    File fHtmlFile = new File(nharchives.getBclj());
                    if (fHtmlFile != null && fHtmlFile.isFile()) {
                        LuceneUtil.getInstance().index(nharchives, strHtmlFile);
                    }
                } catch (Exception var41) {
                    var41.printStackTrace();
                }
            }
        } else {
            // 上传档案附件
            Map<String, Object> map = new HashMap<>();
            map.put("daid", archId);
            // 得到该档案下的其他附件对应的保存路径，保存在统一路径下
            String uploadFilePath = nharchattachMapper.selectByMap(map).get(0).getTplj();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getOriginalFilename();  // 文件名
                File dest = new File(uploadFilePath + '/' + fileName);
                // 数据库记录
                Nharchattach nharchattach = new Nharchattach();
                nharchattach.setDaid(archId);
                nharchattach.setTpmc(fileName);
                nharchattach.setTplx(fileName.substring(0, fileName.lastIndexOf(".")));
                nharchattach.setTplj(uploadFilePath);
                nharchattachMapper.insert(nharchattach);

                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    files[i].transferTo(dest);
                } catch (Exception e) {
                    return ResponseVO.fail(ArchHttpStatus.UPLOAD_FILE_FAIL);
                }
            }
        }
        return ResponseVO.succeed();
    }

    @Override
    public ResponseVO deleteAttachFile(Integer attachId) {
        Nharchattach nharchattach = nharchattachMapper.selectById(attachId);
        String targetFilePath = nharchattach.getTplj() + "/" + nharchattach.getTpmc();
        File file = new File(targetFilePath);
        deleteFile(file);
        nharchattachMapper.deleteById(attachId);
        return ResponseVO.succeed();
    }

    @Override
    public ResponseVO deleteArchTextFile(Integer archId) {
        Nharchives nharchives = nharchivesMapper.selectById(archId);
        if (nharchives != null) {
            String strBCLJ = nharchives.getBclj();
            if (strBCLJ != null && strBCLJ.length() > 0) {
                String strDocLJ = FileUtil.getDocLJByBCLJ(strBCLJ);
                String strFilePath = strDocLJ.substring(0, strDocLJ.lastIndexOf(".")) + (new Date()).getTime() + strDocLJ.substring(strDocLJ.lastIndexOf("."));
                System.out.println(strDocLJ + "修改名称to：" + strFilePath);
                File fDocLJ = new File(strDocLJ);
                File fileFilePath = new File(strFilePath);
                fDocLJ.renameTo(fileFilePath);

                try {
                    LuceneUtil.getInstance().delIndex(nharchives.getDamc());
                } catch (Exception var40) {
                    var40.printStackTrace();
                }
            }
        }
        return ResponseVO.succeed();
    }

    public void deleteFile(File file) {
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            file.delete();
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //打印文件名
            String name = f.getName();
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                deleteFile(f);
            } else {
                f.delete();
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
    }

    @Override
    public ResponseVO getArchTextDocName(String strBCLJ) {
        if (strBCLJ != null && strBCLJ.length() > 0) {
            String strDocLJ = FileUtil.getDocLJByBCLJ(strBCLJ);
            System.out.println(strDocLJ);
            String strDocName = FileUtil.getDocFileNameByDocLJ(strDocLJ);
            System.out.println(strDocName);
            if (strDocName.length() > 0) {
                return ResponseVO.succeed(strDocName);
            }
        }
        return ResponseVO.succeed("");
    }

    @Override
    public ResponseVO updateArch(NharchivesDTO nharchivesDTO) {
        Nharchives nharchives = nharchivesDTO.toPO();
        UpdateWrapper<Nharchives> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", nharchives.getId());
        nharchives.setBclj(nharchivesMapper.selectById(nharchives.getId()).getBclj());

        ExcelManager excelManager = new ExcelManager();
        excelManager.updateExcelNharch(nharchives, FileUtil.getIndexFile());

        if (nharchivesMapper.update(nharchives, updateWrapper) == 1) {
            return ResponseVO.succeed();
        }
        return ResponseVO.fail(ArchHttpStatus.UPDATE_ARCH_FAIL);
    }

    private String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception var7) {
                    System.out.println(var7);
                    b = new byte[0];
                }

                for (int j = 0; j < b.length; ++j) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }

                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }

        return sb.toString();
    }
}
