package Ict.esgProject.controller;

import Ict.esgProject.model.EnterprisesInfo;
import Ict.esgProject.repository.EnterprisesInfoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor
public class testController {

    private EnterprisesInfoMapper enterprisesInfoMapper;

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestParam Map<String,String> params){
        EnterprisesInfo enterprisesInfo = enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email"));
        return ResponseEntity.status(HttpStatus.OK).body(enterprisesInfo);
    }

    @PostMapping("/change")
    public ResponseEntity<?> test2(@RequestBody Map<String,String> params) {
        enterprisesInfoMapper.changePw(params.get("ent_mrg_pw"), params.get("ent_mrg_email"));
        EnterprisesInfo enterprisesInfo = enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email"));
        return ResponseEntity.status(HttpStatus.OK).body(enterprisesInfo);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> test3(@RequestBody Map<String,String> params) {
        EnterprisesInfo test = new EnterprisesInfo();
        test.setEntMrgEmail(params.get("ent_mrg_email"));
        test.setEntMrgPw(params.get("ent_mrg_pw"));
        test.setEntMrgName(params.get("ent_mrg_name"));
        test.setEntMrgMobile(params.get("ent_mrg_mobile"));
        test.setEntMrgSns("NAVER");

        //enterprisesInfoMapper.createEnterprisesMrg(test);
        enterprisesInfoMapper.deleteEnterprisesMrg(test.getEntMrgEmail());

        //EnterprisesInfo enterprisesInfo = enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email"));
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }
}
