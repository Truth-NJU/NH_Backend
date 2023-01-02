package com.example.backendnh.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

public class ArchHtmlUtil {
    public ArchHtmlUtil() {
    }

    public static String getArchTextFromHtml(String strHtmlPathFile) {
        String htmlContent = "";
        String strReturn = "";
        File file = new File(strHtmlPathFile);
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[128];
//            int iLength = true;

            int iLength;
            while((iLength = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, iLength);
            }

            htmlContent = baos.toString();
        } catch (Exception var22) {
            var22.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException var20) {
                var20.printStackTrace();
            }

            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException var19) {
                var19.printStackTrace();
            }

        }

        if (htmlContent == null) {
            htmlContent = "";
        }

        Parser parser = null;
        TextExtractingVisitor visitor = new TextExtractingVisitor();

        try {
            parser = new Parser(htmlContent);
            parser.visitAllNodesWith(visitor);
            strReturn = visitor.getExtractedText();
        } catch (ParserException var21) {
            strReturn = htmlContent;
        }

        return strReturn;
    }

    public static void main(String[] args) {
        getArchTextFromHtml("C:/Users/lzx/Desktop/1234.html");
    }
}
