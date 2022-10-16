package Ict.esgProject.service;

import Ict.esgProject.model.EnterprisesInfo;
import Ict.esgProject.repository.EnterprisesInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class LoginService {
    private final EnterprisesInfoMapper enterprisesInfoMapper;

    public ResponseEntity<?> loginProcess(Map<String,String> loginInfo){
        String enterPrisesMrgEmail = loginInfo.get("ent_mrg_email");
        String enterPrisesMrgInputPw = loginInfo.get("ent_mrg_pw");

        //db 조회
        EnterprisesInfo enterprisesInfo = enterprisesInfoMapper.findByEmail(enterPrisesMrgEmail);

        Map<String,Object> response = new HashMap<>();

        if (enterprisesInfo == null) {
            response.put("message","가입 되지 않은 회원입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            if(enterprisesInfo.getEntMrgPw().equals(enterPrisesMrgInputPw)){
                response.put("message","로그인 성공!");
                response.put("enterprisesInfo",enterprisesInfo);
                response.put("role","user");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("message","비밀번호가 틀렸습니다!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
    }

    public EnterprisesInfo checkInfo(Map<String,String> userInfo){
        EnterprisesInfo check = new EnterprisesInfo();
        check.setEntMrgEmail(userInfo.get("ent_mrg_email"));
        check.setEntMrgMobile(userInfo.get("ent_mrg_mobile"));
        EnterprisesInfo user = enterprisesInfoMapper.findEnterprisesInfo(check);
        if( user != null ) return user;
        else return null;
    }

    public boolean changePassword(EnterprisesInfo changeInfo){
        int res = enterprisesInfoMapper.changePw(changeInfo);

        if(res > 0 ) return true;
        else return false;
    }
}
