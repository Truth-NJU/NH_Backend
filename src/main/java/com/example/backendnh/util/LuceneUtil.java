package com.example.backendnh.util;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.example.backendnh.bean.Doc;
import com.example.backendnh.bean.DocModel;
import com.example.backendnh.bean.MapModel;
import com.example.backendnh.po.Nharchives;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LuceneUtil {
    public static SystemConfig systemConfig = new SystemConfig();
    public static String indexFile;
    private static LuceneUtil lUtil;
    private static Directory indexDirectory;
    private static File docDir;
    private static Analyzer analyzer;

    static {
        indexFile = systemConfig.getArchIndexPath();
        lUtil = null;
        analyzer = new ComplexAnalyzer();
    }

    private LuceneUtil() {
    }

    private LuceneUtil(String fileUrl) throws Exception {
        docDir = new File(fileUrl);
        if (!docDir.exists() || !docDir.canRead()) {
            throw new Exception("yichang[" + docDir.getAbsolutePath() + "]yichang");
        }
    }

    public static LuceneUtil getInstance() {
        if (lUtil == null) {
            try {
                indexDirectory = FSDirectory.open(Paths.get(indexFile));
//                indexDirectory = FSDirectory.open(new File(indexFile));
                lUtil = new LuceneUtil();
            } catch (Exception var1) {
                var1.printStackTrace();
            }
        }

        return lUtil;
    }

    public static LuceneUtil getInstance(String fileUrl) {
        if (lUtil == null) {
            try {
//                indexDirectory = FSDirectory.open(new File(indexFile));
                indexDirectory = FSDirectory.open(Paths.get(indexFile));
                lUtil = new LuceneUtil(fileUrl);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return lUtil;
    }

    public void index(Nharchives nharchives, String fileUrl) throws Exception {
        File file = new File(fileUrl);
        lUtil.makeIndex(nharchives, file);
    }

    public DocModel search(String keyword, int currPage, int pageSize) throws Exception {
        return lUtil.findDocs_new(keyword, currPage, pageSize, "0");
    }

    public DocModel search(String keyword) throws Exception {
        return lUtil.findDocs_new(keyword, "0");
    }

    public DocModel metadataSearch(int currPage, int pageSize, List<MapModel> mapList) throws Exception {
        if (lUtil == null) {
            lUtil = new LuceneUtil(indexFile);
        }

        return lUtil.findDocsByMeta(currPage, pageSize, mapList);
    }

    public void makeIndex(Nharchives nharchives, File fHtmlFile) throws Exception {
        try {
//            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            if (this.isIndexExist()) {
                iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            } else {
                iwc.setOpenMode(OpenMode.CREATE);
            }

            IndexWriter writer = new IndexWriter(indexDirectory, iwc);
            this.indexDocs(nharchives, writer, fHtmlFile);
            writer.close();
        } catch (Exception var5) {
            System.out.println("创建\"" + nharchives.getDamc() + "\"索引异常!");
            throw var5;
        }
    }

    public void delIndex(String strDAMC) throws Exception {
        try {
//            IndexWriter writer = new IndexWriter(indexDirectory, new IndexWriterConfig(Version.LUCENE_4_9, analyzer));
            IndexWriter writer = new IndexWriter(indexDirectory, new IndexWriterConfig(analyzer));
            writer.deleteDocuments(new Term[]{new Term("damc", strDAMC)});
            writer.close();
        } catch (Exception var3) {
            System.out.println("删除\"" + strDAMC + "\"索引异常!");
            throw var3;
        }
    }

    public void indexDocs(Nharchives nharchives, IndexWriter writer, File file) throws Exception {
        if (file.canRead() && nharchives != null) {
            FileInputStream fis;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException var15) {
                var15.printStackTrace();
                return;
            }

            try {
                Document doc = new Document();
                doc.add(new StringField("path", file.getPath(), Store.YES));
                doc.add(new StringField("bh", nharchives.getBh(), Store.YES));
                doc.add(new StringField("wb", nharchives.getWb(), Store.YES));
                doc.add(new StringField("flh", nharchives.getFlh(), Store.YES));
                doc.add(new StringField("csrq", nharchives.getCsrq(), Store.YES));
                doc.add(new StringField("damc", nharchives.getDamc(), Store.YES));
                doc.add(new LongPoint("modified", file.lastModified()));
                doc.add(new TextField("contents", FileUtil.getFileString(file), Store.YES));
                if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                    writer.addDocument(doc);
                } else {
                    writer.updateDocument(new Term("path", file.getPath()), doc);
                }

                writer.commit();
            } catch (Exception var14) {
                throw var14;
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception var13) {
                        throw var13;
                    }
                }

            }
        }

    }

    public DocModel findDocs(String keyword, int currPage, int pageSize, String flag) throws Exception {
        int start = (currPage - 1) * pageSize;
        int findCnt = start + pageSize;
        IndexReader reader = null;
        IndexSearcher searcher = null;
        DocModel docModel = new DocModel();
        List<Doc> docList = new ArrayList();
        Doc docVo = null;

        try {
            reader = DirectoryReader.open(indexDirectory);
            searcher = new IndexSearcher(reader);
            MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{"path", "bh", "wb", "flh", "csrq", "damc", "modified", "contents"}, analyzer);
//            MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_4_9,new String[]{"path", "bh", "wb", "flh", "csrq", "damc", "modified", "contents"}, analyzer);
            parser.setDefaultOperator(MultiFieldQueryParser.AND_OPERATOR);
            BooleanQuery query = null;
            TermQuery query1;
            if (flag.equals("0")) {
                if (keyword.contains(" >> ")) {
                    String[] a = keyword.split(" >> ");
                    if (a.length > 0) {
//                        query=new BooleanQuery();
                        BooleanQuery.Builder builder = new BooleanQuery.Builder();
                        for (int i = 0; i < a.length; ++i) {
//                            query.add(new TermQuery(new Term("contents", a[i])), Occur.MUST);
                            BooleanClause bc = new BooleanClause(new TermQuery(new Term("contents", a[i])), Occur.MUST);
                            builder.add(bc);
                        }
                        query = builder.build();
                    }
                } else {
                    query1 = new TermQuery(new Term("contents", keyword));
                    BooleanQuery.Builder builder = new BooleanQuery.Builder();
                    builder.add(new BooleanClause(query1, Occur.MUST));
                    query = builder.build();
//                    query = new BooleanQuery();
//                    query.add(query1, Occur.MUST);
                }
            } else {
                TermQuery query2;
                if (flag.equals("ARCH")) {
                    query1 = new TermQuery(new Term("contents", keyword));
                    query2 = new TermQuery(new Term("type", "ARCH"));
                    BooleanQuery.Builder builder = new BooleanQuery.Builder();
                    builder.add(new BooleanClause(query1, Occur.MUST));
                    builder.add(new BooleanClause(query2, Occur.MUST));
                    query = builder.build();
//                    query = new BooleanQuery();
//                    query.add(query1, Occur.MUST);
//                    query.add(query2, Occur.MUST);
                } else if (flag.equals("EDOC")) {
                    query1 = new TermQuery(new Term("contents", keyword));
                    query2 = new TermQuery(new Term("type", "EDOC"));
                    BooleanQuery.Builder builder = new BooleanQuery.Builder();
                    builder.add(new BooleanClause(query1, Occur.MUST));
                    builder.add(new BooleanClause(query2, Occur.MUST));
                    query = builder.build();
//                    query = new BooleanQuery();
//                    query.add(query1, Occur.MUST);
//                    query.add(query2, Occur.MUST);
                } else {
                    WildcardQuery query3 = new WildcardQuery(new Term("contents", "*" + keyword + "*"));
                    BooleanQuery.Builder builder = new BooleanQuery.Builder();
                    builder.add(new BooleanClause(query3, Occur.MUST));
                    query = builder.build();
//                    query = new BooleanQuery();
//                    query.add(query3, Occur.MUST);
                }
            }

            TopDocs tds = searcher.search(query, findCnt, new Sort(new SortField("modified", Type.LONG, false)));
            System.out.println("" + tds.totalHits);
            docModel.setCount((int) tds.totalHits.value);
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
            highlighter.setTextFragmenter(new SimpleFragmenter(200));
            ScoreDoc[] hits = this.loadDocs(tds, start, pageSize);
            Document hitDoc = null;
            String fileContent = null;

            for (int i = 0; i < hits.length; ++i) {
                hitDoc = reader.document(hits[i].doc);
                docVo = new Doc();
                docVo.setPath(URLEncoder.encode(hitDoc.get("path")));
                docVo.setModified(StrUtil.str2Long(hitDoc.get("modified")));
                docVo.setBh(hitDoc.get("bh"));
                docVo.setCsrq(hitDoc.get("csrq"));
                docVo.setDamc(hitDoc.get("damc"));
                docVo.setFlh(hitDoc.get("flh"));
                docVo.setWb(hitDoc.get("wb"));
                fileContent = FileUtil.readFile(hitDoc.get("path"), 0);
                fileContent = fileContent.replaceAll(System.getProperty("line.separator"), " ");
                fileContent = fileContent.replaceAll("\t", " ");
                fileContent = fileContent.replaceAll("\f", " ");
                fileContent = fileContent.replaceAll("<", "&lt;");
                fileContent = fileContent.replaceAll(">", "&gt;");
                fileContent = fileContent.replaceAll("\"", "&quot;");
                fileContent = fileContent.replaceAll("'", "&apos;");
                docList.add(docVo);
            }

            docModel.setDocList(docList);
            DocModel var22 = docModel;
            return var22;
        } catch (Exception var29) {
            var29.printStackTrace();
            throw var29;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception var28) {
                throw var28;
            }

        }
    }

    public DocModel findDocs_new(String keyword, int currPage, int pageSize, String flag) throws Exception {
        lUtil = getInstance();
        DocModel docModel = new DocModel();
        List<Doc> docList = new ArrayList();
        Doc docVo = null;
        IndexReader reader = null;
        Query query = null;
        IndexSearcher indexSearcher = null;

        try {
            reader = DirectoryReader.open(indexDirectory);
            indexSearcher = new IndexSearcher(reader);
            String[] stringQuery = keyword.split(" ");
            String[] fields = new String[stringQuery.length];

            for (int i = 0; i < stringQuery.length; ++i) {
                fields[i] = "contents";
            }

            BooleanClause.Occur[] occ = new BooleanClause.Occur[stringQuery.length];

            for (int i = 0; i < stringQuery.length; ++i) {
                occ[i] = Occur.MUST;
            }

//            query = MultiFieldQueryParser.parse(Version.LUCENE_4_9,stringQuery, fields, occ, analyzer);
            query = MultiFieldQueryParser.parse(stringQuery, fields, occ, analyzer);
            TopDocs topDocs = indexSearcher.search(query, 100000);
            // todo 是否需要改为long
            int totalCount = (int) topDocs.totalHits.value;
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("共检索出 " + totalCount + " 个资料!");
            int begin = pageSize * (currPage - 1);
            int end = Math.min(begin + pageSize, scoreDocs.length);

            int iAllPage;
            for (iAllPage = begin; iAllPage < end; ++iAllPage) {
                int docID = scoreDocs[iAllPage].doc;
                Document doc = indexSearcher.doc(docID);
                docVo = new Doc();
                docVo.setPath(URLEncoder.encode(doc.get("path")));
                docVo.setModified(StrUtil.str2Long(doc.get("modified")));
                docVo.setBh(doc.get("bh"));
                docVo.setCsrq(doc.get("csrq"));
                docVo.setDamc(doc.get("damc"));
                docVo.setFlh(doc.get("flh"));
                docVo.setWb(doc.get("wb"));
                docList.add(docVo);
            }

            if (totalCount > 0) {
                if (totalCount % pageSize == 0) {
                    iAllPage = totalCount % pageSize;
                } else {
                    iAllPage = totalCount % pageSize + 1;
                }

                docModel.setiAllCount(totalCount);
                docModel.setiAllPage(iAllPage);
                docModel.setiCurrPage(currPage);
            }

            docModel.setDocList(docList);
            DocModel var23 = docModel;
            return var23;
        } catch (Exception var31) {
            var31.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception var30) {
                throw var30;
            }

        }

        return null;
    }

    public DocModel findDocs_new(String keyword, String flag) throws Exception {
        lUtil = getInstance();
        DocModel docModel = new DocModel();
        List<Doc> docList = new ArrayList();
        Doc docVo = null;
        IndexReader reader = null;
        Query query = null;
        IndexSearcher indexSearcher = null;

        try {
            reader = DirectoryReader.open(indexDirectory);
            indexSearcher = new IndexSearcher(reader);
            String[] stringQuery = keyword.split(" ");
            String[] fields = new String[stringQuery.length];

            for (int i = 0; i < stringQuery.length; ++i) {
                fields[i] = "contents";
            }

            BooleanClause.Occur[] occ = new BooleanClause.Occur[stringQuery.length];

            for (int i = 0; i < stringQuery.length; ++i) {
                occ[i] = Occur.MUST;
            }

//            query = MultiFieldQueryParser.parse(Version.LUCENE_4_9,stringQuery, fields, occ, analyzer);
            query = MultiFieldQueryParser.parse(stringQuery, fields, occ, analyzer);
            TopDocs topDocs = indexSearcher.search(query, 100000);
            // todo 是否需要改为long
            int totalCount = (int) topDocs.totalHits.value;
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("共检索出 " + totalCount + " 个资料!");

            for (int i = 0; i < scoreDocs.length; ++i) {
                int docID = scoreDocs[i].doc;
                Document doc = indexSearcher.doc(docID);
                docVo = new Doc();
                docVo.setPath(URLEncoder.encode(doc.get("path")));
                docVo.setModified(StrUtil.str2Long(doc.get("modified")));
                docVo.setBh(doc.get("bh"));
                docVo.setCsrq(doc.get("csrq"));
                docVo.setDamc(doc.get("damc"));
                docVo.setFlh(doc.get("flh"));
                docVo.setWb(doc.get("wb"));
                docList.add(docVo);
            }


            docModel.setDocList(docList);
            DocModel var23 = docModel;
            return var23;
        } catch (Exception var31) {
            var31.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception var30) {
                throw var30;
            }

        }

        return null;
    }

    public boolean isIndexExist() {
        File file = new File(indexFile);
        if (!file.exists()) {
            file.mkdirs();
        }

        String indexSufix = "/segments.gen";
        File index = new File(indexFile + indexSufix);
        return index.exists();
    }

    public ScoreDoc[] loadDocs(TopDocs docs, int start, int pageSize) {
        int sdLen = docs.scoreDocs.length;
        if (start >= sdLen) {
            return new ScoreDoc[0];
        } else {
            ScoreDoc[] sdArr = new ScoreDoc[sdLen - start];

            for (int i = 0; i < sdArr.length; ++i) {
                sdArr[i] = docs.scoreDocs[start + i];
            }

            return sdArr;
        }
    }

    public DocModel findDocsByMeta(int currPage, int pageSize, List<MapModel> mapList) throws Exception {
        int start = (currPage - 1) * pageSize;
        int findCnt = start + pageSize;
        IndexReader reader = null;
        IndexSearcher searcher = null;
        DocModel docModel = new DocModel();
        List<Doc> docList = new ArrayList();
        Doc docVo = null;

        try {
            String[] str = new String[]{"type", "fileType", "path", "modified", "title", "contents"};
            int StringLength = str.length + mapList.size();
            String[] str1 = new String[StringLength];

            int i;
            for (i = 0; i < str.length; ++i) {
                str1[i] = str[i];
            }

            if (mapList != null && mapList.size() > 0) {
                for (i = 0; i < mapList.size(); ++i) {
                    str1[i + 6] = ((MapModel) mapList.get(i)).getKey();
                }
            }

            reader = DirectoryReader.open(indexDirectory);
            searcher = new IndexSearcher(reader);
//            MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_4_9,str1, analyzer);
            MultiFieldQueryParser parser = new MultiFieldQueryParser(str1, analyzer);
            parser.setDefaultOperator(MultiFieldQueryParser.AND_OPERATOR);
            BooleanQuery query = null;
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            if (mapList != null && mapList.size() > 0) {
                for (int m = 0; m < mapList.size(); ++m) {
                    builder.add(new TermQuery(new Term(((MapModel) mapList.get(m)).getKey(), ((MapModel) mapList.get(m)).getValue())), Occur.MUST);
                }
            }
            query = builder.build();
            TopDocs tds = searcher.search(query, findCnt, new Sort(new SortField("modified", Type.LONG, false)));
            System.out.println("" + tds.totalHits);
            // todo 是否需要改为long
            docModel.setCount((int) tds.totalHits.value);
            ScoreDoc[] hits = this.loadDocs(tds, start, pageSize);
            Document hitDoc = null;
            String fileContent = null;

            for (int m = 0; m < hits.length; ++m) {
                hitDoc = reader.document(hits[m].doc);
                docVo = new Doc();
                docVo.setPath(URLEncoder.encode(hitDoc.get("path")));
                docVo.setModified(StrUtil.str2Long(hitDoc.get("modified")));
                docVo.setBh(hitDoc.get("bh"));
                docVo.setCsrq(hitDoc.get("csrq"));
                docVo.setDamc(hitDoc.get("damc"));
                docVo.setFlh(hitDoc.get("flh"));
                docVo.setWb(hitDoc.get("wb"));
                fileContent = FileUtil.readFile(hitDoc.get("path"), 0);
                fileContent = fileContent.replaceAll(System.getProperty("line.separator"), " ");
                fileContent = fileContent.replaceAll("\t", " ");
                fileContent = fileContent.replaceAll("\f", " ");
                fileContent = fileContent.replaceAll("<", "&lt;");
                fileContent = fileContent.replaceAll(">", "&gt;");
                fileContent = fileContent.replaceAll("\"", "&quot;");
                fileContent = fileContent.replaceAll("'", "&apos;");
                docList.add(docVo);
            }

            docModel.setDocList(docList);
            DocModel var22 = docModel;
            return var22;
        } catch (Exception var29) {
            throw var29;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception var28) {
                throw var28;
            }

        }
    }

    public static void main(String[] args) {
        try {
            getInstance().delIndex("蒋介石七月四日工作记录");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
