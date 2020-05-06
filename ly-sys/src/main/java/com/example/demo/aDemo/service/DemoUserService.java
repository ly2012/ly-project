package com.example.demo.aDemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.aDemo.entity.DemoUserEntity;
import com.example.demo.common.utils.PageUtils;
import java.util.Map;


/**
 * 测试用户
 *
 * @author
 */
public interface DemoUserService extends IService<DemoUserEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 根据用户名，查询测试用户
	 */
	DemoUserEntity queryByDemoName(String username);

	/**
	 * 保存用户
	 */
	void saveUser(DemoUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(DemoUserEntity user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);
}
