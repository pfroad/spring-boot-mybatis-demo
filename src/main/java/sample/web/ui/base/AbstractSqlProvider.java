package sample.web.ui.base;

import java.lang.reflect.Field;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import sample.web.ui.annotations.Column;
import sample.web.ui.annotations.Table;

abstract public class AbstractSqlProvider<T> {
	abstract public Class<T> getEntityClass();
	
	public String save(@Param("entity") final T t) {
		return new SQL() { {
			INSERT_INTO(getTableName());
			
			Field[] fields = getEntityClass().getDeclaredFields();
			
			if (fields != null) {
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.get(t) == null) continue;
					
					Column column = field.getAnnotation(Column.class);
					VALUES(column == null ? field.getName() : column.columnName(), "#{entity." + field.getName() + "}");
				}
			}
		} }.toString();
	}
	
	public String getTableName() {
		return getEntityClass().getAnnotation(Table.class).tableName();
	}
	
	public String 
}
