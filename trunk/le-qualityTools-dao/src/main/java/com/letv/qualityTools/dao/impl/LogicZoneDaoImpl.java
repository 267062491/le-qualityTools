package com.letv.qualityTools.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.qualityTools.dao.LogicZoneDao;
import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.query.LogicZoneQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * LogicZoneDAO实现类<br/>
 * 对'逻辑区储区对照表'表进行基本的操作
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
@Repository
public class LogicZoneDaoImpl extends BaseDao implements LogicZoneDao {
    /** namespace */
    private final String namespace = LogicZoneDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<LogicZone> queryLogicZoneList(LogicZoneQuery queryBean) {
        return (List<LogicZone>) queryForList(namespace +".queryLogicZoneList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(LogicZone logicZone) {
        return insert(namespace +".insert", logicZone);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(LogicZone logicZone) {
        return update(namespace +".update", logicZone);
    }

    /**
     * {@inheritDoc}
     */
    public int queryLogicZoneCount(LogicZoneQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryLogicZoneCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<LogicZone> queryLogicZoneListWithPage(LogicZoneQuery queryBean) {
        return (List<LogicZone>) queryForList(namespace +".queryLogicZoneListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(LogicZone configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public LogicZone getLogicZoneById(Long id) {
        return (LogicZone) queryForObject(namespace +".getLogicZoneById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(LogicZone logicZone) {
        int count = (Integer) queryForObject(namespace +".exist", logicZone);
        return count > 0;
    }
}
