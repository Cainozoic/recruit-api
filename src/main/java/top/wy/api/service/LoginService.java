package top.wy.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.wy.api.entity.UserEntity;
import top.wy.base.context.model.JwtSubjectModel;
import top.wy.base.response.ResResponse;
import top.wy.base.util.JwtTokenUtils;

@RestController
@Api(tags = "登录接口")
public class LoginService {

    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public ResResponse in(@RequestBody UserEntity model) {
        ResResponse successResponse = ResResponse.createSuccessResponse(null);
        if ("admin".equals(model.getUsername()) && "admin".equals(model.getPassword())) {
            successResponse.setBsCode(0);
            successResponse.setMsg("登录成功!");
            JwtSubjectModel loginUser = new JwtSubjectModel();
            loginUser.setId(1L);
            String token = JwtTokenUtils.createToken(loginUser);
            successResponse.setData(token);
        } else {
            successResponse.setBsCode(1);
            successResponse.setMsg("登录失败!");
        }
        return successResponse;
    }


}
