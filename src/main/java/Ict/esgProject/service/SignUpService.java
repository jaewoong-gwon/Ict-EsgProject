package Ict.esgProject.service;

import Ict.esgProject.model.Enterprises;
import Ict.esgProject.model.EnterprisesMrg;
import Ict.esgProject.repository.EnterPrisesMrgMapper;
import Ict.esgProject.repository.EnterprisesMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SignUpService {
    private final EnterprisesMapper enterprisesMapper;
    private final EnterPrisesMrgMapper enterPrisesMrgMapper;

    //기업 담당자
    public ResponseEntity<?> signUp(Map<String,String> userInfo){
        Enterprises enterprises = new Enterprises();
        //mapper 에 전달할 Enterprises model 객체 생성 후 세팅.
        enterprises.setEntMrgEmail(userInfo.get("ent_mrg_email"));
        enterprises.setEntName(userInfo.get("ent_name"));
        enterprises.setEntCat(userInfo.get("ent_cat"));
        enterprises.setEntDetailsCat(userInfo.get("ent_details_cat"));
        enterprises.setEntMajorProd(userInfo.get("ent_major_prod"));
        enterprises.setEntMajorClnt(userInfo.get("ent_major_clnt"));
        enterprises.setEntCert(0);
        enterprises.setEntRegNo(userInfo.get("ent_reg_no"));

        // db 에 기업 정보 저장.
        enterprisesMapper.createEnterprises(enterprises);

        // db 에 비밀번호 세팅.
        enterPrisesMrgMapper.changePw(userInfo.get("ent_mrg_pw"),userInfo.get("ent_mrg_email"));


        Map<String,String> response = new HashMap<>();

        response.put("ent_mrg_email",enterprises.getEntMrgEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
