package com.letv.qualityTools.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.letv.common.utils.serialize.JsonHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.sdk.api.request.LogicZoneRequest;
import com.letv.qualityTools.sdk.api.response.dto.LogicZoneResponseDto;
import com.letv.qualityTools.service.LogicZoneService;

/**
 * 逻辑区储区对照表REST服务：提供有关逻辑区储区对照表的接口
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:07
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class LogicZoneResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private LogicZoneService logicZoneService; 

    /**
     * 查询逻辑区储区对照表信息
     * 
     * @param request
     *            逻辑区储区对照表请求参数
     * @return 逻辑区储区对照表返回对象
     * 
     */
    @POST
    @Path("/logicZone/getLogicZone")
    public Wrapper<?> getLogicZone(LogicZoneRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getLogicZone 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            LogicZone logicZone = logicZoneService.getLogicZoneById(request.getId());
            LogicZoneResponseDto responseDto = convert(logicZone);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询逻辑区储区对照表数据异常", e);
            return WrapMapper.error();
        }
    }


    // 数据转换
    private LogicZoneResponseDto convert(LogicZone logicZone) {
        if (null == logicZone) {
            return null;
        }

        LogicZoneResponseDto logicZoneResponseDto = new LogicZoneResponseDto();
        BeanUtils.copyProperties(logicZone, logicZoneResponseDto);
        return logicZoneResponseDto;
    }

    // 数据转换
    private List<LogicZoneResponseDto> convertList(List<LogicZone> logicZones) {
        if (CollectionUtils.isEmpty(logicZones)) {
            return null;
        }

        List<LogicZoneResponseDto> list = new ArrayList<LogicZoneResponseDto>(logicZones.size());
        for (LogicZone logicZone : logicZones) {
            list.add(convert(logicZone));
        }
        return list;
    }




}
