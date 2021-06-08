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
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books")
	public @ResponseBody List<Book> getAllBooks() throws Exception {
		return bookService.getBooks();
	}
	@RequestMapping(value = "/book/{bookid}/inventory", method = RequestMethod.POST)
	public @ResponseBody Book changeInventory(@PathVariable("bookid") Long book_id, @RequestParam("inventory") int inventory) throws Exception {
		return bookService.changeInventory(book_id, inventory);
	}
	@RequestMapping(value = "/book/{bookid}/price", method = RequestMethod.POST)
	public @ResponseBody Book changePrice(@PathVariable("bookid") Long book_id, @RequestParam("price") BigDecimal price) throws Exception {
		return bookService.changePrice(book_id, price);
	}
	@RequestMapping(value = "/book/{bookid}/author", method = RequestMethod.POST)
	public @ResponseBody Book changeAuthor(@PathVariable("bookid") Long book_id, @RequestParam("author") String author) throws Exception {
		return bookService.changeAuthor(book_id, author);
	}
	@RequestMapping(value = "/book/{bookid}/description", method = RequestMethod.POST)
	public @ResponseBody Book changeDescription(@PathVariable("bookid") Long book_id, @RequestParam("description") String description) throws Exception {
		return bookService.changeDescription(book_id, description);
	}
	@RequestMapping(value = "/book/{bookid}/isbn", method = RequestMethod.POST)
	public @ResponseBody Book changeISBN(@PathVariable("bookid") Long book_id, @RequestParam("isbn") String isbn) throws Exception {
		return bookService.changeISBN(book_id, isbn);
	}
	@RequestMapping(value = "/book/{bookid}/name", method = RequestMethod.POST)
	public @ResponseBody Book changeName(@PathVariable("bookid") Long book_id, @RequestParam("name") String name) throws Exception {
		return bookService.changeName(book_id, name);
	}
	@RequestMapping(value = "/book/add", method = RequestMethod.POST)
	public @ResponseBody Book changeName(@RequestParam("name") String name,
										 @RequestParam("author") String author,
										 @RequestParam("image") String image,
										 @RequestParam("price") BigDecimal price,
										 @RequestParam("inventory") int inventory,
										 @RequestParam("isbn") String isbn,
										 @RequestParam("description") String description,
										 @RequestParam("type") String type) throws Exception {
		return bookService.addBook(name, author, price, inventory, description, type, image, isbn);
	}

	@RequestMapping(value = "/book/{bookId}/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("bookId") Long book_id) throws Exception {
		bookService.delete(book_id);
		return "success";
	}

}
