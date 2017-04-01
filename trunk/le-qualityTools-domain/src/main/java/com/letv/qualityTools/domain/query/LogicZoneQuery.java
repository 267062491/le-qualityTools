package com.letv.qualityTools.domain.query;

import java.util.Date;

import com.letv.common.utils.page.Query;

/**
 * LogicZoneQuery：逻辑区储区对照表查询类
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:06
 * 
 */
public class LogicZoneQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 库房编码 */
	private String warehouseNo; 
    /** 逻辑区id */
	private String logicNo; 
    /** 储区id */
	private String zoneNo; 
    /** 创建时间 */
	private Date createTime; 
    /** 修改时间 */
	private Date updateTime; 
    /** 创建人 */
	private String createUser; 
    /** 修改人 */
	private String updateUser; 
    /** 删除标识（0：删除；1：未删除） */
	private Integer yn; 
    /** 开始时间 */
    private Date startTime; 
    /** 结束时间 */
    private Date endTime; 
	
    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getWarehouseNo(){
		return warehouseNo;
	}
	    
    public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	
    public String getLogicNo(){
		return logicNo;
	}
	    
    public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}
	
    public String getZoneNo(){
		return zoneNo;
	}
	    
    public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	
    public Date getCreateTime(){
		return createTime;
	}
	    
    public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
    public Date getUpdateTime(){
		return updateTime;
	}
	    
    public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
    public String getCreateUser(){
		return createUser;
	}
	    
    public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
    public String getUpdateUser(){
		return updateUser;
	}
	    
    public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
    public Integer getYn(){
		return yn;
	}
	    
    public void setYn(Integer yn) {
		this.yn = yn;
	}

    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
