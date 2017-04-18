package com.letv.qualityTools.utils.study.reflect;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-18
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class TestReflectEntity {

    public TestReflectEntity(){
        System.out.println("我是没有参数的构造函数");
    }

    public TestReflectEntity(String par1 , String par2){
        System.out.println("我是有两个string类型参数的构造函数");
    }

    private TestReflectEntity(String par1 , String par2,int par3){
        System.out.println("我是有两个string类型,一个int类型参数的私有构造函数");
    }
    protected TestReflectEntity(String par1 , String par2,int par3,Integer par4){
        System.out.println("我是有两个string类型,一个int类型,一个Integer类型 参数的protected构造函数");
    }


    private Integer code ;
    private String content ;

    public String methodOne(){
        String content = "我是methodOne" ;
        System.out.print(content);
        return  content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
