package com.example.backendnh.util;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

public class FileUtil {
    public FileUtil() {
    }

    public static String readFile(String path, int words) throws UnsupportedEncodingException {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(new File(path));
        } catch (FileNotFoundException var17) {
            return "";
        }

        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();

        try {
            String line = br.readLine();

            while (true) {
                if (line != null) {
                    sb.append(line).append(System.getProperty("line.separator"));
                    if (words <= 0 || sb.length() <= words) {
                        line = br.readLine();
                        continue;
                    }
                }

                String var9 = sb.toString();
                return var9;
            }
        } catch (IOException var19) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (isr != null) {
                    isr.close();
                }

                if (fis != null) {
                    fis.close();
                }
            } catch (IOException var18) {
                return "";
            }

        }

        return "";
    }

    public static String getFileString(File fPathName) {
        String strFileString = "";
        if (fPathName != null && fPathName.exists() && fPathName.isFile()) {
            FileInputStream fis = null;
            ByteArrayOutputStream baos = null;

            try {
                fis = new FileInputStream(fPathName);
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                int iTemp;
                while ((iTemp = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, iTemp);
                }

                strFileString = baos.toString();
                fis.close();
                baos.close();
                strFileString = StringEscapeUtils.unescapeHtml4(strFileString);
                String var7 = strFileString;
                return var7;
            } catch (Exception var19) {
                var19.printStackTrace();
                return "";
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException var18) {
                        var18.printStackTrace();
                    }
                }

                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException var17) {
                        var17.printStackTrace();
                    }
                }

            }
        } else {
            return "";
        }
    }

    public static ArrayList<File> getChildrenDirs(String strParentDir) {
        ArrayList<File> lChildrenDirs = new ArrayList();
        if (strParentDir != null && strParentDir.length() > 0) {
            File fParentDir = new File(strParentDir);
            if (fParentDir != null && fParentDir.isDirectory()) {
                File[] fChildrenDirs = fParentDir.listFiles();

                for (int i = 0; fChildrenDirs != null && i < fChildrenDirs.length; ++i) {
                    if (fChildrenDirs[i] != null && fChildrenDirs[i].isDirectory()) {
                        lChildrenDirs.add(fChildrenDirs[i]);
                    }
                }
            }
        }

        return lChildrenDirs;
    }

    public static boolean isArchDir(String strArchDir) {
        File fArchDir = new File(strArchDir);
        if (fArchDir != null && fArchDir.isDirectory()) {
            File[] fTemp = fArchDir.listFiles();
            if (fTemp != null && fTemp.length > 0) {
                for (int i = 0; i < fTemp.length; ++i) {
                    if (fTemp[i] != null && fTemp[i].isDirectory()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static String getArchiveByDir(String strArchDir) {
        File fArchDir = new File(strArchDir);
        if (fArchDir != null && fArchDir.isDirectory()) {
            File[] fTemp = fArchDir.listFiles();
            if (fTemp != null && fTemp.length > 0) {
                for (int i = 0; i < fTemp.length; ++i) {
                    if (fTemp[i] != null && fTemp[i].isFile() && (fTemp[i].getAbsolutePath().toLowerCase().endsWith(".doc") || fTemp[i].getAbsolutePath().toLowerCase().endsWith(".docx"))) {
                        return fTemp[i].getAbsolutePath();
                    }
                }
            }
        }

        return "";
    }

    public static String getHtmlFileByArchFile(String strArchFile) {
        String strResult = "";
        SystemConfig systemConfig = new SystemConfig();
        String strArchPath = systemConfig.getArchPath();
        String strArchTxtPath = systemConfig.getTxtArchPath();
        if (strArchFile != null && strArchFile.length() > 0) {
            strArchPath = strArchPath.replaceAll("\\\\", "/");
            strArchTxtPath = strArchTxtPath.replaceAll("\\\\", "/");
            strArchFile = strArchFile.replaceAll("\\\\", "/");
            if (strArchFile.indexOf(strArchPath) != -1) {
                strResult = strArchFile.replaceAll(strArchPath, strArchTxtPath);
                if (strResult != null && strResult.length() > 0) {
                    strResult = strResult.substring(0, strResult.lastIndexOf("."));
                    strResult = strResult + ".html";
                }

                return strResult;
            }
        }

        return strResult;
    }

    public static String getDocLJByBCLJ(String strBCLJ) {
        SystemConfig sc = new SystemConfig();
        String strArchRoot = sc.getArchPath();
        String strTxtArchRoot = sc.getTxtArchPath();
        if (strBCLJ != null && strBCLJ.length() > 0) {
            strBCLJ = strBCLJ.replaceAll(strTxtArchRoot, strArchRoot);
            String strDocLJ = strBCLJ.substring(0, strBCLJ.lastIndexOf(".html"));
            File fDocLJ = new File(strDocLJ + ".docx");
            if (fDocLJ != null && fDocLJ.exists() && fDocLJ.isFile()) {
                return strDocLJ + ".docx";
            }

            fDocLJ = new File(strDocLJ + ".doc");
            if (fDocLJ != null && fDocLJ.exists() && fDocLJ.isFile()) {
                return strDocLJ + ".doc";
            }
        }

        return "";
    }

    public static String getDocFileNameByDocLJ(String strDocLJ) {
        return strDocLJ != null && strDocLJ.length() > 0 ? strDocLJ.substring(strDocLJ.lastIndexOf("/") + 1) : "";
    }

    public static void convertDoc2Html(String strDocFile, String strHtmlFile) throws Exception {
        new File(strDocFile);
        File fHtmlFile = new File(strHtmlFile);
        long startTime = System.currentTimeMillis();
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(strDocFile));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); ++i) {
                Picture pic = (Picture) pics.get(i);

                try {
                    createDirAndFile("./" + pic.suggestFullFileName());
                    pic.writeImageContent(new FileOutputStream("./" + pic.suggestFullFileName()));
                } catch (FileNotFoundException var15) {
                    var15.printStackTrace();
                }
            }
        }

        Document htmlDocument = wordToHtmlConverter.getDocument();
        if (fHtmlFile == null || !fHtmlFile.isFile()) {
            createDirAndFile(strHtmlFile);
        }

        FileOutputStream out = new FileOutputStream(fHtmlFile);
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty("encoding", "GBK");
        serializer.setOutputProperty("indent", "yes");
        serializer.setOutputProperty("method", "html");
        serializer.transform(domSource, streamResult);
        out.close();
        System.out.println("with " + (System.currentTimeMillis() - startTime) + " ms. Generate " + strHtmlFile);
    }

    public static void convertDocx2Html(String strDocxFile, String strHtmlFile) throws Exception {
        File fDocxFile = new File(strDocxFile);
        File fHtmlFile = new File(strHtmlFile);
        long startTime = System.currentTimeMillis();
        XWPFDocument document = new XWPFDocument(new FileInputStream(fDocxFile));
        XHTMLOptions options = XHTMLOptions.create();
        File imageFolder = new File("./");
        options.setExtractor(new FileImageExtractor(imageFolder));
        options.URIResolver(new FileURIResolver(imageFolder));
        if (fHtmlFile == null || !fHtmlFile.isFile()) {
            createDirAndFile(strHtmlFile);
        }

        OutputStream out = Files.newOutputStream(new File(strHtmlFile).toPath());
        XHTMLConverter.getInstance().convert(document, out, options);
        System.out.println("with " + (System.currentTimeMillis() - startTime) + " ms. Generate " + strHtmlFile);
    }

    private static boolean createDirAndFile(String strPathFile) {
        if (strPathFile != null && strPathFile.length() > 0) {
            strPathFile = strPathFile.replaceAll("\\\\", "/");
            String strPath = strPathFile.substring(0, strPathFile.lastIndexOf("/"));
            System.out.println("strPath:" + strPath);
            File fPath = new File(strPath);
            if (fPath == null || !fPath.isDirectory()) {
                fPath.mkdirs();
            }

            File fPathFile = new File(strPathFile);
            if (fPathFile == null || !fPathFile.isFile()) {
                try {
                    return fPathFile.createNewFile();
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            }
        }

        return true;
    }

    public static String getIndexFile() {
        SystemConfig systemConfig = new SystemConfig();
        String strArchPath = systemConfig.getArchPath();
        File fArchPath = new File(strArchPath);
        if (fArchPath != null && fArchPath.isDirectory()) {
            File[] fTemp = fArchPath.listFiles();

            for (int i = 0; fTemp != null && i < fTemp.length; ++i) {
                if (fTemp[i] != null && fTemp[i].isFile() && fTemp[i].getName() != null && fTemp[i].getName().toLowerCase().endsWith(".xls")) {
                    return fTemp[i].getAbsolutePath();
                }
            }
        }

        return "";
    }

    public static ArrayList<File> getArchPics(String strArchDir) {
        ArrayList<File> lArchPics = new ArrayList();
        if (strArchDir != null && strArchDir.length() > 0) {
            File fArchDir = new File(strArchDir);
            if (fArchDir != null && fArchDir.isDirectory()) {
                File[] fChildren = fArchDir.listFiles();

                for (int i = 0; fChildren != null && i < fChildren.length; ++i) {
                    if (fChildren[i] != null && fChildren[i].isFile()) {
                        String strFileName = fChildren[i].getName();
                        if (strFileName.toLowerCase().endsWith(".jpg") || strFileName.toLowerCase().endsWith(".png") || strFileName.toLowerCase().endsWith(".pdf")) {
                            lArchPics.add(fChildren[i]);
                        }
                    }
                }
            }
        }

        return lArchPics;
    }

    public static String getFileName(String strFileName) {
        return strFileName != null && strFileName.length() > 0 ? strFileName.substring(0, strFileName.lastIndexOf(".")) : "";
    }

    public static String getFileSuffixByName(String strFileName) {
        return strFileName != null && strFileName.length() > 0 ? strFileName.substring(strFileName.lastIndexOf(".") + 1, strFileName.length()) : "";
    }

    public static boolean makeDFPreviewImg(String strPdfPath, String strPreviewImgPath) {
        if (strPdfPath != null && strPdfPath.length() > 0 && strPreviewImgPath != null && strPreviewImgPath.length() > 0) {
            try {
                File fPDF = new File(strPdfPath);
                RandomAccessFile raf = new RandomAccessFile(fPDF, "r");
                FileChannel channel = raf.getChannel();
                MappedByteBuffer buf = channel.map(MapMode.READ_ONLY, 0L, channel.size());
                PDFFile pdffile = new PDFFile(buf);
                PDFPage page = pdffile.getPage(1);
                Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
                Image img = page.getImage(rect.width, rect.height, rect, (ImageObserver) null, true, true);
                BufferedImage tag = new BufferedImage(rect.width, rect.height, 1);
                tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height, (ImageObserver) null);
                File fPreviewImgPath = new File(strPreviewImgPath);
                if (fPreviewImgPath == null || !fPreviewImgPath.isFile()) {
                    fPreviewImgPath.createNewFile();
                }

                FileOutputStream out = new FileOutputStream(strPreviewImgPath);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
                param2.setQuality(1.0F, false);
                encoder.setJPEGEncodeParam(param2);
                encoder.encode(tag);
                channel.close();
                raf.close();
                out.close();
                return true;
            } catch (Exception var15) {
                var15.printStackTrace();
            }
        }

        return false;
    }

    public static boolean isPreviewImg(ArrayList<File> alImg, String strImgFileName) {
        if (alImg != null && alImg.size() > 0 && strImgFileName != null && strImgFileName.length() > 0) {
            for (int i = 0; i < alImg.size(); ++i) {
                File fTemp = (File) alImg.get(i);
                if (fTemp != null) {
                    String strTempName = fTemp.getName();
                    String strTempSuffix = getFileSuffixByName(strTempName);
                    if (strTempSuffix.toLowerCase().equals("pdf") && getFileName(strImgFileName).equals(getFileName(strTempName))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String str = "d:/Test/中文lucenetxtfile/B01(1)戴佳佳/总统文物/总统文物.html";
        System.out.println(getDocLJByBCLJ(str));
        System.out.println(getDocFileNameByDocLJ(getDocLJByBCLJ(str)));
    }
}
