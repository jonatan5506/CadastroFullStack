const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Itel = document.querySelector(".tel");
const Isenha = document.querySelector(".senha");

function cadastrar() {

    /*vai fazer o post para a Api. Serve como modelo*/
    fetch("http://localhost:8080/api",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({/**aqui entra o Json */
                nome: Inome.value,
                email: Iemail.value,
                tel: Itel.value,
                senha: Isenha.value
            })
        })
        .then(function (res) { console.log(res) })
        .catch(function (res) { console.log(res) })
};

/*Função para limpar os campos após o cadastro dos dados */
function limpar(){
    Inome.value = "";
    Isenha.value = "";
    Iemail.value = "";
    Itel.value = "";
}

/*Método para capturar as informações inseridas*/
formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    cadastrar();
    limpar();
});