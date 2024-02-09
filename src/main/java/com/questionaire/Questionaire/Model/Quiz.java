package com.questionaire.Questionaire.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
    private List<Question> questions;
    // Multiple questions will be part of one quiz and also these questions can be part of multiple quiz

    //This Entity creates 2 tables : first table with ID and title
    //                               second table with ID and questions

}