package com.questionaire.Questionaire.Controller;

import com.questionaire.Questionaire.Model.Question;
import com.questionaire.Questionaire.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Questions")
public class QuestionController {

    @Autowired
    QuestionService questionservice;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionservice.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        //@PathVariable is used to access value from the URL
        return questionservice.getQuestionsByCategory(category);

    }

    @PostMapping("addQuestions")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        //@Request Body is used in post requests to assign value present in request body to a variable/object
        return questionservice.addQuestions(question);
    }

    @PutMapping("updateQuestions/{id}")
    public ResponseEntity<String> updateQuestions(@PathVariable Integer id , @RequestBody Question question){
        return  questionservice.updateQuestions(id,question);
    }

    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionservice.deleteQuestions(id);

    }
}
