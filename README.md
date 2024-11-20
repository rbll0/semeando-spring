# Semeando 🌱 - Educação Sustentável com Gamificação

## 1. Nome da Aplicação

### **Semeando** - Aprendendo Energia e Sustentabilidade

--------------------------------------------

## 2. Apresentação dos Integrantes do Grupo

- **Gustavo Rabelo Frere - RM553326**:  
  Desenvolvedor backend, responsável pela implementação de serviços e lógica de negócios em **Java com Spring Boot**, integração com **Oracle Database**, e design de **APIs REST**.

- **Felipe Arcanjo Matos dos Anjos - RM554018**:  
  Responsável pela modelagem do banco de dados e desenvolvimento do frontend em **Kotlin**.

- **Marcelo Vieira Junior - RM553640**:
  Responsável pela documentação, testes de integração com **Postman**, e organização do cronograma de desenvolvimento.

--------------------------------------------

## 3. Sobre o Projeto

**Semeando** é uma aplicação gamificada que educa adolescentes e jovens adultos sobre sustentabilidade e práticas ambientais saudáveis. Inspirado no Duolingo, o sistema apresenta quizzes interativos que abordam temas como:

- **Fontes de Energia Renovável**
- **Consumo Doméstico**
- **Transporte Sustentável**
- **Mudanças Climáticas**
- **Práticas de Economia de Energia**

O sistema utiliza uma abordagem de gamificação para manter o engajamento, oferecendo:

- **Pontuação e medalhas**
- **Streaks diários**
- **Ranking mensal por região**
- **Missões em grupo**

--------------------------------------------

## 4. Como Rodar a Aplicação

### 4.1 Pré-requisitos

Certifique-se de ter as seguintes ferramentas e configurações:

- **Java 17**
- **Maven**
- **Oracle Database**
- **Docker e Docker Compose**
- **Máquina virtual configurada no Azure**

---

### 4.2 Passos para execução

#### 1. Clone o repositório
```bash
git clone https://github.com/seu-repositorio/semeando.git
cd semeando
```
--------------------------------------------
### 2. Configure o banco de dados

No arquivo `application.yml` localizado em `src/main/resources`, configure as credenciais do banco de dados Oracle conforme o exemplo abaixo:

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@<SEU_HOST>:<PORTA>/<SID>
    username: <SEU_USUARIO>
    password: <SUA_SENHA>
    driver-class-name: oracle.jdbc.OracleDriver
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.OracleDialect
```

#### 3. Compile o projeto
```bash
mvn clean install
```
--------------------------------------------
### 3. Configure o Docker Compose

Atualize o arquivo `docker-compose.yml` para incluir os containers da aplicação e do banco de dados. Abaixo está um exemplo adaptado para o seu projeto:

```yaml
version: '3.8'
services:
  java-service:
    container_name: java-service
    build:
      context: Java_Advanced/sprint-2/challenge
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:oracle:thin:@<SEU_HOST>:1521:<SID>
      SPRING_DATASOURCE_USERNAME: <SEU_USUARIO>
      SPRING_DATASOURCE_PASSWORD: <SUA_SENHA>
      SPRING_JPA_HIBERNATE_DDL_AUTO: update

  oracle-db:
    container_name: oracle-db
    image: oracle/database:19.3.0-ee
    environment:
      ORACLE_PWD: <ORACLE_PASSWORD>
    ports:
      - "1521:1521"
    volumes:
      - oracle-data:/opt/oracle/oradata

volumes:
  oracle-data:
```
--------------------------------------------
### Passos para execução

1. Certifique-se de que o arquivo `Dockerfile` está configurado corretamente para a aplicação Java.

2. Substitua os seguintes valores no `docker-compose.yml` conforme necessário:
    - `<SEU_HOST>`: O endereço do banco de dados.
    - `<SID>`: O identificador do banco de dados Oracle.
    - `<SEU_USUARIO>`: O nome de usuário do banco de dados.
    - `<SUA_SENHA>`: A senha do banco de dados.
    - `<ORACLE_PASSWORD>`: A senha para o usuário Oracle no container.

3. Execute os comandos a seguir para iniciar os serviços:
   No terminal, navegue até o diretório onde o arquivo `docker-compose.yml` está localizado e execute:

   ```bash
   docker-compose up -d
   ```
--------------------------------------------

### 5. Deploy em Nuvem

O deploy foi realizado na **Azure** utilizando uma máquina virtual Linux configurada com Docker.

#### Informações de Acesso

- **URL da aplicação**: [http://<SEU_IP_AZURE>:8080](http://<SEU_IP_AZURE>:8080)
- **Swagger UI**: [http://<SEU_IP_AZURE>:8080/swagger-ui.html](http://<SEU_IP_AZURE>:8080/swagger-ui.html)

#### Credenciais do Banco de Dados
- **Usuário**: `seu-usuario`
- **Senha**: `sua-senha`

--------------------------------------------
### 6. Documentação da API

A documentação da API foi gerada utilizando **Swagger**. Para acessá-la, utilize o seguinte link:

- **Swagger UI**: [http://<SEU_IP_AZURE>:8080/swagger-ui.html](http://<SEU_IP_AZURE>:8080/swagger-ui.html)

---

### Arquivo de Testes (Postman)

Os testes de API foram organizados em uma coleção Postman, que pode ser importada diretamente no aplicativo. A coleção inclui exemplos para todos os endpoints do sistema, permitindo validar as funcionalidades implementadas.

#### **Passos para Importar e Testar no Postman:**

1. Baixe o arquivo da coleção : [Semeando.postman_collection.json](./documents/postman/Semeando.postman_collection.json).
2. No Postman, clique em **Import**.
3. Selecione o arquivo baixado.
4. Utilize os exemplos disponíveis na coleção para testar os endpoints.

---

#### **Exemplos na Coleção:**

- **GET /usuarios**: Lista todos os usuários cadastrados.
- **POST /usuarios**: Cadastra um novo usuário.
- **GET /levels**: Lista todos os níveis.
- **POST /levels**: Cria um novo nível.
- **GET /perguntas/paginadas**: Retorna perguntas com paginação.

--------------------------------------------
### 7. Vídeo de Apresentação

Disponibilizamos dois vídeos no YouTube demonstrando nossa solução:

1. **Vídeo Demonstrativo (Funcionamento do Sistema)**  
   Um vídeo explicativo com duração máxima de 10 minutos, demonstrando o funcionamento do sistema e suas principais funcionalidades:  
   [Assista aqui](https://youtu.be/SEU_LINK_VIDEO_DEMONSTRACAO)

2. **Vídeo Pitch**  
   Um vídeo de apresentação com duração de até 3 minutos, destacando os pontos fortes e o impacto do projeto:  
   [Assista aqui](https://youtu.be/SEU_LINK_VIDEO_PITCH)
