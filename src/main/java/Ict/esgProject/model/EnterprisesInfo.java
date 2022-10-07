package Ict.esgProject.model;

import lombok.Data;

/*
    기업 관리자 INNER JOIN 기업
 */
@Data
public class EnterprisesInfo {
    //Enterprises_Mrg
    private String entMrgEmail;
    private String entMrgPw;
    private String entMrgName;
    private String entMrgMobile;
    private String entMrgSns;
    //Enterprises
    private String entName;
    private String entCat;
    private String entDetailsCat;
    private String entMajorProd;
    private String entMajorClnt;
    private int entCert;
    private String entRegNo;
}
