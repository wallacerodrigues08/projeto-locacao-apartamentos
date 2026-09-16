console.log("JavaScript carregado!");


function carregarApartamentos() {

    const container = document.getElementById("apartamentos");

    container.innerHTML = "";

    fetch("http://localhost:8080/apartamentos/disponiveis")

        .then(response => {
            return response.json();
        })

        .then(apartamentos => {

            apartamentos.forEach(apartamento => {

                const card = document.createElement("div");

                card.innerHTML = `
                    <h3>${apartamento.titulo}</h3>

                    <p>${apartamento.descricao}</p>

                    <p>
                        Valor do aluguel:
                        R$ ${apartamento.valorAluguel.toFixed(2)}
                    </p>

                    <p>
                        Edifício:
                        ${apartamento.edificio.nome}
                    </p>

                    <p>
                        Endereço:
                        ${apartamento.edificio.endereco}
                    </p>

                    <p>
                        Disponível: Sim
                    </p>

                    <button onclick="alugarApartamento(${apartamento.id})">
                        Alugar
                    </button>
                `;

                container.appendChild(card);

            });

        })

        .catch(error => {

            console.error("Erro ao buscar apartamentos:", error);

        });

}

function carregarApartamentosAlugados() {

    const container = document.getElementById("apartamentos-alugados");

    container.innerHTML = "";

    fetch("http://localhost:8080/apartamentos")

        .then(response => response.json())

        .then(apartamentos => {

            const alugados = apartamentos.filter(
                apartamento => apartamento.disponivel === false
            );

            alugados.forEach(apartamento => {

                const card = document.createElement("div");

                card.innerHTML = `
                    <h3>${apartamento.titulo}</h3>

                    <p>${apartamento.descricao}</p>

                    <p>
                        Valor do aluguel:
                        R$ ${apartamento.valorAluguel.toFixed(2)}
                    </p>

                    <p>
                        Edifício:
                        ${apartamento.edificio.nome}
                    </p>

                    <p>
                        Endereço:
                        ${apartamento.edificio.endereco}
                    </p>

                    <p>
                        Disponível: Não
                    </p>

                    <button onclick="verLocatario(${apartamento.id})">
                        Ver locatário
                    </button>

                    <p id="locatario-${apartamento.id}"></p>
                `;

                container.appendChild(card);

            });

        })

        .catch(error => {

            console.error(
                "Erro ao buscar apartamentos alugados:",
                error
            );

        });

}

function verLocatario(id) {

    fetch(`http://localhost:8080/apartamentos/${id}/locatario`)

        .then(response => {

            if (!response.ok) {
                throw new Error("Erro ao buscar locatário");
            }

            return response.json();

        })

        .then(locatario => {

            const elemento = document.getElementById(`locatario-${id}`);

            elemento.innerHTML = `
                <strong>Locatário:</strong><br>
                Nome: ${locatario.nome}<br>
                E-mail: ${locatario.email}<br>
                Telefone: ${locatario.telefone}
            `;

        })

        .catch(error => {

            console.error(error);

        });

}

carregarApartamentos();
carregarApartamentosAlugados();

function mostrarTela(tela) {

    const menu = document.getElementById("menu-principal");
    const alugar = document.getElementById("tela-alugar");
    const anunciar = document.getElementById("tela-anunciar");

    menu.style.display = "none";
    alugar.style.display = "none";
    anunciar.style.display = "none";

    if (tela === "alugar") {
        alugar.style.display = "block";
    }

    if (tela === "anunciar") {
        anunciar.style.display = "block";
    }

}


document.getElementById("form-anunciar").addEventListener("submit", function(event) {

    event.preventDefault();

    const mensagem = document.getElementById("mensagem-anunciar");

    const locador = {
        nome: document.getElementById("nome-locador").value,
        email: document.getElementById("email-locador").value,
        telefone: document.getElementById("telefone-locador").value
    };

    fetch("http://localhost:8080/locadores", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(locador)
    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Erro ao cadastrar locador");
        }

        return response.json();

    })
    .then(locadorCadastrado => {

        console.log("Locador cadastrado:", locadorCadastrado);

        const edificio = {
            nome: document.getElementById("nome-edificio").value,
            endereco: document.getElementById("endereco-edificio").value
        };

        return fetch("http://localhost:8080/edificios", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(edificio)
        });

    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Erro ao cadastrar edifício");
        }

        return response.json();

    })
    .then(edificioCadastrado => {

        console.log("Edifício cadastrado:", edificioCadastrado);

        const apartamento = {
            edificio: {
                id: edificioCadastrado.id
            },
            titulo: document.getElementById("titulo-apartamento").value,
            descricao: document.getElementById("descricao-apartamento").value,
            valorAluguel: Number(document.getElementById("valor-aluguel").value),
            disponivel: true
        };

        return fetch("http://localhost:8080/apartamentos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(apartamento)
        });

    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Erro ao cadastrar apartamento");
        }

        return response.json();

    })
    .then(apartamentoCadastrado => {

        console.log("Apartamento cadastrado:", apartamentoCadastrado);

        mensagem.textContent = "Imóvel publicado com sucesso!";

    })
    .catch(error => {

        console.error(error);

        mensagem.textContent = "Erro ao cadastrar imóvel.";

    });

});


let apartamentoSelecionado = null;


function alugarApartamento(id) {

    apartamentoSelecionado = id;

    document.getElementById("formulario-aluguel").style.display = "block";

    document.getElementById("formulario-aluguel").scrollIntoView({
        behavior: "smooth"
    });

}


function cancelarAluguel() {

    apartamentoSelecionado = null;

    document.getElementById("formulario-aluguel").style.display = "none";

    document.getElementById("form-aluguel").reset();

}


document.getElementById("form-aluguel").addEventListener("submit", function(event) {

    event.preventDefault();

    const locatario = {

        nome: document.getElementById("nome-locatario").value,

        email: document.getElementById("email-locatario").value,

        telefone: document.getElementById("telefone-locatario").value

    };

    fetch(`http://localhost:8080/apartamentos/${apartamentoSelecionado}/alugar`, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(locatario)

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Erro ao realizar aluguel");
        }

        return response.json();

    })

    .then(apartamentoAlugado => {

        console.log("Apartamento alugado:", apartamentoAlugado);

        document.getElementById("mensagem-aluguel").textContent =
            "Apartamento alugado com sucesso!";

        document.getElementById("form-aluguel").reset();

        apartamentoSelecionado = null;

     setTimeout(() => {

    document.getElementById("formulario-aluguel").style.display = "none";

    carregarApartamentos();
    carregarApartamentosAlugados();

}, 1500);

    })

    .catch(error => {

        console.error(error);

        document.getElementById("mensagem-aluguel").textContent =
            "Erro ao realizar aluguel.";

    });

});