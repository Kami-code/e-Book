/*
 *
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.bookstore.search;

import com.bookstore.dto.DataPage;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// https://blog.csdn.net/lovely960823/article/details/110046111
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SearchController {
    @Autowired
    BookService bookService;

    @RequestMapping("/searchText/{name}")
    public List<Book> searchText(@PathVariable("name") String text) throws IOException,ParseException{
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("E:\\indexDir"));
        // ??????????????????
        IndexReader reader = DirectoryReader.open(directory);
        // ??????????????????
        IndexSearcher searcher = new IndexSearcher(reader);
        // ?????????????????????,????????????????????????????????????????????????????????????
        QueryParser parser = new QueryParser("description", new MyIKAnalyzer());
        // ??????????????????
        Query query = parser.parse(text);
        // ?????????????????????
        TopDocs topDocs = searcher.search(query, 10);
        // ???????????????
        System.out.println("?????????????????????" + topDocs.totalHits + "?????????");
        // ???????????????????????????ScoreDoc?????????.SocreDoc?????????????????????????????????????????????
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        List<Book> list = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            // ??????????????????
            int docID = scoreDoc.doc;
            // ????????????????????????
            Document doc = reader.document(docID);
            Book book = bookService.findBookById(Long.valueOf(doc.get("id")));
            list.add(book);
        }
        return list;
    }
}
