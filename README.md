📱 Aplicação Mobile – Sistema de Chamados

Aplicação mobile desenvolvida para consumir uma API de gerenciamento de chamados, permitindo que usuários acompanhem, criem e interajam com seus chamados de forma simples, segura e eficiente.

Este aplicativo atua como cliente complementar do sistema principal, não possuindo acesso direto ao banco de dados.

🎯 Objetivo do Projeto

Fornecer uma interface mobile para o sistema de chamados, possibilitando:

Acesso rápido aos chamados do usuário

Criação de novos chamados

Comunicação com o atendente via chat/comentários

Autenticação segura

Consumo de API REST seguindo boas práticas

⚙️ Funcionalidades

✅ Login com autenticação JWT

📄 Listagem de chamados

🔍 Visualização de detalhes do chamado

➕ Criação de novos chamados

💬 Chat/comentários por chamado

🔐 Comunicação segura com a API

🔒 Criptografia de dados sensíveis

🧱 Arquitetura do App

Aplicação desenvolvida em Android (Java)

Comunicação via API REST

Consumo de endpoints utilizando Retrofit

Autenticação baseada em JWT

App desacoplado do backend (sem acesso direto ao banco)

🔌 Integração com a API

O aplicativo consome uma API externa responsável por toda a regra de negócio, autenticação e persistência de dados.

📌 Repositório do sistema principal (API):
👉 https://github.com/DoubleG2s/Gerenciamento-de-Chamados

Durante o desenvolvimento, a API era executada localmente e exposta externamente utilizando ngrok, permitindo que o aplicativo mobile se comunicasse com o backend em tempo real.

🌐 Endpoints Consumidos

Os principais endpoints utilizados pelo aplicativo estão definidos em:

services/ApiService.java


Exemplos:

POST /api/mobile/auth/login

GET /api/mobile/ticket

GET /api/mobile/ticketdetalhe/{id}

POST /api/mobile/ticket

POST /api/tickets/{id}/comentarios

🚀 Como Rodar o Projeto
Pré-requisitos

Android Studio

API de chamados em execução

Conta no ngrok (opcional)

1️⃣ Clonar o repositório
git clone https://github.com/JulioDev404/AplicacaoMobileSistemaChamdos-.git

2️⃣ Executar a API

Clone e execute o backend (repositório separado)

Certifique-se de que os endpoints mobile estejam ativos

3️⃣ Expor a API com ngrok
ngrok http 5000

4️⃣ Configurar a Base URL

Atualize a base URL no projeto mobile com a URL gerada pelo ngrok.

Exemplo:

https://xxxx-xx-xx-xx.ngrok-free.app/

5️⃣ Executar o App

Rodar no emulador ou dispositivo físico

🔐 Segurança

Autenticação JWT

Tokens enviados via header HTTP

Criptografia aplicada a dados sensíveis

Comunicação via API REST

🧪 Status do Projeto

✔ Funcional
✔ Integrado com API real
✔ Autenticação e chat implementados
✔ Projeto acadêmico com aplicação prática

🧠 Aprendizados

Consumo de APIs REST em aplicações mobile

Autenticação JWT

Integração entre sistemas independentes

Uso do Retrofit

Comunicação segura entre mobile e backend

👨‍💻 Autor

Julio Cesar de Alencar Pedrosa
📧 Email: julio.dev404@outlook.com

💻 GitHub: https://github.com/JulioDev404
