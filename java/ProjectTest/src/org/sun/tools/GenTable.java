/**
 *
 * Copyright © 2017, Forp Co., LTD
 *
 * All Rights Reserved.
 *
 */

package org.sun.tools;

/**
 * 代码生成表属性
 *
 * @author liShengJie
 * @version 2017/3/21 16:55
 */

public class GenTable {
	private String className;
	private String classComment;
	private String classAuthor;
	private String tableName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassComment() {
		return classComment;
	}

	public void setClassComment(String classComment) {
		this.classComment = classComment;
	}

	public String getClassAuthor() {
		return classAuthor;
	}

	public void setClassAuthor(String classAuthor) {
		this.classAuthor = classAuthor;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
