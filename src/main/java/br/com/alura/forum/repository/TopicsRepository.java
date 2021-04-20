package br.com.alura.forum.repository;

import br.com.alura.forum.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TopicsRepository extends MongoRepository<Topic, String> {

    List<Topic> findByCourseName(String nameCourse);
}
