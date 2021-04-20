package br.com.alura.forum.dto;

import br.com.alura.forum.model.Topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDto {

    private String id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;

    public TopicDto(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dateCreation = topic.getDateCreation();
    }

    public static List<TopicDto> convertToDtoList(List<Topic> topics){
        return topics.stream().map(TopicDto::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
}
