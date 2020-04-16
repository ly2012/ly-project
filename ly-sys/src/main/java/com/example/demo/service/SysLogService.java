package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.utils.PageUtils;
import com.example.demo.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
