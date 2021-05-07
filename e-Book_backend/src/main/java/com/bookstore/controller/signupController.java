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

import com.bookstore.dao.UserRepo;
import com.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import com.alibaba.fastjson.JSONObject;


@RestController
public class signupController {
	@Autowired
	private UserRepo userRepo;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody User signUp(HttpServletRequest request) throws Exception {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		User user = new User(account, password, 1);
		userRepo.save(user);
		return user;
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody JSONObject  logIn(HttpServletRequest request) throws Exception {
		String username = request.getParameter("account");
		String password = request.getParameter("password");
		List<User> search_users = userRepo.findByUsernameAndPassword(username, password);
		JSONObject result = new JSONObject();
		if (search_users.size() == 0) {
			result.put("result", "fail");
			result.put("info", "Login failed! Please check username and password.");
		}
		else {
			result.put("result", "success");
			result.put("info", "Login success!");
			result.put("username", search_users.get(0).getUsername());
			result.put("user_type", search_users.get(0).getType());
		}
		return result;
	}

}
