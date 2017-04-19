package com.letv.qualityTools.utils.study.reflect;

import com.fasterxml.jackson.databind.util.Annotations;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-18
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class RunMethod {

    public void getFullPackageName(){
        TestReflectEntity testReflect = new TestReflectEntity();
        System.out.println(TestReflectEntity.class.getName()); // 和下面是一个效果
        System.out.println(testReflect.getClass().getName());
    }

    /**
     * 创建Class类对象 ， 有三种方法
     * 如下方法内
     */
    public void createClassObject(){
        try {
            // 1、 forName 通过完整类路径
            Class<?> class1 = Class.forName("com.letv.qualityTools.utils.study.reflect.TestReflectEntity");
            // 2、通过类对象实例 .getClass() 获得Class 对象
            Class<?> class2 = new TestReflectEntity().getClass();
            // 3、通过类名.class 获得Class 对象
            Class<?> class3 = TestReflectEntity.class;
            System.out.println("类名称   " + class1.getName());
            System.out.println("类名称   " + class2.getName());
            System.out.println("类名称   " + class3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  newInstance 描述
     *  clazz.newInstance()  只能对public entity() 进行实例化  也就是只能对 public 的 无参构造函数进行实例化
     *  如果要多有参构造函数进行实例化， 则需要首先 获得 相应的构造函数 ， 然后执行构造函数的 newInstance() 方法  ， 入下面的例子
     */
    public void newInstanceDescribe(){

        Class clazz = TestReflectEntity.class ;
        /**
         * 对无参构造函数的类进行实例化 ,可以直接通过  class.newInstance() 进行实例化
         */
        try {
            TestReflectEntity testReflectEntity = (TestReflectEntity) clazz.newInstance();
            System.out.println(testReflectEntity.getCode());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /**
         * 对有参数构造函数的类进行实例化 ， 通过 class.getConstructor(par) 获得对应的构造函数， 然后在用获得的构造函数进行 newInstance(par) 进行实例
         */
        try {
            Constructor<TestReflectEntity> constructor = clazz.getDeclaredConstructor(String.class,String.class);
            TestReflectEntity testReflectEntity = constructor.newInstance("par1","par2");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 构造函数描述 ， 有4种获得构造函数的方法，
     *  获得所有public
     *  获得所有包括private
     *  按照参数获得public
     *  按照参数获包括private
     *
     * 特别注意 ：getDeclaredConstructor 能够获取到当前类中所有的范围的（public private protected）
     *            getConstructor 能够获取到当前类以及父类、接口的所有的public的
     */
    public void constructorDescribe()  {
        Class clazz = TestReflectEntity.class ;
        try {
            /**
             * 获取构造函数
             * 注意点1：Integer 和 int 的 class 是不一样的  Integer.class / int.class
             * 注意点2：访问范围熟悉 private , protected ,public 是正常new 对象是一样的
             * 注意点3：如果private 或者 protected 超出范围不能访问， 则通过constructor.setAccessible(true) 为可访问的
             */
            Constructor[] constructor = clazz.getConstructors();// 获得所有的 public 的构造函数
            Constructor[] constructorAll = clazz.getDeclaredConstructors();// 获得所有的构造函数， 包括 private
            Constructor<TestReflectEntity> publicDoubleString = clazz.getConstructor(String.class,String.class);// 获得指定参数类型的public构造函数
            Constructor<TestReflectEntity> constructorPrivateDoubleStringOneInt = clazz.getDeclaredConstructor(String.class,String.class,int.class);// 获得指定参数类型的构造函数，可以是private
            constructorPrivateDoubleStringOneInt.setAccessible(true);//设置构造函数为可以访问
            Constructor<TestReflectEntity> constructorPrivateDoubleStringOneIntOneInteger = clazz.getDeclaredConstructor(String.class,String.class,int.class,Integer.class);// 获得指定参数类型的构造函数，可以是private
            constructorPrivateDoubleStringOneIntOneInteger.setAccessible(true);//设置构造函数为可以访问
            System.out.println(constructorPrivateDoubleStringOneIntOneInteger.newInstance("", "", 1, 1));
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * 方法描述 ， 有4种获得构造函数的方法，
     *  获得所有public
     *  获得所有包括private
     *  按照名称和参数获得public
     *  按照名称和参数获包括private
     */
    public void methodDescribe()  {
        try {
            /**
             * 获取到某个方法并调用该方法的步骤
             * 1、得到类 TestReflectEntity.class
             * 2、对类进行实例化  clazz.newInstance()
             * 3、得到方法     clazz.getDeclaredMethod("methodTwo",String.class)
             * 4、方法调用， 指定类的实例和参数值   method.invoke(testReflectEntity,"方法2")
             */
            Class clazz = TestReflectEntity.class ;
            TestReflectEntity testReflectEntity = (TestReflectEntity) clazz.newInstance();
            Method[] methods = clazz.getMethods();  // 所有public 的方法
            Method[] methodsAll = clazz.getDeclaredMethods();// 所有方法 包括private
            Method methodAll = clazz.getDeclaredMethod("methodTwo",String.class); // 获得指定名称和参数的方法， 包括private
            Method method = clazz.getMethod("methodTwo",String.class); // 获得指定名称和参数的public方法
            method.invoke(testReflectEntity,"方法2");// 执行方法调用， 指定调用哪个类，具体参数值
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    /**
     * 属性描述 ， 有4种获得构造函数的方法，
     *  获得所有public
     *  获得所有包括private
     *  按照名称和参数获得public
     *  按照名称和参数获包括private
     */
    public void filedDescribe()  {
        /**
         * 获取到某个属性并并获取属性值的步骤
         * 1、得到类 TestReflectEntity.class
         * 2、对类进行实例化  clazz.newInstance()
         * 3、得到属性     clazz.getDeclaredField("methodTwo")
         * 4、方法调用， 执行field的get或者set方法
         */

        try {
            /**
             * 在已有的对象实例的基础上通过反射操作熟悉
             * 说明，也可以对已有对象实例进行操作
             */
            TestReflectEntity testReflectEntity = new TestReflectEntity();
            testReflectEntity.setCode(1);
            testReflectEntity.setContent("wer");
            Class<TestReflectEntity> entityClass = (Class<TestReflectEntity>) testReflectEntity.getClass();
            Field fieldContent = entityClass.getDeclaredField("content");
            fieldContent.setAccessible(true);
            System.out.println(String.valueOf(fieldContent.get(testReflectEntity)));

            /**
             * 通过反射创建对象，然后操作属性
             */
            Class<TestReflectEntity> clazz = TestReflectEntity.class;
            TestReflectEntity testReflectEntity1 = clazz.newInstance();
            Field field = clazz.getDeclaredField("content") ;
            field.setAccessible(true);
            field.set(testReflectEntity1,"我是set进去的");
            System.out.println("==" + field.get(testReflectEntity1));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * class的其他方法
     */
    public void otherApi() {
        Class<TestReflectEntity> clazz =TestReflectEntity.class;
        /**
         * 获得类的注解
         */
        Annotation[] annotations = clazz.getAnnotations();
        Annotation annotationService = clazz.getAnnotation(Service.class);

        /**
         * 获得类的内部类
         */
        Class<?>[] inClassAll = clazz.getDeclaredClasses();
        for(Class c : inClassAll){
            /**
             * 获得内部类所在的外部类
             */
            Class outClass = c.getDeclaringClass();
        }

        /**
         * 获得父类
         */
        Class supperClass = clazz.getSuperclass();

        /**
         * 获得包
         */
        Package packag = clazz.getPackage();

    }

    public static void main(String[] args) throws Exception {
        Class clazz = TestReflectEntity.class;
        Field[] fileds = clazz.getDeclaredFields();
        Field[] fileds1 = clazz.getFields();
        System.out.print("11");
    }




}
