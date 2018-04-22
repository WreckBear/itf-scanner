# itf-scanner 接口扫描器

## 背景：
	有一个项目是springmvc提供URL接口的，现在想统计一下springmvc暴漏的接口有哪些？有这么几个办法：
	1. 用springmvc的RequestMappingHandlerMapping类获取应用中注册过的所有URL接口。
	2. 自己扫描所有的类，分析用过@RequestMapping注解的类和方法所映射URL值。
	
	现在是这样，第一种方法的前提是应用必须跑起来，如果你的应用跑不起来，那就用第二种，而itf-scanner项目就是第二种的实现。
	
## 思路：
#### 一、首先扫描所有的类，分两种：
1. 扫描所有的源文件(*.java)，对源文件的字符串进行分析，这个不用说，难度较大！跳过。
2. 扫描所有的编译好的字节码文件，利用ClassLoader将字节码文件加载成Class对象，再利用反射得到注解的信息，比较容易！

itf-scanner采用的第二种方式。

#### 二、ClassLoader加载类时需要同时加载非本项目的类
