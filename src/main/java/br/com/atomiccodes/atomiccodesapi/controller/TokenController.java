package br.com.atomiccodes.atomiccodesapi.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atomiccodes.atomiccodesapi.controller.dto.LoginRequest;
import br.com.atomiccodes.atomiccodesapi.controller.dto.LoginResponse;
import br.com.atomiccodes.atomiccodesapi.entities.Roles;
import br.com.atomiccodes.atomiccodesapi.repository.UsuarioRepository;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class TokenController {
	private final JwtEncoder jwtEncoder;
	private final UsuarioRepository usuarioRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	public TokenController(JwtEncoder jwtEncoder, 
			UsuarioRepository usuarioRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.jwtEncoder = jwtEncoder;
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		var user = usuarioRepository.findByEmail(loginRequest.email());
		
		if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, this.passwordEncoder)) {
			throw new BadCredentialsException("Usuário ou senha está inválido");
		}
		
		var expiresIn = 1800L; // 30 minutos
		var now = Instant.now();

		var scopes = user.get().getRoles()
				.stream()
				.map(Roles::getNome)
				.collect(Collectors.joining(" "));
		
		var claims = JwtClaimsSet.builder()
				.issuer("atomiccodesapi")
				.subject(user.get().getUserId().toString())
				.expiresAt(now.plusSeconds(expiresIn))
				.issuedAt(now)
				.claim("scope", scopes)
				.build();
		var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		
		return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn, user.get()));
	}
}
