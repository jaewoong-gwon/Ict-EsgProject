package Ict.esgProject.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//P,E,S,G 를 묶는 추상클래스.
@Getter
@Setter
@ToString
public class EvalCat {
    private int evalResultIdx;
    private int qusNo;
    private int ansNo;

    public EvalCat(){

    }
    public EvalCat(int evalResultIdx, int qusNo, int ansNo) {
        this.evalResultIdx = evalResultIdx;
        this.qusNo = qusNo;
        this.ansNo = ansNo;
    }

}
