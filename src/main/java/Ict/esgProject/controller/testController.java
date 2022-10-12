package Ict.esgProject.controller;

import Ict.esgProject.model.EnterprisesInfo;
import Ict.esgProject.repository.EnterprisesInfoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/esg")
public class testController {
    private EnterprisesInfoMapper enterprisesInfoMapper;

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestParam Map<String,String> asd) {
//        EnterprisesInfo enterprisesInfo = enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email"));
//        log.debug("aa", new Throwable());
//        return ResponseEntity.status(HttpStatus.OK).body(enterprisesInfo);
        return null;
        }

    @GetMapping("/select")
    public ResponseEntity<?> tt(@RequestParam Map<String,String> params){
        Map<String,Object> res = new HashMap<>();
        res.put("message","fuck");
        res.put("enterprisesInfo",enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email")));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/change")
    public ResponseEntity<?> test2(@RequestBody Map<String,String> params) {
        int res = enterprisesInfoMapper.changePw(params.get("ent_mrg_pw"), params.get("ent_mrg_email"));
        if(res > 0) return ResponseEntity.status(HttpStatus.OK).body(enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email")));
        else return null;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> test3(@RequestBody HashMap<String,String> params) {
        int idx = enterprisesInfoMapper.findIdxByEmail(params.get("ent_mrg_email"));
        EnterprisesInfo test = new EnterprisesInfo();
        test.setEntMrgEmail(params.get("ent_mrg_email"));
        test.setEntMrgPw(params.get("ent_mrg_pw"));
        test.setEntMrgMobile(params.get("ent_mrg_mobile"));
        test.setEntMrgName(params.get("ent_mrg_name"));
        test.setEntMrgSns("NAVER");
        test.setEntIdx(idx);

        String res = "";
        int result = enterprisesInfoMapper.createEnterprisesMrg(test);
        if(result >0) res = "성공";
        else res ="실패";

//        enterprisesInfoMapper.deleteEnterprisesMrg(test.getEntMrgEmail());

        //EnterprisesInfo enterprisesInfo = enterprisesInfoMapper.findByEmail(params.get("ent_mrg_email"));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
