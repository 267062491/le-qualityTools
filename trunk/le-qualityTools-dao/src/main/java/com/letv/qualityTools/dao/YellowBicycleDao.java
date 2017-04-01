package com.letv.qualityTools.dao;

import com.letv.qualityTools.domain.YellowBicycle;
import com.letv.qualityTools.domain.query.YellowBicycleQuery;

import java.util.List;

/**
 * LogicZoneDao接口<br/>
 * 对'逻辑区储区对照表'表进行基本的操作
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
public interface YellowBicycleDao {
    
    /**
     * 新增对象
     * 
     * @param yellowBicycle
     * @return
     */
    public boolean insert(YellowBicycle yellowBicycle);

    /**
     * 更新对象
     * 
     * @param yellowBicycle
     * @return
     */
    public boolean update(YellowBicycle yellowBicycle);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<YellowBicycle> queryYellowBicycleList(YellowBicycleQuery queryBean);

}
