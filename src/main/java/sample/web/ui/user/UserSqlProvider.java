package sample.web.ui.user;

import sample.web.ui.base.AbstractSqlProvider;
import sample.web.ui.user.entity.User;

public class UserSqlProvider extends AbstractSqlProvider<User> {
	@Override
	public Class getEntityClass() {
		return User.class;
	}
}
