package sample.web.ui.user.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import sample.web.ui.user.entity.User;
import sample.web.ui.user.provider.UserSqlProvider;

@Mapper
public interface UserMapper {
	@Select("select * from user where username = #{username}")
	User findByUsername(@Param("username") String username);
	
	@InsertProvider(type = UserSqlProvider.class, method = "insertSql")
	int save(User user);
	
	@SelectProvider(type = UserSqlProvider.class, method = "getSql")
	User get(Long id);
}
