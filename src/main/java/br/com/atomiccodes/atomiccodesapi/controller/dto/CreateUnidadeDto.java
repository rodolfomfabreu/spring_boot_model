package br.com.atomiccodes.atomiccodesapi.controller.dto;

public record CreateUnidadeDto(String nome,
                                String tipo,
                                String email,
                                String endereco,
                                String municipio,
                                String cnpj,
                                String uf,
                                String codigoIbge,
                                String codigoCnes
) {

}
