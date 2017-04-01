package com.letv.qualityTools.controller;
   

import java.io.File;
import java.util.List;

import com.letv.common.utils.serialize.JsonHelper;
import com.letv.portal.sdk.dto.User;
import com.letv.qualityTools.utils.constant.JsonHelperImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letv.qualityTools.domain.LogicZone;
import com.letv.qualityTools.domain.query.LogicZoneQuery;
import com.letv.qualityTools.service.LogicZoneService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * LogicZoneController ：逻辑区储区对照表控制器
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
*/
@Controller
@RequestMapping("logicZone")
public class LogicZoneController extends BaseController {

    @Autowired
    private LogicZoneService logicZoneService;

    /** 视图前缀 */
    private static final String viewPrefix ="logicZone";
    
    private static final Log LOG = LogFactory.getLog(LogicZoneController.class);

    /**
     * 首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        return viewPrefix + "/index";
    }
    
    /**
     * 分页 查询数据
     * 
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPage")
    public String queryByPage(Model model, PageUtil page, LogicZoneQuery query) {
        try {
            List<LogicZone> dataList = logicZoneService.queryLogicZoneListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("logicZone queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 逻辑区储区对照表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 逻辑区储区对照表----添加
     * 
     * @param logicZone
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(LogicZone logicZone) {
        try {
            logicZone.setCreateUser(getLoginUserCnName());
            if (logicZoneService.insert(logicZone)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("logicZone add fail, exist logicZone.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("logicZone add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 逻辑区储区对照表----更新跳转
     * 
     * @param model
     * @param logicZone
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, LogicZone logicZone) {
        try {
            LogicZone logicZoneResult = logicZoneService.getLogicZoneById(logicZone.getId());
            model.addAttribute("logicZone", logicZoneResult);
        } catch (Exception e) {
            LOG.error("logicZone updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 逻辑区储区对照表----更新
     * 
     * @param model
     * @param logicZone
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, LogicZone logicZone) {
        try {
            logicZone.setUpdateUser(getLoginUserCnName());
            if (logicZoneService.update(logicZone)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("logicZone update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 逻辑区储区对照表----删除
     * 
     * @param logicZone
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(LogicZone logicZone) {
        try {
            logicZone.setUpdateUser(getLoginUserCnName());
            if (logicZoneService.delete(logicZone)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("logicZone delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 逻辑区储区对照表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(LogicZoneQuery query) {
        try {
            List<LogicZone> list = logicZoneService.queryLogicZoneList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("logicZone query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询逻辑区储区对照表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(LogicZoneQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            LogicZone logicZone = logicZoneService.getLogicZoneById(query.getId());
            if (logicZone != null) {
                return new Wrapper<LogicZone>().result(logicZone);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询逻辑区储区对照表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail logicZone has error.", e);
            return error();
        }
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, LogicZone logicZone) {
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        // String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String aa = "[{\"key0\":\"11\",\"key1\":\"/Date(22)/\",\"key2\":\"33\"},{\"key0\":\"44\",\"key1\":\"/Date(55)/\",\"key2\":\"66\"},{\"key0\":\"77\",\"key1\":\"/Date(88)/\",\"key2\":\"99\"}]" ;
        List<Object> list = JsonHelperImpl.jsonFormatArrayToListBean(LogicZone.class,aa);
        JSONArray json = JSONArray.fromObject(logicZone.getLogicNo() );

        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象

                System.out.println(job.get("name")+"=") ;  // 得到 每个对象中的属性值
            }
        }

        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
        return new Wrapper<LogicZone>().result(logicZone);
    }
}
