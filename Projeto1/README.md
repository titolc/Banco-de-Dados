## 📋 Projeto 1 – Sistema de Cadastro (Clientes e Pedidos)

Este sistema permite o cadastro, listagem, atualização e exclusão de **clientes** e **pedidos** usando **Java + Swing + JDBC + MySQL** com **HikariCP** para gerenciamento de conexões.

---

### ✅ Requisitos

* Java JDK 8 ou superior
* MySQL Server
* Apache Maven
* IDE (recomendado: IntelliJ IDEA ou Eclipse)

---

### ⚙️ Configuração do Banco de Dados

1. Crie o banco de dados no MySQL:

```sql
CREATE DATABASE sistema_cadastro;
```

2. Execute os scripts SQL fornecidos (por exemplo, `schema.sql`) para criar as tabelas `clientes` e `pedidos`.

3. Configure as credenciais de acesso ao banco no arquivo `ConnectionFactory.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sistema_cadastro";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

---

### ▶️ Como Executar

1. Clone o repositório ou baixe o projeto.

2. No terminal, vá até a pasta do projeto e execute:

```bash
mvn clean install
```

3. Execute a classe principal para iniciar o sistema (por exemplo):

```bash
java -cp target/seu-projeto.jar br.com.sistema.Main
```

Ou simplesmente execute via IDE a classe `Main.java` (ou `ClienteCadastroGUI.java`, `PedidoCadastroGUI.java`).

---

### 🧪 Testar conexão com o banco

Execute a classe `ConnectionPoolTest.java` para verificar se a conexão está funcionando:

```java
Conexão estabelecida com sucesso!
```

---

### 📁 Estrutura do Projeto

```
src/
 └─ br/com/sistema/
      ├─ dao/
      ├─ model/
      ├─ gui/
      ├─ db/
      └─ ConnectionFactory.java
```

---
