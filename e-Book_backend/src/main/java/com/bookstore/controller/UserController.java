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

import com.bookstore.dto.DataPage;
import com.bookstore.dto.UserDto;
import com.bookstore.repository.UserRepository;
import com.bookstore.entity.User;
import com.bookstore.response.LogInResponse;
import com.bookstore.response.SignUpResponse;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
	@Autowired
	private UserService userService;


	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public @ResponseBody
	SignUpResponse SignUp(@RequestParam("account") String username, @RequestParam("password") String password, @RequestParam("telnum") String telnum, @RequestParam("email") String email) throws Exception {
		SignUpResponse response = new SignUpResponse();
		try {
			UserDto userDto = userService.SignUp(username, password, telnum, email);
			response.setSuccess(userDto);
		}
		catch (Exception e) {
			response.setFail(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public @ResponseBody
	LogInResponse  LogIn(@RequestParam("account") String username, @RequestParam("password") String password) throws Exception {
		LogInResponse response = new LogInResponse();
		try {
			UserDto userDto = userService.LogIn(username, password);
			response.setSuccess(userDto);
		}
		catch (Exception e) {
			response.setFail(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/user/get/all", method = RequestMethod.GET)
	public @ResponseBody
	List<UserDto> GetAll() throws Exception {
		List<UserDto> userDto = userService.GetAll();
		return userDto;
	}

	@RequestMapping(value = "/user/{userid}/status", method = RequestMethod.POST)
	public @ResponseBody
	UserDto ChangeBlock(@PathVariable("userid") Long userid, @RequestParam("status") int status) throws Exception {
		UserDto userDto = userService.ChangeBlockedStatus(userid, status);
		return userDto;
	}

	@RequestMapping(value = "/user/page/{pageNumber}", method = RequestMethod.GET)
	public @ResponseBody
	DataPage<User> GetUserPageByNumber(@PathVariable("pageNumber") int pageNumber) throws Exception {
		DataPage<User> userPage = userService.GetUserPage(pageNumber - 1);
		return userPage;
	}

}
