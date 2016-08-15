package sample.web.ui.user.provider;

import sample.web.ui.base.AbstractSqlProvider;
import sample.web.ui.user.entity.User;

public class UserSqlProvider extends AbstractSqlProvider<User, Long> {
	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}
}
