package top.wy.base.response;

import lombok.Data;

@Data
public class ResResponse {

    /**
     * 返回结果状态值,返回值为 0 或 1，0 表示请求失败；1 表示请求成功。
     */
    private Integer status;

    /**
     * 返回状态说明 当 status 为 0 时，info 会返回具体错误原因，否则返回“OK”。
     */
    private String info;

    /**
     * 业务编码
     */
    private Integer bsCode;

    /**
     * 业务反馈信息
     */
    private String msg;

    /**
     * ObjData
     */
    private Object data;

    /**
     * 扩展信息
     */
    private Object extra;

    private ResResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResResponse createSuccessResponse(String msg) {
        return new ResResponse(1, msg);
    }

    public static ResResponse createSuccessResponse(Object data) {
        return new ResResponse(1, "请求成功!", data);
    }

    public static ResResponse createSuccessResponse(String msg, Object data) {
        return new ResResponse(1, msg, data);
    }


}
