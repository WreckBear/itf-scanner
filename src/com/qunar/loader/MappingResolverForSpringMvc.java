package com.qunar.loader;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MappingResolverForSpringMvc {

	private List<Class> classes;
	
	public MappingResolverForSpringMvc(List<Class> classes) {
		this.classes = classes;
	}
	
	public List<String> getMapping() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		ArrayList<String> list = new ArrayList<>();
		for (Class clazz : classes) {
			Annotation[] annotations =  clazz.getAnnotations();
			
			// ��ȡ�����RequestMapping�ļ�¼
			for (Annotation annotation : annotations) {
				String name = annotation.annotationType().getName();
				if(name.equals("org.springframework.web.bind.annotation.RequestMapping")){
					Method declaredMethod = annotation.annotationType().getDeclaredMethod("value");
					String[] maaping = (String[])declaredMethod.invoke(annotation);
					list.addAll(Arrays.asList(maaping));
				}
			}
			
			// ��ȡ��ÿ�����ÿ��������ע��
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				Annotation[] m_annotations = method.getAnnotations();
				for (Annotation annotation : m_annotations) {
					String name = annotation.annotationType().getName();
					if(name.equals("org.springframework.web.bind.annotation.RequestMapping")
							|| name.equals("org.springframework.web.bind.annotation.GetMapping")
							|| name.equals("org.springframework.web.bind.annotation.PostMapping")){
						Method declaredMethod = annotation.annotationType().getDeclaredMethod("value");
						String[] maaping = (String[])declaredMethod.invoke(annotation);
						list.addAll(Arrays.asList(maaping));
					}
				}
			}
		}
		
		return list;
	}
	
}
