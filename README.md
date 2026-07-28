# 🎓 Plataforma de Cursos Online

Projeto desenvolvido com **Java** e **Spring Boot** com o objetivo de aprofundar conhecimentos em desenvolvimento backend, arquitetura de software e boas práticas de programação.

## 📌 Objetivo

Criar uma plataforma de cursos online onde usuários podem se cadastrar, autenticar-se e realizar matrículas em cursos compostos por módulos e aulas.

O projeto foi desenvolvido para fins de estudo, aplicando conceitos utilizados em aplicações corporativas modernas.

---

# 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot 4.0.7
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* H2 Database
* Docker
* Kubernetes
* Lombok
* Gradle

---

# 🏗️ Arquitetura

O projeto segue os princípios da **Arquitetura Limpa (Clean Architecture)**, promovendo:

* Separação de responsabilidades
* Baixo acoplamento
* Alta coesão
* Facilidade para manutenção e testes

Além disso, foram aplicados princípios do **SOLID** para tornar o código mais escalável e sustentável.

---

# 🔐 Segurança

A autenticação é realizada utilizando **JWT (JSON Web Token)**.

### Fluxo de autenticação

1. Usuário realiza login.
2. As credenciais são validadas.
3. Um token JWT é gerado.
4. O token é enviado nas requisições protegidas.
5. O sistema valida o token antes de permitir o acesso aos recursos.

---

# 🗄️ Modelagem do Banco de Dados

## Relacionamentos

```text
USERS
│
├── ENROLLMENTS
│     │
│     └── COURSES
│            │
│            └── MODULES
│                    │
│                    └── LESSONS
```
````text
+-------------+
|    USERS    |
+-------------+
| id          |
| name        |
| email       |
| password    |
| role        |
+-------------+
       |
       | 1
       |
       | N
+-------------+
| ENROLLMENTS |
+-------------+
| id          |
| user_id     |
| course_id   |
| progress    |
| status      |
+-------------+
       |
       | N
       |
       | 1
+-------------+
|   COURSES   |
+-------------+
| id          |
| name        |
| description |
| author      |
+-------------+
       |
       | 1
       |
       | N
+-------------+
|   MODULES   |
+-------------+
| id          |
| title       |
| course_id   |
+-------------+
       |
       | 1
       |
       | N
+-------------+
|   LESSONS   |
+-------------+
| id          |
| title       |
| video_url   |
| duration    |
+-------------+
`````

````text

UserEntity
      |
      | OneToMany
      ↓
EnrollmentEntity
      ↑
      | ManyToOne
CourseEntity
      |
      | OneToMany
      ↓
ModuleEntity
      |
      | OneToMany
      ↓
LessonEntity
````

## Estrutura das Entidades

### User

Representa os usuários da plataforma.

### Course

Representa os cursos disponíveis.

### Enrollment

Representa a matrícula de um usuário em um curso.

### Module

Representa os módulos pertencentes a um curso.

### Lesson

Representa as aulas pertencentes a um módulo.

---

# 📂 Estrutura do Projeto

```text
src
└── main
    └── java
        └── plataformadecurso
            ├── Auth
            ├── User
            ├── Course
            ├── Enrollment
            ├── Module
            ├── Lesson
            ├── Security
            ├── Config
            └── Exception
```

---

# ⚙️ Como Executar o Projeto

### Clonar o repositório

```bash
git clone https://github.com/seu-usuario/plataforma-de-cursos-online.git
```

### Entrar na pasta

```bash
cd plataforma-de-cursos-online
```

### Executar a aplicação

```bash
./gradlew bootRun
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

---

# 📈 Funcionalidades Implementadas

* Cadastro de usuários
* Login com JWT
* Gerenciamento de cursos
* Gerenciamento de módulos
* Gerenciamento de aulas
* Matrícula em cursos
* Controle de progresso do aluno
* Persistência de dados com JPA

---

# 🎯 Próximos Passos

* Implementar testes unitários com Mockito
* Implementar testes de integração
* Adicionar documentação com Swagger/OpenAPI
* Migrar do H2 para PostgreSQL
* Adicionar controle de permissões (Roles)
* Implementar CI/CD com GitHub Actions
* Deploy utilizando Docker e Kubernetes
* Adicionar observabilidade (Logs e Métricas)

---

# 📚 Conceitos Aplicados

* Clean Architecture
* SOLID
* REST API
* DTO Pattern
* Repository Pattern
* Service Layer Pattern
* JWT Authentication
* Dependency Injection
* Exception Handling
* JPA/Hibernate

---

# 👨‍💻 Autor

**Daniel Anderson Brandão Campos**

Desenvolvedor Backend Java | Spring Boot | APIs REST | Docker | Kubernetes

