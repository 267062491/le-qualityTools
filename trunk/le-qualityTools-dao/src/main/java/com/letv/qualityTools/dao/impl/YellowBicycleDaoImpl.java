package com.letv.qualityTools.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.qualityTools.dao.LogicZoneDao;
import com.letv.qualityTools.dao.YellowBicycleDao;
import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.YellowBicycle;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.qualityTools.domain.query.YellowBicycleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
@Repository
public class YellowBicycleDaoImpl extends BaseDao implements YellowBicycleDao {
    /** namespace */
    private final String namespace = YellowBicycleDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<YellowBicycle> queryYellowBicycleList(YellowBicycleQuery queryBean) {
        return (List<YellowBicycle>) queryForList(namespace +".queryYellowBicycleList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(YellowBicycle yellowBicycle) {
        return insert(namespace +".insert", yellowBicycle);
    }

    public boolean update(YellowBicycle yellowBicycle) {
        return update(namespace +".update", yellowBicycle);
    }

}
