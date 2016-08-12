package sample.web.ui.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.web.ui.user.entity.User;
import sample.web.ui.user.mapper.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value = "username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public User findByUsername(@PathVariable("username") final String username) {
		return userMapper.findByUsername(username);
	}
}
