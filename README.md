
# Projeto back-end com Spring Boot Cadastro de Contas a Pagar

- Spring Framework
- Spring Boot
- Spring Data
- REST
- Swagger
- Mysql
- JdbcTemplate
- JUnit5
- Mockito

-( Projeto em desenvolvimento : Cobertura de testes atualmente = 79,3% )

O projeto consiste em uma API para cadastro de contas a pagar, desenvolvido totalmente com a tecnologia Java, utilizando as tecnologias acima mencionadas.


## Configurando o projeto

1) git clone ou download do zip: https://github.com/GabrielGRS0206/spring-control-finance

2) Importe o projeto em sua IDE de preferência

3) Altere o usuario e senha para que o projeto possa acessar o banco mysql. 
  * Vá até `/src/main/resources/application.properties`;
  * Altere as propriedades informado o usuário e senha do seu banco de dados: 
    - spring.datasource.username=usuario
    - spring.datasource.password=senha
    - spring.datasource.url = jdbc:mariadb://localhost:3306/seuBancoDeDados?useSSL=false
4) Na classe App de um run na sua IDE.
5) Acesse: http://localhost:8080/swagger-ui.html para visualizar os endpoints




