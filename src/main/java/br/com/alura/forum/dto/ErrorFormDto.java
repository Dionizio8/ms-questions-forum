package br.com.alura.forum.dto;

public class ErrorFormDto {
    private String parameter;
    private String error;

    public ErrorFormDto(String parameter, String error) {
        this.parameter = parameter;
        this.error = error;
    }

    public String getParameter() {
        return parameter;
    }

    public String getError() {
        return error;
    }
}
