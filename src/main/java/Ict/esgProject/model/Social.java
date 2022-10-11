package Ict.esgProject.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Social extends EvalCat{
    private int socialIdx;
    Social(int evalResultIdx, int qusNo, int ansNo,int socialIdx){
        super(evalResultIdx,qusNo,ansNo);
        this.socialIdx = socialIdx;
    }
}
