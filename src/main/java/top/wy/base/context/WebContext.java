package top.wy.base.context;

import top.wy.base.context.model.JwtSubjectModel;
import top.wy.base.util.JwtTokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {

    private static final ThreadLocal<String> THREAD_LOCAL_TOKEN = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> THREAD_LOCAL_REQUEST = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> THREAD_LOCAL_RESPONSE = new ThreadLocal<>();
    private static final ThreadLocal<JwtSubjectModel> THREAD_LOCAL_CURRENT_USER = new ThreadLocal<>();

    public static String getToken() {
        return THREAD_LOCAL_TOKEN.get();
    }

    public static Long getCurrentUserId() {
        JwtSubjectModel jwtSubjectModel = THREAD_LOCAL_CURRENT_USER.get();
        if (jwtSubjectModel == null) {
            return -1L;
        } else {
            return jwtSubjectModel.getId();
        }
    }

    public static HttpServletRequest getRequest() {
        return THREAD_LOCAL_REQUEST.get();
    }

    public static HttpServletResponse getResponse() {
        return THREAD_LOCAL_RESPONSE.get();
    }

    public static void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        THREAD_LOCAL_REQUEST.set(request);
        THREAD_LOCAL_RESPONSE.set(response);
    }

    public static void setToken(String token) {
        THREAD_LOCAL_TOKEN.set(token);
        if ("".equals(token) || token == null) {
            THREAD_LOCAL_CURRENT_USER.set(null);
            return;
        }
        JwtSubjectModel jwtSubjectModel = JwtTokenUtils.parseToken(token, JwtSubjectModel.class);
        THREAD_LOCAL_CURRENT_USER.set(jwtSubjectModel);
    }

}
