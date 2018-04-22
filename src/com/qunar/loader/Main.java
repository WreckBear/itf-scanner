package com.qunar.loader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		// ������Ŀ�ĸ�Ŀ¼
		File projectBin = new File("E:\\worksapce\\20180409-boji\\springsecurity\\target\\classes");
		
		// �õ���Ŀ�µ�POM�ļ�����·��
		MavenClassPathResolver mvnResolver = new MavenClassPathResolver();
		URL[] mvnClassPath = mvnResolver.getMvnClassPath(new File("E:\\worksapce\\20180409-boji\\springsecurity\\pom.xml"));
		
		ClassScanner classScanner = new ClassScanner(mvnClassPath);
		
		// ��ȡ��Ŀ�����е������
		List<Class> classes = classScanner.getClasses(projectBin);
		
		// �����������е�ӳ��·��
		MappingResolverForSpringMvc resolver = new MappingResolverForSpringMvc(classes);
		List<String> mapping = resolver.getMapping();
		
		// ��ӡ��·��
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
