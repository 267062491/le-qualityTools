package com.letv.qualityTools.manager.impl;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.qualityTools.dao.LogicZoneDao;
import com.letv.qualityTools.dao.YellowBicycleDao;
import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.YellowBicycle;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.qualityTools.domain.query.YellowBicycleQuery;
import com.letv.qualityTools.manager.LogicZoneManager;
import com.letv.qualityTools.manager.YellowBicycleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
@Component
public class YellowBicycleManagerImpl extends BaseManager implements YellowBicycleManager {
	
    @Autowired
    private YellowBicycleDao  yellowBicycleDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(YellowBicycle yellowBicycle) {
        return yellowBicycleDao.insert(yellowBicycle);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final YellowBicycle yellowBicycle) {
        return yellowBicycleDao.update(yellowBicycle);
    }

    /**
     * {@inheritDoc}
     */
    public List<YellowBicycle> queryYellowBicycleList(YellowBicycleQuery queryBean) {
        return yellowBicycleDao.queryYellowBicycleList(queryBean);
    }
}
