package com.qunar.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Generated;

/**
 * 根据Maven的仓库位置和项目的pom文件，获取对应的classpath
 * 
 * @author imlin
 *
 */
public class MavenClassPathResolver {

	/** maven本地仓库的位置 */
	private File mvnRepository;

	public MavenClassPathResolver() {
		// 默认是家目录的.m2/repository文件夹
		String getenv = System.getProperty("user.home");
		mvnRepository = new File(getenv+"/.m2/repository/");
	}
	
	public MavenClassPathResolver(File mvnRepository) {
		this.mvnRepository = mvnRepository;
	}

	/**
	 * 
	 * @param pomFile
	 * @return
	 * @throws MalformedURLException 
	 */
	public URL[] getMvnClassPath(File pomFile) throws MalformedURLException {
		// 1.将Dependences元素全部取出
		MvnDependence[] ds = getDependencesFromPom(pomFile);
		// 2.分析每一个Dependence的路径
		String[] dsPath = resolvePath(ds);
		// 3.获取到每一个Dependence的jar包路径
		URL[] urls = new URL[dsPath.length];
		for (int i = 0; i < urls.length; i++) {
			urls[i] = new URL("file:\\"+dsPath[i]);
		}
		return urls;
	}

	/*
	 * 从Pom文件中读取出所有的依赖项，并封装成Denpendence对象
	 */
	private MvnDependence[] getDependencesFromPom(File pomFile) {
		return null;
	}

	/**
	 * 根据Dependence数组计算出各自的路径
	 * @param ds
	 * @return
	 */
	private String[] resolvePath(MvnDependence[] ds) {
		
		return null;
	}

	class MvnDependence {
		String groupId;
		String artificatedId;
		String versionId;

		public MvnDependence(String groupId,String artificatedId,String versionId) {
			this.groupId = groupId;
			this.artificatedId = artificatedId;
			this.versionId = versionId;
		}
	}
}
