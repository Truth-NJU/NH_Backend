package com.example.backendnh.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.backendnh.po.Nharchives;
import com.example.backendnh.util.FileUtil;
import com.example.backendnh.util.SystemConfig;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelManager {
    public ExcelManager() {
    }

    public Nharchives[] getNharchFromExcel(InputStream excelis) {
        Nharchives[] nharchives = (Nharchives[])null;

        try {
            Workbook book = Workbook.getWorkbook(excelis);
            Sheet sheet = book.getSheet(0);
            int iRows = sheet.getRows();
            int iColumns = sheet.getColumns();
            System.out.println("iColumns:" + iColumns);
            Calendar ca = new GregorianCalendar();
            SystemConfig systemConfig = new SystemConfig();
            String strTempDir = systemConfig.getTempdir();
            String strFileDir = strTempDir + "/" + ca.get(1) + (ca.get(2) + 1);
            if (iRows > 1 && iColumns == 25) {
                nharchives = new Nharchives[iRows - 2];

                for(int i = 2; i < iRows; ++i) {
                    Cell[] cellTemp = sheet.getRow(i);
                    nharchives[i - 2] = new Nharchives();
                    nharchives[i - 2].setBh(cellTemp[0].getContents());
                    nharchives[i - 2].setCsrq(cellTemp[14].getContents());
                    nharchives[i - 2].setDamc(cellTemp[5].getContents());
                    nharchives[i - 2].setFlh("");
                    nharchives[i - 2].setFwjg(cellTemp[15].getContents());
                    nharchives[i - 2].setFwrq(cellTemp[12].getContents());
                    nharchives[i - 2].setGcdw(cellTemp[18].getContents());
                    nharchives[i - 2].setGjc(cellTemp[9].getContents());
                    nharchives[i - 2].setGjrw(cellTemp[11].getContents());
                    nharchives[i - 2].setHf(this.getIntegerValueFromExcel(cellTemp[20]));
                    nharchives[i - 2].setJm(cellTemp[6].getContents());
                    nharchives[i - 2].setSwjg(cellTemp[16].getContents());
                    nharchives[i - 2].setSwrq(cellTemp[13].getContents());
                    nharchives[i - 2].setSy(cellTemp[8].getContents());
                    nharchives[i - 2].setSzdajh(cellTemp[19].getContents());
                    nharchives[i - 2].setWb(cellTemp[2].getContents());
                    nharchives[i - 2].setXgdy(cellTemp[10].getContents());
                    nharchives[i - 2].setYslh(cellTemp[7].getContents());
                    nharchives[i - 2].setYwdt(cellTemp[23].getContents());
                    nharchives[i - 2].setZrh("");
                    nharchives[i - 2].setBclj(strFileDir);
                    System.out.println(nharchives[i - 2].getDamc());
                }
            }

            book.close();
            return nharchives;
        } catch (Exception var13) {
            var13.printStackTrace();
            return null;
        }
    }

    public Nharchives getNharchByArchName(String strArchDir, String strIndexFile, String strArchName) {
        Nharchives nharchive = null;
        SystemConfig systemConfig = new SystemConfig();
        File fIndexFile = new File(strIndexFile);
        InputStream excelis = null;

        try {
            if (fIndexFile != null && fIndexFile.isFile()) {
                excelis = new FileInputStream(fIndexFile);
                Workbook book = Workbook.getWorkbook(excelis);
                Sheet sheet = book.getSheet(0);
                int iRows = sheet.getRows();
                Cell[] cellMetaData = sheet.getRow(0);
                int iArchName = this.getColNumByName(cellMetaData, "????????????");

                for(int i = 0; i < iRows; ++i) {
                    Cell[] cellTemp = sheet.getRow(i);
                    if (cellTemp != null && iArchName < cellTemp.length && cellTemp[iArchName] != null && cellTemp[iArchName].getContents().trim().equals(strArchName)) {
                        nharchive = new Nharchives();
                        if (this.getColNumByName(cellMetaData, "??????") < cellTemp.length) {
                            nharchive.setBh(cellTemp[this.getColNumByName(cellMetaData, "??????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setCsrq(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setDamc(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "?????????") < cellTemp.length) {
                            nharchive.setFlh(cellTemp[this.getColNumByName(cellMetaData, "?????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setFwjg(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setFwrq(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setGcdw(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "?????????") < cellTemp.length) {
                            nharchive.setGjc(cellTemp[this.getColNumByName(cellMetaData, "?????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setGjrw(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "??????") < cellTemp.length) {
                            nharchive.setHf(this.getIntegerValueFromExcel(cellTemp[this.getColNumByName(cellMetaData, "??????")]));
                        }

                        if (this.getColNumByName(cellMetaData, "??????") < cellTemp.length) {
                            nharchive.setJm(cellTemp[this.getColNumByName(cellMetaData, "??????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setSwjg(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setSwrq(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "??????") < cellTemp.length) {
                            nharchive.setSy(cellTemp[this.getColNumByName(cellMetaData, "??????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "??????????????????") < cellTemp.length) {
                            nharchive.setSzdajh(cellTemp[this.getColNumByName(cellMetaData, "??????????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "??????") < cellTemp.length) {
                            nharchive.setWb(cellTemp[this.getColNumByName(cellMetaData, "??????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "??????") < cellTemp.length) {
                            nharchive.setXgdy(cellTemp[this.getColNumByName(cellMetaData, "??????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setYslh(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setYwdt(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "????????????") < cellTemp.length) {
                            nharchive.setWzjg(cellTemp[this.getColNumByName(cellMetaData, "????????????")].getContents());
                        }

                        if (this.getColNumByName(cellMetaData, "?????????") < cellTemp.length) {
                            nharchive.setZrh("");
                        }

                        // todo ???????????????,?????????toLowerCase
                        String strArch = FileUtil.getArchiveByDir(strArchDir);
                        if (strArch != null && strArch.length() > 0) {
                            nharchive.setBclj(FileUtil.getHtmlFileByArchFile(strArch));
                        } else {
                            String strArchPath = systemConfig.getArchPath();
                            String strArchTxtPath = systemConfig.getTxtArchPath();
                            String strHtmlPath = strArchDir + "/" + strArchName + ".html";
                            strHtmlPath = strHtmlPath.replaceAll(strArchPath, strArchTxtPath);
                            nharchive.setBclj(strHtmlPath);
                        }
                    }
                }

                book.close();
            }
        } catch (Exception var27) {
            var27.printStackTrace();
        } finally {
            if (excelis != null) {
                try {
                    excelis.close();
                } catch (IOException var26) {
                    var26.printStackTrace();
                }
            }

        }

        return nharchive;
    }

    private int getColNumByName(Cell[] cellMetaData, String strColName) {
        if (cellMetaData != null && cellMetaData.length > 0 && strColName != null && strColName.length() > 0) {
            for(int i = 0; i < cellMetaData.length; ++i) {
                if (cellMetaData[i] != null && cellMetaData[i].getContents() != null && strColName.equals(cellMetaData[i].getContents().trim())) {
                    return i;
                }
            }
        }

        return 0;
    }

    private Integer getIntegerValueFromExcel(Cell cell) {
        try {
            if (cell != null && cell.getContents().trim().length() > 0) {
                return new Integer(cell.getContents().trim());
            }
        } catch (Exception var3) {
        }

        return new Integer("0");
    }

    public boolean updateExcelNharch(Nharchives nharchives, String strIndexPathFile) {
        if (nharchives != null && strIndexPathFile != null && strIndexPathFile.length() > 0) {
            File fIndexPathFile = new File(strIndexPathFile);

            try {
                Cell[] cellMetaData = this.getExcelMetaData(strIndexPathFile);
                Workbook rwb = Workbook.getWorkbook(fIndexPathFile);
                Sheet ws = rwb.getSheet(0);
                int iRows = ws.getRows();
                int iArchName = this.getColNumByName(cellMetaData, "????????????");

                for(int i = 0; i < iRows; ++i) {
                    Cell[] cellTemp = ws.getRow(i);
                    if (cellTemp != null && iArchName < cellTemp.length && cellTemp[iArchName] != null && cellTemp[iArchName].getContents().trim().equals(nharchives.getDamc())) {
                        this.updateExcelCell(strIndexPathFile, nharchives.getBh(), this.getColNumByName(cellMetaData, "??????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getCsrq(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getFlh(), this.getColNumByName(cellMetaData, "?????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getFwjg(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getFwrq(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getGcdw(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getGjc(), this.getColNumByName(cellMetaData, "?????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getGjrw(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getHf().toString(), this.getColNumByName(cellMetaData, "??????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getJm(), this.getColNumByName(cellMetaData, "??????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getSwjg(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getSwrq(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getSy(), this.getColNumByName(cellMetaData, "??????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getSzdajh(), this.getColNumByName(cellMetaData, "??????????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getWb(), this.getColNumByName(cellMetaData, "??????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getXgdy(), this.getColNumByName(cellMetaData, "??????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getYslh(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getYwdt(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getWzjg(), this.getColNumByName(cellMetaData, "????????????"), i);
                        this.updateExcelCell(strIndexPathFile, nharchives.getZrh(), this.getColNumByName(cellMetaData, "?????????"), i);
                    }
                }

                return true;
            } catch (Exception var11) {
                var11.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public Cell[] getExcelMetaData(String strIndexPathFile) {
        File fIndexPathFile = new File(strIndexPathFile);
        if (strIndexPathFile != null && strIndexPathFile.length() > 0 && fIndexPathFile.exists() && fIndexPathFile.isFile()) {
            try {
                Workbook workbook = Workbook.getWorkbook(fIndexPathFile);
                Sheet ws = workbook.getSheet(0);
                int iRows = ws.getRows();
                Cell[] cellMetaData = ws.getRow(0);
                workbook.close();
                return cellMetaData;
            } catch (Exception var7) {
                var7.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void updateExcelCell(String strIndexPathFile, String strValue, int iCol, int iRow) {
        File fIndexPathFile = new File(strIndexPathFile);
        if (strIndexPathFile != null && strIndexPathFile.length() > 0 || !fIndexPathFile.exists() || !fIndexPathFile.isFile()) {
            try {
                Workbook rwb = Workbook.getWorkbook(fIndexPathFile);
                WritableWorkbook wwb = Workbook.createWorkbook(fIndexPathFile, rwb);
                WritableSheet ws = wwb.getSheet(0);
                WritableCell writableCell = ws.getWritableCell(iCol, iRow);
                System.out.print("(" + iCol + "," + iRow + ")");
                Label label;
                if (writableCell.getType() == CellType.EMPTY) {
                    System.out.print("EMPTY ");
                    label = new Label(iCol, iRow, strValue);
                    ws.addCell(label);
                } else if (writableCell.getType() == CellType.LABEL) {
                    System.out.print("LABEL ");
                    label = (Label)writableCell;
                    label.setString(strValue);
                } else if (writableCell.getType() == CellType.NUMBER) {
                    System.out.print("NUMBER ");
                    Number number = (Number)writableCell;

                    try {
                        number.setValue(Double.valueOf(strValue));
                    } catch (Exception var12) {
                        var12.printStackTrace();
                        number.setValue(0.0);
                    }
                }

                wwb.write();
                wwb.close();
                rwb.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        String strArchDir = "D:/Test/lucenefile/B01(1)?????????/????????????";
        String strIndex = "D:/Test/lucenefile/NH_EXCEL??????20150127??????.xls";
        ExcelManager em = new ExcelManager();
        Nharchives nha = em.getNharchByArchName(strArchDir, strIndex, "????????????");
        nha.setBh("ceshiceshi");
        nha.setWb("??????");
        nha.setCsrq("???????");
        em.updateExcelNharch(nha, strIndex);
    }
}
