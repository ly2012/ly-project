package com.example.demo.aDemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.aDemo.dao.DemoUserDao;
import com.example.demo.aDemo.entity.DemoUserEntity;
import com.example.demo.aDemo.service.DemoUserService;
import com.example.demo.common.exception.RRException;
import com.example.demo.common.utils.Constant;
import com.example.demo.common.utils.PageUtils;
import com.example.demo.common.utils.Query;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 测试用户
 *
 * @author
 */
@Service("sysUserService")
public class DemoUserServiceImpl extends ServiceImpl<DemoUserDao, DemoUserEntity> implements DemoUserService {
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Long createUserId = (Long)params.get("createUserId");

		IPage<DemoUserEntity> page = this.page(
			new Query<DemoUserEntity>().getPage(params),
			new QueryWrapper<DemoUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"user_id", createUserId)
		);

		return new PageUtils(page);
	}

	@Override
	public DemoUserEntity queryByDemoName(String username) {
		return baseMapper.queryByDemoName(username);
	}

	@Override
	@Transactional
	public void saveUser(DemoUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.save(user);
	}

	@Override
	@Transactional
	public void update(DemoUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		DemoUserEntity userEntity = new DemoUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<DemoUserEntity>().eq("user_id", userId).eq("password", password));
	}

}