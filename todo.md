

## Tenant And Document 
Todo list on  6 Sep 2025
 - add document
 - update document
 - delete document




## Tenant And Address (contact)
Todo list on  7 Sep 2025
A tenant can have one to many addresses.
- add address
- update address
- delete address


## need to restructure source-code,
- use base-class to avoid DRY for common operation.


## CRUD for House Entity back-end and frontend (master-data view)


the-niche-coliving
│
├── domain                 # Core business concepts & entities
│   └── entity             # JPA entities, value objects
│   └── common             # Shared constants, enums, utilities
│   └── exception          # Domain-specific exceptions
│
├── application            # Business logic & use cases
│   └── service            # Application services, orchestrating domain
│   └── repository         # Interfaces for persistence (implementations in infra)
│   └── exception          # Application-specific exceptions
│
├── infrastructure         # Optional but recommended
│   └── repository         # Implementations of repository interfaces
│   └── config             # DB config, Kafka, external API clients, etc.
│
└── rest                   # Web layer / API
└── controller         # REST controllers
└── payload            # DTOs / request/response objects
└── config             # Web / Spring MVC config
└── exception          # API-level exception handlers
