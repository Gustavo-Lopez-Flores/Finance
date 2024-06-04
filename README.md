### Finance 💰

Finance é uma aplicação Android para o controle de finanças pessoais, permitindo aos usuários gerenciar registros de usuários, contas bancárias e transações financeiras com o uso do banco de dados Room.

## Funcionalidades

1. **Registro de Usuários** 
2. **Login de Usuários cadastrados** 
3. **Operações de Atualização de Senha, Exclusão de conta e Adição de foto de perfil para Usuários cadastrados** 
4. **Registro de Contas Bancárias após o login do usuário** 
5. **Registro de Transações Financeiras após o login do usuário** 
6. **Registro de Limites de Gasto após o login do usuário** 
7. **Registro de Categorias de Movimentação após o login do usuário** 
8. **Operações CRUD nas tabelas (Contas Bancárias, Transação Financeira, Limite de Gasto, Categoria), armazenadas no Room Database** 
9. **Inserção de Transação Financeira com seleção de Categoria a partir de uma Conta Bancária existente** 
10. **Visualização de Contas Bancárias, Transações Financeiras, Limites de Gasto e Categorias de Movimentação em ListViews e abertura da instância selecionada** 
11. **Emissão de notificações caso o usuário ultrapasse o limite de gastos definido na categoria** 
12. **Transição entre as telas através de um bottom navigation** 

## Observações Importantes ⚠️

1. **Validação de email e senha durante o login, com consulta ao banco de dados para verificação** 
2. **Verificação de campos obrigatórios e validação de formatos durante o registro e atualização de usuários** 
3. **Controle de integridade referencial ao excluir ou atualizar Contas Bancárias (verificar se existem Transações associadas)** 
4. **Controle de integridade referencial ao excluir ou atualizar Limites de Gastos (verificar se existem Categorias associadas)** 
5. **Listagem de Contas Bancárias, Transações, Categorias e Limites de Gasto em ListViews para fácil visualização** 
6. **Integração com o banco de dados Room para persistência dos dados** 

## Requisitos Funcionais 📋

1. **Cadastro de Usuário**: O aplicativo deve permitir que novos usuários se cadastrem fornecendo nome, email, senha e foto de perfil.
2. **Login de Usuário**: Usuários cadastrados devem poder fazer login utilizando email e senha.
3. **Gestão de Contas Bancárias**: Usuários devem poder adicionar, visualizar, editar e excluir contas bancárias.
4. **Gestão de Transações Financeiras**: Usuários devem poder registrar, visualizar, editar e excluir transações financeiras.
5. **Gestão de Limites de Gasto**: Usuários devem poder definir, visualizar, editar e excluir limites de gasto para diferentes categorias.
6. **Gestão de Categorias**: Usuários devem poder adicionar, visualizar, editar e excluir categorias de movimentação financeira.
7. **Notificações**: O aplicativo deve notificar os usuários quando os limites de gasto forem ultrapassados.
8. **Validação e Segurança**: O aplicativo deve validar entradas de usuário e garantir a segurança das informações pessoais e financeiras.

## Estrutura do Banco de Dados 🗄️

### Tabelas

1. **Usuário**
   - `id`: Int (Primary Key)
   - `nome`: String
   - `email`: String (Unique)
   - `senha`: String
   - `fotoPerfil`: String (URL ou Path)

2. **ContaBancaria**
   - `id`: Int (Primary Key)
   - `usuarioId`: Int (Foreign Key)
   - `nomeBanco`: String
   - `saldo`: Double

3. **TransacaoFinanceira**
   - `id`: Int (Primary Key)
   - `contaId`: Int (Foreign Key)
   - `categoriaId`: Int (Foreign Key)
   - `valor`: Double
   - `data`: Date
   - `descricao`: String

4. **LimiteGasto**
   - `id`: Int (Primary Key)
   - `usuarioId`: Int (Foreign Key)
   - `categoriaId`: Int (Foreign Key)
   - `valorLimite`: Double

5. **Categoria**
   - `id`: Int (Primary Key)
   - `usuarioId`: Int (Foreign Key)
   - `nomeCategoria`: String

## Como Executar o Projeto 🚀

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/finance-app.git
   ```
2. **Abra o projeto no Android Studio**.
3. **Configure o ambiente**:
   - Certifique-se de que você tenha o SDK do Android configurado.
   - Verifique as dependências no `build.gradle`.
4. **Construa e execute o aplicativo em um dispositivo ou emulador Android**.

## Contribuições 🤝

Contribuições são bem-vindas! Por favor, abra uma issue ou envie um pull request.

---

**Nota:** Este projeto foi desenvolvido como parte de um trabalho acadêmico na disciplina de Programação para Dispositivos Móveis.
