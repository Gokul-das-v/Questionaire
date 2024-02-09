package com.questionaire.Questionaire.Service;

import com.questionaire.Questionaire.Dao.QuestionDao;
import com.questionaire.Questionaire.Dao.QuizDao;
import com.questionaire.Questionaire.Model.Question;
import com.questionaire.Questionaire.Model.QuestionWrapper;
import com.questionaire.Questionaire.Model.Quiz;
import com.questionaire.Questionaire.Model.Response;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizdao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String quizName) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(quizName);
            quiz.setQuestions(questionDao.findRandomQuestionsByCategory(category, numQ));
            quizdao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to create Quiz" , HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        try {
            Quiz quiz = quizdao.getReferenceById(id);
            List<Question> questionsFromDB = quiz.getQuestions();
            List<QuestionWrapper> questionForUser = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper questionWrapper = new QuestionWrapper();
                questionWrapper.setQuestionTitle(q.getQuestionTitle());
                questionWrapper.setID(q.getID());
                questionWrapper.setOption1(q.getOption1());
                questionWrapper.setOption2(q.getOption2());
                questionWrapper.setOption3(q.getOption3());
                questionWrapper.setOption4(q.getOption4());
                questionForUser.add(questionWrapper);
            }
            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        List<QuestionWrapper> emptyWrapper = new ArrayList<>();
        return  new ResponseEntity<>(emptyWrapper,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Integer> getScore(Integer id, List<Response> responses) {
        try{
             Quiz quiz = quizdao.getReferenceById(id);
             List<Question> questionsFromDB = quiz.getQuestions();
             int scoreTracker=0;
             int i=0;
             for(Response r: responses){
                 if(r.getRightAnswer().equals(questionsFromDB.get(i).getRightAnswer())){
                     scoreTracker++; /*Incrementing the score */
                     i++;
                 }
             }
            return new ResponseEntity<>(scoreTracker,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);

    }
}
