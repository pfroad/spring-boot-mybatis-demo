package sample.web.ui.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sample.web.ui.user.entity.User;

@Mapper
public interface UserMapper {
	@Select("select * from user where username = #{username}")
	User findByUsername(@Param("username") String username);
}
