package STUDY.CUSTOM.token;

import STUDY.CUSTOM.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class TokenService {

    private final Base64.Decoder decoder;
    private final Base64.Encoder encoder;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;
    private final int EXPIRE_SECONDS = 60 * 60;

    public TokenService() {
        this.decoder = Base64.getUrlDecoder();
        this.encoder = Base64.getEncoder();
    }

    /**
     * 유저 데이터를 JWT로 암호화 하는 함수.
     * @param user User 객체
     * @return String 생성된 JWT String
     */
    public String encode(User user) {
        Date now = new Date();
        return Jwts.builder()
                //따로 config 로 빼줘도 좋음 - JWT 라는 value 값
                .setHeaderParam("type","JWT")
                .setIssuer("JIWEON-JEONG")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(EXPIRE_SECONDS).toMillis()))
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .signWith(SignatureAlgorithm.HS256, encoder.encodeToString(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    /**
     * JWT String 을 Map 으로 파싱해주는 함수.
     * @param token JWT String
     * @return Map<String, Object> key, value 형태의 Map 데이터
     * @throws ParseException 파싱 실패 예외처리
     */
    public Map<String, Object> parse(String token) throws ParseException {
        String[] chunks = token.split("\\.");
        String jwtBodyString = new String(decoder.decode(chunks[1]));
        JSONParser parser = new JSONParser(jwtBodyString);
        return parser.parseObject();
    }

}
