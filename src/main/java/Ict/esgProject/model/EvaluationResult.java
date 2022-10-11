package Ict.esgProject.model;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationResult {
    private int evalResultIdx;
    private String entMrgEmail;
    private Date evalDate;
    private String evalFeedback;
}
