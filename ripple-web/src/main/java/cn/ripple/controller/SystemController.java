package cn.ripple.controller;

import cn.hutool.core.util.StrUtil;
import cn.ripple.constant.SecurityConstant;
import cn.ripple.entity.User;
import cn.ripple.entity.wechat.Jscode2session;
import cn.ripple.enums.WechatErrCodeEnum;
import cn.ripple.exception.RippleException;
import cn.ripple.service.UserService;
import cn.ripple.service.WeChatService;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: Edwin
 * @Description: 系统相关接口
 */
@Slf4j
@RestController
@Api(description = "登录相关接口")
@RequestMapping("/system")
public class SystemController extends BaseController {
    @Value("${qky.tokenExpireTime}")
    private Integer tokenExpireTime;

    @Autowired
    UserService userService;

    @Autowired
    private WeChatService weChatService;


    @RequestMapping(value = "/ssoLogin", method = RequestMethod.POST)
    @ApiOperation(value = "微信单点登录接口")
    public String ssoLogin(@RequestBody User user) {
        if (StrUtil.isBlank(user.getWeChatCode())) {
            throw new RippleException("登录验证code不能为空");
        }
        Jscode2session jscode2session = weChatService.jscode2session(user.getWeChatCode());
        if (jscode2session.getErrcode() == WechatErrCodeEnum.SUCCESS.getValue()) {
            int expires_in = tokenExpireTime * 60 * 1000;
            user = userService.synBasUser(jscode2session.getOpenid(), user);
            userService.setCurUser(user);
            String token = Jwts.builder()
                    .setSubject(user.getWechatId())
                    .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(new ArrayList<>()))
                    .claim("userCode", user.getCode())
                    .setExpiration(new Date(System.currentTimeMillis() + expires_in))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                    .compact();
            return SecurityConstant.TOKEN_SPLIT + token;
        } else {
            throw new RippleException("from wechat:"+jscode2session.getErrcode()+":"+jscode2session.getErrmsg());
        }
    }
}
