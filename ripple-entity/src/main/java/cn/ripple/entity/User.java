package cn.ripple.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author: Edwin
 * @Description: 用户表
 */
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column
	@ApiModelProperty(value = "唯一code")
	private String code;

	@Column
	@ApiModelProperty(value = "微信平台id")
	private String wechatId;

	@Column
	@ApiModelProperty(value = "账号名称")
	private String accountName;

	@Column
	@ApiModelProperty(value = "昵称")
	private String nickName;

	@Column
	@ApiModelProperty(value = "头像url")
	private String avatarUrl;

	@Column
	@ApiModelProperty(value = "手机号码")
	private String phone;

	@Transient
	@ApiModelProperty(value = "用户名")
	@TableField(exist=false)
	private String username;

	@Transient
	@ApiModelProperty(value = "密码")
	@TableField(exist=false)
	private String password;

	@Transient
	@ApiModelProperty(value = "微信登录验证code")
	@TableField(exist=false)
	private String weChatCode;

}
