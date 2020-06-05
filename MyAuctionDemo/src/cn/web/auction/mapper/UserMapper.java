package cn.web.auction.mapper;

import cn.web.auction.pojo.User;
import cn.web.auction.pojo.userExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(userExample example);

    int deleteByExample(userExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(userExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") userExample example);

    int updateByExample(@Param("record") User record, @Param("example") userExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}