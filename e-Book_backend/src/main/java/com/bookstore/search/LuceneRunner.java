package com.bookstore.search;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 项目启动后,立即执行
 * from: https://www.jianshu.com/p/e2324e5af705
 */
@Component
@Order(value = 1)
class LuceneRunner implements ApplicationRunner {
    @Autowired
    BookService bookService;

    public void createIndex() throws IOException {
        List<Book> books = bookService.getBooks();
        // 创建文档的集合
        Collection<Document> docs = new ArrayList<>();
        for(int i = 0; i < books.size(); i++){
            //contentMapper.insertSelective(list1.get(i));
            // 创建文档对象
            Document document1 = new Document();
            Book currentBook = books.get(i);
            System.out.println(currentBook);
            //StringField会创建索引，但是不会被分词，TextField，即创建索引又会被分词。
            document1.add(new StringField("id", String.valueOf(currentBook.getId()), Field.Store.YES));
            document1.add(new TextField("name", currentBook.getName(), Field.Store.YES));
            document1.add(new TextField("price", String.valueOf(currentBook.getPrice()), Field.Store.YES));
            document1.add(new TextField("description", currentBook.getDescription(), Field.Store.YES));
            docs.add(document1);
        }

        // 索引目录类,指定索引在硬盘中的位置，我的设置为D盘的indexDir文件夹
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("E:\\indexDir"));
        // 引入IK分词器
        Analyzer analyzer = new MyIKAnalyzer();
        // 索引写出工具的配置对象，这个地方就是最上面报错的问题解决方案
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        // 把文档集合交给IndexWriter
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        /**
         * 启动后将同步Product表,并创建index
         */
        createIndex();
    }
}
