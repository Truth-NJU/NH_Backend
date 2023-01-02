package com.example.backendnh.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.backendnh.mapper.NhclassesMapper;
import com.example.backendnh.po.Nhclasses;
import org.apache.commons.lang3.StringEscapeUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

public class Utility {

    @Autowired
    static NhclassesMapper nhclassesMapper;

    public Utility() {
    }

    public String getCurrentDate() {
        Calendar ca = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat();
        return df.format(ca.getTime());
    }

    public static String getV(Object object) {
        return object != null ? object.toString() : "";
    }

    public static String getCNString(String str) {
        if (str != null && str.length() > 0) {
            try {
                return new String(str.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
            }
        }

        return "";
    }

    public static String getFileString(String strFilePathName) {
        String strFileString = "";
        if (strFilePathName != null && strFilePathName.length() > 0) {
            FileInputStream fis = null;
            ByteArrayOutputStream baos = null;

            String var8;
            try {
                File file = new File(strFilePathName);
                if (file != null && file.exists() && file.isFile()) {
                    fis = new FileInputStream(file);
                    baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];

                    int iTemp;
                    while ((iTemp = fis.read(buffer)) != -1) {
                        baos.write(buffer, 0, iTemp);
                    }

                    strFileString = baos.toString();
                    fis.close();
                    baos.close();
                }

                var8 = strFileString;
            } catch (Exception var20) {
                var20.printStackTrace();
                return "";
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }
                }

                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException var18) {
                        var18.printStackTrace();
                    }
                }

            }

            return var8;
        } else {
            return "";
        }
    }

    public static String markFileString(String strMark, String strFileString) {
        if (strMark != null && strMark.trim().length() > 0 && strFileString != null && strFileString.length() > 0) {
            String[] strMarks = strMark.split(" ");
            strFileString = StringEscapeUtils.unescapeHtml4(strFileString);

            for (int i = 0; i < strMarks.length; ++i) {
                if (strMarks[i] != null && strMarks[i].length() > 0) {
                    String strMarkFront = "<font style=\"background-color:yellow\">" + strMarks[i] + "</font>";
                    strFileString = strFileString.replaceAll(strMarks[i], strMarkFront);
                }
            }
        }

        return strFileString;
    }

    public static HashMap<String, Nhclasses> getFLHMapByFLH(String strFLH) {
        HashMap<String, Nhclasses> hmFLH = new HashMap();
        if (strFLH != null && strFLH.length() > 0) {
            String[] strFLHs = strFLH.split("\\.");
            if (strFLHs != null && strFLHs.length > 0) {
                String strFLHTemp = strFLHs[0];
                if (strFLHTemp != null && strFLHTemp.trim().length() == 1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("classid", strFLHTemp.trim());
                    map.put("parentid", 0);
                    List<Nhclasses> lNhclasses = nhclassesMapper.selectByMap(map);
                    // List<Nhclasses> lNhclasses = nhclassesDAO.findFirstFLHByClassid(strFLHTemp.trim());
                    if (lNhclasses != null && lNhclasses.size() > 0) {
                        Nhclasses nhclassesTemp = (Nhclasses) lNhclasses.get(0);
                        hmFLH.put("1", nhclassesTemp);
                    }
                } else {
                    if (strFLHTemp == null || strFLHTemp.trim().length() <= 1) {
                        return null;
                    }

                    String strFirstFLH = strFLHTemp.trim().substring(0, 1);
                    Integer iFirstFLHID = null;
                    if (strFirstFLH != null && strFirstFLH.trim().length() == 1) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("classid", strFirstFLH.trim());
                        map.put("parentid", 0);
                        List<Nhclasses> lNhclasses = nhclassesMapper.selectByMap(map);
//                        List<Nhclasses> lNhclasses = nhclassesDAO.findFirstFLHByClassid(strFirstFLH.trim());
                        if (lNhclasses != null && lNhclasses.size() > 0) {
                            Nhclasses nhclassesTemp = (Nhclasses) lNhclasses.get(0);
                            iFirstFLHID = nhclassesTemp.getId();
                            hmFLH.put("1", nhclassesTemp);
                        }
                    }

                    String strSecondFLH = strFLHTemp.trim().substring(1, strFLHTemp.trim().length());
                    Integer iSecondFLHID = null;
                    if (strSecondFLH != null && strSecondFLH.trim().length() > 0) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("classid", strSecondFLH.trim());
                        map.put("parentid", iFirstFLHID);
                        List<Nhclasses> lNhclasses = nhclassesMapper.selectByMap(map);
//                        List<Nhclasses> lNhclasses = nhclassesDAO.findByClassid(strSecondFLH.trim(), iFirstFLHID);
                        if (lNhclasses != null && lNhclasses.size() > 0) {
                            Nhclasses nhclassesTemp = (Nhclasses) lNhclasses.get(0);
                            iSecondFLHID = nhclassesTemp.getId();
                            hmFLH.put("2", nhclassesTemp);
                        }
                    }

                    Integer iNFLHID = iSecondFLHID;

                    for (int i = 1; i < strFLHs.length; ++i) {
                        if (strFLHs[i] != null && strFLHs[i].trim().length() > 0) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("classid", strFLHs[i]);
                            map.put("parentid", iNFLHID);
                            List<Nhclasses> lNhclasses = nhclassesMapper.selectByMap(map);
//                            List<Nhclasses> lNhclasses = nhclassesDAO.findByClassid(strFLHs[i], iNFLHID);
                            if (lNhclasses != null && lNhclasses.size() > 0) {
                                Nhclasses nhclassesTemp = (Nhclasses) lNhclasses.get(0);
                                hmFLH.put(String.valueOf(i + 2), nhclassesTemp);
                                iNFLHID = nhclassesTemp.getId();
                            }
                        }
                    }
                }

                System.out.println("看看分类号：");

                for (int i = 0; i < hmFLH.size(); ++i) {
                    System.out.println(i + 1 + ":" + ((Nhclasses) hmFLH.get(String.valueOf(i + 1))).getClasscn());
                }

                return hmFLH;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        getFLHMapByFLH("A11.3");
    }
}
