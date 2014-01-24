package com.coffee.core.aop;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class Injection implements ClassFileTransformer {

    private static final String WEB = "WEB-INF" + File.separatorChar + "lib";

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 如果加载Business类才拦截
        if (className == null || !className.startsWith("com/xikang")) {
            return null;
        }

        // javassist的包名是用点分割的，需要转换下
        if (className.indexOf("/") != -1) {
            className = className.replaceAll("/", ".");
        }
        
        try {
            ClassPool pool = ClassPool.getDefault();

            // web项目的特殊处理
            String cp = loader.getResource("").getPath();
            if (cp.contains("WEB-INF")) {
                cp = cp.substring(0, cp.length() - 1);

                int pos = cp.indexOf("WEB-INF");
                String libPath = cp.substring(0, pos) + WEB;
                // 增加 WEB-INF/lib 下面的依赖包
                pool.insertClassPath(libPath + "/*");

                // 增加 WEB-INF/classes 下面的类
                pool.insertClassPath(cp);
            }
            
            // 取得tomcat下面的依赖
            String catalinaHome = System.getProperty("catalina.home");
            if (catalinaHome != null && !catalinaHome.equals("")) {
                pool.insertClassPath(catalinaHome + File.separatorChar + "lib/*");
            }

            // 通过包名获取类文件
            CtClass cc = pool.get(className);
            // 获得所有方法名的方法
            CtMethod[] cms = cc.getDeclaredMethods();
            for (CtMethod m : cms) {
                m.insertBefore("{ com.coffee.core.aop.Process.before($args); }");
                m.insertAfter("{ com.coffee.core.aop.Process.after($args); }");
            }
            return cc.toBytecode();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void agentmain(String options, Instrumentation ins) {
        ins.addTransformer(new Injection());
    }

    public static void premain(String options, Instrumentation ins) {
        ins.addTransformer(new Injection());
    }
}
