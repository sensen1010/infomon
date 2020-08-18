package com.zhixue.infomon.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhixue.infomon.entity.Users;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;


/**
 * @author sensen
 * @date 2020-07-21 15:53
 */
@Service("TokenService")
public class TokenService {

    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
   //  private static final long EXPIRE_TIME = 30*1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    public String getToken(Users user) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        //        //私钥及加密算法
//        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//        //设置头信息
//        HashMap<String, Object> header = new HashMap<>(2);
//        header.put("typ", "JWT");
//        header.put("alg", "HS256");
//        //附带username和userID生成签名
//        return JWT.create().withHeader(header).
//                withClaim("no",user.getNo())
//               .withClaim("pow",user.getPow()).
//                        withExpiresAt(date).sign(algorithm);

        String token = "";
        System.out.println(user);
        token = JWT.create().withAudience(user.getNo())// 将 user id 保存到 token 里面
                .withExpiresAt(date)//设置过期时间
                .sign(Algorithm.HMAC256(user.getPow()));// 以 password 作为 token 的密钥
        System.out.println("发送"+token);
        return token;
    }
}
