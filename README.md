# Locação de Apartamentos

Sistema web para cadastro e locação de apartamentos, desenvolvido com backend, frontend e banco de dados.

## Tecnologias utilizadas

* Java 21
* Spring Boot 4.1.1
* Spring Data JPA
* Spring Web MVC
* Spring Validation
* PostgreSQL
* HTML
* CSS
* JavaScript
* Maven

## Requisitos

Para executar o projeto, é necessário ter instalado:

* Java 21
* Maven
* PostgreSQL
* Navegador web
* Visual Studio Code
* Extensão Live Server para executar o frontend

## Banco de dados

O projeto utiliza PostgreSQL.

Crie um banco de dados chamado:

```text
locacao_apartamentos
```

As configurações de acesso ao banco estão no arquivo:

```text
src/main/resources/application.properties
```

As configurações utilizadas são:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/locacao_apartamentos
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
```

> Altere `spring.datasource.username` e `spring.datasource.password` de acordo com as configurações do PostgreSQL da máquina utilizada.

## Como executar o backend

1. Certifique-se de que o PostgreSQL está em execução.
2. Crie o banco de dados `locacao_apartamentos`.
3. Verifique as configurações do arquivo `application.properties`.
4. Abra o projeto backend em uma IDE ou terminal.
5. Execute a aplicação Spring Boot.
6. O backend será iniciado na porta `8080`.

Backend:

```text
http://localhost:8080
```

## Como executar o frontend

O frontend está separado do projeto backend e está localizado na pasta:

```text
projetolocacao-frontend/
└── locacaoapartamentos-frontend/
    ├── index.html
    ├── style.css
    └── script.js
```

Para executar:

1. Abra a pasta `locacaoapartamentos-frontend` no Visual Studio Code.
2. Abra o arquivo `index.html`.
3. Clique com o botão direito no arquivo e selecione **Open with Live Server**.
4. O frontend será aberto no navegador.

O endereço utilizado pelo frontend é:

```text
http://127.0.0.1:5500
```

O backend deve estar em execução para que o frontend consiga realizar as operações.

## Funcionalidades

O sistema permite:

* Cadastrar edifícios;
* Cadastrar apartamentos;
* Visualizar a disponibilidade dos apartamentos;
* Alugar um apartamento;
* Visualizar o locatário de um apartamento alugado.

## API

A API segue o padrão RESTful e utiliza JSON para a comunicação entre o frontend e o backend.

### Edifícios

Listar edifícios:

```text
GET /edificios
```

Cadastrar edifício:

```text
POST /edificios
```

### Apartamentos

Listar apartamentos:

```text
GET /apartamentos
```

Listar apartamentos disponíveis:

```text
GET /apartamentos/disponiveis
```

Cadastrar apartamento:

```text
POST /apartamentos
```

Alugar apartamento:

```text
POST /apartamentos/{id}/alugar
```

Visualizar o locatário de um apartamento:

```text
GET /apartamentos/{id}/locatario
```

## Estrutura do projeto

### Backend

```text
locacaoapartamentos/
└── src/
    └── main/
        ├── java/
        │   └── locacaoapartamentos/
        │       ├── controller/
        │       ├── model/
        │       ├── repository/
        │       └── service/
        │
        └── resources/
            └── application.properties
```

### Frontend

```text
projetolocacao-frontend/
└── locacaoapartamentos-frontend/
    ├── index.html
    ├── style.css
    └── script.js
```

## Observações

O projeto utiliza:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Dessa forma, o Hibernate atualiza a estrutura das tabelas do banco de dados de acordo com as entidades da aplicação.

O banco de dados deve estar em execução antes de iniciar o backend.
