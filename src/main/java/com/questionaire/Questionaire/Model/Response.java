package com.questionaire.Questionaire.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Response {
    @Id
    private Integer id;
    private String rightAnswer;
}
