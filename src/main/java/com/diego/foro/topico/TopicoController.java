package com.diego.foro.topico;

import com.diego.foro.topico.dto.TopicoRequest;
import com.diego.foro.topico.dto.TopicoResponse;
import com.diego.foro.user.Usuario;
import com.diego.foro.user.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;
    private final UsuarioRepository usuarios;

    public TopicoController(TopicoRepository repository, UsuarioRepository usuarios) {
        this.repository = repository;
        this.usuarios = usuarios;
    }

    @GetMapping
    public List<TopicoResponse> listar() {
        return repository.findAll().stream()
                .map(t -> new TopicoResponse(t.getId(), t.getTitulo(), t.getMensaje(), t.getCurso(),
                        t.getAutor() != null ? t.getAutor().getId() : null, t.getCreadoEn()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> obtener(@PathVariable Long id) {
        return repository.findById(id)
                .map(t -> ResponseEntity.ok(new TopicoResponse(t.getId(), t.getTitulo(), t.getMensaje(), t.getCurso(),
                        t.getAutor() != null ? t.getAutor().getId() : null, t.getCreadoEn())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> crear(@Valid @RequestBody TopicoRequest request, Authentication auth) {
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Usuario autor = usuarios.findByEmail(auth.getName()).orElseThrow();
        Topico t = new Topico();
        t.setTitulo(request.getTitulo());
        t.setMensaje(request.getMensaje());
        t.setCurso(request.getCurso());
        t.setAutor(autor);
        Topico save = repository.save(t);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TopicoResponse(save.getId(), save.getTitulo(), save.getMensaje(), save.getCurso(),
                        autor.getId(), save.getCreadoEn()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, Authentication auth) {
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return repository.findById(id).map(t -> {
            repository.deleteById(id);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
