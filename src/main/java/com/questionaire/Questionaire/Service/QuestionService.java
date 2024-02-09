package com.questionaire.Questionaire.Service;

import com.questionaire.Questionaire.Dao.QuestionDao;
import com.questionaire.Questionaire.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestions(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Add Question",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> updateQuestions(Integer id, Question question) {
        try {
            Question existingQuestion = questionDao.getReferenceById(id);
            if (existingQuestion.getID().equals(id)) {
                existingQuestion.setCategory(question.getCategory());
                existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
                existingQuestion.setOption1(question.getOption1());
                existingQuestion.setOption2(question.getOption2());
                existingQuestion.setOption3(question.getOption3());
                existingQuestion.setOption4(question.getOption4());
                existingQuestion.setRightAnswer(question.getRightAnswer());
                existingQuestion.setQuestionTitle(question.getQuestionTitle());
                questionDao.save(existingQuestion);
                // To update an already existing question
                //we initially retrieve the existing question using ID and then set the latest values based oen the current object
                return new ResponseEntity<>("success", HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Update Record", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteQuestions(Integer id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("successfully deleted the question from DB", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to delete the record", HttpStatus.BAD_REQUEST);
    }
}
