package Ict.esgProject.service;

import Ict.esgProject.model.*;
import Ict.esgProject.repository.EvaluationResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class EvaluationResultService {
    private final EvaluationResultMapper evaluationResultMapper;

    public boolean createEvaluationResult(String entMrgEmail){
        EvaluationResult evaluationResult = new EvaluationResult();
        evaluationResult.setEntMrgEmail(entMrgEmail);

        Date regDate = Calendar.getInstance().getTime();
        evaluationResult.setEvalDate(regDate);

        int result = evaluationResultMapper.createEvaluation(evaluationResult);
        if(result > 0 ) return true;
        else return false;
    }

    public ResponseEntity<?> getEvaluationResult(Map<String,String> params){
        EvaluationResult res = evaluationResultMapper.findEvaluationByEmail(params.get("ent_mrg_email"));
        if(res != null){
            List<EvalCat> publicList = evaluationResultMapper.findListPublicByIdx(res.getEvalResultIdx());
            List<EvalCat> environmentList = evaluationResultMapper.findListEnvironmentByIdx(res.getEvalResultIdx());
            List<EvalCat> socialList = evaluationResultMapper.findListSocialByIdx(res.getEvalResultIdx());
            List<EvalCat> governanceList = evaluationResultMapper.findListGovernanceByIdx(res.getEvalResultIdx());


            Map<String,Object> response = new HashMap<>();
            response.put("public",process(publicList));
            response.put("environment",process(environmentList));
            response.put("social",process(socialList));
            response.put("governance",process(governanceList));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패!");
    }

    //응답 데이터 만들기.
    public Map<String, Object> process(List<EvalCat> list){
        int sum = 0;
        int count = 0;
        int[] ansNo = new int[list.size()];  //생성 시 각 인덱스의 값은 (자동)0 으로 초기화.
        for(int i = 0; i<list.size();  i++){
                if(list.get(i).getAnsNo() != 0){ //답변이 0 일 경우, 해당 배점을 총점에서 제외하기 위한 if.
                    ansNo[i] = list.get(i).getAnsNo();
                    sum += list.get(i).getAnsNo();
                    count++;
                }
            }

        float score = sum * 100 / (float) (count * 5);
        Map<String, Object> result = new HashMap<>();
        result.put("score",score);
        result.put("ansNo",ansNo);

        return result;
    }

    public ResponseEntity<?> createEvalCat(List<EvalCat> evalCats){
return null;
    }
}
