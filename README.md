# itau-challenge

> API REST em Java 19 / Spring Boot para receber “transações” em memória e calcular estatísticas em tempo real.

---

- Visão Geral

Este projeto implementa uma API REST que:

1. **Recebe** transações (valor + data/hora no padrão ISO 8601).  
2. **Armazena** tudo em memória (sem banco de dados externo).  
3. **Limpa** o repositório de transações sob demanda.  
4. **Calcula** estatísticas (contagem, soma, média, mínimo e máximo) das transações dos últimos 60 segundos.

---

## Tecnologias

- Java 19  
- Spring Boot  
- Maven (wrapper incluído)  
- Git / GitHub  