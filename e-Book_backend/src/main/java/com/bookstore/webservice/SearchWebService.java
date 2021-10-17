package com.bookstore.webservice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.entity.Book;
import org.apache.lucene.queryparser.classic.ParseException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.util.List;

@WebService
public interface SearchWebService {
    public String findBookByDescription(@WebParam(name="description") String description) throws IOException, ParseException;
}
