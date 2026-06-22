# 🏥 Hospital - Sistema de Gerenciamento

API RESTful desenvolvida com **Spring Boot** para gerenciar pacientes, médicos, consultas e internações de um hospital. O sistema utiliza **PostgreSQL** como banco de dados relacional.

---

## 📋 Sobre o Projeto

O sistema permite cadastrar e gerenciar:
- **Pacientes** (cadastro, busca, listagem e remoção)
- **Médicos** (cadastro e listagem)
- **Consultas** (cadastro, vinculando paciente e médico)
- **Internações** (vinculadas a um paciente)
- **Ranking de médicos** por quantidade de consultas realizadas

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.x
- Maven
- Spring Data JPA + PostgreSQL Driver
- Spring Boot Validation
- Spring Boot Actuator
- Lombok
- JUnit + Mockito (testes unitários)
- Spring Boot Test + MockMvc (testes de integração)
- Docker + Docker Compose

---

## 🐳 Rodando com Docker (recomendado)

Essa é a forma mais simples de rodar o projeto — não precisa ter Java, Maven nem PostgreSQL instalados na máquina, só o Docker.

### Pré-requisitos

- Docker Desktop instalado e em execução

### Passo a passo

**1. Clone o repositório:**

```bash
git clone [<URL_DO_REPOSITORIO>](https://github.com/LarissaS10/Projeto-Hospital-Assessment)
```

**2. Acesse a pasta do projeto:**

```bash
cd hospital
```

**3. Suba os containers:**

```bash
docker-compose up --build
```

Isso vai automaticamente:
- Criar um container com o PostgreSQL (porta `5432`)
- Buildar e subir a aplicação Spring Boot (porta `8080`)
- Criar as tabelas no banco
- Popular dados iniciais (médicos e pacientes de exemplo)

**4. Aguarde a mensagem no terminal:**

```
Dados iniciais carregados com sucesso!
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

### Parando os containers

```bash
docker-compose down
```

### Parando e resetando o banco (apaga todos os dados)

```bash
docker-compose down -v
```

---
