# 🌐 Atomic Codes API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.1-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-3.9.4-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
</p>

<div align="center">
  <b>🇧🇷 Português | <a href="#english-version">🇺🇸 English below</a></b>
</div>

---

## 📑 Sumário | Table of Contents
- [Sobre o Projeto | About](#sobre-o-projeto--about)
- [Tecnologias | Technologies](#tecnologias--technologies)
- [Estrutura | Structure](#estrutura--structure)
- [Instalação e Execução | Setup & Run](#instalação-e-execução--setup--run)
- [Autor | Author](#autor--author)

---

## Sobre o Projeto | About

**PT-BR:**
> Uma API REST desenvolvida com Spring Boot para gerenciamento de usuários, incluindo autenticação JWT, entidades de usuários, roles e unidades. Ideal para aplicações que necessitam de controle de acesso e gerenciamento de usuários.

**EN:**
> A REST API developed with Spring Boot for user management, including JWT authentication, user entities, roles, and units. Ideal for applications that need access control and user management.

---

## 🚀 Tecnologias | Technologies

**PT-BR:**
- **Java 21**: Linguagem de programação principal.
- **Spring Boot 4.0.1**: Framework para desenvolvimento de aplicações Java.
- **Spring Security**: Para autenticação e autorização.
- **JWT (JSON Web Tokens)**: Para autenticação stateless.
- **Spring Data JPA**: Para persistência de dados.
- **MySQL**: Banco de dados relacional.
- **Maven**: Gerenciador de dependências e build.
- **Docker**: Para containerização.

**EN:**
- **Java 21**: Main programming language.
- **Spring Boot 4.0.1**: Framework for Java application development.
- **Spring Security**: For authentication and authorization.
- **JWT (JSON Web Tokens)**: For stateless authentication.
- **Spring Data JPA**: For data persistence.
- **MySQL**: Relational database.
- **Maven**: Dependency management and build tool.
- **Docker**: For containerization.

---

## 🗂️ Estrutura | Structure
```
atomiccodesapi/
├── src/
│   ├── main/
│   │   ├── java/br/com/atomiccodes/atomiccodesapi/
│   │   │   ├── AtomiccodesapiApplication.java
│   │   │   ├── config/
│   │   │   │   ├── AdminUserConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── TokenController.java
│   │   │   │   ├── UserController.java
│   │   │   │   └── dto/
│   │   │   │       ├── CreateUsuarioDto.java
│   │   │   │       ├── LoginRequest.java
│   │   │   │       └── LoginResponse.java
│   │   │   ├── entities/
│   │   │   │   ├── Roles.java
│   │   │   │   ├── Unidades.java
│   │   │   │   └── Usuarios.java
│   │   │   └── repository/
│   │   │       ├── RoleRepository.java
│   │   │       ├── UnidadeRepository.java
│   │   │       └── UsuarioRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── app.pub
│   │       └── data.sql
│   └── test/
│       └── java/br/com/atomiccodes/atomiccodesapi/
│           └── AtomiccodesapiApplicationTests.java
├── docker/
│   ├── docker-compose.yml
│   └── data.sql
├── pom.xml
├── README.md
└── mvnw
```

---

## ⚙️ Instalação e Execução | Setup & Run

**PT-BR:**
1. **Pré-requisitos:** Java 21, Maven 3.9+, MySQL 8.0, Docker (opcional)
2. **Clone o repositório:**
   ```bash
   git clone <repository-url>
   cd atomiccodesapi
   ```
3. **Configure o banco de dados:**
   - Instale e configure o MySQL.
   - Crie um banco de dados chamado `mydb`.
   - Atualize as credenciais em `src/main/resources/application.properties` se necessário.
4. **Execute com Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```
5. **Ou com Docker:**
   ```bash
   cd docker
   docker-compose up
   ```

**EN:**
1. **Prerequisites:** Java 21, Maven 3.9+, MySQL 8.0, Docker (optional)
2. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd atomiccodesapi
   ```
3. **Configure the database:**
   - Install and configure MySQL.
   - Create a database named `mydb`.
   - Update credentials in `src/main/resources/application.properties` if necessary.
4. **Run with Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```
5. **Or with Docker:**
   ```bash
   cd docker
   docker-compose up
   ```

---

## 👨‍💻 Autor | Author

**PT-BR:**

<div align="center">

**Rodolfo M. F. Abreu**  
Desenvolvedor de software apaixonado por tecnologia, aprendizado contínuo e boas práticas de programação. Sempre em busca de novos desafios e oportunidades para colaborar em projetos inovadores.

[![GitHub](https://img.shields.io/badge/GitHub-rodolfomfabreu-black?style=for-the-badge&logo=github)](https://github.com/salamandery)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Rodolfo%20Abreu-blue?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/rodolfo-marques-ferreira-de-abreu/)

Sinta-se à vontade para entrar em contato para dúvidas, sugestões ou colaborações!

</div>

**EN:**

<div align="center">

**Rodolfo M. F. Abreu**  
Software developer passionate about technology, continuous learning, and best programming practices. Always looking for new challenges and opportunities to collaborate on innovative projects.

[![GitHub](https://img.shields.io/badge/GitHub-rodolfomfabreu-black?style=for-the-badge&logo=github)](https://github.com/salamandery)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Rodolfo%20Abreu-blue?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/rodolfo-marques-ferreira-de-abreu/)

Feel free to get in touch for questions, suggestions, or collaborations!

</div>

---

<div align="center">
  <b>Feito com 💙 para estudos de Spring Boot, Java e APIs REST.<br/>
  Made with 💙 for Spring Boot, Java and REST APIs studies.</b>
</div>

---

<div align="center" id="english-version">
  <b>🇺🇸 English version above | <a href="#top">🇧🇷 Versão em português acima</a></b>
</div>