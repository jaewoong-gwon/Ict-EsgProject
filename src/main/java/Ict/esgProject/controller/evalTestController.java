package Ict.esgProject.controller;

import Ict.esgProject.model.EvalCat;
import Ict.esgProject.model.EvaluationResult;
import Ict.esgProject.model.Public;
import Ict.esgProject.repository.EvaluationResultMapper;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class evalTestController {

    private final EvaluationResultMapper evaluationResultMapper;

    @GetMapping("f")
    public ResponseEntity<?> t1(@RequestParam Map<String,String> params){
        EvaluationResult res = evaluationResultMapper.findEvaluationByEmail(params.get("ent_mrg_email"));
        if(res != null){
            List<EvalCat> publics = evaluationResultMapper.findListPublicByIdx(res.getEvalResultIdx());
            int sum = 0;
            int count = 0;
            int[] ansNo = new int[publics.size()+1];
            for(int i = 0; i<publics.size(); i++){
                if(publics.get(i).getAnsNo() != 0){
                    if(i != 0) ansNo[i] = publics.get(i).getQusNo();
                    sum += publics.get(i).getAnsNo();
                    count++;
                }
            }

            float score = sum * 100 / (float) (count * 5);
            Map<String, Object> temp = new HashMap<>();
            temp.put("score",score);
            temp.put("ansNo",ansNo);

            Map<String,Object> response = new HashMap<>();
            response.put("public",temp);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else return null;
    }
}
