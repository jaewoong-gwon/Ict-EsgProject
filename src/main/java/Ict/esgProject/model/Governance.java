package Ict.esgProject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Governance extends EvalCat{
    private int govIdx;
    Governance(int evalResultIdx, int qusNo, int ansNo, int govIdx){
        super(evalResultIdx,qusNo,ansNo);
        this.govIdx = govIdx;
    }
}
