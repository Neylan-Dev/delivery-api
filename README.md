<h1 align="center">Delivery-Api</h1>

<p align="center">
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white">
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge">
</p>

<p align="center">Este projeto foi iniciado durante o mergulho spring rest java da Algaworks, onde foi implementada uma API java que gerência dados de clientes e entregas, segue em andamento onde novas funcionalidades estão sendo incorporadas ao projeto</p>
<hr>

# 🛠 Tecnologias

- [Spring](https://spring.io/)
- [Java](https://www.java.com/pt-BR/)
- [Mysql](https://www.mysql.com/)

<hr>

<h2 id="projeto">📝 Sobre o projeto </h1>

<p> Aplicação feita com spring boot que gerência dados de entregas de clientes onde é possível cadastrar/atualizar/excluir o mesmo permitindo que o mesmo seja relacionado com entrega. As entregas são compostas por estados: PENDENTE, FINALIZADO e CANCELADO, onde a aplicação permite a mudança dos estados de acordos com as regras definidas, e também é composta por ocorrências onde pode definir uma mensagem explicando o que aconteceu na entrega como por exemplo: a pessoa que iria receber o produto não estava em casa.</p>

<p> Nesse projeto foram implementados, validações por meio da criação de anotações especificas, camada de modelo, repositórios, controladores, camada de serviço, DTOs para requisição e resposta, exception personalizada, exception handler, model mapper (responsável por fazer o parse de um objeto para outro), testes de integração, testes de unidade e etc.</p>

<hr>

<h2 id="modulo">🧰 Módulos </h1>

- [Delivery-Common](https://github.com/Neylan-Dev/delivery-common)
- [Delivery-Api]