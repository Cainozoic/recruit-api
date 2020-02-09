package top.wy.api.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import top.wy.base.dao.entity.BaseEntity;

@ApiModel("用户实体")
@Data
public class UserEntity extends BaseEntity {

    private String username;

    private String password;

    private String email;

}
