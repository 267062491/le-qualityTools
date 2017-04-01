package com.letv.qualityTools.dao;

import java.util.List;

import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
/**
 * LogicZoneDao接口<br/>
 * 对'逻辑区储区对照表'表进行基本的操作
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
public interface LogicZoneDao {
    
    /**
     * 新增对象
     * 
     * @param logicZone 
     * @return
     */
    public boolean insert(LogicZone logicZone);

    /**
     * 更新对象
     * 
     * @param logicZone
     * @return
     */
    public boolean update(LogicZone logicZone);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<LogicZone> queryLogicZoneList(LogicZoneQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryLogicZoneCount(LogicZoneQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<LogicZone> queryLogicZoneListWithPage(LogicZoneQuery queryBean);

    /**
     * 删除记录
     * 
     * @param logicZone
     * @return
     */
    public boolean delete(LogicZone logicZone);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public LogicZone getLogicZoneById(Long id);

    /**
     * 判断是否存在
     * 
     * @param logicZone
     * @return
     */
    public boolean exist(LogicZone logicZone);

}
