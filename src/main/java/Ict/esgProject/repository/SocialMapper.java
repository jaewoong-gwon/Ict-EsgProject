package Ict.esgProject.repository;

import Ict.esgProject.model.Social;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocialMapper {

    @Select("SELECT qus_no, ans_no FROM Social WHERE eval_result_idx = #{evalResultIdx}")
    List<Social> findSocialEval(int evalResultIdx);
}
