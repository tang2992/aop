package com.coffee.core.aop;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;

public class InjectionTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        //获取存放CtClass的容器ClassPool
        ClassPool cp = ClassPool.getDefault();
        //创建一个类加载器
        Loader cl = new Loader();
        //增加一个转换器，让类加载的时候
        cl.addTranslator(cp, new MyTranslator());
        //将类装载到JVM
        try {
            cl.run("com.coffee.core.aop.InjectionTest$MyTranslator", null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static class MyTranslator implements Translator {

        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        }

        /* *
         * 类装载到JVM前进行代码织入
         */
        public void onLoad(ClassPool pool, String classname) {
            System.out.println("Class is :" + classname);
            if (!"com.coffee.core.aop.ProxyTest1".equals(classname)) {
                return;
            }
            //通过报名获取类文件
            try {
                pool.importPackage("com.coffee.core.aop");
                CtClass cc = pool.get(classname);
                
                CtConstructor[] cons = cc.getConstructors();
                CtBehavior[] bes = cc.getDeclaredBehaviors();
                CtField[] fis = cc.getDeclaredFields();
                CtMethod[] mes = cc.getDeclaredMethods();
                
                //获得指定方法名的方法
                CtMethod m = cc.getDeclaredMethod("process2");
                CtClass[] cs = m.getParameterTypes();
                CtClass r = m.getReturnType();
                
                pool.insertClassPath("");
                pool.insertClassPath("");

                //在方法执行前插入代码
                m.insertBefore("{ com.coffee.core.aop.Process.before($args); }");
                m.insertAfter("{ com.coffee.core.aop.Process.after(); }");
            } catch (NotFoundException e) {
            } catch (CannotCompileException e) {
            }
        }

        public static void main(String[] args) {
            ProxyTest1 proxy1 = new ProxyTest1();
            proxy1.process2("12345", 27);
        }
    }
}
