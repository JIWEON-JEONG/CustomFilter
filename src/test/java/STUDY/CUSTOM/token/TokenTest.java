package STUDY.CUSTOM.token;

import STUDY.CUSTOM.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenTest {

    @Autowired
    TokenService tokenService;

    @Test
    public void createToken() {
        User.builder()
                .email("test")
                .
    }
}
