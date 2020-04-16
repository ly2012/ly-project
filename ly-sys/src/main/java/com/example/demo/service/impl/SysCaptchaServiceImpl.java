package com.example.demo.service.impl;

import com.example.demo.common.exception.RRException;
import com.example.demo.common.utils.RedisUtils;
import com.example.demo.service.SysCaptchaService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 * @author
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl implements SysCaptchaService {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new RRException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();
        redisUtils.set(uuid, code, 300);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
    	
    	
    	String codeEntity = redisUtils.get(uuid).toString();
    	if(codeEntity == null) {
    		return false;
    	}
    	if(codeEntity.equals(code)) {
    		return true;
    	}
        return false;
    }
}
