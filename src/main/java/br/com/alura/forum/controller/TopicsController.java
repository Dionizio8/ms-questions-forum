package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicsController {

    @Autowired
    private TopicsRepository topicsRepository;

    @RequestMapping("/topics")
    public List<TopicDto> list() {
        List<Topic> topics = topicsRepository.findAll();
        return TopicDto.convertToDtoList(topics);
    }
}
