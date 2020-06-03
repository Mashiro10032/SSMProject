package cn.web.auction.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.auction.mapper.UserMapper;
import cn.web.auction.pojo.User;
import cn.web.auction.pojo.UserExample;
import cn.web.auction.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	//select * from auctionuser where userName=?and userPassword=?
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User login(String username, String userpassword) {
		//初始化Example
		UserExample example = new UserExample();
		//调用example
		// TODO Auto-generated method stub
		return null;
	}

}
