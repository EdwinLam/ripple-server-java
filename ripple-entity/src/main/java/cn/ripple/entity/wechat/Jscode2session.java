package cn.ripple.entity.wechat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录凭证校验s
 * @Author: Edwin
 * @Description:
 */
@Data
public class Jscode2session {
    @ApiModelProperty(value = "用户唯一标识")
    private String openid;

    @ApiModelProperty(value = "会话密钥")
    private String session_key;

    @ApiModelProperty(value = "用户在开放平台的唯一标识符")
    private String unionid;

    @ApiModelProperty(value = "错误码")
    private Integer errcode;

    @ApiModelProperty(value = "错误信息")
    private String errmsg;
}
