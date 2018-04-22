package com.qunar.loader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		// 定义项目的根目录
		File projectBin = new File("E:\\worksapce\\20180409-boji\\springsecurity\\target\\classes");
		
		// 得到项目下的POM文件的类路径
		MavenClassPathResolver mvnResolver = new MavenClassPathResolver();
		URL[] mvnClassPath = mvnResolver.getMvnClassPath(new File("E:\\worksapce\\20180409-boji\\springsecurity\\pom.xml"));
		
		ClassScanner classScanner = new ClassScanner(mvnClassPath);
		
		// 获取项目下所有的类对象
		List<Class> classes = classScanner.getClasses(projectBin);
		
		// 分析所有类中的映射路径
		MappingResolverForSpringMvc resolver = new MappingResolverForSpringMvc(classes);
		List<String> mapping = resolver.getMapping();
		
		// 打印类路径
		System.out.println(mapping);
		
	}

	private static void fun1() throws MalformedURLException, ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		File bin = new File("E:\\worksapce\\20180409-boji\\springsecurity\\target\\classes");
		String mavenRep = "C:/Users/imlin/.m2/repository/";
		URL[] classpathes = new URL[]{
			bin.toURL(),
			new URL("file:/"+ mavenRep +"org/springframework/spring-core/5.0.5.RELEASE/spring-core-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/spring-beans/5.0.5.RELEASE/spring-beans-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/spring-jcl/5.0.5.RELEASE/spring-jcl-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/spring-expression/5.0.5.RELEASE/spring-expression-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/spring-context/5.0.5.RELEASE/spring-context-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/spring-web/5.0.5.RELEASE/spring-web-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/spring-webmvc/5.0.5.RELEASE/spring-webmvc-5.0.5.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/security/spring-security-config/5.0.4.RELEASE/spring-security-config-5.0.4.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/security/spring-security-core/5.0.4.RELEASE/spring-security-core-5.0.4.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"org/springframework/security/spring-security-web/5.0.4.RELEASE/spring-security-web-5.0.4.RELEASE.jar"),
			new URL("file:/"+ mavenRep +"javax/servlet/javax.servlet-api/3.0.1/javax.servlet-api-3.0.1.jar"),
		};
		ClassScanner classScanner = new ClassScanner(classpathes);
		
		try {
			List<Class> classes = classScanner.getClasses(bin);
			MappingResolverForSpringMvc resolver = new MappingResolverForSpringMvc(classes);
			List<String> mapping = resolver.getMapping();
			System.out.println(mapping);
		} catch (Error e) {
			e.printStackTrace();
		}
	}
	
}
