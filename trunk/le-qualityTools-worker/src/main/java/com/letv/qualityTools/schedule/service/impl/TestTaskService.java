package com.letv.qualityTools.schedule.service.impl;

import org.perf4j.aop.Profiled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.letv.schedule.core.BusinessService;
import com.letv.schedule.domain.Task;

/**
 * 测试 任务
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:07
 * 
 */
@Service("testTaskService")
public class TestTaskService implements BusinessService<Task> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTaskService.class);

    @Profiled(tag = "TestTaskService.executeSingleTask")
    public boolean executeSingleTask(final Task task, final String ownSign) throws Exception {
        LOGGER.info("TestTaskService do something: id" + task.getTaskId());

        return true;
    }

}
