package Ict.esgProject.repository;

import Ict.esgProject.model.EnterprisesInfo;
import org.apache.ibatis.annotations.*;


/*
 * @Author 권재웅
 * @Param
 * @Return
 * EnterprisesInfo -> Enterprises_Mrg 와 Enterprises 테이블을 INNER JOIN 해둔 VIEW
 */
@Mapper
public interface EnterprisesInfoMapper {

    //View 에서 조회
    @Select("SELECT * FROM Enterprises_Info WHERE ent_mrg_email = #{ent_mrg_email}")
    EnterprisesInfo findByEmail(String entMrgEmail);



    //Enterprises
    @Select("SELECT ent_idx FROM Enterprises WHERE ent_mrg_email = #{ent_mrg_email}")
    int findIdxByEmail(String entMrgEmail);

    //Enterprises_mrg 테이블에 대한 C,U,D
    @Select("SELECT ent_mrg_pw FROM Enterprises_mrg WHERE ent_mrg_email = #{ent_mrg_email} ")
    String findPwByEmail(String entMrgPw);

    @Insert("INSERT INTO Enterprises_mrg (ent_mrg_email,ent_mrg_pw,ent_mrg_name,ent_mrg_mobile,ent_mrg_sns,ent_idx) " +
            "VALUES (#{entMrgEmail},#{entMrgPw},#{entMrgName},#{entMrgMobile},#{entMrgSns},#{entIdx})")
    int createEnterprisesMrg(EnterprisesInfo enterprisesInfo);

    @Update("UPDATE Enterprises_mrg SET ent_mrg_pw = #{entMrgPw} WHERE ent_mrg_email = #{entMrgEmail}")
    int changePw(String entMrgPw,String entMrgEmail);

    @Delete("DELETE FROM Enterprises_mrg WHERE ent_mrg_email = #{entMrgEmail}")
    void deleteEnterprisesMrg(String entMrgEmail);

    //Enterprises 테이블에 대한 C,U,D
    @Insert("INSERT INTO Enterprises (ent_mrg_email,ent_name,ent_major_prod,ent_cert)" +
            " VALUES (#{entMrgEmail},#{entName},#{entMajorProd},#{entCert}")
    int createEnterprises(EnterprisesInfo enterprisesInfo);

}
