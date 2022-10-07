package Ict.esgProject.controller;

import Ict.esgProject.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
public class LoginController {
    private LoginService loginService;

    @PostMapping("/esg/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> loginInfo){
        return loginService.loginProcess(loginInfo);
    }
}
