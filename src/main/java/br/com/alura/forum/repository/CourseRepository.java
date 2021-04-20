package br.com.alura.forum.repository;

import br.com.alura.forum.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
    Course findByName(String nameCourse);
}
