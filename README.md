# Pass-in

Microsserviço de gerenciamento de eventos presenciais. Esse projeto faz parte da NLW da RocketSeat

### Ferramentas utilizadas
  1. Spring Boot
  2. Spring Data JPA
  3. Lombok
  4. Flyway
  5. HSQLDB

### API

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
  
