package com.qunar.loader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * ������
 * @author imlin
 *
 */
public class ClassScanner {

	private File[] srcs;
	private URL[] classpathes;
	private URLClassLoader loader = null;
	
	public ClassScanner(URL[] classpathes) {
		this(classpathes, null);
	}
	
	public ClassScanner(URL[] classpathes,File[] srcs) {
		this.srcs = srcs;
		this.classpathes = classpathes;
		loader = new URLClassLoader(classpathes);
	}
	
	public List<Class> getClasses(){
		if(srcs == null || srcs.length == 0)
			throw new IllegalArgumentException("srcû�б���ʼ��");
		return getClasses();
	}
	
	public List<Class> getClasses(File[] srcDirs) throws ClassNotFoundException{
		List<Class> list = new ArrayList<>();
		
		for (File srcDir : srcDirs) {
			list.addAll(getClasses(srcDir));
		}
		
		return list;
	}
	
	
	/**
	 * ��srcĿ¼�������е���
	 * @param classpathes
	 * @param file
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public List<Class> getClasses(File srcDir) throws ClassNotFoundException{
		List<Class> list = new ArrayList<>();
		
		List<File> fileList = new ArrayList<>();
		getAllFiles(srcDir,fileList);
		File[] files = new File[fileList.size()];
		for (int i = 0; i < files.length; i++) {
			files[i] = fileList.get(i);
		}
		for (File file : files) {
			// �õ�ÿ�����ȫ�޶���
			String className = getQualifieldName(file);
			
			Class<?> loadClass = loader.loadClass(className);
			list.add(loadClass);
		}
		
		return list;
	}
	
	/**
	 * �ݹ�ĳ��ľ�����е��ļ�
	 * @param srcDir
	 * @return
	 */
	private void getAllFiles(File File,List<File> list) {
		if(File.isFile()){
			list.add(File);
			return;
		}
		
		File[] listFiles = File.listFiles();
		for (File file2 : listFiles) {
			getAllFiles(file2, list);
		}
	}

	/**
	 * �õ��������޶���
	 * @param file
	 * @return
	 */
	private String getQualifieldName(File file){
		int indexOf = file.getAbsolutePath().indexOf("com");
		String classFileName = file.getAbsolutePath().substring(indexOf);
		String ps = File.separatorChar+"";
		return classFileName.replaceAll(".class", "").replaceAll(ps+ps, ".");
	}
	
}
