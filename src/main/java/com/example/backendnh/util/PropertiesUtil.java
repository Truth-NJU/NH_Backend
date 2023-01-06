package com.example.backendnh.util;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesUtil {

    public static final Properties properties = new Properties();
    public static final String path = "configoracle.properties";

    public static void init() {
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        try {
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定的value值
     *
     * @param key 要删除的属性key
     * @author liguangni
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * 新增或者修改配置文件（如果指定的key存在，则修改，否则执行新增）
     *
     * @param key 要删除的属性key
     * @author liguangni
     */
    public static void update(String key, String value,String path_tmp) {
        properties.setProperty(key, value);
        try (
                OutputStreamWriter output = new OutputStreamWriter(Files.newOutputStream(Paths.get(path_tmp)), StandardCharsets.UTF_8);
        ) {
            properties.store(output, "");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定的属性值
     *
     * @param key 要删除的属性key
     * @author liguangni
     */
    public static void delete(String key) {
        properties.remove(key);
        try (
                FileOutputStream oFile = new FileOutputStream(path);
        ) {
            properties.store(oFile, "");
            oFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的键值对
     *
     * @author liguangni
     */
    public static void list() {
        Enumeration en = properties.propertyNames(); // 得到配置文件的名字
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = properties.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }
}