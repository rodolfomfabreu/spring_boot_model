package br.com.atomiccodes.atomiccodesapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import br.com.atomiccodes.atomiccodesapi.entities.Roles;
import br.com.atomiccodes.atomiccodesapi.entities.Usuarios;
import br.com.atomiccodes.atomiccodesapi.repository.RoleRepository;
import br.com.atomiccodes.atomiccodesapi.repository.UsuarioRepository;

import java.time.Instant;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
    					   UsuarioRepository usuarioRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Verificar ou criar a role ADMIN
        Roles roleAdmin = roleRepository.findByNome(Roles.Values.ADMIN.name());
        if (roleAdmin == null) {
            roleAdmin = new Roles();
            roleAdmin.setNome(Roles.Values.ADMIN.name());
            roleAdmin = roleRepository.save(roleAdmin);
        }
        
        final Roles finalRoleAdmin = roleAdmin;

        try {
            // Verificar se o email existe sem carregar relacionamentos
            boolean emailExists = usuarioRepository.existsByEmail("admin@admin.com");
            
            if (!emailExists) {
                var user = new Usuarios();
                user.setEmail("admin@admin.com");
                user.setNome("Administrador");
                user.setConselho("123456");
                user.setConselhoClasse("COREN");
                user.setCpf("111.111.111-21");
                user.setDataCadastro(Instant.now());
                user.setPassword(passwordEncoder.encode("123"));
                user.setRoles(Set.of(finalRoleAdmin));
                usuarioRepository.save(user);
                System.out.println("Usuario admin criado com sucesso");
            } else {
                System.out.println("admin ja existe");
            }
        } catch (Exception e) {
            // Se houver erro, tentar criar mesmo assim (pode ser que os dados estejam corrompidos)
            System.err.println("Erro ao verificar/criar usuario admin: " + e.getMessage());
            try {
                // Limpar dados corrompidos e criar novo
                var user = new Usuarios();
                user.setEmail("admin@admin.com");
                user.setNome("Administrador");
                user.setConselho("123456");
                user.setConselhoClasse("COREN");
                user.setCpf("111.111.111-21");
                user.setDataCadastro(Instant.now());
                user.setPassword(passwordEncoder.encode("123"));
                user.setRoles(Set.of(finalRoleAdmin));
                usuarioRepository.save(user);
                System.out.println("Usuario admin criado com sucesso (após erro)");
            } catch (Exception ex) {
                System.err.println("Erro crítico ao criar usuario admin: " + ex.getMessage());
                // Não relançar exceção para não impedir a inicialização da aplicação
            }
        }
    }
}