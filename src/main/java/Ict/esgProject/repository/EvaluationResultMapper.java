package Ict.esgProject.repository;

import Ict.esgProject.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface EvaluationResultMapper {

    //진단 결과 조회 - 결과 리스트 조회.
    @Select("SELECT * FROM Evaluation_Result WHERE ent_mrg_email = #{entMrgEmail}")
    List<EvaluationResult> findAllByEmail(String entMrgEmail);

    //상세 진단 결과 조회 - 상세 점수를 얻기 위해, 하나의 결과만 리턴. 이메일과 진단 날짜로 조회 ( 평가는 하루에 한번으로 제한)
    @Select("SELECT * FROM Evaluation_Result WHERE (ent_mrg_email = #{entMrgEmail}) AND (eval_date) = #{evalDate}")
    EvaluationResult findEvaluation(EvaluationResult evaluationResult);

    @Select("SELECT eval_result_idx FROM Evaluation_Result WHERE (ent_mrg_email = #{entMrgEmail}) AND (eval_date) = #{evalDate}")
    int findEvaluationIdx(EvaluationResult evaluationResult);
    @Insert("INSERT ent_mrg_email, eval_date INTO Evaluation_Result VALUES #{entMrgEmail},#{evalDate}")
    int createEvaluation(EvaluationResult evaluation);

    /*
        평가 항목 - P,E,S,G 의 경우 별도의 매퍼를 만들지 않고, 현재 매퍼에서 처리.
         -> 추후 수정해야할 수도 있지만 현재는 따로 만들 경우 너무 복잡해진다고 생각함.
            P,E,S,G 의 경우 SELECT 기능 하나 밖에 존재x, 굳이 따로 만들 필요가 없다고 생각.
     */
    @Select("SELECT * FROM Public WHERE eval_result_idx = #{evalResultIdx}")
    List<EvalCat> findListPublicByIdx(int evalResultIdx);
    @Select("SELECT * FROM Environment WHERE eval_result_idx = #{evalResultIdx}")
    List<EvalCat> findListEnvironmentByIdx(int evalResultIdx);

    @Select("SELECT * FROM Social WHERE eval_result_idx = #{evalResultIdx}")
    List<EvalCat> findListSocialByIdx(int evalResultIdx);

    @Select("SELECT * FROM Governance WHERE eval_result_idx = #{evalResultIdx}")
    List<EvalCat> findListGovernanceByIdx(int evalResultIdx);

    @Insert("INSERT INTO Public eval_result_idx,qus_no,ans_no VALUES (#{evalResultIdx},#{qusNo},#{ansNo})")
    boolean createPublic(EvalCat pub);
    @Insert("INSERT INTO Social eval_result_idx,qus_no,ans_no VALUES (#{evalResultIdx},#{qusNo},#{ansNo})")
    boolean createSocial(EvalCat social);
    @Insert("INSERT INTO Environment eval_result_idx,qus_no,ans_no VALUES (#{evalResultIdx},#{qusNo},#{ansNo})")
    boolean createEnvironment(EvalCat env);
    @Insert("INSERT INTO Governance eval_result_idx,qus_no,ans_no VALUES (#{evalResultIdx},#{qusNo},#{ansNo})")
    boolean createGovernance(EvalCat gov);
}
