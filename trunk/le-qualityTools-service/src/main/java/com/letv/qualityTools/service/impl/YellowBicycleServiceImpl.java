package com.letv.qualityTools.service.impl;

import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.YellowBicycle;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.qualityTools.domain.query.YellowBicycleQuery;
import com.letv.qualityTools.manager.LogicZoneManager;
import com.letv.qualityTools.manager.YellowBicycleManager;
import com.letv.qualityTools.service.LogicZoneService;
import com.letv.qualityTools.service.YellowBicycleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
@Service
public class YellowBicycleServiceImpl implements YellowBicycleService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(YellowBicycleServiceImpl.class);

    @Autowired
    private YellowBicycleManager yellowBicycleManager;


    /**
     * {@inheritDoc}
     */
    public boolean insert(YellowBicycle yellowBicycle) {
        boolean resultFlag = false;
        try {
            YellowBicycleQuery queryBean = new YellowBicycleQuery () ;
            queryBean.setCode(yellowBicycle.getCode());
            queryBean.setPassword(yellowBicycle.getPassword());
            List<YellowBicycle> list = this.queryYellowBicycleList(queryBean);
            if(CollectionUtils.isEmpty(list)){
                resultFlag = yellowBicycleManager.insert(yellowBicycle);
            }else {
                yellowBicycle.setState("1");
                yellowBicycleManager.update(yellowBicycle);
            }
        } catch (ExistedException e) {
            throw e;
        } catch (Exception e) {

        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(YellowBicycle yellowBicycle) {
        boolean resultFlag = false;
        try {
            if (null != yellowBicycle && null != yellowBicycle.getId()) {
                resultFlag = yellowBicycleManager.update(yellowBicycle);
            } else {
            }
        } catch (Exception e) {
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public List<YellowBicycle> queryYellowBicycleList(YellowBicycleQuery queryBean) {
        List<YellowBicycle> logicZoneList = null;
        try {
            logicZoneList = yellowBicycleManager.queryYellowBicycleList(queryBean);
        } catch (Exception e) {
            LOG.error("LogicZoneServiceImpl#queryLogicZoneList has error.", e);
        }
        return logicZoneList;
    }

}

