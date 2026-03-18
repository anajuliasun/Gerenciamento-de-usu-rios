# 👥 Sistema de Gerenciamento de Usuários

Aplicação desktop desenvolvida em **Java** com interface gráfica **Swing** e persistência em banco de dados **MySQL**, utilizando **Maven** para gerenciamento do projeto.

---

## 📋 Sobre o Projeto

O sistema permite realizar operações completas de CRUD (**Criar, Ler, Atualizar e Deletar**) de usuários através de uma interface gráfica simples e funcional.

A aplicação segue uma estrutura em camadas, separando:

* Interface (UI)
* Lógica da aplicação
* Acesso a dados (Repository)

---

## 🗂️ Estrutura do Projeto

```
gerenciamento-usuarios/
├── src/
│   └── main/
│       └── java/
│           ├── br/gerenciamento/
│           │   ├── Gerenciamento.java
│           │   └── model/
│           │       └── Usuario.java
│           ├── repository/
│           │   └── MySQLRepository.java
│           └── ui/
│               ├── MenuPrincipal.java
│               ├── FormCadastro.java
│               ├── FormListagem.java
│               └── AtualizarUsuario.java
├── pom.xml
└── README.md
```

---

## 🚀 Funcionalidades

* Cadastro de usuários
* Listagem de usuários
* Atualização de dados
* Exclusão de usuários
* Interface gráfica com Swing
* Persistência em banco MySQL

---

## 🛠️ Tecnologias Utilizadas

* Java
* Java Swing
* Maven
* MySQL
* JDBC

---

## ⚙️ Pré-requisitos

* Java JDK 11 ou superior
* Maven 3.6 ou superior
* MySQL 8 ou superior (ou Docker)

---

## 🗄️ Configuração do Banco de Dados

### Opção 1: Manual

```sql
CREATE DATABASE gerenciamento;
USE gerenciamento;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    senha VARCHAR(100) NOT NULL
);
```

### Opção 2: Docker (recomendado)

```bash
docker run -d \
  --name mysql-app \
  -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=sua_senha \
  -e MYSQL_DATABASE=gerenciamento \
  -v mysql_data:/var/lib/mysql \
  mysql:8
```

---

## 🔧 Configuração da Conexão

No arquivo `MySQLRepository.java`, configure:

```java
String url = "jdbc:mysql://localhost:3307/gerenciamento?allowPublicKeyRetrieval=true&useSSL=false";
String user = "root";
String password = "sua_senha";
```

---

## ▶️ Como Executar

### Clonar o repositório

```bash
git clone https://github.com/davidanielMnds/gerenciamento-usuarios.git
cd gerenciamento-usuarios
```

### Executar com Maven

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="br.gerenciamento.Gerenciamento"
```

Ou execute pela sua IDE (IntelliJ ou NetBeans).

---
