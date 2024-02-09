package com.questionaire.Questionaire.Controller;

import com.questionaire.Questionaire.Model.QuestionWrapper;
import com.questionaire.Questionaire.Model.Response;
import com.questionaire.Questionaire.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category , @RequestParam Integer numQ,@RequestParam String quizName){
        return quizService.createQuiz(category,numQ,quizName);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.getScore(id,response);
    }
}
