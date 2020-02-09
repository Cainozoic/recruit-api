package top.wy.base.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("BaseEntity")
public class BaseEntity {

    @ApiModelProperty(name = "id",value = "id")
    protected Long id;

    @ApiModelProperty(name = "createTime",value = "创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime",value = "更新时间")
    protected LocalDateTime updateTime;

}
