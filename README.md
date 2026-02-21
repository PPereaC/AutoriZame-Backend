# AutoriZame Backend

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/technologies/downloads/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

> Backend REST API para la aplicación de AutoriZame basado en tecnología Blockchain (NFTs) y almacenamiento descentralizado (IPFS).

## 📋 Índice

- [Descripción](#-descripción)
- [Arquitectura](#-arquitectura)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Configuración](#-configuración)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [API Endpoints](#-api-endpoints)
- [Microservicios Blockchain](#-microservicios-blockchain)
- [Demostración](#-demostración)

---

## 📖 Descripción

**AutoriZame Backend** es una aplicación Spring Boot que proporciona una API REST para la gestión de la aplicación AutoriZame. El sistema integra tecnologías blockchain mediante microservicios independientes para:

- **Almacenamiento en IPFS**: Subida y recuperación de metadatos de pedidos
- **Gestión de NFTs**: Mintado y transferencia de tokens de autorización en blockchain

El proyecto implementa una capa de persistencia completa con **Spring Data JPA** y **MySQL**, permitiendo el almacenamiento y gestión de entidades de manera persistente.

---

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         AUTORIZAME BACKEND                                  │
│                      (Spring Boot + Java 21)                                │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  ┌─────────────────────┐     ┌─────────────────────┐     ┌───────────────┐  │
│  │  ClienteController  │     │ BlockchainController│     │  Servicios    │  │
│  │  (REST API Clientes)│     │ (REST API Blockchain│     │  (Lógica)     │  │
│  └──────────┬──────────┘     └──────────┬──────────┘     └───────┬───────┘  │
│             │                           │                        │          │
│             ▼                           ▼                        ▼          │
│  ┌─────────────────────┐     ┌─────────────────────┐     ┌───────────────┐  │
│  │  ClienteService     │     │ PinataService       │     │ Repositorios  │  │
│  │  (JPA Persistence)  │     │ SmartContractService│     │ (JPA)         │  │
│  └──────────┬──────────┘     └──────────┬──────────┘     └───────┬───────┘  │
│             │                           │                        │          │
│             ▼                           ▼                        ▼          │
│  ┌─────────────────────┐     ┌─────────────────────┐     ┌───────────────┐  │
│  │  MySQL Database     │     │  RestClient         │     │  Entidades    │  │
│  │  (autorizame)       │     │  (HTTP Clients)     │     │  JPA          │  │
│  └─────────────────────┘     └──────────┬──────────┘     └───────────────┘  │
│                                         │                                  │
└─────────────────────────────────────────┼──────────────────────────────────┘
                                        │
              ┌─────────────────────────┼─────────────────────────┐
              │                         │                         │
              ▼                         ▼                         ▼
┌─────────────────────┐    ┌─────────────────────┐    ┌─────────────────────┐
│  MS Wrapper IPFS    │    │  MS Wrapper Smart   │    │     MySQL (XAMPP)   │
│  (Puerto 3001)      │    │  Contract (Puerto   │    │                     │
│  - Pinata/IPFS      │    │  3002)              │    │  - Base de datos    │
│  - Subida metadata  │    │  - Mintar NFTs      │    │  - Persistencia     │
│  - Recuperar CID    │    │  - Transferir NFTs  │    │  - Entidad Cliente  │
└─────────────────────┘    └─────────────────────┘    └─────────────────────┘
```

---

## 🛠️ Tecnologías

| Capa | Tecnología | Versión |
|------|-----------|---------|
| Framework | Spring Boot | 4.0.0 |
| Lenguaje | Java | 21 |
| Persistencia | Spring Data JPA | - |
| Base de Datos | MySQL | 8.0+ |
| Documentación API | SpringDoc OpenAPI | 2.8.5 |
| HTTP Client | RestClient (Spring 6) | - |
| Build Tool | Maven | - |

---

## 📋 Requisitos Previos

### Software Necesario

- **Java 21** o superior ([Descargar](https://www.oracle.com/java/technologies/downloads/))
- **MySQL 8.0** o superior (o XAMPP con MySQL)
- **Maven** (incluido como wrapper `./mvnw`)
- **Git** (para clonar repositorios)

### Microservicios Blockchain (Requeridos)

Para el funcionamiento completo del sistema, deben estar ejecutándose los siguientes microservicios:

| Microservicio | Puerto | Repositorio | Descripción |
|--------------|--------|-------------|-------------|
| **MS Wrapper IPFS (Pinata)** | 3001 | [GitHub](https://github.com/PPereaC/ms_wrapper_ipfs) | Wrapper para subir y recuperar archivos de IPFS mediante Pinata |
| **MS Wrapper Smart Contract** | 3002 | [GitHub](https://github.com/PPereaC/ms_wrapper_sc) | Wrapper para interactuar con contratos inteligentes (mintar/transferir NFTs) |

---

## ⚙️ Configuración

### 1. Base de Datos MySQL

Configura tu base de datos en `src/main/resources/application.properties`:

```properties
# Configuración MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/autorizame?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# URLs de Microservicios Blockchain
microservicios.pinata.url=http://localhost:3001
microservicios.smartcontract.url=http://localhost:3002
```

### 2. Iniciar MySQL (XAMPP)

```bash
# Iniciar XAMPP
sudo /opt/lampp/lampp start

# Verificar que MySQL está corriendo
sudo /opt/lampp/lampp status
```

### 3. Iniciar Microservicios Blockchain

#### MS Wrapper IPFS (Pinata)
```bash
# Clonar repositorio
git clone https://github.com/PPereaC/ms_wrapper_ipfs.git
cd ms_wrapper_ipfs

# Instalar dependencias
npm install

# Configurar variables de entorno (crear archivo .env)
echo "PINATA_API_KEY=tu_api_key" > .env
echo "PINATA_SECRET_API_KEY=tu_secret_key" >> .env

# Iniciar microservicio
npm start
```

#### MS Wrapper Smart Contract
```bash
# Clonar repositorio
git clone https://github.com/PPereaC/ms_wrapper_sc.git
cd ms_wrapper_sc

# Instalar dependencias
npm install

# Configurar variables de entorno (crear archivo .env)
echo "PRIVATE_KEY=tu_private_key" > .env
echo "SEPOLIA_RPC_URL=https://sepolia.infura.io/v3/tu_project_id" >> .env
echo "CONTRACT_ADDRESS=0xTuContratoDesplegado" >> .env

# Iniciar microservicio
npm start
```

---

## 🚀 Instalación y Ejecución

### 1. Clonar Repositorio

```bash
git clone https://github.com/PPereaC/AutoriZame-Backend.git
cd AutoriZame-Backend
```

### 2. Compilar Proyecto

```bash
./mvnw clean compile
```

### 3. Ejecutar Aplicación

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

### 4. Acceder a Documentación API

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

---

## 📡 API Endpoints

### Clientes (`/api/v1/clientes`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/clientes` | Registrar nuevo cliente |
| `GET` | `/api/v1/clientes` | Listar todos los clientes |
| `GET` | `/api/v1/clientes/{id}` | Obtener cliente por ID |
| `PUT` | `/api/v1/clientes/{id}` | Actualizar cliente |
| `DELETE` | `/api/v1/clientes/{id}` | Eliminar cliente |

#### Ejemplo: Crear Cliente

```bash
curl -X POST http://localhost:8080/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@test.com",
    "contrasena": "Password123#",
    "direccionEthereum": "0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b"
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@test.com",
  "direccionEthereum": "0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b",
  "fechaRegistro": "2024-02-21T14:30:00"
}
```

### Blockchain (`/api/v1/blockchain`)

#### IPFS Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/blockchain/ipfs/subir` | Subir metadata a IPFS |
| `GET` | `/api/v1/blockchain/ipfs/recuperar/{cid}` | Recuperar metadata por CID |

#### NFT Endpoints (Smart Contract)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/blockchain/nft/mintar` | Mintear nuevo NFT de autorización |
| `POST` | `/api/v1/blockchain/nft/transferir` | Transferir NFT a otra dirección |

#### Ejemplo: Subir a IPFS

```bash
curl -X POST http://localhost:8080/api/v1/blockchain/ipfs/subir \
  -H "Content-Type: application/json" \
  -d '{
    "idPedido": 1,
    "addressCliente": "0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b",
    "addressAutorizado": "0x742d35Cc6634C0532925a3b844Bc9e7595f8dEeB"
  }'
```

**Respuesta:**
```json
{
  "success": true,
  "pinHash": "bafkreib34l3kaa7vtps7o2cgoypiorig4lj3f44riahlequjxv3izesqsm",
  "gatewayUrl": "https://gateway.pinata.cloud/ipfs/bafkreib34l3kaa7vtps7o2cgoypiorig4lj3f44riahlequjxv3izesqsm"
}
```

#### Ejemplo: Mintear NFT

```bash
curl -X POST http://localhost:8080/api/v1/blockchain/nft/mintar \
  -H "Content-Type: application/json" \
  -d '{
    "destinatario": "0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b",
    "personaAutorizada": "0x0000000000000000000000000000000000000000",
    "referenciaExterna": 1,
    "pin": 1234,
    "rutaDatos": "QmTest123"
  }'
```

**Respuesta:**
```json
{
  "success": true,
  "mensaje": "Token mintado correctamente",
  "transactionHash": "0xc0e8bee199f5e7ddc59a05564489efa6c83817999094656b8ec97dd6d894e082",
  "bloque": 10308197
}
```

---

## 🔗 Microservicios Blockchain

### MS Wrapper IPFS (Pinata)

**Repositorio:** [https://github.com/PPereaC/ms_wrapper_ipfs](https://github.com/PPereaC/ms_wrapper_ipfs)

Microservicio Node.js que actúa como wrapper para la API de Pinata, permitiendo:

- **Subir archivos/metadata** a IPFS
- **Recuperar contenido** mediante CID (Content Identifier)
- **Gestión de pinning** para garantizar persistencia

**Endpoints expuestos:**
- `POST /subirMetadata` - Sube JSON metadata a IPFS
- `GET /recuperarMetadata/:cid` - Recupera metadata por CID

### MS Wrapper Smart Contract

**Repositorio:** [https://github.com/PPereaC/ms_wrapper_sc](https://github.com/PPereaC/ms_wrapper_sc)

Microservicio Node.js para interactuar con contratos inteligentes en la red Sepolia (Ethereum Testnet):

- **Mintar NFTs** de autorización
- **Transferir NFTs** entre direcciones
- **Consultar ownership** y metadatos de tokens

**Endpoints expuestos:**
- `POST /mintarAutorizacion` - Crea nuevo NFT
- `POST /transferirAutorizacion` - Transfiere NFT existente

---
## Flujo Completo de Prueba

```bash
# 1. Crear cliente (persistencia JPA)
curl -X POST http://localhost:8080/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Demo","email":"demo@test.com","contrasena":"Password123#","direccionEthereum":"0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b"}'

# 2. Listar clientes
curl http://localhost:8080/api/v1/clientes

# 3. Subir metadata a IPFS
curl -X POST http://localhost:8080/api/v1/blockchain/ipfs/subir \
  -d '{"idPedido":1,"addressCliente":"0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b","addressAutorizado":"0x742d35Cc6634C0532925a3b844Bc9e7595f8dEeB"}'

# 4. Mintear NFT
curl -X POST http://localhost:8080/api/v1/blockchain/nft/mintar \
  -d '{"destinatario":"0x44728bf9ee6207587edc2239c76d9ed5cfb96f2b","personaAutorizada":"0x0000000000000000000000000000000000000000","referenciaExterna":1,"pin":1234,"rutaDatos":"QmTest123"}'
```

---

## 📁 Estructura del Proyecto

```
AutoriZame-Backend/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/autorizame/
│       │       ├── AutoriZameBackendApplication.java
│       │       ├── client/                    # RestClients
│       │       │   ├── PinataClient.java
│       │       │   └── SmartContractClient.java
│       │       ├── controllers/               # REST Controllers
│       │       │   ├── ClienteController.java
│       │       │   └── BlockchainController.java
│       │       ├── models/                    # Entidades JPA
│       │       │   └── entity/
│       │       │       └── Cliente.java
│       │       ├── repository/                # Repositorios
│       │       │   └── ClienteRepository.java
│       │       ├── service/                   # Servicios Blockchain
│       │       │   ├── PinataService.java
│       │       │   └── SmartContractService.java
│       │       └── services/                   # Servicios de Negocio
│       │           ├── ClienteService.java
│       │           ├── AutorizadoService.java
│       │           └── PedidoService.java
│       └── resources/
│           ├── application.properties
│           └── static/
├── pom.xml
└── README.md
```

---

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT. Ver archivo `LICENSE` para más detalles.

---

## 👤 Autor

**Pablo Perea Campos** - [GitHub](https://github.com/PPereaC)

---

## 🤝 Agradecimientos

- Repositorios de microservicios blockchain:
  - [ms_wrapper_ipfs](https://github.com/PPereaC/ms_wrapper_ipfs) - Integración IPFS/Pinata
  - [ms_wrapper_sc](https://github.com/PPereaC/ms_wrapper_sc) - Integración Smart Contracts
