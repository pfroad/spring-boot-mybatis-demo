package sample.web.ui.base;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.ibatis.jdbc.SQL;

import sample.web.ui.annotations.Column;
import sample.web.ui.annotations.PrimaryKey;
import sample.web.ui.annotations.Table;

abstract public class AbstractSqlProvider<T, PK extends Serializable> {
	abstract public Class<T> getEntityClass();
	
	public String getTableName() {
		return getEntityClass().getAnnotation(Table.class).tableName();
	}
	
	public String getPrimaryKey() {
		Field[] fields = getEntityClass().getDeclaredFields();
		
		if (fields != null) {
			for (Field field : fields) {
				if (field.getAnnotation(PrimaryKey.class) != null) {
					return field.getName();
				}
			}
		}
		
		return "id";
	}
	
	/**
	 * get object by primary key sql
	 * @param id
	 * @return
	 */
	public String getSql(final PK id) {
		return new SQL(){ {
			SELECT("*");
			FROM(getTableName());
			WHERE(getPrimaryKey() + " = #{id}");
		} }.toString();
	}
	
	public String insertSql(final T t) throws Exception {
		return new SQL() { {
			INSERT_INTO(getTableName());
			
			Field[] fields = getEntityClass().getDeclaredFields();
			
			if (fields != null) {
				for (Field field : fields) {
					field.setAccessible(true);
					
					Column column = field.getAnnotation(Column.class);
					
					if (field.get(t) == null) {
						if (!column.nullable()) {
							throw new Exception(column.columnName() + " cannot be nullable!");
						} else {
							continue;
						}
					}
					
					VALUES(column == null ? field.getName() : column.columnName(), "#{" + field.getName() + "}");
				}
			}
		} }.toString();
	}
	
	public String updateSql(final T t) throws IllegalAccessException {
		return new SQL() { {
			UPDATE(getTableName());
			Field[] fields = getEntityClass().getDeclaredFields();
			
			String primaryKey = null;
			
			if (fields != null) {
				for (Field field : fields) {
					field.setAccessible(true);
					
					if (field.get(t) == null) continue;
					
					if (field.getAnnotation(PrimaryKey.class) != null) {
						primaryKey = field.getName();
						continue;
					}
					
					Column column = field.getAnnotation(Column.class);
					
					if (!column.updatable()) continue;
					
					SET(column + " = #{" + field.getName() + "}");
				}
			}
			WHERE(primaryKey + " = #{id}");
		} }.toString();
	}
	
	/**
	 * delete by primary key
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 */
	public String deleteSql(final PK id) throws IllegalAccessException {
		return new SQL() { {
			DELETE_FROM(getTableName());
			WHERE(getPrimaryKey() + " = #{id}");
		} }.toString();
	}
	
	/**
	 * delete by primary key
	 * table must have column that named is_deleted
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 */
	public String logicDeleteSql(final PK id) throws IllegalAccessException {
		return new SQL() { {
			UPDATE(getTableName());
			SET("is_deleted = true");
			WHERE(getPrimaryKey() + " = #{id}");
		} }.toString();
	}
	
}
