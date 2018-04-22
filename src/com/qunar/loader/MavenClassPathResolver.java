package com.qunar.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Generated;

/**
 * ����Maven�Ĳֿ�λ�ú���Ŀ��pom�ļ�����ȡ��Ӧ��classpath
 * 
 * @author imlin
 *
 */
public class MavenClassPathResolver {

	/** maven���زֿ��λ�� */
	private File mvnRepository;

	public MavenClassPathResolver() {
		// Ĭ���Ǽ�Ŀ¼��.m2/repository�ļ���
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
		// 1.��DependencesԪ��ȫ��ȡ��
		MvnDependence[] ds = getDependencesFromPom(pomFile);
		// 2.����ÿһ��Dependence��·��
		String[] dsPath = resolvePath(ds);
		// 3.��ȡ��ÿһ��Dependence��jar��·��
		URL[] urls = new URL[dsPath.length];
		for (int i = 0; i < urls.length; i++) {
			urls[i] = new URL("file:\\"+dsPath[i]);
		}
		return urls;
	}

	/*
	 * ��Pom�ļ��ж�ȡ�����е����������װ��Denpendence����
	 */
	private MvnDependence[] getDependencesFromPom(File pomFile) {
		return null;
	}

	/**
	 * ����Dependence�����������Ե�·��
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
