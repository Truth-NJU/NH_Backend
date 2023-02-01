package com.example.backendnh.service;

import com.example.backendnh.bean.ImpArchivesResult;
import com.example.backendnh.dto.ArchmarkDTO;
import com.example.backendnh.dto.NharchivesDTO;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ArchService {

    ResponseVO getAllChildrenDirs(String appendDir);

    ImpArchivesResult impArchives(String strCurrDir, boolean blnCover);

    boolean impSingleArchive(String strArchiveDir, boolean blnCover);

    ResponseVO getAllArchives();

    ResponseVO addArchive(NharchivesDTO nharchivesDTO);

    ResponseVO deleteArch(Integer archId);

    ResponseVO getArchContentById(Integer archId);

    /**
     * 得到档案全文
     *
     * @param archId
     * @return
     */
    ResponseVO getArchFullTextById(Integer archId);

    ResponseVO addArchRemark(ArchmarkDTO archmarkDTO);

    ResponseVO findAllRemarks();

    ResponseVO findAllMarkByArchId(Integer archId);

    ResponseVO deleteArchMarkById(Integer markId);

    ResponseVO getArchIdByMarkId(Integer markId);

    ResponseVO getAttachByArchId(Integer archId);

    /**
     * 得到所有文别，档案中心展示要用
     *
     * @return
     */
    ResponseVO getAllWb();

    /**
     * 档案中心页面快速查询
     *
     * @param strBH
     * @param strDAMC
     * @param strWB
     * @return
     */
    ResponseVO quickSearch(String strBH, String strDAMC, String strWB);

    ResponseVO advancedSearch(String strBH, String strDAMC, String strWB, String strJM, String strFLH, String strGJC, String strGJRW, String strXGDY, String strFWRQStart, String strFWRQEnd, String strSWRQStart, String strSWRQEnd, String strCSRQStart, String strCSRQEnd, String strFWJG, String strSWJG, String strGCDW);


    /**
     * 内容分类中的分类查询
     *
     * @param strFLH
     * @param strGCDW
     * @return
     */
    ResponseVO classSearch(String strFLH, String strGCDW);

    /**
     * 机构分类中的查询
     *
     * @param strFWJG
     * @return
     */
    ResponseVO orgSearch(String strFWJG);

    String downloadFile(HttpServletResponse response, String strAttachID, String strType);

    /**
     * @param files
     * @param strType doc 上传正文，否则上传附件，也就是图片
     * @param archId  对应档案的id
     * @return
     */
    ResponseVO uploadFile(MultipartFile[] files, String strType, Integer archId);

    /**
     *
     * @param files
     * @param txtOrSrc 上传的是src文件夹还是txt文件夹
     * @return
     */
    ResponseVO uploadFileToPath(MultipartFile[] files,String txtOrSrc);

    /**
     * 删除档案下的附件
     *
     * @param attachId
     * @return
     */
    ResponseVO deleteAttachFile(Integer attachId);

    /**
     * 删除档案正文文件
     *
     * @param archId
     * @return
     */
    ResponseVO deleteArchTextFile(Integer archId);

    /**
     * 通过档案正文的保存路径获得档案正文的名称
     *
     * @param strBCLJ 档案正文的保存路径
     * @return
     */
    ResponseVO getArchTextDocName(String strBCLJ);

    /**
     * 编辑页面更新档案
     * @param nharchivesDTO
     * @return
     */
    ResponseVO updateArch(NharchivesDTO nharchivesDTO);
}

