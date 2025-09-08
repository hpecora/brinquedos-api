
Integrantes:
- Henrique Pecora - RM556612
- santhiago de gobbi - RM98420
- livia de oliveira - rm556281

⚙️ Configuração do Projeto
1. Clonar o repositório
git clone https://github.com/SEU_USUARIO/brinquedos-api.git
cd brinquedos-api

2. Configurar o application.properties

Edite o arquivo src/main/resources/application.properties com suas credenciais do banco Oracle:

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect

server.port=8081

3. Rodar a aplicação
.\mvnw.cmd spring-boot:run


A API estará disponível em:
👉 http://localhost:8081/brinquedos

4.📌 Endpoints da API
Criar um brinquedo (POST)

POST /brinquedos

{
  "nome": "Lego City",
  "tipo": "Blocos",
  "classificacao": "6+",
  "tamanho": "Médio",
  "preco": 199.90
}

Listar todos os brinquedos (GET)

GET /brinquedos

Buscar brinquedo por ID (GET)

GET /brinquedos/{id}

Atualizar brinquedo (PUT)

PUT /brinquedos/{id}

Deletar brinquedo (DELETE)

DELETE /brinquedos/{id}



5. 🖥 IDE Utilizada

IntelliJ IDEA 

6. 🚀 Como rodar localmente

Ter o Java 17 instalado.

Ter o Oracle Database (ou acesso via FIAP) configurado.

Ajustar o application.properties.

Rodar mvnw spring-boot:run.

Testar no Postman.
