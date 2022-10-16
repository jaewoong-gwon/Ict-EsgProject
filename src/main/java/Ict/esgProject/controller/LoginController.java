package Ict.esgProject.controller;

import Ict.esgProject.model.EnterprisesInfo;
import Ict.esgProject.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/esg")
public class LoginController {
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> loginInfo){
        return loginService.loginProcess(loginInfo);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkInfo(@RequestBody  Map<String,String> userInfo){
        EnterprisesInfo user = loginService.checkInfo(userInfo);
        if(user != null) return ResponseEntity.status(HttpStatus.OK).body(user);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 정보의 사용자가 없습니다. 다시 확인해주세요!");
    }

    @PostMapping("/change/pw")
    public ResponseEntity<String> changePassword(EnterprisesInfo changeInfo){
        if(loginService.changePassword(changeInfo)){
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 성공");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 변경 실패");
    }
}
