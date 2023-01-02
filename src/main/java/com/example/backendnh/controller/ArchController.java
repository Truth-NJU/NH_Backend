package com.example.backendnh.controller;


import com.example.backendnh.bean.ImpArchivesResult;
import com.example.backendnh.dto.NharchivesDTO;
import com.example.backendnh.service.ArchService;
import com.example.backendnh.util.SystemConfig;
import com.example.backendnh.vo.ArchmarkVO;
import com.example.backendnh.vo.ImpArchResultVO;
import com.example.backendnh.vo.NharchivesVO;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/nh/arch")
public class ArchController {

    @Autowired
    ArchService archService;

    @PostMapping("/getAllChildrenDirs")
    public ResponseVO getAllChildrenDirs(@RequestParam String appendDir) {
        return archService.getAllChildrenDirs(appendDir);
    }

    /**
     * 导入基本信息
     *
     * @param dir     相对于根目录的相对路径
     * @param impType
     * @return
     */
    @PostMapping("/importArch")
    public ResponseVO importArchives(@RequestParam String dir, @RequestParam String impType) {
        ImpArchivesResult impResult = null;
        boolean blnCover = false;
        SystemConfig systemConfig = new SystemConfig();
        // 获取全路径
        dir = systemConfig.getArchPath() + "/" + dir;
        // 导入
        if (impType != null && impType.equalsIgnoreCase("coverimp")) {
            blnCover = true;
        }
        if (dir != null && dir.length() > 0) {
            impResult = archService.impArchives(dir, blnCover);
        }

        // 导入结果
        int iSuccess = 0;
        int iFail = 0;
        int iExist = 0;
        List<String> alError = null;
        List<String> alSuccess = null;
        List<String> alExist = null;
        if (impResult != null) {
            alError = impResult.getAlErrorArchive();
            alSuccess = impResult.getAlSucessArchive();
            alExist = impResult.getAlExistArchive();

            alError = new ArrayList<>(new HashSet<>(alError));
            alSuccess = new ArrayList<>(new HashSet<>(alSuccess));
            alExist = new ArrayList<>(new HashSet<>(alExist));

            iFail = alError.size();
            iSuccess = alSuccess.size();
            iExist = alExist.size();
        }
        ImpArchResultVO impArchResultVO = new ImpArchResultVO();
        impArchResultVO.setIExist(iExist);
        impArchResultVO.setIFail(iFail);
        impArchResultVO.setISuccess(iSuccess);
        impArchResultVO.setAlError(alError);
        impArchResultVO.setAlExist(alExist);
        impArchResultVO.setAlSuccess(alSuccess);
        return ResponseVO.succeed(impArchResultVO);
    }

    /**
     * 得到所有的档案，展示在档案中心中
     *
     * @return
     */
    @PostMapping("/getAllArch")
    public ResponseVO getAllArchives() {
        return archService.getAllArchives();
    }


    @PostMapping("/addArch")
    public ResponseVO addArch(@RequestBody NharchivesVO nharchivesVO) {
        return archService.addArchive(nharchivesVO.toDTO());
    }

    @PostMapping("/deleteArch")
    public ResponseVO deleteArch(@RequestParam Integer archId) {
        return archService.deleteArch(archId);
    }

    /**
     * 通过档案id获取档案所有的属性
     *
     * @param archId
     * @return
     */
    @PostMapping("/getArchContent")
    public ResponseVO getArchContent(@RequestParam Integer archId) {
        return archService.getArchContentById(archId);
    }


    /**
     * 通过档案id获取档案全文
     *
     * @param archId
     * @return
     */
    @PostMapping("/getArchFullText")
    public ResponseVO getArchFullText(@RequestParam Integer archId) {
        return archService.getArchFullTextById(archId);
    }


    /**
     * 提交读者反馈
     *
     * @param archmarkVO
     * @return
     */
    @PostMapping("/addArchmark")
    public ResponseVO addArchmark(@RequestBody ArchmarkVO archmarkVO) {
        return archService.addArchRemark(archmarkVO.toDTO());
    }


