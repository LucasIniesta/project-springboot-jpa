# Projeto Spring JPA

Uma API RESTful construída com Spring Boot e JPA para um sistema de e-commerce completo.

## Visão Geral

Este projeto é uma aplicação Spring Boot que fornece uma API RESTful para um sistema de e-commerce. O sistema gerencia usuários, produtos, categorias, pedidos e pagamentos. Utiliza Spring Data JPA para operações de banco de dados e segue as melhores práticas para design de API e tratamento de exceções.

## Funcionalidades

- Gerenciamento completo de usuários (CRUD)
- Catálogo de produtos com categorias
- Sistema de pedidos com itens e status
- Processamento de pagamentos
- Endpoints de API RESTful para todas as entidades
- Tratamento de exceções robusto
- Validação de dados
- Integração com banco de dados via JPA

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- Maven
- Banco de dados H2 (presumido, modificar se estiver usando um banco de dados diferente)

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── lucas/
│   │           └── projeto_spring_jpa/
│   │               ├── config/
│   │               │   └── TestConfig.java (presumido)
│   │               ├── entities/
│   │               │   ├── Category.java
│   │               │   ├── Order.java
│   │               │   ├── OrderItem.java
│   │               │   ├── Payment.java
│   │               │   ├── Product.java
│   │               │   ├── User.java
│   │               │   ├── enums/
│   │               │   │   └── OrderStatus.java
│   │               │   └── pk/
│   │               │       └── OrderItemPK.java
│   │               ├── repositories/
│   │               │   ├── CategoryRepository.java
│   │               │   ├── OrderItemRepository.java
│   │               │   ├── OrderRepository.java
│   │               │   ├── ProductRepository.java
│   │               │   └── UserRepository.java
│   │               ├── resources/
│   │               │   ├── CategoryResource.java
│   │               │   ├── OrderResource.java
│   │               │   ├── ProductResource.java
│   │               │   ├── UserResource.java
│   │               │   └── exceptions/
│   │               ├── services/
│   │               │   ├── CategoryService.java
│   │               │   ├── OrderService.java
│   │               │   ├── ProductService.java
│   │               │   ├── UserService.java
│   │               │   └── exceptions/
│   │               │       ├── DabaseException.java
│   │               │       └── ResourceNotFindException.java
│   │               └── ProjetoSpringJpaApplication.java
│   └── resources/
│       └── application.properties
└── test/
```

## Como Começar

### Pré-requisitos

- Java 8 ou superior
- Maven

### Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/LucasIniesta/project-springboot-jpa.git
   cd projeto-spring-jpa
   ```

2. Construa o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A aplicação será iniciada em `http://localhost:8080`.

## Endpoints da API

### Usuários

| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | /users | Obter todos os usuários |
| GET    | /users/{id} | Obter usuário por ID |
| POST   | /users | Criar um novo usuário |
| PUT    | /users/{id} | Atualizar usuário por ID |
| DELETE | /users/{id} | Deletar usuário por ID |

### Produtos

| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | /products | Obter todos os produtos |
| GET    | /products/{id} | Obter produto por ID |

### Categorias

| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | /categories | Obter todas as categorias |
| GET    | /categories/{id} | Obter categoria por ID |

### Pedidos

| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | /orders | Obter todos os pedidos |
| GET    | /orders/{id} | Obter pedido por ID |

## Modelo de Domínio

O projeto implementa o seguinte modelo de domínio:

### Entidades Principais

1. **User (Usuário)**
   - Representa um usuário do sistema
   - Atributos: id, name, email, phone, password
   - Relacionamentos: Um usuário pode ter vários pedidos (Orders)

2. **Product (Produto)**
   - Representa um produto disponível para venda
   - Atributos: id, name, description, price, imgUrl
   - Relacionamentos: Um produto pode pertencer a várias categorias e estar em vários itens de pedido

3. **Category (Categoria)**
   - Representa uma categoria de produtos
   - Atributos: id, name
   - Relacionamentos: Uma categoria pode conter vários produtos

4. **Order (Pedido)**
   - Representa um pedido feito por um usuário
   - Atributos: id, moment, orderStatus
   - Relacionamentos: Um pedido pertence a um usuário, contém vários itens (OrderItem) e pode ter um pagamento

5. **OrderItem (Item de Pedido)**
   - Representa um item dentro de um pedido
   - Atributos: quantity, price
   - Relacionamentos: Associa um produto a um pedido com uma quantidade específica
   - Utiliza uma chave composta (OrderItemPK) para mapear o relacionamento

