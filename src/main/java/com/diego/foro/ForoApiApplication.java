package com.diego.foro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diego.foro.user.Role;
import com.diego.foro.user.Usuario;
import com.diego.foro.user.UsuarioRepository;
import com.diego.foro.topico.Topico;
import com.diego.foro.topico.TopicoRepository;

@SpringBootApplication
public class ForoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForoApiApplication.class, args);
    }

    // Seed de un usuario y algunos tópicos de ejemplo
    @Bean
    CommandLineRunner init(UsuarioRepository usuarios, TopicoRepository topicos) {
        return args -> {
            if (usuarios.findByEmail("admin@foro.com").isEmpty()) {
                var encoder = new BCryptPasswordEncoder();
                Usuario admin = new Usuario();
                admin.setEmail("admin@foro.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                usuarios.save(admin);

                Usuario user = new Usuario();
                user.setEmail("user@foro.com");
                user.setPassword(encoder.encode("user123"));
                user.setRole(Role.USER);
                usuarios.save(user);

                Topico t1 = new Topico(null, "Bienvenida", "¡Hola a todos!", "General", admin);
                Topico t2 = new Topico(null, "Spring Security", "¿Cómo protejo rutas?", "Java", user);
                topicos.save(t1);
                topicos.save(t2);
            }
        };
    }
}
