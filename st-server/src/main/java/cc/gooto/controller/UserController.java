package cc.gooto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cc.gooto.base.ResultResponse;
import cc.gooto.entity.User;
import cc.gooto.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public ResultResponse<Object> login(@RequestBody User user, HttpSession session) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("user_name", user.getUserName());
		User one = userService.getOne(queryWrapper);
		if (one == null) {
			return ResultResponse.error("不存在该用户");
		}
		if (!user.getPassword().equals(one.getPassword())) {
			return ResultResponse.error("密码错误");
		}
		session.setAttribute("user", one);
		one.setPassword(null);
		return ResultResponse.success(one);
	}
}
