aop
===

通过aop对项目进行拦截，记录调用链的相关信息

目的：取得调用链的相关信息（进程、线程、执行时长、类、方法、参数等）。

方法：通过使用javassist和Instrumentation来实现AOP，此种方式是在类加载器加载类时进行拦截，然后在字节码中添加自定义代码实现的。

1、普通JAVA项目:在项目启动参数中加入：-javaagent:D:\Workspaces\Eclipse\core-aop\target\core-aop-0.0.1.jar来实现

2、Web项目（需要部署在Tomcat中）：
a、修改catalina.sh文件
CATALINA_OPTS="$CATALINA_OPTS -javaagent:/opt/tomcat-zookeeper/shared/core-aop-0.0.1.jar"
CLASSPATH="$CLASSPATH:/opt/tomcat-zookeeper/shared/javassist-3.18.1-GA.jar:/opt/tomcat-zookeeper/shared/json-20090211.jar"

b、把依赖的jar包放到tomcat的shared目录下面（core-aop-0.0.1.jar、javassist-3.18.1-GA.jar）

输出：
Track:sid:eb3dba5e-4984-4be9-8fa8-06f973370fa3	uid:1	startTime:1390442277245	endTime:1390442277245	porcess:509@vm-120	thread:http-9090-2	className:com.xikang.zookeeper.server.ZookeeperService	methodName:getInstance	param:[]
Track:sid:eb3dba5e-4984-4be9-8fa8-06f973370fa3	uid:2	startTime:1390442277246	endTime:1390442277246	porcess:509@vm-120	thread:http-9090-2	className:com.xikang.zookeeper.server.ZookeeperService	methodName:getConfigClient	param:["196"]
Track:sid:eb3dba5e-4984-4be9-8fa8-06f973370fa3	uid:3	startTime:1390442277246	endTime:1390442277247	porcess:509@vm-120	thread:http-9090-2	className:com.xikang.zookeeper.server.ZookeeperResource	methodName:convertNodePath	param:["safety.mongodb.files"]
Track:sid:eb3dba5e-4984-4be9-8fa8-06f973370fa3	uid:4	startTime:1390442277247	endTime:1390442277247	porcess:509@vm-120	thread:http-9090-2	className:com.xikang.zookeeper.server.ConfigClient	methodName:getPath	param:["safety/mongodb/files"]
Track:sid:eb3dba5e-4984-4be9-8fa8-06f973370fa3	uid:5	startTime:1390442277247	endTime:1390442277255	porcess:509@vm-120	thread:http-9090-2	className:com.xikang.zookeeper.server.ConfigClient	methodName:getItem	param:["safety/mongodb/files"]
Track:sid:eb3dba5e-4984-4be9-8fa8-06f973370fa3	uid:6	startTime:1390442277247	endTime:1390442277256	porcess:509@vm-120	thread:http-9090-2	className:com.xikang.zookeeper.server.ZookeeperResource	methodName:getNodeValue	param:["196","safety.mongodb.files"]

