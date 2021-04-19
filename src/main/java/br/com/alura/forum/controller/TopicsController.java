package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicsController {

    @RequestMapping("/topics")
    public List<TopicDto> list() {
        Topic topic = new Topic("Dúvida", "Dúvida com Spring", new Course("Spring", "Programação"));

        return TopicDto.convertToDtoList(Arrays.asList(topic, topic, topic));
    }
}
