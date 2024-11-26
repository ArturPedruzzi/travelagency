## API Endpoints

### 1. Cadastro de Destinos de Viagem:

- **Método:** `POST`  
- **URL:** `/api/destinos`  
- **Body (raw):**

  ```json
  {
    "nome": "Nome do destino",
    "localizacao": "Localização do destino",
    "descricao": "Descrição do destino"
  }
  ```

### 2. Listagem de Destinos de Viagem:

- **Método:** `GET`  
- **URL:** `/api/destinos`  
- **Resposta (JSON):**

  ```json
  [
    {
        "id": 1, 
        "nome": "Fernando de Noronha",
        "localizacao": "Pernambuco",
        "descricao": "Um paraíso para os amantes de surfe e natureza.",
        "avaliacaoMedia": 9.5,
        "quantidadeAvaliacoes": 8
    },
    {
        "id": 2,
        "nome": "Maresias",
        "localizacao": "São Paulo",
        "descricao": "Uma das praias mais famosas para surfe no Brasil.",
        "avaliacaoMedia": 8.8,
        "quantidadeAvaliacoes": 15
    }
  ]
  ```

### 3. Pesquisa de Destinos:

- **Método:** `GET`  
- **URL para buscar destinos pelo nome:** `/api/destinos/pesquisar?nome=Maresias`
- **URL para buscar destinos pelo nome:** `/api/destinos/pesquisar?localizacao=São Paulo`
- **URL para buscar destinos pelo nome e localização:** `/api/destinos/pesquisar?nome=Maresias&localizacao=São Paulo`
- **Resposta (JSON):**

  ```json
  [
    {
        "id": 2,
        "nome": "Maresias",
        "localizacao": "São Paulo",
        "descricao": "Uma das praias mais famosas para surfe no Brasil.",
        "avaliacaoMedia": 8.8,
        "quantidadeAvaliacoes": 15
    }
  ]
  ```

### 4. Visualização de Informações Detalhadas:

- **Método:** `GET`  
- **URL:** `/api/destinos/{id}`
- **Resposta (JSON):**

  ```json
  {
    "id": 1,
    "nome": "Fernando de Noronha",
    "localizacao": "Pernambuco",
    "descricao": "Um paraíso para os amantes de surfe e natureza.",
    "avaliacaoMedia": 9.5,
    "quantidadeAvaliacoes": 8
  }
  ```

### 5. Avaliação de Destino de Viagens:

- **Método:** `GET`  
- **URL:** `/api/destinos/{id}/avaliar?nota=8`
- **Nota: O valor de nota deve ser entre 1 e 10.**
- **Resposta (JSON):**

  ```json
  {
    "id": 2,
    "nome": "Maresias",
    "localizacao": "São Paulo",
    "descricao": "Uma das praias mais famosas para surfe no Brasil.",
    "avaliacaoMedia": 8.9,
    "quantidadeAvaliacoes": 16
  }
  ```

### 6. Exclusão de Destinos de Viagem:

- **Método:** `DELETE`  
- **URL:** `/api/destinos/{id}`
