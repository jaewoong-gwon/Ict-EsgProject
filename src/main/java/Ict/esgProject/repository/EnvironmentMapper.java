package Ict.esgProject.repository;

import Ict.esgProject.model.Environment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnvironmentMapper {

    @Select("SELECT qus_no, ans_no FROM Environment WHERE eval_result_idx = #{evalResultIdx}")
    List<Environment> findEnvironmentEval(int evalResultIdx);
}
