package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String message;
    @NotNull
    @NotEmpty
    private String nameCourse;

    public Topic hydrate(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(nameCourse);
        return new Topic(title, message, course);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
