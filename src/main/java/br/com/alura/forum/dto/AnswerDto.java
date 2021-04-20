package br.com.alura.forum.dto;

import br.com.alura.forum.model.Answer;

import java.time.LocalDateTime;

public class AnswerDto {

    private String id;
    private String message;
    private String nameAuthor;
    private LocalDateTime dateCreation = LocalDateTime.now();

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.dateCreation = answer.getDateCreation();
        this.nameAuthor = answer.getAuthor().getName();
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }
}
