package br.com.atomiccodes.atomiccodesapi.controller.dto;

import br.com.atomiccodes.atomiccodesapi.entities.Usuarios;

public record LoginResponse(String accessToken, Long expiresIn, Usuarios user) {

}
