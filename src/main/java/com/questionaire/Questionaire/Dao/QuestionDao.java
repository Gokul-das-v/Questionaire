package com.questionaire.Questionaire.Dao;

import com.questionaire.Questionaire.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Data Access Layer (Mainly interacts with DB)
@Repository
public interface QuestionDao extends JpaRepository <Question,Integer> {
    // Spring Data JPA aims to significantly improve the implementation of data access layers by reducing the effort
    // Syntax: public interface InterfaceName extends JpaRepository <ModelClassName,PrimaryKeyDatatype>

    List<Question> findByCategory(String Category);

    @Query(value = "SELECT * FROM question q WHERE q.category =:Category ORDER BY RANDOM() LIMIT :numQ"
            , nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String Category, Integer numQ);

    // @Query annotation can be used to retrieve custom records from a database , we can pass in the query along with it
    // We can use method variables within in the query ---> By using colon followed by variable name EXAMPLE :Category
}