package com.questionaire.Questionaire.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class QuestionWrapper {

    @Id
    private Integer ID;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTitle;

}
