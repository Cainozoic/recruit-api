package top.wy.base.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {

    /**
     * 加密key值
     */
    private static final String SECRET = "jwcx-vip";

    /**
     * 过期时间
     */
    private static final long EXPIRE = 60 * 60 * 1000;

    private static final String PAYLOAD = "payload";

    private static final String EXP = "exp";

    /*public static String createToken(String id, String subject) {
        long now = System.currentTimeMillis();
        long exp = now + EXPIRE;
        JwtBuilder builder = Jwts.builder()
                .setId(id)   //设置唯一编号
                .setSubject(subject)//设置主题  可以是JSON数据
                .setIssuedAt(new Date())//设置签发日期
                .setExpiration(new Date(exp))
                .signWith(SignatureAlgorithm.HS256, KEY);
        return builder.compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }*/

    public static <T> String createToken(T obj) {
        try {
            long now = System.currentTimeMillis();
            long exp = now + EXPIRE;
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(obj);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, exp);
            return signer.sign(claims);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseToken(String token, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(token);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*public static void main(String[] args) {
        JwtSubjectModel jwtSubjectModel = new JwtSubjectModel();
        jwtSubjectModel.setId(2L);
        String token = createToken(jwtSubjectModel);
        System.out.println(token);
        System.out.println(parseToken(token,JwtSubjectModel.class));
    }*/

}
