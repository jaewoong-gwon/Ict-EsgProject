package Ict.esgProject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Environment extends EvalCat {
    private int envIdx;

    Environment(int evalResultIdx, int qusNo, int ansNo,int envIdx){
        super(evalResultIdx,qusNo,ansNo);
        this.envIdx = envIdx;
    }
}
