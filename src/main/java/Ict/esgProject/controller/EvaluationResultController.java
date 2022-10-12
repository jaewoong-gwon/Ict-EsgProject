package Ict.esgProject.controller;

import Ict.esgProject.service.EvaluationResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/esg")
public class EvaluationResultController {
    private EvaluationResultService evaluationResultService;

    @GetMapping("/select/result/all")
    public ResponseEntity<?> findEvalResult(@RequestParam Map<String,String> params){
        // email 을 parameter 로 받아서 평가 결과 List 조회.
        return evaluationResultService.getEvaluationResultList(params);
    }

    @GetMapping("/select/result")
    public ResponseEntity<?> findEval(@RequestParam Map<String,Object> params) throws ParseException {
        // email 을 parameter 로 받아서 db 조회. -> 상세 평가 결과(점수) 조회
        return evaluationResultService.getEvaluationResult(params);
    }

    @PostMapping("/insert/result")
    public ResponseEntity<?> createEval(@RequestBody Map<String,Object> params) throws ParseException {
        // Request Parameter 로 기업 담당자 이메일(ent_mrg_email), 평가 날짜(eval_date) 를 받아 전달.

        if(evaluationResultService.createEvaluationResult(params)){
            return evaluationResultService.getEvaluationResult(params);
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
    }
}
