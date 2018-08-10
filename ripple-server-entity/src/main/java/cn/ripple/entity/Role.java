package cn.ripple.entity;

import cn.ripple.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Edwin
 */
@Data
@Entity
@Table(name = "r_role")
@TableName("r_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父角色id")
    private String parentId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}