# SpringBoot-RestAPI

Projeto de uma API Rest completa, com do rotas autenticadas e documentadas com SWAGGER, também contem rotas e serviços para analise de metricas da aplicação.

## O que é p projeto?
    
- Api Rest completa com rotas protegidas por autenticação de usuario e admin.
- Métodos _**HTTP**_ : GET, POST, PUT e DELETE.
- Análise de metricas da aplicação pelo PROMETHEUS.
- Dashboard das metricas pelo GRAFANA.

## Rotas 

Rotas de acesso para as series registradas no sistema, traz dados paginados tendo parametros para configuração das paginasÇ

    /series?page={NumberPage}&size={PageSize}

Método **GET** para acessar a serie pelo seu ID: 
    
    /series/{id}

Método **GET** para pegar estudante pelo nome:

    /seriesfindByName?name={Name}

### rotas para admin:

Método **POST** para criar uma serie:

    /admin/series

Método **PUT** para alterar um estudante: 

    /admin/series
 
    *Para alterar um estudante é nescessario passar seu ID no corpo da requisição, exemplo:

    {
        "id": 1,
        "name": "Stranger things"
    }

Método **DELETE** para um estudante:

    /admin/series/{id}
    
    
## SWAGGER
 
Como nova atualização o swagger esta disponivel, tendo uma documentação completa da API, basta acessar ao rodar a aplicação:

    localhost:8080/swagger-ui.html
 
## PROMETHEUS

Para acesso as metricas no prometheus:

    localhost:9090
    
## GRAFANA

Para acesso as dashboards da aplicação:

  localhost:3000