    @PostMapping("/findAllMark")
    public ResponseVO findAllArchMark() {
        return archService.findAllRemarks();
    }

    @PostMapping("/findAllMarkByArchId")
    public ResponseVO findAllMarkByArchId(@RequestParam Integer archId) {
        return archService.findAllMarkByArchId(archId);
    }


    @PostMapping("/delMark")
    public ResponseVO deleteArchMarkById(@RequestParam Integer remarkId) {
        return archService.deleteArchMarkById(remarkId);
    }

    /**
     * 通过反馈id得到该反馈对应的档案id
     *
     * @param markId
     * @return
     */
    @PostMapping("/getArchIdByMarkId")
    public ResponseVO getArchIdByMarkId(Integer markId) {
        return archService.getArchIdByMarkId(markId);
    }


    /**
     * 通过档案id获得档案下的所有附件
     *
     * @param archId
     * @return
     */
    @PostMapping("/getAttachByArchId")
    public ResponseVO getAttachByArchId(Integer archId) {
        return archService.getAttachByArchId(archId);
    }

    @PostMapping("/getAllWb")
    public ResponseVO getAllWb() {
        return archService.getAllWb();
    }

    @PostMapping("/quickSearch")
    public ResponseVO quickSearch(@RequestParam String strBH, @RequestParam String strDAMC, @RequestParam String strWB) {
        return archService.quickSearch(strBH, strDAMC, strWB);
    }

    @PostMapping("/advancedSearch")
    public ResponseVO advancedSearch(@RequestParam String strBH, @RequestParam String strDAMC, @RequestParam String strWB, @RequestParam String strJM, @RequestParam String strFLH, @RequestParam String strGJC, @RequestParam String strGJRW, @RequestParam String strXGDY, @RequestParam String strFWRQStart, @RequestParam String strFWRQEnd, @RequestParam String strSWRQStart, @RequestParam String strSWRQEnd, @RequestParam String strCSRQStart, @RequestParam String strCSRQEnd, @RequestParam String strFWJG, @RequestParam String strSWJG, @RequestParam String strGCDW) {
        return archService.advancedSearch(strBH, strDAMC, strWB, strJM, strFLH, strGJC, strGJRW, strXGDY, strFWRQStart, strFWRQEnd, strSWRQStart, strSWRQEnd, strCSRQStart, strCSRQEnd, strFWJG, strSWJG, strGCDW);
    }

    @PostMapping("/classSearch")
    public ResponseVO classSearch(@RequestParam String strFLH, @RequestParam String strGCDW) {
        return archService.classSearch(strFLH, strGCDW);
    }


    @PostMapping("/orgSearch")
    public ResponseVO orgSearch(@RequestParam String strFWJG) {
        return archService.orgSearch(strFWJG);
    }


    @PostMapping("/download")
    public String downloadFile(HttpServletResponse response, @RequestParam String strAttachID, @RequestParam String strType) {
        return archService.downloadFile(response, strAttachID, strType);
    }

    @PostMapping("/upload")
    public ResponseVO uploadFile(@RequestParam MultipartFile[] files, @RequestParam String strType, @RequestParam Integer archId) {
        return archService.uploadFile(files, strType, archId);
    }

    @PostMapping("/deleteAttachFile")
    public ResponseVO deleteAttachFile(@RequestParam Integer attachId){
        return archService.deleteAttachFile(attachId);
    }

    @PostMapping("/deleteArchTextFile")
    ResponseVO deleteArchTextFile(@RequestParam Integer archId){
        return archService.deleteArchTextFile(archId);
    }
    @PostMapping("/getArchTextDocName")
    public ResponseVO getArchTextDocName(@RequestParam String strBCLJ) {
        return archService.getArchTextDocName(strBCLJ);
    }

    @PostMapping("/updateArch")
    public ResponseVO updateArch(@RequestBody NharchivesVO nharchivesVO){
        return archService.updateArch(nharchivesVO.toDTO());
    }
}

