package com.example.demo.service.impl;

import com.example.demo.common.utils.R;
import com.example.demo.common.utils.RedisUtils;
import com.example.demo.oauth2.TokenGenerator;
import com.example.demo.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {

	@Autowired
    private RedisUtils redisUtils;

	@Override
	public R createToken(long userId) {
		
		//生成一个token
		String tokenId = TokenGenerator.generateValue();
		//tokenId放到redis中，30分钟失效
		redisUtils.set(tokenId, userId, 30*60);
		R r = R.ok().put("token", tokenId);
		return r;
	}

	@Override
	public void logout(String token) {
		
		String userId = redisUtils.get(token);
		if(userId != null){
			redisUtils.delete(userId);
		}
	}
}
