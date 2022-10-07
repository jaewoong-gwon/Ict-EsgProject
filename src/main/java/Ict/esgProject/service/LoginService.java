package Ict.esgProject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class LoginService {
    private final EnterPrisesMrgMapper enterPrisesMrgMapper;

    public ResponseEntity<?> loginProcess(Map<String,String> loginInfo){
        String enterPrisesMrgEmail = loginInfo.get("email");
        String enterPrisesMrgPw = loginInfo.get("pw");

        //db 조회
        EnterprisesMrg enterprisesMrg = enterPrisesMrgMapper.findByEmail(enterPrisesMrgEmail);

        Map<String,String> response = new HashMap<>();

        if (enterprisesMrg == null) {
            response.put("message","가입 되지 않은 회원입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            if(enterprisesMrg.getEntMrgPw().equals(enterPrisesMrgPw)){
                response.put("message","로그인 성공!");
                response.put("ent_mrg_email",enterPrisesMrgPw);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("message","비밀번호가 틀렸습니다!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
    }
}
