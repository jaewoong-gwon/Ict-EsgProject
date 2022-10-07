package Ict.esgProject.repository;

import Ict.esgProject.model.EnterprisesMrg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnterPrisesMrgMapper {

    @Select("SELECT * FROM Enterprises_mrg")
    List<EnterprisesMrg> findAll();

    @Select("SELECT * FROM Enterprises_mrg WHERE (ent_mrg_email = #{entMrgEmail)")
    EnterprisesMrg findByEmail(String entMrgEmail);

    @Insert("INSERT INTO Enterprises_mrg (ent_mrg_email,ent_mrg_name,ent_mrg_mobile,ent_mrg_sns)" +
            " VALUES (#{entMrgEmail},#{entMrgName},#{entMrgMobile},#{entMrgSns} ")
    void createEnterprisesMrg(EnterprisesMrg enterprisesMrg);

    @Update("UPDATE Enterprises_mrg SET ent_mrg_pw = #{entMrgPw} WHERE ent_mrg_email = #{entMrgEmail}")
    void changePw(String entMrgPw,String entMrgEmail);

}
