package cn.web.auction.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.web.auction.service.UserService;
import cn.web.auction.mapper.UserMapper;
import cn.web.auction.pojo.User;
import cn.web.auction.pojo.userExample;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public User login(String username, String userpassword) {
		// TODO Auto-generated method stub
		//初始化example
		userExample example = new userExample();
		//调用example内部的criteria类来拼接并且初始化criteria
		userExample.Criteria criteria = example.createCriteria();
		//利用criteria拼接
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(userpassword);
		List<User> list = userMapper.selectByExample(example);
		if(!list.isEmpty()) {  //登录成功
			return list.get(0);
		}
		//登录失败
		return null;
	}

}
