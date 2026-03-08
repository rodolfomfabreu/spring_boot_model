package br.com.atomiccodes.atomiccodesapi.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.atomiccodes.atomiccodesapi.controller.dto.CreateUnidadeDto;
import br.com.atomiccodes.atomiccodesapi.entities.Unidades;
import br.com.atomiccodes.atomiccodesapi.exception.ResponseEntityException;
import br.com.atomiccodes.atomiccodesapi.repository.UnidadeRepository;
import br.com.atomiccodes.atomiccodesapi.repository.UsuarioRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin("*")
public class UnidadeController {

    private final UnidadeRepository unidadeRepository;

    private final UsuarioRepository usuarioRepository;

    public UnidadeController(UnidadeRepository unidadeRepository, UsuarioRepository usuarioRepository) {
        this.unidadeRepository = unidadeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/unidades")
    public ResponseEntity<List<Unidades>> listAll() {
        return ResponseEntity.ok(unidadeRepository.findAll());
    }
    

    @PostMapping("/unidades")
    public ResponseEntity<?> createUnidade(@RequestBody CreateUnidadeDto dto,
                                               JwtAuthenticationToken token
    ) {
        var user = usuarioRepository.findById(Long.valueOf(token.getName()));

        if (user.isEmpty()) {
            var error = new ResponseEntityException(
                404, 
                "Usuário não encontrado", 
                "Erro ao buscar usuário", 
                "/notificacoes-violencia"
            );
            return ResponseEntity.status(error.getStatus()).body(error);
        }

        var unidade = new Unidades();
        unidade.setNome(dto.nome());
        unidade.setTipo(dto.tipo());
        unidade.setEmail(dto.email());
        unidade.setEndereco(dto.endereco());
        unidade.setMunicipio(dto.municipio());
        unidade.setCnpj(dto.cnpj());
        unidade.setAtivo(true);
        unidade.setOwner(user.get());

        unidadeRepository.save(unidade);

        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/unidades/{id}")
    public ResponseEntity<?> deleteUnidade(@PathVariable("id") Long unidadeId,
                                               JwtAuthenticationToken token) {
        var user = usuarioRepository.findById(Long.valueOf(token.getName()));

        if (user.isEmpty()) {
            var error = new ResponseEntityException(
                404, 
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

        if (!unidade.get().getOwner().equals(user.get())) {
            return ResponseEntity.status(403).build();
        }

        unidadeRepository.deleteById(unidadeId);
        return ResponseEntity.ok().build();
    }
}