6. **Payment (Pagamento)**
   - Representa o pagamento de um pedido
   - Atributos: id, moment
   - Relacionamentos: Um pagamento está associado a um pedido

### Enumerações

1. **OrderStatus (Status do Pedido)**
   - Define os possíveis estados de um pedido
   - Valores: WAITING_PAYMENT, PAID, SHIPPED, DELIVERED, CANCELED

### Classes Auxiliares

1. **OrderItemPK**
   - Classe de chave primária composta para a entidade OrderItem
   - Combina as referências para Order e Product

## Fluxo de Processos

### Processo de Compra

1. Um usuário (User) é registrado no sistema
2. O usuário navega pelo catálogo de produtos (Product) organizados por categorias (Category)
3. O usuário cria um pedido (Order) com status inicial WAITING_PAYMENT
4. Itens (OrderItem) são adicionados ao pedido, cada um referenciando um produto e sua quantidade
5. Quando o pagamento é processado, um objeto Payment é criado e associado ao pedido
6. O status do pedido é atualizado conforme o progresso (PAID, SHIPPED, DELIVERED)

### Arquitetura em Camadas

O projeto segue uma arquitetura em camadas:

1. **Camada de Recursos (Resources)**
   - Controllers REST que expõem os endpoints da API
   - Recebem requisições HTTP e delegam para a camada de serviço
   - Tratam a serialização/deserialização de objetos JSON

2. **Camada de Serviço (Services)**
   - Contém a lógica de negócio
   - Gerencia transações
   - Implementa validações e regras de negócio
   - Trata exceções específicas do domínio

3. **Camada de Repositório (Repositories)**
   - Interfaces que estendem JpaRepository
   - Fornecem métodos para operações CRUD e consultas personalizadas
   - Abstraem o acesso ao banco de dados

4. **Camada de Entidades (Entities)**
   - Classes de domínio mapeadas para tabelas do banco de dados
   - Definem o modelo de dados e relacionamentos

### Exemplos de Requisição e Resposta

#### Obter todos os usuários

```
GET /users
```

Resposta:
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao@exemplo.com",
    "phone": "123-456-7890"
  },
  {
    "id": 2,
    "name": "Maria Souza",
    "email": "maria@exemplo.com",
    "phone": "987-654-3210"
  }
]
```

#### Obter usuário por ID

```
GET /users/1
```

Resposta:
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@exemplo.com",
  "phone": "123-456-7890"
}
```

#### Criar um novo usuário

```
POST /users
```

Corpo da requisição:
```json
{
  "name": "Novo Usuário",
  "email": "novo@exemplo.com",
  "phone": "555-123-4567"
}
```

Resposta:
```json
{
  "id": 3,
  "name": "Novo Usuário",
  "email": "novo@exemplo.com",
  "phone": "555-123-4567"
}
```

#### Obter todos os produtos

```
GET /products
```

Resposta:
```json
[
  {
    "id": 1,
    "name": "Smartphone XYZ",
    "description": "Smartphone de última geração",
    "price": 1999.90,
    "imgUrl": "smartphone.jpg",
    "categories": [
      {
        "id": 1,
        "name": "Eletrônicos"
      }
    ]
  },
  {
    "id": 2,
    "name": "Notebook ABC",
    "description": "Notebook com processador de alta performance",
    "price": 4500.00,
    "imgUrl": "notebook.jpg",
    "categories": [
      {
        "id": 1,
        "name": "Eletrônicos"
      },
      {
        "id": 3,
        "name": "Computadores"
      }
    ]
  }
]
```

#### Obter produto por ID

```
GET /products/1
```

Resposta:
```json
{
  "id": 1,
  "name": "Smartphone XYZ",
  "description": "Smartphone de última geração",
  "price": 1999.90,
  "imgUrl": "smartphone.jpg",
  "categories": [
    {
      "id": 1,
      "name": "Eletrônicos"
    }
  ]
}
```

#### Obter todas as categorias

```
GET /categories
```

Resposta:
```json
[
  {
    "id": 1,
    "name": "Eletrônicos"
  },
  {
    "id": 2,
    "name": "Livros"
  },
  {
    "id": 3,
    "name": "Computadores"
  }
]
```

#### Obter pedido por ID

```
GET /orders/1
```

