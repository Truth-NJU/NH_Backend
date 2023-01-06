package com.example.backendnh.util;

import java.io.*;
import java.net.URLDecoder;

// todo 解决乱码问题
public class SystemConfig {
    public static final String strLocalConfigDescrible = "配置文件";
    public static String strLocalConfigFile = null;

    public PropertiesUtil propertiesUtil;
    public static String[] strConfigNames = new String[]{"BaseDn.ldap", "Pawd.ldap", "Url.ldap", "User.ldap", "Filter.ldap", "LdapType", "PageShowCount", "sysusername", "syspassword", "tempdir", "ArchPath", "ArchIndexPath", "TxtArchPath"};
    public static final int BaseDn_ldap = 0;
    public static final int Pawd_ldap = 1;
    public static final int Url_ldap = 2;
    public static final int User_ldap = 3;
    public static final int Filter_ldap = 4;
    public static final int LdapType = 5;
    public static final int PageShowCount = 6;
    public static final int sysusername = 7;
    public static final int syspassword = 8;
    public static final int tempdir = 9;
    public static final int archpath = 10;
    public static final int archindexpath = 11;
    public static final int txtarchpath = 12;

    public SystemConfig() {
        String strTmp = this.getClass().getResource("SystemConfig.class").getPath();

        try {
            strTmp = URLDecoder.decode(strTmp, "utf-8");
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }
        String strClassPath = "target";
        strTmp = strTmp.substring(0, strTmp.indexOf(strClassPath));
        String strLocalConfigFile = strTmp + "target/classes/configoracle.properties";
        System.out.println("具体配置文件路径：" + strLocalConfigFile);
        strLocalConfigFile = strLocalConfigFile.replaceAll("file:", ""); // 服务器上遇到的问题
        propertiesUtil = new PropertiesUtil(strLocalConfigFile);
    }

    public String getProperty(String strKey) {
        String strReturn = "";

        try {
            strReturn = propertiesUtil.get(strKey);
        } catch (Exception var4) {
            System.err.println("读取属性" + strKey + "出现错误,错误信息:" + var4.getMessage());
        }

        return strReturn;
    }

    public void setProperty(String strKey, String strValue) {
        propertiesUtil.update(strKey, strValue);
    }

    public String getFilter_ldap() {
        return this.getProperty(strConfigNames[4]);
    }

    public String getUser_ldap() {
        return this.getProperty(strConfigNames[3]);
    }

    public String getUrl_ldap() {
        return this.getProperty(strConfigNames[2]);
    }

    public String getPawd_ldap() {
        return this.getProperty(strConfigNames[1]);
    }

    public String getBaseDn_ldap() {
        return this.getProperty(strConfigNames[0]);
    }

    public void setBaseDn_ldap(String baseDn) {
        this.setProperty(strConfigNames[0], baseDn);
    }

    public String getLdapType() {
        return this.getProperty(strConfigNames[5]);
    }

    public int getPageShowCount() {
        int intReturn = 10;

        try {
            intReturn = Integer.parseInt(this.getProperty(strConfigNames[6]));
        } catch (Exception var3) {
        }

        return intReturn;
    }

    public void setPageShowCount(String strPageShowCount) {
        this.setProperty(strConfigNames[6], strPageShowCount);
    }

    public String getSysusername() {
        return this.getProperty(strConfigNames[7]);
    }

    public void setSysusername(String strSysusername) {
        this.setProperty(strConfigNames[7], strSysusername);
    }

    public String getSyspassword() {
        return this.getProperty(strConfigNames[8]);
    }

    public void setSyspassword(String strSyspassword) {
        this.setProperty(strConfigNames[8], strSyspassword);
    }

    public String getTempdir() {
        return this.getProperty(strConfigNames[9]);
    }

    public void setTempdir(String strTempdir) {
        this.setProperty(strConfigNames[9], strTempdir);
    }

    public String getArchPath() {
        return this.getProperty(strConfigNames[10]);
    }

    public void setArchPath(String strArchPath) {
        this.setProperty(strConfigNames[10], strArchPath);
    }

    public String getArchIndexPath() {
        return this.getProperty(strConfigNames[11]);
    }

    public void setArchIndexPath(String strArchInexPath) {
        this.setProperty(strConfigNames[11], strArchInexPath);
    }

    public String getTxtArchPath() {
        return this.getProperty(strConfigNames[12]);
    }

    public void setTxtArchPath(String strTxtArchPath) {
        this.setProperty(strConfigNames[12], strTxtArchPath);
    }

    public static void main(String[] args) {
        SystemConfig fc = new SystemConfig();
        fc.setBaseDn_ldap("ou=系统根元素");
//        fc.setProperty("PageShowCount", "10");
//        fc.setArchPath("C:/Test/luceneindex");
//        fc.setArchIndexPath("C:/Test/luceneindex");
//        System.out.println(fc.storeProperty());
//        System.out.println(fc.getArchPath());
//        System.out.println(fc.getArchIndexPath());
    }
}
