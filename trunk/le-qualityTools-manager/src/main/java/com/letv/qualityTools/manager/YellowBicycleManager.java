package com.letv.qualityTools.manager;

import com.letv.qualityTools.domain.YellowBicycle;
import com.letv.qualityTools.domain.query.YellowBicycleQuery;

import java.util.List;

/**
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
public interface YellowBicycleManager {

    /**
     * 单个增加对象信息
     * 
     * @return
     */
    public boolean insert(YellowBicycle yellowBicycle);

    /**
     * 更新 对象信息
     * 
     * @return false：失败 true：成功
     */
    public boolean update(YellowBicycle yellowBicycle);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<YellowBicycle> queryYellowBicycleList(YellowBicycleQuery queryBean);

}
