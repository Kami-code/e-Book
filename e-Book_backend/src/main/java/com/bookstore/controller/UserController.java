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

import com.bookstore.dto.UserDto;
import com.bookstore.repository.UserRepository;
import com.bookstore.entity.User;
import com.bookstore.response.LogInResponse;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;


@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody User signUp(HttpServletRequest request) throws Exception {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(account);
		user.setPassword(password);
		user.setUsertype(1);
		userRepository.save(user);
		return user;
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody LogInResponse  logIn(@RequestParam("account") String username, @RequestParam("password") String password) throws Exception {
		UserDto userDto = userService.LogIn(username, password);
		LogInResponse response = new LogInResponse();
		if (userDto == null) {
			return response.setFail();
		}
		else {
			return response.setSuccess(userDto);
		}
	}

}
