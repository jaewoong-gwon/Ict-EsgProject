package Ict.esgProject.service;

import Ict.esgProject.model.*;
import Ict.esgProject.repository.EvaluationResultMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EvaluationResultService {
    private final EvaluationResultMapper evaluationResultMapper;

    @Transactional
    public boolean createEvaluationResult(Map<String,Object> params) {
        EvaluationResult evaluationResult = new EvaluationResult();
        evaluationResult.setEntMrgEmail(String.valueOf(params.get("ent_mrg_email"))); // P.K 설정.

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date evalDate = null;
        try {
            evalDate = formatter.parse(String.valueOf(Calendar.getInstance().getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        evaluationResult.setEvalDate(evalDate); // 평가 날짜 설정.

        int result = evaluationResultMapper.createEvaluation(evaluationResult);

        if (result > 0) {
            int evalIdx = evaluationResultMapper.findEvaluationIdx(evaluationResult);
            List<Integer> publicList = processRequestParams(params, "public");
            List<Integer> socialList = processRequestParams(params, "social");
            List<Integer> environmentList = processRequestParams(params, "environment");
            List<Integer> governanceList = processRequestParams(params, "governance");

            for (int i = 0; i < publicList.size(); i++) {
                EvalCat pub = new EvalCat("p", evalIdx, i, publicList.get(i));
                if(evaluationResultMapper.createPublic(pub)) return false;
            }
            for (int i = 0; i < socialList.size(); i++) {
                EvalCat social = new EvalCat("s", evalIdx, i, socialList.get(i));
                if(evaluationResultMapper.createSocial(social)) return false;
            }
            for (int i = 0; i < environmentList.size(); i++) {
                EvalCat env = new EvalCat("e", evalIdx, i, environmentList.get(i));
                if(evaluationResultMapper.createEnvironment(env)) return false;
            }
            for (int i = 0; i < governanceList.size(); i++) {
                EvalCat gov = new EvalCat("g", evalIdx, i, governanceList.get(i));
                if(evaluationResultMapper.createGovernance(gov)) return false;
            }

        }
        return true;
    }

    public ResponseEntity<?> getEvaluationResultList(Map<String,String> params){

        String entMrgEmail = params.get("ent_mrg_email");
        List<EvaluationResult> evaluationResultList = evaluationResultMapper.findAllByEmail(entMrgEmail);
        Map<String,Object> response = new HashMap<>();
        if(evaluationResultList.isEmpty()){ //데이터 조회 실패
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
        }else { //조회 성공
            response.put("response",evaluationResultList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }


    }

    //상세 진단 결과 조회
    public ResponseEntity<?> getEvaluationResult(Map<String,Object> params) throws ParseException {
        EvaluationResult evaluationResult = new EvaluationResult();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date evalDate = formatter.parse(String.valueOf(params.get("eval_date")));

        evaluationResult.setEntMrgEmail(String.valueOf(params.get("ent_mrg_email")));
        evaluationResult.setEvalDate(evalDate);

         EvaluationResult res = evaluationResultMapper.findEvaluation(evaluationResult);

         // 기업 담당자 이메일과, 평가 날짜로 db 조회 성공시,
        if(res != null){
            //각각의 평가 항목을 받아옴.
            List<EvalCat> publicList = evaluationResultMapper.findListPublicByIdx(res.getEvalResultIdx());
            List<EvalCat> environmentList = evaluationResultMapper.findListEnvironmentByIdx(res.getEvalResultIdx());
            List<EvalCat> socialList = evaluationResultMapper.findListSocialByIdx(res.getEvalResultIdx());
            List<EvalCat> governanceList = evaluationResultMapper.findListGovernanceByIdx(res.getEvalResultIdx());

            // process 메소드에서 해당 결과 들을 점수화.
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

    //요청 데이터 가공
    public List<Integer> processRequestParams(Map<String,Object> params,String key){
        String[] requestPrams = String.valueOf(params.get(key))
                                .replace("[","")
                                .replace("[","")
                                .split(",");

        List<Integer> catList = new ArrayList<>();

        for(String i : requestPrams){
            catList.add(Integer.parseInt(requestPrams[Integer.parseInt(i)]));
        }

        return catList;
    }

}
