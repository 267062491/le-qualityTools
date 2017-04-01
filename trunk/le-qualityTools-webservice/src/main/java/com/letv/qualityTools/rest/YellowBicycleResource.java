package com.letv.qualityTools.rest;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.qualityTools.domain.YellowBicycle;
import com.letv.qualityTools.domain.query.YellowBicycleQuery;
import com.letv.qualityTools.service.YellowBicycleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wangshanjing
 * @version 2017-2-12 20:46:07
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class YellowBicycleResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private YellowBicycleService yellowBicycleService;

    /**
     * 老接口， 不建议使用了
     * 保存车牌号码和密码
     * @param info
     * @return
     */
    @Deprecated
    @POST
    @Path("/yellowBicycle/submitInfo")
    public Wrapper<?> submitInfo(String info ){
        try {
            String[] infos = info.split("&");
            YellowBicycle yellowBicycle = new YellowBicycle () ;
            for(String fo :infos){
                String[] value = fo.split("=");
                if(fo.contains("code")){
                    yellowBicycle.setCode(value[1]);
                } else if(fo.contains("password")){
                    yellowBicycle.setPassword(value[1]);
                } else if(fo.contains("createUser")){
                    yellowBicycle.setCreateUser(value[1]);
                    yellowBicycle.setUpdateUser(value[1]);
                }
            }
            yellowBicycle.setUpdateTime(new Date());
            yellowBicycle.setCreateTime(new Date());
            yellowBicycle.setState("0");
            yellowBicycleService.insert(yellowBicycle);
            return WrapMapper.ok().result("谢谢，辛苦了");
        } catch (Exception e) {
            return WrapMapper.error();
        }
    }

    /**
     * 老接口， 不建议使用了
     * 根据车牌号码，查询车牌密码
     * @param info
     * @return
     */
    @Deprecated
    @POST
    @Path("/yellowBicycle/searchInfo")
    public Wrapper<?> searchInfo(String info ){
        try {
            YellowBicycleQuery queryBean = new YellowBicycleQuery () ;
            queryBean.setCode(info.split("=")[1]);
            List<YellowBicycle> list =  yellowBicycleService.queryYellowBicycleList(queryBean);
            return WrapMapper.ok().result(list);
        } catch (Exception e) {
            return WrapMapper.error();
        }
    }



    /**
     * 保存车牌号码和密码
     * @param yellowBicycle
     * @return
     */
    @POST
    @Path("/yellowBicycle/submitInfoNew")
    public Wrapper<?> submitInfoNew(YellowBicycle yellowBicycle ){
        try {
            yellowBicycle.setUpdateTime(new Date());
            yellowBicycle.setCreateTime(new Date());
            yellowBicycle.setState("0");
            yellowBicycleService.insert(yellowBicycle);
            return WrapMapper.ok().result("谢谢，辛苦了");
        } catch (Exception e) {
            return WrapMapper.error();
        }
    }

    /**
     * 根据车牌号码，查询车牌密码
     * @param yellowBicycleQuery
     * @return
     */
    @POST
    @Path("/yellowBicycle/searchInfoNew")
    public Wrapper<?> searchInfoNew(YellowBicycleQuery yellowBicycleQuery ){
        try {
            List<YellowBicycle> list =  yellowBicycleService.queryYellowBicycleList(yellowBicycleQuery);
            return WrapMapper.ok().result(list);
        } catch (Exception e) {
            return WrapMapper.error();
        }
    }
}
