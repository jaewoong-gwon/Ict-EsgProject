package Ict.esgProject.repository;

import Ict.esgProject.model.Governance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GovernanceMapper {
    @Select("SELECT qus_no, ans_no FROM Governance WHERE eval_result_idx = #{evalResultIdx}")
    List<Governance> findGovernanceEval(int evalResultIdx);
}
