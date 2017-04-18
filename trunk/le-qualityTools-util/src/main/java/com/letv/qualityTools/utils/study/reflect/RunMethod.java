package com.letv.qualityTools.utils.study.reflect;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
     * 构造函数描述 ， 有4中获得构造函数的方法，
     *  获得所有public
     *  获得所有包括private
     *  按照参数获得public
     *  按照参数获包括private
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

    public static void main(String[] args) throws Exception {

    }


}
