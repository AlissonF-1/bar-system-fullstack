// O endereço do seu endpoint público de listagem de mesas
// (Note: usamos a porta 8081, que estava no seu application.properties)
const API_URL = 'http://localhost:8081/api'; // CORREÇÃO: Usar a porta 8081
const mesasContainer = document.getElementById('container-mesas');
const modal = document.getElementById('mesaModal');
let cardapioCache = []; // Cache para itens de cardápio

// Função para buscar e renderizar as mesas
async function carregarMesas() {
    try {
        // Chamada para a rota pública de listar mesas
        const response = await fetch(`${API_URL}/cliente/mesas`);
        
        if (!response.ok) {
             throw new Error(`Erro ${response.status}: Falha ao buscar mesas. Servidor ativo?`); 
        }

        const mesas = await response.json(); 
        
        // Busca o cardápio (publicamente) para preencher o modal
        const resCardapio = await fetch(`${API_URL}/cardapio`);
        if(resCardapio.ok) cardapioCache = await resCardapio.json();
        
        // --- Renderização na Tela Principal ---
        const container = document.getElementById('container-mesas');
        container.innerHTML = '';
        
        if(mesas.length === 0) { container.innerHTML = 'Nenhuma mesa cadastrada.'; return; }

        mesas.forEach(mesa => {
            const card = document.createElement('div');
            card.className = `mesa-card status-${mesa.status}`;
            card.innerHTML = `<span class="mesa-id">ID: ${mesa.id}</span><span class="mesa-numero">Mesa ${mesa.numero}</span><span class="mesa-status">${mesa.status}</span>`;
            card.onclick = () => abrirModal(mesa);
            container.appendChild(card);
        });

    } catch (error) {
        // Erro de conexão/segurança
        document.getElementById('container-mesas').innerHTML = `<p style="color:red">Erro Fatal: Não foi possível conectar ao Backend Spring. Verifique se o servidor está rodando.</p>`;
        console.error('Erro de Rede:', error);
    }
}

// --- LÓGICA DO MODAL (FUNÇÕES ADICIONADAS PARA COMPLETUDE) ---

function gerarOpcoesProdutos() {
    if (cardapioCache.length === 0) return '<option disabled>Nenhum item</option>';
    // Cria as opções do SELECT para o modal
    return cardapioCache.map(item => `<option value="${item.id}">${item.nome} (R$ ${item.preco})</option>`).join('');
}

function fecharModal() { document.getElementById('mesaModal').style.display = 'none'; carregarMesas(); }

// Implementação simples das interações:
async function abrirModal(mesa) {
    const titulo = document.getElementById('modal-titulo');
    const conteudo = document.getElementById('conteudo-dinamico');

    titulo.innerText = `Mesa ${mesa.numero} (${mesa.status})`;
    modal.style.display = 'flex';
    conteudo.innerHTML = 'Carregando...';

    // Rota pública para consultar a conta (GET /api/cliente/mesas/{id})
    const resConta = await fetch(`${API_URL}/cliente/mesas/${mesa.id}`);
    const dadosConta = await resConta.json();
    
    // **Ajuste:** Para fins de demonstração, o conteúdo do modal é renderizado aqui.
    // O código completo do modal deve ser copiado do seu código que você enviou antes, ajustando apenas a API_URL e as chamadas.
    
    // ... (Aqui entraria a lógica de abrirModal que você já tem no seu Mesas.html) ...

    // Chamadas de API (aqui apenas simulam o envio, a verificação está no backend)
    // Exemplo da chamada de abertura
    if (mesa.status === 'LIVRE') {
        conteudo.innerHTML = `
            <h3 style="color:#fff">Novo Cliente</h3>
            <input type="number" id="qtdPessoas" value="2" min="1">
            <button class="btn-abrir" onclick="abrirMesa(${mesa.id})">INICIAR ATENDIMENTO</button>
        `;
    } else {
        // Mostra o status da conta
        conteudo.innerHTML = `<h3 style="color:red">Conta: R$ ${dadosConta.detalhes.totalGeral}</h3>`;
    }
}

// Stubs para as funções de interação
async function abrirMesa(id) { /* Implementação */ fecharModal(); }
// ... (O restante das suas funções de Garçom) ...

// Inicia o carregamento e o loop de atualização
document.addEventListener('DOMContentLoaded', carregarMesas);
setInterval(carregarMesas, 3000);