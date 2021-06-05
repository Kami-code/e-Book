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
package com.bookstore.controller;

import com.bookstore.repository.BookRepository;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class BookController {
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BookService bookService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/books")
	public @ResponseBody List<Book> getAllBooks() throws Exception {
		return bookService.getBooks();
	}
	@RequestMapping(value = "/book/inventory/{bookid}", method = RequestMethod.POST)
	public @ResponseBody Book changeInventory(@PathVariable("bookid") Long book_id, @RequestParam("inventory") int inventory) throws Exception {
		return bookService.changeInventory(book_id, inventory);
	}
	@RequestMapping(value = "/book/price/{bookid}", method = RequestMethod.POST)
	public @ResponseBody Book changePrice(@PathVariable("bookid") Long book_id, @RequestParam("price") BigDecimal price) throws Exception {
		Book local_book = bookRepo.getBookById(book_id);
		BigDecimal threshold = new BigDecimal("0.0");
		if (local_book == null || price.compareTo(threshold) < 0) {
			return null;
		}
		local_book.setPrice(price);
		local_book = bookRepo.save(local_book);
		return local_book;
	}
}
