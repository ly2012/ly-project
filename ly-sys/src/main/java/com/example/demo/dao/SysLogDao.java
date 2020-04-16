package com.example.demo.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
