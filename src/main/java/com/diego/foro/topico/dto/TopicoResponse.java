package com.diego.foro.topico.dto;

import java.time.LocalDateTime;

public class TopicoResponse {
    private Long id;
    private String titulo;
    private String mensaje;
    private String curso;
    private Long usuarioId;
    private LocalDateTime creadoEn;

    public TopicoResponse(Long id, String titulo, String mensaje, String curso, Long usuarioId, LocalDateTime creadoEn) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
        this.usuarioId = usuarioId;
        this.creadoEn = creadoEn;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public String getCurso() { return curso; }
    public Long getUsuarioId() { return usuarioId; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
}
