package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.TopicsRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicUpdateForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String message;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic update(String id, TopicsRepository topicsRepository) {
        Topic topic = topicsRepository.findById(id).get();
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topicsRepository.save(topic);
    }
}
