aop
===

通过aop对项目进行拦截，记录调用链的相关信息

目的：取得调用链的相关信息（进程、线程、执行时长、类、方法、参数等）。

方法：通过使用javassist和Instrumentation来实现对应用系统的调用关系进行记录，此种方式是在类加载器加载类时进行拦截，然后在字节码中添加自定义代码实现的。

1、普通JAVA项目:在项目启动参数中加入：-javaagent:D:\Workspaces\Eclipse\core-aop\target\core-aop-0.0.1.jar来实现

2、Web项目（需要部署在Tomcat中）：
a、修改catalina.sh文件
CATALINA_OPTS="$CATALINA_OPTS -javaagent:/opt/tomcat-zookeeper/shared/core-aop-0.0.1.jar"
CLASSPATH="$CLASSPATH:/opt/tomcat-zookeeper/shared/javassist-3.18.1-GA.jar:/opt/tomcat-zookeeper/shared/json-20090211.jar"

b、把依赖的jar包放到tomcat的shared目录下面（core-aop-0.0.1.jar、javassist-3.18.1-GA.jar、json-20090211.jar）
