package Ict.esgProject.controller;

import Ict.esgProject.model.EvalCat;
import Ict.esgProject.service.EvaluationResultService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
public class EvaluationResultController {
    private EvaluationResultService evaluationResultService;

    @GetMapping("findEval")
    public ResponseEntity<?> findEval(@RequestParam Map<String,String> params){
        return evaluationResultService.getEvaluationResult(params);
    }

    @PostMapping("createEval")
    public ResponseEntity<?> createEval(@RequestBody HashMap<String,Object> params){
//        String entMrgEmail = (String) params.get("ent_mrg_email");
        System.out.println("getName : " + params.get("ent_mrg_email").getClass().getName());
        System.out.println("toString : " + params.get("ent_mrg_email").toString());
        System.out.println("getName : " + params.get("environment").getClass().getName());
        System.out.println("toString : " + params.get("environment").toString());
        System.out.println("getName : " + params.get("governance").getClass().getName());
        System.out.println("toString : " + params.get("governance").toString());
        System.out.println("getName : " + params.get("public").getClass().getName());
        System.out.println("toString : " + params.get("public").toString());

//        String st = (String) params.get("public");
//        String[] stArrays = st.split(",");
//        System.out.println(st);
//        int[] intArrays = new int[stArrays.length];
//        for(int i = 0; i < stArrays.length; i++){
//            intArrays[i] = Integer.parseInt(stArrays[i]);
//        }
//        System.out.println(Arrays.toString(intArrays));
//        System.out.println("________________________");
//        System.out.println(params.get("social").getClass().getName());
//        System.out.println(params.get("social").toString());
        ArrayList intArrays2 = (ArrayList) params.get("social");
        System.out.println(intArrays2.toString());
//        JSONArray object = new JSONArray(params).getJSONObject();
//        if(evaluationResultService.createEvaluationResult(entMrgEmail)){
//            List<EvalCat> publicList = params.get("")
//        }
        return null;
    }
}
