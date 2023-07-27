package cn.edu.whut.springbear.course.common.util.alogrithm;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-27 20:16
 */
public class JwtHelper {
    /**
     * 创建 token 字符串
     *
     * @param claimsMap 需要加密的内容 key&val 集合
     * @param signKey   自定义的加密字符串
     * @param expires   有效时间，单位为毫秒
     * @return token
     */
    public static String createToken(Map<String, Object> claimsMap, String signKey, Long expires) {
        JwtBuilder builder = Jwts.builder();
        builder.setSubject("USER-TOKEN")
                .setExpiration(new Date(System.currentTimeMillis() + expires))
                .signWith(SignatureAlgorithm.HS512, signKey);

        // 遍历所有需要加密内容的 key&val
        Set<Map.Entry<String, Object>> entries = claimsMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            builder.claim(entry.getKey(), entry.getValue());
        }
        return builder.compressWith(CompressionCodecs.GZIP).compact();
    }

    /**
     * 根据 key 从 token 中获取 key 对应的 value 值
     *
     * @param token   token
     * @param key     key
     * @param signKey 生成 token 时指定的加密字符串
     * @return key 在 token 中 value 值
     */
    public static Object get(String token, String key, String signKey) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get(key);
    }
}
