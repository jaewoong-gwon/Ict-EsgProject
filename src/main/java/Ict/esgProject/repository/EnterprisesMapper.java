package Ict.esgProject.repository;

import Ict.esgProject.model.Enterprises;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnterprisesMapper {
    @Select("SELECT * FROM Enterprises")
    List<Enterprises> findAll();

    @Select("SELECT * FROM Enterprises WHERE ent_mrg_email = #{entMrgEmail}")
    Enterprises findByEnterprises(String entMrgEmail);

    @Select("Select * FROM Enterprises WHERE ent_name = #{entName}")
    List<Enterprises> findByEnterprisesName(String entName);

    @Insert("INSERT INTO Enterprises (ent_mrg_email,ent_name,ent_major_prod,ent_cert)" +
            " VALUES (#{entMrgEmail},#{entName},#{entMajorProd},#{entCert}")
    void createEnterprises(Enterprises enterprises);
}
