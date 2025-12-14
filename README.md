# 🍻 Bar Experience System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

> **Sistema Fullstack para gestão inteligente de bares e restaurantes.** > Integração total entre Salão, Cozinha e Cliente em tempo real.

---

## 💡 Sobre o Projeto

O **Bar Experience System** foi desenvolvido para modernizar o fluxo de atendimento em estabelecimentos gastronômicos. A solução elimina a dependência de comandas de papel e centraliza a operação em um sistema digital robusto, garantindo agilidade e inclusão.

O sistema conecta três pontas vitais do negócio:
1.  **O Garçom:** Lança pedidos e gerencia mesas via tablet/celular.
2.  **A Cozinha (KDS):** Recebe e atualiza o status dos pedidos instantaneamente.
3.  **O Cliente:** Acompanha a conta em tempo real com recursos de acessibilidade.

---

## ✨ Funcionalidades Principais

### 👨‍🍳 KDS - Kitchen Display System (Monitor de Cozinha)
* **Tempo Real:** Os pedidos aparecem na cozinha no exato momento em que o garçom confirma no salão.
* **Fluxo de Status:** O item nasce como *Pendente* (⏳) e o cozinheiro marca como *Pronto* (✅), notificando automaticamente o sistema.
* **Interface Industrial:** Design de alto contraste para leitura rápida em ambientes movimentados.

### 📱 App do Cliente (Comanda Digital) & Acessibilidade
* **Transparência:** O cliente visualiza o consumo, taxas de serviço e couvert em tempo real.
* **Inclusão Social:** Foco total em acessibilidade com duas ferramentas nativas:
    * 🔊 **Leitor de Voz (TTS):** O sistema "lê" o resumo da conta para pessoas com deficiência visual.
    * 👁️ **Alto Contraste:** Modo de visualização adaptado para pessoas com baixa visão ou daltonismo.

### 🤵 Painel do Garçom
* **Mapa de Mesas:** Visualização intuitiva do status das mesas: *Livre* (Verde), *Ocupada* (Azul) e *Fechada/Pagamento* (Vermelho).
* **Gestão Ágil:** Adição de itens, cancelamento com justificativa e controle de ocupação.

### 📊 Painel Administrativo (Dashboard)
* **Business Intelligence:** Gráficos de faturamento diário, ticket médio e ranking de itens mais vendidos.
* **Gestão:** Cadastro completo de Cardápio e Mesas.
* **Configurações:** Definição dinâmica de taxas (Gorjeta Comida/Bebida) e valor do Couvert.

---

## 📸 Galeria do Projeto

### 📊 Painel Administrativo
> Visão estratégica do negócio com gráficos e métricas financeiras.
![Dashboard Admin]
> <img width="1366" height="691" alt="dashboard-admin" src="https://github.com/user-attachments/assets/e023bbcf-5a79-43df-8d68-c9433f07075e" />

### 👨‍🍳 Monitor KDS (Cozinha)
> Controle de pedidos pendentes e prontos com atualização instantânea.
![KDS Cozinha]
> <img width="1366" height="678" alt="kds-cozinha" src="https://github.com/user-attachments/assets/c1a746d0-615b-4597-a564-1d6a0de1167f" />

### 📱 Comanda Digital (Cliente)
> Interface limpa para o cliente, com destaque para os botões de Acessibilidade.
![App Cliente]
<img width="1366" height="678" alt="app-cliente" src="https://github.com/user-attachments/assets/827c5c3a-af22-4155-98f6-f39e81fed6a2" />

### 🤵 Gestão de Mesas (Garçom)
> Controle visual do status de ocupação do salão.
![Painel Garçom]
<img width="1366" height="670" alt="painel-garcom" src="https://github.com/user-attachments/assets/8365ee20-b3f2-45c8-85d8-293c9fc8a129" />

---

## 🛠️ Tecnologias Utilizadas

### Backend (API REST)
* **Java 17**
* **Spring Boot 3** (Spring Web, Spring Data JPA)
* **H2 Database** (Banco em memória para prototipagem rápida)
* **Maven** (Gerenciamento de dependências)

### Frontend (Client-Side)
* **HTML5 & CSS3** (Design Responsivo, Glassmorphism e Neon UI)
* **JavaScript (Vanilla)** (Fetch API para consumo de dados e manipulação DOM)
* **Web Speech API** (Para o recurso de leitura de tela)

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
* Java JDK 17+ instalado.
* Git instalado.

### Passo 1: Backend
1.  Clone este repositório:
    ```bash
    git clone [https://github.com/AlissonF-1/bar-system-fullstack.git](https://github.com/AlissonF-1/bar-system-fullstack.git)
    ```
2.  Importe a pasta `api_bar` na sua IDE (Eclipse, IntelliJ ou VS Code).
3.  Execute a classe `ApiBarApplication.java`.
    * *Nota: O banco H2 será criado automaticamente em memória.*

### Passo 2: Frontend
1.  Navegue até a pasta `Frontend`.
2.  Abra os arquivos no navegador (Recomendado usar Live Server):
    * **Garçom:** `Mesas.html`
    * **Cozinha:** `Cozinha.html`
    * **Cliente:** `Cliente.html?id=1`
    * **Admin:** `Admin.html`

---

## 👨‍💻 Autor

Desenvolvido por **Alisson Flaynn**

Entre em contato!
* [alissonlucca2@gmail.com]

---
*Projeto desenvolvido para fins de estudo e portfólio de Desenvolvimento Fullstack.*
