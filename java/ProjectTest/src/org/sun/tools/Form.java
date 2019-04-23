/**
 * Project Name:ProjectTest
 * File Name:form.java
 * Package Name:org.sun.tools
 * Date:2019年4月23日下午1:46:32
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package org.sun.tools;

import java.io.File;
import java.io.IOException;
import java.sql.JDBCType;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


/**
 * ClassName:form 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月23日 下午1:46:32 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class Form {
    /**
     * 根据表名创建表
     * @param tableName
     * @throws JDOMException
     * @throws IOException
     */
    public void createTable(String tableName) throws JDOMException, IOException {
        File file = new File(MU.WEB_APP_PATH_RESOURCE_ORACLE);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(file);
        Element foo = doc.getRootElement();
        String createTableSql = foo.getChild("table").getText();
        if (StringUtils.isNoneBlank(createTableSql)) {
            createTableSql = createTableSql.replace("$[TABLENAME]", tableName.toUpperCase());
        }
//        jdbc.execute(createTableSql);
        System.out.println("createTableSql:\n"+createTableSql);
        

    }

    /**
     * 根据表名创建表    MYSQL
     * @param tableName 表名称
     */
    public void createTableForMySql(String tableName) throws Exception {
        File file = new File(MU.WEB_APP_PATH_RESOURCE_MYSQL);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(file);
        Element foo = doc.getRootElement();
        String createTableSql = foo.getChild("table").getText();
        if (StringUtils.isNotBlank(createTableSql)) {
            createTableSql = createTableSql.replace("$[TABLENAME]", tableName.toUpperCase());
        }
//        jdbc.execute(createTableSql);
        System.out.println("createTableForMySql:\n"+createTableSql);
    }
    /**
     * 字段
     * addColumn:().
     * @author Mu Xiaobai
     * @param formName
     * @param fieldName
     * @param fieldType
     * @param fieldSize
     * @param digit
     * @param notNull
     * @param defaultValue
     * @throws Exception
     * @since JDK 1.8
     */
    public void addColumn(String formName, String fieldName, String fieldType, String fieldSize, String digit, boolean notNull, String defaultValue) throws Exception
    {
        String addColumnSql = "ALTER TABLE " + formName.toUpperCase() + " ADD " + fieldName.toUpperCase() + " " + fieldType;
        if (StringUtils.isNoneBlank(fieldSize, digit))
        {
            addColumnSql += "(" + fieldSize + "," + digit + ")";
        }
        else
        {
            if (StringUtils.isNotBlank(fieldSize))
            {
                addColumnSql += "(" + fieldSize + ")";
            }
        }
        if (notNull)
        {
            addColumnSql += " NOT NULL ";
        }
        if (StringUtils.isNotBlank(defaultValue))
        {
            addColumnSql += " DEFAULT '" + defaultValue + "' ";
        }
        else
        {
//            if ("MySQL".equalsIgnoreCase(FORP.DB_TYPE) && JDBCType.TIMESTAMP.getName().equals(fieldType))
//            {
//                addColumnSql += " default CURRENT_TIMESTAMP ";
//            }
        }
        System.out.println("update sql:\n"+addColumnSql);
//        jdbc.execute(addColumnSql);
    }

}

