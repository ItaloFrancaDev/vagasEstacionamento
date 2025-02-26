Projeto Estacionamento

Este é um sistema de gerenciamento de vagas de estacionamento desenvolvido em Spring Boot. Ele permite o cadastro de vagas, reservas, liberação de vagas e cálculo de custos com base no tempo de locação.
Descrição do Projeto

O sistema foi desenvolvido para simular um cenário real de gerenciamento de vagas de estacionamento. As principais funcionalidades incluem:

    Cadastro de vagas: Cada vaga possui um número, tipo (comum ou VIP), valor por hora e status (disponível, reservada ou ocupada).

    Reserva de vagas: Um usuário pode reservar uma vaga disponível.

    Liberação de vagas: Ao encerrar uma reserva, o sistema calcula o valor total com base no tempo de locação e atualiza o status da vaga.

    Consulta de vagas disponíveis: Lista todas as vagas com status "disponível".

Instruções de Configuração e Build
Pré-requisitos

    Java 8 ou superior.

    Maven para gerenciamento de dependências e build.

    Banco de dados H2 (em memória ou em arquivo).

Configuração

    Clone o repositório:
   
    git clone https://github.com/ItaloFrancaDev/projeto-estacionamento.git

    Navegue até o diretório do projeto:
    cd projeto-estacionamento

    Configure o banco de dados no arquivo application.properties:
   
    # Configuração do H2 Database (em memória)
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

    # Configuração do H2 Console
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console

    Para usar um banco de dados em arquivo, altere a URL:
    properties
   
    spring.datasource.url=jdbc:h2:file:C:/caminho/para/banco/dados;AUTO_SERVER=TRUE

    Compile o projeto:
    
    mvn clean install



Decisões Arquiteturais

Arquitetura do Projeto

O projeto foi desenvolvido seguindo o padrão MVC (Model-View-Controller) e utiliza as seguintes camadas:

    Controller: Responsável por receber as requisições HTTP e retornar as respostas adequadas.

    Service: Contém a lógica de negócio e interage com o repositório.

    Repository: Responsável pela persistência dos dados no banco de dados.

    Model: Representa as entidades do sistema (ex: ParkingSpot, Reservation).

Tecnologias Utilizadas

    Spring Boot: Framework para desenvolvimento de aplicações Java.

    H2 Database: Banco de dados em memória para desenvolvimento e testes.

    JPA/Hibernate: Para mapeamento objeto-relacional (ORM).

    Lombok: Para reduzir boilerplate code (getters, setters, construtores).

    JUnit e Mockito: Para testes unitários e de integração.

    Jacoco: Para análise de cobertura de testes.

Escolha do Banco de Dados

O H2 Database foi escolhido por ser leve, fácil de configurar e ideal para desenvolvimento e testes. Ele pode ser usado em memória ou em arquivo, dependendo das necessidades do projeto.
Testes Automatizados

Foram implementados testes unitários para os serviços e testes de integração para os controladores. O uso do Mockito permite simular dependências e testar a lógica de negócio de forma isolada.
Endpoints da API
Vagas de Estacionamento

    Cadastrar uma nova vaga:

        Método: POST

        URL: /api/vagas

        Body (JSON):
        json
        Copy

        {
          "numero": "A01",
          "tipo": "COMUM",
          "valorHora": 10.00,
          "status": "DISPONIVEL"
        }

    Listar vagas disponíveis:

        Método: GET

        URL: /api/vagas/disponiveis

Reservas

    Criar uma reserva:

        Método: POST

        URL: /api/reservas?vagaId=1

    Encerrar uma reserva:

        Método: PUT

        URL: /api/reservas/{id}/encerrar
