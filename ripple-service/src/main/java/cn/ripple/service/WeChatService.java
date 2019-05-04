package cn.ripple.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.ripple.entity.wechat.Jscode2session;
import cn.ripple.enums.WechatErrCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @Author Edwin
 * @Description: 用户表接口
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatService {
    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String secret;

    @Value("${weixin.apiUrl}")
    private String apiUrl;

    /**
     * 登录凭证校验
     * @param jsCode
     * @return
     */
    public Jscode2session jscode2session(String jsCode){
        HashMap<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("appid", appid);
        paramMap.put("secret",secret);
        paramMap.put("js_code", jsCode);
        paramMap.put("grant_type", "authorization_code");
        String jsonResultStr= HttpUtil.get(apiUrl+"/sns/jscode2session", paramMap);
        JSONObject jsonObject= JSONUtil.parseObj(jsonResultStr);
        Jscode2session jscode2session = new Jscode2session();
        BeanUtil.copyProperties(jsonObject,jscode2session);
        if(jscode2session.getOpenid()!=null){
            jscode2session.setErrcode(WechatErrCodeEnum.SUCCESS.getValue());
        }
        return jscode2session;
    }

}
