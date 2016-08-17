package sample.web.ui.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<User> findByUsername(@PathVariable("username") final String username) {
		return userMapper.findByUsername(username);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public int add(@RequestBody User user) {
		return userMapper.save(user);
	}
	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findOne(@PathVariable("id") final Long id) {
		return userMapper.get(id);
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<User> find(@RequestParam("username") final String username) {
		return userMapper.findByUsername(username);
	}
}