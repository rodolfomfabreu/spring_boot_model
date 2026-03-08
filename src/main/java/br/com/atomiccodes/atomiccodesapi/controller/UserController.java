package br.com.atomiccodes.atomiccodesapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atomiccodes.atomiccodesapi.controller.dto.CreateUnidadeUserDto;
import br.com.atomiccodes.atomiccodesapi.controller.dto.CreateUsuarioDto;
import br.com.atomiccodes.atomiccodesapi.controller.dto.SelectUsuarioDto;
import br.com.atomiccodes.atomiccodesapi.entities.Roles;
import br.com.atomiccodes.atomiccodesapi.entities.Usuarios;
import br.com.atomiccodes.atomiccodesapi.exception.ResponseEntityException;
import br.com.atomiccodes.atomiccodesapi.repository.RoleRepository;
import br.com.atomiccodes.atomiccodesapi.repository.UnidadeRepository;
import br.com.atomiccodes.atomiccodesapi.repository.UsuarioRepository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final UnidadeRepository unidadeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UsuarioRepository usuarioRepository,
                          RoleRepository roleRepository,
                          UnidadeRepository unidadeRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.unidadeRepository = unidadeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody CreateUsuarioDto dto) {

        var userRole = roleRepository.findByNome(Roles.Values.USER.name());
        if (userRole == null) {
            userRole = new Roles();
            userRole.setNome(Roles.Values.USER.name());
            userRole = roleRepository.save(userRole);
        }

        var userFromDb = usuarioRepository.findByEmail(dto.email());
        if (userFromDb.isPresent()) {
            var error = new ResponseEntityException(
                404, 
                "Usuário já cadastrado", 
                "Erro ao cadastrar usuário", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }

        var user = new Usuarios();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(userRole));
        user.setNome(dto.nome());
        user.setConselho(dto.conselho());
        user.setDtNascimento(dto.dtNascimento());
        user.setCpf(dto.cpf());
        user.setConselhoClasse(dto.conselhoClasse());
        user.setDataCadastro(Instant.now());

        usuarioRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<SelectUsuarioDto>> listUsers() {
        var users = usuarioRepository.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/unidades_user")
    public ResponseEntity<?> listUnidadeByUsers(JwtAuthenticationToken token) {
        var users = usuarioRepository.findUnidadeByUserId(Long.valueOf(token.getName()));

        if (users.isEmpty()) {
            var error = new ResponseEntityException(
                400, 
                "Usuário não encontrado", 
                "Erro ao buscar usuário", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }

        return ResponseEntity.ok(List.of(users.get()));
    }

    @PostMapping("/unidade_user")
    public ResponseEntity<?> insertUnidadeToUser(@RequestBody CreateUnidadeUserDto dto,
                                                    JwtAuthenticationToken token
    ) {
        var user = usuarioRepository.findById(Long.valueOf(token.getName()));
        if (user.isEmpty()) {
            var error = new ResponseEntityException(
                400, 
                "Usuário não encontrado", 
                "Erro ao buscar usuário", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }
        var unidade = unidadeRepository.findById(dto.unidadeId());
        if (unidade.isEmpty()) {
            var error = new ResponseEntityException(
                400, 
                "Usuário não encontrado", 
                "Erro ao buscar usuário", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }

        user.get().getUnidades().add(unidade.get());
        usuarioRepository.save(user.get());

        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/unidade_user/{id}")
    public ResponseEntity<?> deleteUnidadeToUser(@PathVariable("id") Long unidadeId,
                                                    JwtAuthenticationToken token
    ) {
        var user = usuarioRepository.findById(Long.valueOf(token.getName()));
        if (user.isEmpty()) {
            var error = new ResponseEntityException(
                400, 
                "Usuário não encontrado", 
                "Erro ao buscar usuário", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }
        var unidade = unidadeRepository.findById(unidadeId);
        if (unidade.isEmpty()) {
            var error = new ResponseEntityException(
                404, 
                "Unidade não encontrada", 
                "Erro ao buscar unidade", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }
        user.get().getUnidades().remove(unidade.get());
        usuarioRepository.save(user.get());

        return ResponseEntity.ok().build();
    }
}