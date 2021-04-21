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
    public ResponseEntity<TopicDetailsDto> details(@PathVariable String id) {
        Optional<Topic> topic = topicsRepository.findById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(new TopicDetailsDto(topic.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDetailsDto> update(@PathVariable String id, @RequestBody @Valid TopicUpdateForm form) {
        Optional<Topic> optional = topicsRepository.findById(id);
        if (optional.isPresent()) {
            Topic topic = form.update(id, topicsRepository);
            return ResponseEntity.ok(new TopicDetailsDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Topic> optional = topicsRepository.findById(id);
        if (optional.isPresent()) {
            topicsRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
