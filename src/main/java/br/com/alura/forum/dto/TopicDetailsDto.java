package br.com.alura.forum.dto;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.StatusTopic;
import br.com.alura.forum.model.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailsDto {

    private String id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;
    private String nameAuthor;
    private StatusTopic status;
    private List<AnswerDto> answers;

    public TopicDetailsDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dateCreation = topic.getDateCreation();
        this.nameAuthor = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.answers = new ArrayList<>();
        this.answers.addAll(topic.getAnswer().stream().map(AnswerDto::new).collect(Collectors.toList()));
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

    public String getNameAuthor() {
        return nameAuthor;
    }

    public StatusTopic getStatus() {
        return status;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }
}
