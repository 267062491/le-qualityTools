package com.letv.qualityTools.service;

import java.util.List;

import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * LogicZoneService接口
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
public interface LogicZoneService {

    /**
     * 批量增加对象信息
     * 
     * @param logicZoneList
     * @return
     */
    public boolean insert(List<LogicZone> logicZoneList);

    /**
     * 单个增加对象信息
     * 
     * @param logicZone
     * @return
     */
    public boolean insert(LogicZone logicZone);

    /**
     * 更新 对象信息
     * 
     * @param logicZone
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(LogicZone logicZone);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<LogicZone> queryLogicZoneList(LogicZoneQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<LogicZone> queryLogicZoneListWithPage(LogicZoneQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param logicZone
     *            　
     * @return
     */
    public boolean delete(LogicZone logicZone);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public LogicZone getLogicZoneById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param logicZones
     *            LogicZone集合
     * @return
     */
    public boolean delete(LogicZone[] logicZones);
}
