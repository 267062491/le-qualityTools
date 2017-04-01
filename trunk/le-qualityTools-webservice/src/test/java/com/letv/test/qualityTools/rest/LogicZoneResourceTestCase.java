package com.letv.test.qualityTools.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.qualityTools.rest.request.LogicZoneRequest;
import com.letv.test.qualityTools.rest.response.LogicZoneResponse;
import com.letv.test.qualityTools.rest.response.dto.LogicZone;
import com.letv.test.qualityTools.rest.Urls;

/**
 * LogicZoneResource单元测试
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:07
 * 
 */
public class LogicZoneResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetLogicZone() {
        String url= Urls.API_DOMAIN + "services/logicZone/getLogicZone";

        LogicZoneRequest request = new LogicZoneRequest();
        request.setId(1l);
        LogicZoneResponse response = super.getRestTemplate().postForObject(url, request, LogicZoneResponse.class);
        Assert.notNull(response);
        LogicZone logicZone = super.getResult(response);
        Assert.notNull(logicZone);
    }
}
