package com.maddy.question.repo;

import com.maddy.question.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    public List<QuestionEntity> findByQuizId(Long quizId);
}
