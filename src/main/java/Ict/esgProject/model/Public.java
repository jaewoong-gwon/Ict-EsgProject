package Ict.esgProject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Public extends EvalCat{
    private int pubIdx;
    Public(int evalResultIdx, int qusNo, int ansNo,int pubIdx){
        super(evalResultIdx,qusNo,ansNo);
        this.pubIdx = pubIdx;
    }

}
