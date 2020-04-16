package com.example.demo.service;

import com.example.demo.common.utils.R;

/**
 * 用户Token
 *
 * @author
 */
public interface SysUserTokenService{

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String token);

}
