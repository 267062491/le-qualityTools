package com.letv.qualityTools.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.qualityTools.dao.LogicZoneDao;
import com.letv.qualityTools.manager.LogicZoneManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * LogicZoneManager接口的实现类
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
@Component
public class LogicZoneManagerImpl extends BaseManager implements LogicZoneManager {
	
    @Autowired
    private LogicZoneDao logicZoneDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<LogicZone> logicZoneList) {
        boolean resultFlag = false;
        if (null != logicZoneList && logicZoneList.size() > 0) {
            for (LogicZone logicZone : logicZoneList) {
                resultFlag = logicZoneDao.insert(logicZone);
                if (!resultFlag) {
                    throw new RuntimeException("批量新增表信息异常");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(LogicZone logicZone) {
        return logicZoneDao.insert(logicZone);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final LogicZone logicZone) {
        return logicZoneDao.update(logicZone);
    }

    /**
     * {@inheritDoc}
     */
    public List<LogicZone> queryLogicZoneList(LogicZoneQuery queryBean) {
        return logicZoneDao.queryLogicZoneList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<LogicZone> queryLogicZoneListWithPage(LogicZoneQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new LogicZoneQuery();
        }

        // 查询总数
        int totalItem = queryLogicZoneCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return logicZoneDao.queryLogicZoneListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryLogicZoneCount(LogicZoneQuery queryBean) {
        return logicZoneDao.queryLogicZoneCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(LogicZone logicZone) {
        return logicZoneDao.delete(logicZone);
    }

    /**
     * {@inheritDoc}
     */
    public LogicZone getLogicZoneById(Long id) {
        return logicZoneDao.getLogicZoneById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final LogicZone[] logicZones) {
        boolean resultFlag = false;
        if (null != logicZones && logicZones.length > 0) {
            for (int i = 0; i < logicZones.length; i++) {
                resultFlag = delete(logicZones[i]);
                if (!resultFlag) {
                    throw new RuntimeException("批量删除表信息异常!");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(LogicZone logicZone) {
        return logicZoneDao.exist(logicZone);
    }
}
