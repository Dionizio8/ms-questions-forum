package br.com.alura.forum.controller;

import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.controller.form.TopicUpdateForm;
import br.com.alura.forum.dto.TopicDetailsDto;
import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> list(String nameCourse) {
        if (nameCourse == null) {
            List<Topic> topics = topicsRepository.findAll();
            return TopicDto.convertToDtoList(topics);
        } else {
            List<Topic> topics = topicsRepository.findByCourseName(nameCourse);
            return TopicDto.convertToDtoList(topics);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.hydrate(courseRepository);
        topicsRepository.save(topic);

        URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public TopicDetailsDto details(@PathVariable String id) {
        Topic topic = topicsRepository.findById(id).get();
        return new TopicDetailsDto(topic);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable String id, @RequestBody @Valid TopicUpdateForm form) {
        Topic topic = form.update(id, topicsRepository);
        return ResponseEntity.ok(new TopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) {
        topicsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
