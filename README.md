# Pass-in

Microsserviço de gerenciamento de eventos presenciais. Esse projeto faz parte da NLW da RocketSeat

### Ferramentas utilizadas
  1. Spring Boot
  2. Spring Data JPA
  3. Lombok
  4. Flyway
  5. HSQLDB

## Requisitos

### Requisitos funcionais

- O organizador deve poder cadastrar um novo evento;
- O organizador deve poder visualizar dados de um evento;
- O organizador deve poder visualizar a lista de participantes;
- O participante deve poder se inscrever em um evento;
- O participante deve poder visualizar seu crachá de inscrição;
- O participante deve poder realizar check-in no evento;

### Regras de negócio

- O participante só pode se inscrever em um evento uma única vez;
- O participante só pode se inscrever em eventos com vagas disponíveis;
- O participante só pode realizar check-in em um evento uma única vez;

### Requisitos não-funcionais

- O check-in no evento será realizado através de um QRCode;

## API

  - POST /events/ - Cadastra um evento
  ```
  Payload:

  {
      title: String,
      details: String,
      maximumAttendees: Integer
  }

  ```

  ```
  200 OK:

  {
      "id": String
  }

  ```

  - GET /events/{id} - Busca um evento por Id

  ```
  200 OK:

  {
    "event": {
        "id": String,
        "title": String,
        "detail": String,
        "slug": String,
        "maximumAteendees": Integer
    },
    "attendees": Integer
  }

  ```

  - POST /events/{eventId}/attendees - Registra um participante no evento
    
  ```
  Payload:

  {
      "name": String,
      "email": String
  }

  ```

  ```
  200 OK:
  {
      "id": String
  }
  ```

  - GET /attendees/{attendeeId}/bagde - Retorna o crachá do participante


  ```
  200 OK:
  {
      "badge": {
          "name": String,
          "email": String,
          "url": String,
          "eventId": String
      }
  }
  ```

  - POST /attendees/{attendeeId}/check-in - Realiza o checkin do participante

  ```
  200 OK:
  ```
  
