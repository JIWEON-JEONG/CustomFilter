package STUDY.CUSTOM.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto.UserRequestDto requestDto) throws Exception {
        try {
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .password(requestDto.getPassword())
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("회원가입 Exception");
        }

        return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto.UserRequestDto requestDto) throws Exception {
        try {
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .password(requestDto.getPassword())
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("회원가입 Exception");
        }

        return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
    }
}
