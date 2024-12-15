# API de Gerenciamento de Alunos e Turmas

### Sobre o projeto:
Esta aplicação é uma API RESTful desenvolvida em Java utilizando o framework Spring Boot, com o objetivo de gerenciar alunos e suas associações com turmas. O projeto implementa operações de CRUD para os dois recursos e validações de regras de negócio específicas.

## Funcionalidades
#### CRUD de Alunos:
* Criar um aluno e associá-lo a uma ou mais turmas.
* Atualizar informações de um aluno (nome, CPF, email e turmas associadas).
* Consultar um aluno por ID.
* Listar todos os alunos.
* Excluir um aluno, garantindo que ele não esteja associado a nenhuma turma.

#### CRUD de Turmas:
* Criar uma nova turma.
* Atualizar informações de uma turma (número e ano letivo).
* Consultar uma turma por ID.
* Listar todas as turmas.
* Excluir uma turma.

#### Validações de Regras de Negócio:
* Um aluno não pode se associar a mais de uma vez à mesma turma.
* Uma turma pode ter no máximo 5 alunos.
* Um aluno só pode ser excluído se não estiver associado a nenhuma turma.
* Validação de CPF único para cada aluno.

## Tecnologias Utilizadas
* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* ModelMapper
* Swagger
* Maven
* BeanValidation
* MockMVC
* JUnit (para testes unitários)

## Endpoints da API
#### Alunos:
| Método | Endpoint             | Descrição                    |
|--------|-----------------------|-----------------------------|
| POST   | `/api/alunos`      | Cadastra um novo aluno         |
| GET    | `/api/alunos`      | Consulta todos os alunos       |
| GET    | `/api/alunos/{id}` | Consulta um aluno por ID       |
| PUT    | `/api/alunos/{id}` | Atualiza um aluno              |
| DELETE | `/api/alunos/{id}` | Remove um aluno                |

#### Turmas:
| Método | Endpoint             | Descrição                    |
|--------|-----------------------|-----------------------------|
| POST   | `/api/turmas`      | Cadastra uma nova turma        |
| GET    | `/api/turmas`      | Consulta todas as turmas       |
| GET    | `/api/turmas/{id}` | Consulta uma turma por ID      |
| PUT    | `/api/turmas/{id}` | Atualiza uma turma             |
| DELETE | `/api/turmas/{id}` | Remove uma turma               |

### Exemplos de requisição

#### POST  `/api/alunos/`
```json
{
    "nome": "string",
    "cpf": "99948226438",
    "email": "string",
    "turmasIds": [
        "e076e1d1-bf23-45e3-bd07-9a1f15282eaf"
    ]
}
```

#### GET  `/api/turmas/{id}` 
```json
{
    "id": "e076e1d1-bf23-45e3-bd07-9a1f15282eaf",
    "numero": "2001",
    "anoLetivo": 2,
    "alunos": [
        {
            "id": "578f4769-a33d-447e-9b28-6d537e4b02c7",
            "nome": "João Silva",
            "cpf": "47023812601",
            "email": "joao.silva@email.com"
        }
    ]
}
```
## Instalação e Configuração
#### 1. Clone o repositório:
```bash
git clone https://github.com/samuelmsilva2v/webApiTurmas.git
cd webApiTurmas
```
#### 2. Configure o banco de dados MySQL:
* Crie um banco de dados chamado `bd_webturmasalunos`.
* Atualize o arquivo `application.properties` com suas credenciais do MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bd_webturmasalunos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
#### 3. Execute os testes:
```bash
./mvnw test
```
#### 3. Compile o projeto:
```bash
./mvnw clean install
```
#### 4. Execute a aplicação:
```bash
./mvnw spring-boot:run
```
#### 5. Acesse a documentação da API:
* Acesse o Swagger em: http://localhost:8080/swagger-ui.html

### Autor
- Samuel Maciel da Silva
  - [LinkedIn](https://www.linkedin.com/in/samuelmsilva2v/)
  - [E-mail](mailto:samuelmsilva@outlook.com.br)
