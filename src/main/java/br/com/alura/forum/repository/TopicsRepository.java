package br.com.alura.forum.repository;

import br.com.alura.forum.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicsRepository extends MongoRepository<Topic, String> {
}
