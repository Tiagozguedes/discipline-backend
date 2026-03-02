# Discipline - Backend

API REST para a plataforma de monitoramento de disciplina pessoal.

## Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- SQLite (via sqlite-jdbc + Hibernate Community Dialects)
- Lombok
- Maven

## Endpoints da API

### Dashboard
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/dashboard` | Dados agregados do dashboard |

### Pomodoro
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/pomodoro/stats?period=today` | Estatísticas (today/week) |
| POST | `/api/pomodoro` | Registrar sessão completada |

### Tarefas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/tasks` | Listar todas as tarefas |
| POST | `/api/tasks` | Criar nova tarefa |
| PUT | `/api/tasks/{id}` | Atualizar tarefa |
| DELETE | `/api/tasks/{id}` | Remover tarefa |

### Calendário
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/calendar?startDate=...&endDate=...` | Eventos no período |
| POST | `/api/calendar` | Criar evento |
| DELETE | `/api/calendar/{id}` | Remover evento |

### Hábitos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/habits` | Listar hábitos ativos |
| POST | `/api/habits` | Criar hábito |
| DELETE | `/api/habits/{id}` | Remover hábito |
| POST | `/api/habits/{id}/toggle?date=YYYY-MM-DD` | Marcar/desmarcar completude |

### Financeiro
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/finance/transactions?startDate=...&endDate=...` | Transações no período |
| POST | `/api/finance/transactions` | Criar transação |
| DELETE | `/api/finance/transactions/{id}` | Remover transação |
| GET | `/api/finance/investments` | Listar investimentos |
| POST | `/api/finance/investments` | Criar investimento |
| DELETE | `/api/finance/investments/{id}` | Remover investimento |
| GET | `/api/finance/summary?month=...&year=...` | Resumo financeiro mensal |
| GET | `/api/finance/investments/summary` | Resumo de investimentos |

## Como Rodar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Desenvolvimento
```bash
mvn spring-boot:run
```

O servidor inicia em `http://localhost:8080`.

### Build
```bash
mvn clean package
java -jar target/discipline-backend-1.0.0.jar
```

## Banco de Dados

O projeto usa **SQLite**. O arquivo `discipline.db` é criado automaticamente na raiz do projeto ao iniciar a aplicação. As tabelas são geradas automaticamente pelo Hibernate (`ddl-auto=update`).

## CORS

CORS está configurado para aceitar requisições de qualquer origem, permitindo integração com o frontend React rodando em qualquer porta.
