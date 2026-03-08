package br.com.atomiccodes.atomiccodesapi.controller.dto;

import java.time.LocalDate;

public record CreateUsuarioDto(String email, 
                        String password,
                        String nome,
                        String conselho,
                        String conselhoClasse,
                        LocalDate dtNascimento,
                        String cpf) {

}
