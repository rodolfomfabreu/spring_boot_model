package br.com.atomiccodes.atomiccodesapi.controller.dto;

import java.time.Instant;
import java.time.LocalDate;

public record SelectUsuarioDto(
                        Long userId,
                        String nome,
                        String email,
                        String conselho,
                        String conselhoClasse,
                        LocalDate dtNascimento,
                        String cpf,
                        Instant dataCadastro
                    ) {

}
