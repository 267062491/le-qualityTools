package com.letv.qualityTools.service.impl;

import com.letv.qualityTools.domain.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-3-17
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class SolrJTest {

    //指定solr服务器的地址
    private final static String SOLR_URL = "http://localhost:8080/solr/";
    private final static String core_jobs = "jobs/" ;

    /**
     * 创建SolrServer对象
     *
     * 该对象有两个可以使用，都是线程安全的
     * 1、CommonsHttpSolrServer：启动web服务器使用的，通过http请求的
     * 2、 EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了
     * 3、solr 4.0之后好像添加了不少东西，其中CommonsHttpSolrServer这个类改名为HttpSolrClient
     *
     * @return
     */
    public HttpSolrClient createSolrServer(){
        HttpSolrClient solr = null;
        solr = new HttpSolrClient(SOLR_URL);
        return solr;
    }


    /**
     * 往索引库添加文档
     * @throws IOException
     * @throws SolrServerException
     */
    public void addDoc() throws SolrServerException, IOException {
        //构造一篇文档
        SolrInputDocument document = new SolrInputDocument();
        //往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
        document.addField("id", "8");
        document.addField("name", "中华人民共和国");
        document.addField("description", "我的家在东北松花江上");
        //获得一个solr服务端的请求，去提交  ,选择具体的某一个solr core
        HttpSolrClient solr = new HttpSolrClient(SOLR_URL + core_jobs);
        solr.add(document);
        solr.commit();
        solr.close();
    }


    /**
     * 根据id从索引库删除文档
     */
    public void deleteDocumentById() throws Exception {
        //选择具体的某一个solr core
        HttpSolrClient server = new HttpSolrClient(SOLR_URL+core_jobs);
        //删除文档
        server.deleteById("8");
        //删除所有的索引
        //solr.deleteByQuery("*:*");
        //提交修改
        server.commit();
        server.close();
    }

    /**
     * 查询
     * @throws Exception
     */
    public void querySolr() throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL+core_jobs);
        SolrQuery query = new SolrQuery();
        //下面设置solr查询参数
        //query.set("q", "*:*");// 参数q  查询所有
        query.set("q","国");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来 ，这个作用适用于联想查询

        //参数fq, 给query增加过滤查询条件
        query.addFilterQuery("id:[0 TO 9]");//id为0-4

        //给query增加布尔过滤条件
        //query.addFilterQuery("description:演员");  //description字段中含有“演员”两字的数据

        //参数df,给query设置默认搜索域
        query.set("df", "name");

        //参数sort,设置返回结果的排序规则
        query.setSort("id",SolrQuery.ORDER.desc);

        //设置分页参数
        query.setStart(0);
        query.setRows(10);//每一页多少值

        //参数hl,设置高亮
        query.setHighlight(true);
        //设置高亮的字段
        query.addHighlightField("name");
        //设置高亮的样式
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        //获取查询结果
        QueryResponse response = solrServer.query(query);
        //两种结果获取：得到文档集合或者实体对象

        //查询得到文档的集合
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("通过文档集合获取查询的结果");
        System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
        //遍历列表
        for (SolrDocument doc : solrDocumentList) {
            System.out.println("id:"+doc.get("id")+"   name:"+doc.get("name")+"    description:"+doc.get("description"));
        }
        // 获得实体对象
        List list_person = toBeanList(solrDocumentList,Person.class);
        for(Object obj : list_person){
            Person per = (Person)obj ;
            System.out.println("id:"+per.getId()+"   name:"+per.getName()+"    description:"+per.getDescription());
        }

    }


    /**
     * 单个  SolrDocument 转成 entity
     * @param record
     * @param clazz
     * @return
     */
    public static  Object toBean( SolrDocument record , Class clazz){
        Object o = null;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Field[] fields =   clazz.getDeclaredFields();
        for(Field field:fields){
            Object value = record.get(field.getName());
            try {
                BeanUtils.setProperty(o, field.getName(), value);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return o;
    }

    /**
     * 批量  SolrDocument 转成 entity
     * @param records
     * @param clazz
     * @return
     */
    public static List toBeanList(SolrDocumentList records, Class  clazz){
        List  list = new ArrayList();
        for(SolrDocument record : records){
            list.add(toBean(record,clazz));
        }
        return list;
    }


    public static void main(String[] args) throws Exception {
        SolrJTest solr = new SolrJTest();
        //solr.createSolrServer();
        solr.addDoc();
//        solr.deleteDocumentById();
        solr.querySolr();
    }
}
