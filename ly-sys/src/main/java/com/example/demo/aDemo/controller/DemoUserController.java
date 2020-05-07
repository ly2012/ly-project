package com.example.demo.aDemo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.aDemo.entity.DemoUserEntity;
import com.example.demo.aDemo.service.DemoUserService;
import com.example.demo.common.annotation.SysLog;
import com.example.demo.common.utils.PageUtils;
import com.example.demo.common.utils.R;
import com.example.demo.common.validator.ValidatorUtils;
import com.example.demo.common.validator.group.AddGroup;
import com.example.demo.common.validator.group.UpdateGroup;
import com.example.demo.controller.AbstractController;
import java.util.Map;

/**
 * 系统用户
 *
 * @author
 */
@RestController
@RequestMapping("/demo/user")
public class DemoUserController extends AbstractController {
	@Autowired
	private DemoUserService demoUserService;

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("demo:user:list")
	public R list(@RequestParam Map<String, Object> params){
		
		PageUtils page = demoUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("demo:user:info")
	public R info(@PathVariable("userId") Long userId){
		DemoUserEntity user = demoUserService.getById(userId);		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("demo:user:save")
	public R save(@RequestBody DemoUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		demoUserService.saveUser(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("demo:user:update")
	public R update(@RequestBody DemoUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		demoUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("demo:user:delete")
	public R delete(@RequestBody Long[] userIds){
		
		demoUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
