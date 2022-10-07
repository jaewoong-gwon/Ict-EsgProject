package Ict.esgProject.controller;

import Ict.esgProject.service.NaverSignUpService;
import Ict.esgProject.service.SignUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
public class SnsSignUpController {

    private NaverSignUpService naverLoginService;
    private SignUpService signUpService;

    @GetMapping("login/oauth2/code/naver")
    public ResponseEntity<?> naver(@RequestParam Map<String,String> params){
        //프론트로부터 access_token 받아와서 Naver 에 UserInfo 요청
        // 이후 받아온 사용자 정보를 통한 db 확인, db 확인후 response return.
        return naverLoginService.getUserInfo(params.get("access_token"));
    }

    @PostMapping("esg/sign-up")
    public ResponseEntity<?> singUp(@RequestBody Map<String,String> signUpInfo){
        return signUpService.signUp(signUpInfo);
    }
}
