# Spring Boot Kafka Example - Notification Service with SMS and Email Consumers

Este repositório contém um exemplo de integração do **Spring Boot** com **Kafka**, implementando um **Producer** (Notification Service) e dois **Consumers** (SMS Sender e Email Sender). O Producer envia notificações para tópicos Kafka, e os Consumers processam as mensagens de acordo com o tipo (SMS ou Email).

## Estrutura do Projeto

O projeto é dividido em três serviços principais:

1. **NotificationService (Producer)**: Envia mensagens para o Kafka.
2. **SmsSender (Consumer 1)**: Consome as mensagens do Kafka e as processa como notificações SMS.
3. **EmailSender (Consumer 2)**: Consome as mensagens do Kafka e as processa como notificações por email.

### Arquitetura

- **Producer (NotificationService)** envia mensagens para um tópico Kafka.
- **Consumer 1 (SmsSender)** escuta o tópico de mensagens e as processa como notificações SMS.
- **Consumer 2 (EmailSender)** escuta o tópico de mensagens e as processa como notificações de e-mail.

## Tecnologias Utilizadas

- **Spring Boot 3.x**
- **Kafka** para a mensageria assíncrona
- **Docker** para a containerização da aplicação
- **Java 21**
- **Maven** para gerenciamento de dependências e build

## Pré-requisitos

- Docker e Docker Compose instalados em sua máquina.

## Como Rodar o Projeto

### 1. Clonar o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/lucastavares10/spring-boot-kafka.git
cd spring-boot-kafka/notificationservice
```

### 2. Construir e Subir os Containers Docker

Para compilar e rodar os containers execute o comando:

```bash
docker-compose up --build
```

### 3. Testar a Aplicação

O Producer estará disponível na porta 5000 e pode ser acessado via uma requisição POST para enviar notificações.

```bash
POST http://localhost:8080/notifications/send
```

Com o seguinte corpo de requisição:

```bash
{
  "userId": "user123",
  "message": "Mensagem de teste",
  "type": "SMS"
}
```
