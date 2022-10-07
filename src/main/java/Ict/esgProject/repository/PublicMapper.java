package Ict.esgProject.repository;

import Ict.esgProject.model.Public;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PublicMapper {

    @Select("SELECT qus_no, ans_no FROM Public WHERE eval_result_idx = #{evalResultIdx}")
    List<Public> findPublicEval(int evalResultIdx);
}
