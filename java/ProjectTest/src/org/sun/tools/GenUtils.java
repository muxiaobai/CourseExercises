/**
 * Copyright © 2017, Forp Co., LTD
 *
 * All Rights Reserved.
 *
 */
package org.sun.tools;

import org.apache.commons.lang3.StringUtils;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 代码生成工具类
 *
 * @author  liShengJie
 * @version 2017/3/21 16:35
 */
public class GenUtils
{
	/**
	 * Logger
	 */

	/**
	 * 生成文件(JSP)
	 *
	 * @param template
	 * @param ctx
	 * @param path
	 */
	public static String mergeJsp(Template template, VelocityContext ctx, String path,String fileName) throws
			Exception
	{
		FileOutputStream file = null;
		OutputStreamWriter osw = null;
		try
		{
			file = new FileOutputStream(path);
			osw = new OutputStreamWriter(file, "UTF-8");

			template.merge(ctx, osw);
		}
		finally
		{
			if (null != osw)
				osw.close();

			if (null != file)
				file.close();
		}
		//class文件存储到Mongodb
		DBObject metaData = new BasicDBObject();
		metaData.put("type", ""); // TODO: 2017/4/5 流程模块存储jsp文件到mongodb
		String jspUID = MongoDB.saveFile(path, null, metaData);

		return jspUID;

	}

	/**
	 * 生成文件(VO,SERVICE,CONTROLLER,JSP)
	 *
	 * @param template
	 * @param ctx
	 * @param path
	 * @param packagePath
	 */
	@Deprecated
	public static void merge(Template template, VelocityContext ctx, String path, String packagePath) throws Exception
	{
		FileOutputStream file = null;
		OutputStreamWriter osw = null;
		try
		{
			file = new FileOutputStream(path);
			osw = new OutputStreamWriter(file, "UTF-8");

			//template.insertMongoDBOfmerge(ctx, osw);
		}
		finally
		{
			if (null != osw)
				osw.close();

			if (null != file)
				file.close();
		}

		// 如果是java文件,执行编译
		if (StringUtils.endsWith(path, ".java"))
		{
			//compilerFile(path, FORP.WEB_APP_PATH + "/WEB-INF/classes", packagePath);
		}

		// 如果是link.jsp文件则刷新Spring容器(link.jsp为最后生成文件)
		if (path.contains("link.jsp"))
		{
			GenTable genTable = (GenTable) ctx.get("table");
			String className = genTable.getClassName();

			//AutowireCapableBeanFactory beanFactory = FORP.SPRING_CONTEXT.getAutowireCapableBeanFactory();
			//Class vo = Class.forName("cn.forp.form.vo." + className);
			//beanFactory.autowire(vo, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
			//
			//Class service = Class.forName("cn.forp.form.service."+className+"Service");
			//beanFactory.autowire(service, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
			//
			//Class controller = Class.forName("cn.forp.form.controller." + className + "Controller");
			//beanFactory.autowire(controller, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);

			//((AbstractRefreshableApplicationContext) FORP.SPRING_CONTEXT.getParent()).refresh();

		}
	}

	/**
	 *  编译生成的模块代码(依赖vo<-service<-controller)
	 * @param southPath
	 * @param targetPath
	 * @param packagePath
	 * @throws Exception
	 */
	@Deprecated
	public static void compilerFile(String southPath,String targetPath,String packagePath) throws Exception
	{
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		// 获取编译器实例
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 获取标准文件管理器实例
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

		File file = new File(southPath);

		/**********************************************************/

		// TODO: 2017/3/22 编译依赖jar包路径
		String dependPath = getComplateClassPath();
		Iterable<String> options = Arrays.asList("-encoding", "UTF-8","-d", targetPath, "-cp", dependPath);

		/**********************************************************/


		// 获取要编译的编译单元
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);

		JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics,
				options, null, compilationUnits);
		// 运行编译任务
		Boolean compilResult = compilationTask.call();

		if (!compilResult) {
			throw new Exception("生成编译文件失败");
		}

		fileManager.close();

		file.delete();

		// 执行打包
		String rootPath = targetPath.substring(0, targetPath.indexOf(":"))+":";
		String osname = System.getProperties().getProperty("os.name");
		Process process = null;
		if(osname.contains("Windows")){
			process = Runtime.getRuntime().exec("cmd.exe /c cd "+targetPath+" & "+rootPath+" & jar -uf "+dependPath+" "+packagePath);
		} else {
			String[] cmd = { "/bin/sh", "-c", "cd "+targetPath+" & "+rootPath+" & jar -uf "+dependPath+" "+packagePath };
			process = Runtime.getRuntime().exec(cmd);
			
		}
		// 打包进程执行完成后继续下一步
		process.waitFor();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperties().getProperty("os.name"));
		System.out.println(System.getProperties().getProperty("file.separator"));
		System.out.println(GenUtils.getComplateClassPath());
        System.out.println(GenUtils.getProjectPath());
		
	}

	/**
	 * 获取工程路径
	 *
	 * @return
	 */
	public static String getProjectPath()
	{
		// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = "";
		if (StringUtils.isNotBlank(projectPath))
		{
			return projectPath;
		}

		try
		{
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null)
			{
				while (true)
				{
					File f = new File(file.getPath() + File.separator + "src" + File.separator);
					if (f.exists())
					{
						break;
					}

					if (file.getParentFile() != null)
					{
						file = file.getParentFile();
					}
					else
					{
						break;
					}
				}

				projectPath = file.toString();
			}
		}
		catch (Exception e)
		{
			lg.error("错误：", e);
		}

		return projectPath;
	}

	/**
	 * 将oracle字段类型转意为Java类型
	 *
	 * @param sqlType 字段类型
	 * @param scale   字段小数点位数
	 * @param size    字段长度
	 * @return
	 */
	public static String oracleSqlType2JavaType(String sqlType, int scale, int size) throws BusinessException
	{
		if (sqlType.equalsIgnoreCase("integer"))
		{
			return "Integer";
		}
		else if (sqlType.equalsIgnoreCase("long")) {
			return "Long";

		} else if (sqlType.equalsIgnoreCase("float") || sqlType.equalsIgnoreCase("float precision")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("double") || sqlType.equalsIgnoreCase("double precision")) {
			return "Double";

		} else if (sqlType.equalsIgnoreCase("number") || sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")) {
			return scale == 0 ? (size < 10 ? "Integer" : "Long") : "BigDecimal";

		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("nvarchar2")
				|| sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")) {

			return "String";

		} else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
				|| sqlType.equalsIgnoreCase("timestamp") || sqlType.equalsIgnoreCase("TIMESTAMP(6)")) {
			return "Date";
		}
		throw new Exception("代码生成失败");
	}


	/**
	 * 生成class文件并存储到mongodb
	 *
	 * @param template
	 * @param ctx
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static String insertMongoDBOfmerge(Template template, VelocityContext ctx, String className) throws
			Exception
	{
		String javaPath = FORP.WEB_APP_PATH + "/disk-file/form" + "/" + className + ".java";
		String classDir = FORP.WEB_APP_PATH + "/WEB-INF/classes";
		String classPath = FORP.WEB_APP_PATH + "/WEB-INF/classes/cn/forp/form/service/" + className + ".class";

		File existsFile = new File(classPath);
		if (existsFile.exists()) {
			existsFile.delete();
		}

		FileOutputStream file = null;
		OutputStreamWriter osw = null;
		try
		{
			file = new FileOutputStream(javaPath);
			osw = new OutputStreamWriter(file, "UTF-8");

			template.merge(ctx, osw);
		}
		finally
		{
			if (null != osw)
				osw.close();

			if (null != file)
				file.close();
		}

		// 执行编译
		compilerFile(javaPath, classDir);

		//class文件存储到Mongodb
		DBObject metaData = new BasicDBObject();
		metaData.put("type", FormRule.MONGODB_FORM_RULE);
		String ruleUID = MongoDB.saveFile(classPath, null, metaData);

		return ruleUID;
	}

	/**
	 * 编译java文件
	 *
	 * @param southPath	java源文件路径
	 * @param targetPath	编译class文件存放路径
	 */
	public static void compilerFile(String southPath, String targetPath) throws Exception
	{
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		// 获取编译器实例
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 获取标准文件管理器实例
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		File file = new File(southPath);

		// TODO: 2017/3/22 编译依赖jar包路径

		Iterable<String> options = Arrays.asList("-encoding", "UTF-8", "-d", targetPath, "-cp", getComplateClassPath());

		// 获取要编译的编译单元
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);

		JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics,
				options, null, compilationUnits);
		// 运行编译任务
		
		Boolean compilResult = compilationTask.call();

		if (!compilResult)
		{
			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				lg.warn(diagnostic.getMessage(null));
			}
			throw new Exception("生成编译文件失败");
		}

		fileManager.close();
		//file.delete();
	}

	/**
	 * 获取系统编译环境
	 * @return
	 * @throws Exception
	 */
	private static String getComplateClassPath() throws Exception {
		String osname = System.getProperties().getProperty("os.name");
		String osSplit = ":";
		if(osname.contains("Windows")){
			osSplit = ";";
		}
		StringBuilder dependPath = new StringBuilder();
		dependPath.append(StringUtils.join(new File(FORP.WEB_APP_PATH + "/WEB-INF/lib").listFiles(), osSplit)).append(osSplit);
		if (StringUtils.isNotBlank(System.getProperty("catalina.home"))) {
			dependPath.append(System.getProperty("catalina.home")).append("/lib/jsp-api.jar").append(osSplit);
			dependPath.append(System.getProperty("catalina.home")).append("/lib/servlet-api.jar").append(osSplit);
		}
		dependPath.append(FORP.WEB_APP_PATH + "/WEB-INF/classes").append(osSplit);
		return dependPath.toString();
	}
}