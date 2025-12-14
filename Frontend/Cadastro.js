// O endereço do seu endpoint de registro (certifique-se de que o Spring está rodando na porta 8080)
const API_URL = 'http://localhost:8080/api/auth/registrar'; 
const form = document.getElementById('registroForm');
const messageDiv = document.getElementById('message');

form.addEventListener('submit', async function(event) {
    event.preventDefault(); // Impede o recarregamento da página

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const perfil = document.getElementById('perfil').value;

    const dadosRegistro = {
        username: username,
        password: password,
        perfil: perfil
    };

    messageDiv.textContent = 'Enviando...';
    messageDiv.style.color = '#007bff';

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dadosRegistro)
        });

        const data = await response.json(); // Tenta ler a resposta JSON

        if (response.ok) { // Status 200-299 (Sucesso)
            messageDiv.textContent = `Sucesso! Usuário ${data.username} (${data.perfil}) cadastrado com ID: ${data.id}`;
            messageDiv.style.color = 'green';
            form.reset(); // Limpa o formulário

        } else { // Status de erro (400, 500, etc.)
            let errorMessage = 'Erro desconhecido ao cadastrar.';
            
            // Tentativa de obter a mensagem de erro do backend (ex: "Nome de usuário já está em uso")
            if (data && data.error) {
                errorMessage = data.error; 
            } else if (data && typeof data === 'string') {
                 errorMessage = data;
            } else if (data && data.message) {
                 errorMessage = data.message;
            }
            
            messageDiv.textContent = `Falha: ${errorMessage}`;
            messageDiv.style.color = 'red';
        }

    } catch (error) {
        messageDiv.textContent = 'Erro de Conexão: Seu servidor Spring não está ligado (ECONNREFUSED).';
        messageDiv.style.color = 'red';
        console.error('Erro de rede:', error);
    }
});