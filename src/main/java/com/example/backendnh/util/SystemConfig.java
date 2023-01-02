package com.example.backendnh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Properties;

// todo 解决乱码问题
public class SystemConfig {
    public static final String strLocalConfigDescrible = "配置文件";
    public static String strLocalConfigFile = null;
    private Properties prop = null;
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
//        String strTmp = this.getClass().getResource("SystemConfig.class").getPath();
//
//        try {
//            strTmp = URLDecoder.decode(strTmp, "utf-8");
//        } catch (UnsupportedEncodingException var5) {
//            var5.printStackTrace();
//        }
//
//        System.out.println(strTmp);
//        String strClassPath = "/backendnh/util/SystemConfig";
//        strTmp = strTmp.substring(0, strTmp.indexOf(strClassPath));
//
//        strLocalConfigFile = strTmp + "/configoracle.properties";
        strLocalConfigFile = "/Users/taozehua/Downloads/研究任务/南海系统/NH_System/backend-NH/src/main/java/com/example/backendnh/util/configoracle.properties";
//        System.out.println("配置文件绝对路径：" + strLocalConfigFile);
        File configfile = new File(strLocalConfigFile);
        this.prop = new Properties();

        try {
            if (configfile.exists() && configfile.isFile()) {
                this.prop.load(new FileInputStream(configfile));
            } else {
                for (int i = 0; i < strConfigNames.length; ++i) {
                    this.prop.setProperty(strConfigNames[i], "");
                }

                this.prop.store(new FileOutputStream(configfile), "配置文件");
            }
        } catch (Exception var6) {
            System.err.println("读取本地配置文件出现错误,请查看文件是否存在:" + strLocalConfigFile + ".\n错误信息:" + var6.getMessage());
        }
    }

    public String getProperty(String strKey) {
        String strReturn = "";

        try {
            strReturn = new String(this.prop.getProperty(strKey).getBytes("ISO8859_1"), "GB2312");
        } catch (Exception var4) {
            System.err.println("读取属性" + strKey + "出现错误,错误信息:" + var4.getMessage());
        }

        return strReturn;
    }

    public void setProperty(String strKey, String strValue) {
        this.prop.setProperty(strKey, strValue);
    }

    public boolean storeProperty() {
        boolean blreturn = false;
        if (strLocalConfigFile != null) {
            try {
                File file = new File(strLocalConfigFile);
                FileOutputStream fos = new FileOutputStream(file);
                String strProperties = "#配置文件#\n";
                Enumeration elements = this.prop.keys();

                for (int i = 0; i < this.prop.size(); ++i) {
                    String strkeytemp = (String) elements.nextElement();
                    strProperties = strProperties + strkeytemp + "=" + new String(this.prop.getProperty(strkeytemp).getBytes("ISO8859-1"), "GB2312") + "\n";
                }

                fos.write(strProperties.getBytes());
                blreturn = true;
            } catch (Exception var8) {
                System.err.println("设置属性出现错误,错误信息:" + var8.getMessage());
            }
        }

        return blreturn;
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
        System.out.println(fc.getArchIndexPath());
//        fc.setProperty("PageShowCount", "10");
//        fc.setArchPath("C:/Test/luceneindex");
//        fc.setArchIndexPath("C:/Test/luceneindex");
//        System.out.println(fc.storeProperty());
//        System.out.println(fc.getArchPath());
//        System.out.println(fc.getArchIndexPath());
    }
}
