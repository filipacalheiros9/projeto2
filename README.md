# Projeto_II_desktop

Aplicação desktop para gestão operacional de uma gráfica, construída com **JavaFX** (interface) e **Spring Boot + Spring Data JPA** (lógica e persistência), usando **PostgreSQL**.

## Funcionalidades

- Autenticação de utilizadores (funcionários).
- Separação de dashboards por perfil (gerente e funcionário).
- Gestão de funcionários.
- Gestão de fornecedores e respetivos pagamentos.
- Gestão de projetos, serviços e tipos de impressão/projeto.
- Gestão de materiais, stock e encomendas.

## Stack Tecnológica

- Java 17
- Maven Wrapper (`mvnw` / `mvnw.cmd`)
- Spring Boot 3.4.3
- Spring Data JPA (Hibernate)
- JavaFX 21.0.2
- PostgreSQL

## Pré-requisitos

- JDK 17 instalado
- PostgreSQL em execução local
- Maven (opcional, porque o projeto já inclui Maven Wrapper)

## Configuração da Base de Dados

O projeto lê as configurações em:

- `src/main/resources/application.properties`

Valores atuais:

- `spring.datasource.url=jdbc:postgresql://localhost:5432/projeto2`
- `spring.datasource.username=postgres`
- `spring.datasource.password=...`
- `spring.jpa.hibernate.ddl-auto=update`

1. Criar a base de dados no PostgreSQL:

```sql
CREATE DATABASE projeto2;
```

2. Ajustar utilizador/password no `application.properties` para o teu ambiente.

## Como Executar

### Windows (PowerShell)

1. Compilar:

```powershell
.\mvnw.cmd clean package
```

2. Executar a aplicação JavaFX:

```powershell
.\mvnw.cmd javafx:run
```

### Linux/macOS

1. Compilar:

```bash
./mvnw clean package
```

2. Executar:

```bash
./mvnw javafx:run
```

## Testes

Executar testes automatizados:

### Windows

```powershell
.\mvnw.cmd test
```

### Linux/macOS

```bash
./mvnw test
```

## Estrutura do Projeto

```text
src/main/java/com/example
  controllers/          # Controladores JavaFX
  projeto2/models/      # Entidades JPA
  projeto2/repositories/# Repositórios Spring Data
  projeto2/services/    # Regras de negócio
  Main.java             # Ponto de entrada da aplicação (JavaFX + Spring)

src/main/resources
  fxml/                 # Ecrãs JavaFX
  styles/               # CSS da interface
  images/               # Recursos visuais
  application.properties
```

## Notas

- O ficheiro `Projeto2Application.java` contém código de demonstração para inserir dados e não é o ponto de entrada principal da aplicação.
- O arranque principal é feito em `com.example.Main`.
