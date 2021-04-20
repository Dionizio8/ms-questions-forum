package br.com.alura.forum.controller;

import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> list(String nameCourse) {
        if(nameCourse == null){
            List<Topic> topics = topicsRepository.findAll();
            return TopicDto.convertToDtoList(topics);
        }else{
            List<Topic> topics = topicsRepository.findByCourseName(nameCourse);
            return TopicDto.convertToDtoList(topics);
        }
    }

    @PostMapping
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder){
        Topic topic = form.hydrate(courseRepository);
        topicsRepository.save(topic);

        URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }
}
