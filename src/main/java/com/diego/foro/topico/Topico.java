package com.diego.foro.topico;

import com.diego.foro.user.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(length = 2000)
    private String mensaje;
    private String curso;
    private LocalDateTime creadoEn = LocalDateTime.now();

    @ManyToOne(optional = false)
    private Usuario autor;

    public Topico() {}

    public Topico(Long id, String titulo, String mensaje, String curso, Usuario autor) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
}
