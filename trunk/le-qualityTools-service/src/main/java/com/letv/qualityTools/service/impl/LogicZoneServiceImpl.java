package com.letv.qualityTools.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.qualityTools.manager.LogicZoneManager;
import com.letv.qualityTools.service.LogicZoneService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * LogicZoneService接口的实现类
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
@Service
public class LogicZoneServiceImpl implements LogicZoneService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(LogicZoneServiceImpl.class);

    @Autowired
    private LogicZoneManager logicZoneManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.batchInsert")
    public boolean insert(List<LogicZone> logicZoneList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(logicZoneList)) {
                resultFlag = logicZoneManager.insert(logicZoneList);
            } else {
                LOG.warn("LogicZoneServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.insert")
    public boolean insert(LogicZone logicZone) {
        boolean resultFlag = false;
        try {
            if (null != logicZone) {
                if (logicZoneManager.exist(logicZone)) {
                    throw new ExistedException();
                }
                resultFlag = logicZoneManager.insert(logicZone);
            } else {
                LOG.warn("LogicZoneServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("LogicZoneServiceImpl#insert failed, logicZone has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.update")
    public boolean update(LogicZone logicZone) {
        boolean resultFlag = false;
        try {
            if (null != logicZone && null != logicZone.getId()) {
                resultFlag = logicZoneManager.update(logicZone);
            } else {
                LOG.warn("LogicZoneServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.queryLogicZoneList")
    public List<LogicZone> queryLogicZoneList(LogicZoneQuery queryBean) {
        List<LogicZone> logicZoneList = null;
        try {
            logicZoneList = logicZoneManager.queryLogicZoneList(queryBean);
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#queryLogicZoneList has error.", e);
        }
        return logicZoneList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.queryLogicZoneListWithPage")
    public List<LogicZone> queryLogicZoneListWithPage(LogicZoneQuery queryBean, PageUtil pageUtil) {
        List<LogicZone> logicZoneList = null;
        try {
            logicZoneList = logicZoneManager.queryLogicZoneListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#queryLogicZoneListWithPage has error.", e);
        }
        return logicZoneList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.delete")
    public boolean delete(LogicZone logicZone) {
        boolean resultFlag = false;
        try {
            if (null != logicZone && null != logicZone.getId()) {
                resultFlag = logicZoneManager.delete(logicZone);
            } else {
                LOG.warn("LogicZoneServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.batchDelete")
    public boolean delete(LogicZone[] logicZones) {
        boolean resultFlag = false;
        try {
            if (null != logicZones && logicZones.length > 0) {
                resultFlag = logicZoneManager.delete(logicZones);
            } else {
                LOG.warn("LogicZoneServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LogicZoneService.getLogicZoneById")
    public LogicZone getLogicZoneById(Long id) {
        LogicZone logicZone = null;
        try {
            if (null != id) {
                logicZone = logicZoneManager.getLogicZoneById(id);
            } else {
                LOG.warn("LogicZoneServiceImpl#getLogicZoneById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#getLogicZoneById has error.", e);
        }
        return logicZone;
    }
}