Resposta:
```json
{
  "id": 1,
  "moment": "2023-06-20T21:53:07Z",
  "orderStatus": "PAID",
  "client": {
    "id": 1,
    "name": "João Silva",
    "email": "joao@exemplo.com",
    "phone": "123-456-7890"
  },
  "items": [
    {
      "quantity": 2,
      "price": 1999.90,
      "product": {
        "id": 1,
        "name": "Smartphone XYZ",
        "description": "Smartphone de última geração",
        "price": 1999.90
      }
    },
    {
      "quantity": 1,
      "price": 4500.00,
      "product": {
        "id": 2,
        "name": "Notebook ABC",
        "description": "Notebook com processador de alta performance",
        "price": 4500.00
      }
    }
  ],
  "payment": {
    "id": 1,
    "moment": "2023-06-20T23:42:10Z"
  },
  "total": 8499.80
}
```

#### Atualizar usuário

```
PUT /users/1
```

Corpo da requisição:
```json
{
  "name": "Nome Atualizado",
  "email": "atualizado@exemplo.com",
  "phone": "555-987-6543"
}
```

Resposta:
```json
{
  "id": 1,
  "name": "Nome Atualizado",
  "email": "atualizado@exemplo.com",
  "phone": "555-987-6543"
}
```

#### Deletar usuário

```
DELETE /users/1
```

Resposta: 204 No Content

## Tratamento de Erros

A API utiliza códigos de status HTTP padrão para indicar o sucesso ou falha das requisições:

- 200 OK: A requisição foi bem-sucedida
- 201 Created: Um novo recurso foi criado
- 204 No Content: A requisição foi bem-sucedida, mas não há conteúdo para retornar
- 400 Bad Request: A requisição foi inválida
- 404 Not Found: O recurso solicitado não foi encontrado
- 500 Internal Server Error: Ocorreu um erro no servidor

As respostas de erro incluem uma mensagem descrevendo o erro.

## Desenvolvimento

### Adicionando uma Nova Entidade

1. Crie uma nova classe de entidade no pacote `entities`
2. Crie uma interface de repositório no pacote `repositories`
3. Crie uma classe de serviço no pacote `services`
4. Crie uma classe de controlador no pacote `resources`

### Implementação de Relacionamentos

O projeto implementa diversos tipos de relacionamentos entre entidades:

1. **Um-para-Muitos (One-to-Many)**
   - Exemplo: Um usuário pode ter vários pedidos
   - Implementação: `@OneToMany` na classe User apontando para Order

2. **Muitos-para-Um (Many-to-One)**
   - Exemplo: Vários pedidos podem pertencer a um usuário
   - Implementação: `@ManyToOne` na classe Order apontando para User

3. **Muitos-para-Muitos (Many-to-Many)**
   - Exemplo: Um produto pode pertencer a várias categorias e uma categoria pode ter vários produtos
   - Implementação: `@ManyToMany` nas classes Product e Category

4. **Um-para-Um (One-to-One)**
   - Exemplo: Um pedido tem um pagamento
   - Implementação: `@OneToOne` na classe Order apontando para Payment

### Tratamento de Exceções

O projeto implementa um sistema robusto de tratamento de exceções:

1. **ResourceNotFindException**
   - Lançada quando um recurso solicitado não é encontrado
   - Mapeada para o status HTTP 404 (Not Found)

2. **DatabaseException**
   - Lançada quando ocorre um erro relacionado ao banco de dados
   - Mapeada para o status HTTP 500 (Internal Server Error)

### Boas Práticas Implementadas

1. **Arquitetura em Camadas**
   - Separação clara entre recursos, serviços e repositórios

2. **Injeção de Dependências**
   - Uso de `@Autowired` para injetar dependências

3. **Tratamento de Exceções Centralizado**
   - Handlers globais para tratar exceções de forma consistente

4. **Validação de Dados**
   - Validação de entrada antes de processar operações

5. **Transações**
   - Gerenciamento de transações com `@Transactional`

## Contribuindo

1. Faça um fork do repositório
2. Crie um branch para sua feature (`git checkout -b feature/recurso-incrivel`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona um recurso incrível'`)
4. Faça push para o branch (`git push origin feature/recurso-incrivel`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.

## Contato

Lucas Iniesta Simões - l.iniesta.94@gmail.com

Link do Projeto: [https://github.com/lucasiniesta/projeto-spring-jpa](https://github.com/lucasiniesta/projeto-spring-jpa)
