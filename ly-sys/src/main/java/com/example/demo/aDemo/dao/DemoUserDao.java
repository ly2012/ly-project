package com.example.demo.aDemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.aDemo.entity.DemoUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author
 */
@Mapper
public interface DemoUserDao extends BaseMapper<DemoUserEntity> {
	
	
	DemoUserEntity queryByDemoName(String username);

}
