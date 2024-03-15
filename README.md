## **Sistema de biblioteca: Gestão de Livros**
Trabalho Final ADA: Ser + Tech Programa 50+

            Gestão de Livros e Empréstimos da Biblioteca.
            Definir prazo de devolução e multas por atraso.

**Equipe**:
* Jefferson Domareski
* Otair Caetano
* Helder Gonçalves
* Marcelo Rocha
* Jorge Rosivan
___

### Tecnologia:
* Java 17
* SpringBoot 3.2.3
* Swagger 3
* WSL2 - Ubuntu 22.04
* PostgreSql 16.2
* Docker 24.0
* IntelliJ IDEA 2023.3.4 (Community Edition)
* DBeaver 24.0

### Funcionalidades:

* localhost:8080/usuario: gerencia os usuários da biblioteca:<br>
   * get: Retorna lista com todos os usuários cadastrados.    
   * get (/{id}): Retorna o usuário do ID fornecido.   
   * post: Cadastra o usuário com os dados fornecido por um body:json.
  
* localhost:8080/livro: gerencia os livros na biblioteca:<br>
  * get: Retorna lista com todos os livros cadastrados.    
  * get (/{id}): Retorna o livro do ID fornecido.   
  * post: Cadastra o livro com os dados fornecido por um body:json.
   
* localhost:8080/emprestimo: gerencia todos os empréstimos dos livros:<br>
  * get: Retorna lista com todos os empréstimos efetuados.    
  * get (/{id}): Retorna o empréstimo do ID fornecido.   
  * post: Cadastra o emprestimo com os dados fornecido por um body:json.
    * obs: Caso não fornecer o "DataEmprestimo" ou "PrazoDevolucao" no body:json, os mesmo serão calculados.
  * put (/{id}): Retorna o empréstimo do ID fornecido.
      * obs1: Caso não fornecer o "Datadevolucao" no body:json, sistema grava a data atual.
      * obs2: Retorna o valor da multa, caso ultrapasse a data de devolução.

> Classes do Projeto:
> 1) Livro
> 2) Usuario
> 3) Emprestimo

Diagrama:

![diagrama.png](images%2Fdiagrama.png)


Acesso da documentação via Swagger:

* http://localhost:8080/swagger-ui/index.html


Tela de execução do Swagger:
![swagger.png](images%2Fswagger.png)






