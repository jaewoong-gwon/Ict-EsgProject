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
public class SignUpService {
    private final EnterprisesInfoMapper enterprisesInfoMapper;

    //기업 담당자
    public ResponseEntity<?> signUp(Map<String,String> userInfo){
        EnterprisesInfo enterprisesInfo = new EnterprisesInfo();

        //mapper 에 전달할 EnterprisesInfo model 객체 생성
        //Enterprises 정보 세팅
        enterprisesInfo.setEntMrgEmail(userInfo.get("ent_mrg_email"));
        enterprisesInfo.setEntName(userInfo.get("ent_name"));
        enterprisesInfo.setEntCat(userInfo.get("ent_cat"));
        enterprisesInfo.setEntDetailsCat(userInfo.get("ent_details_cat"));
        enterprisesInfo.setEntMajorProd(userInfo.get("ent_major_prod"));
        enterprisesInfo.setEntMajorClnt(userInfo.get("ent_major_clnt"));
        enterprisesInfo.setEntCert(0);
        enterprisesInfo.setEntRegNo(userInfo.get("ent_reg_no"));
        //Enterprises_mrg 정보 세팅
        enterprisesInfo.setEntMrgEmail(userInfo.get("ent_mrg_email"));
        enterprisesInfo.setEntMrgPw(userInfo.get("ent_mrg_pw"));
        enterprisesInfo.setEntMrgName(userInfo.get("ent_mrg_name"));
        enterprisesInfo.setEntMrgMobile(userInfo.get("ent_mrg_mobile"));
        enterprisesInfo.setEntMrgSns(userInfo.get("ent_mrg_sns"));


        // db 에 기업 담당자 정보 저장.
        enterprisesInfoMapper.createEnterprisesMrg(enterprisesInfo);

        // db 에 기업 정보 저장. -> 기업이 기엄 담당자 정보 중 email 을 fk 로 가지므로, 기업 담당자 정보 저장이 선행되어야함.
        enterprisesInfoMapper.createEnterprises(enterprisesInfo);

        Map<String,String> response = new HashMap<>();

        response.put("ent_mrg_email",enterprisesInfo.getEntMrgEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
