package com.diego.foro.topico.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotBlank
    private String curso;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}
