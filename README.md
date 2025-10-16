🧪 API Automation Testing Framework
> **Tech Stack:** REST Assured • TestNG • TDD • Maven • POM • Data Driven • Env Config

## 📘 1️⃣ Overview
Framework ini adalah API Automation Testing Framework yang mengimplementasikan:
- TestNG untuk Test Runner berbasis TDD
- REST Assured untuk API testing
- Page Object Model (POM) untuk struktur modular
- Data Driven Testing menggunakan Excel/JSON/DataProvider
- Environment Configuration (.env / config.properties)
- End-to-End Chaining Test menggunakan data dinamis antar API

## ⚙️ 2️⃣ Setup & Installation

### 🔹 Prerequisites
- Java JDK 17+
- Maven 3.8+
- IDE (VSCode / IntelliJ / Eclipse)

### 🔹 Setup Environment Variables

📄 Gunakan .env untuk ubah Data Statis:

### `.env`

```
BASE_URL=https://dummyjson.com
TOKEN=your-token-here
ENV=staging
```

📄 Setup Urutan Eksekusi di testng.xml
File testng.xml berfungsi sebagai controller utama eksekusi test.
Di dalamnya kamu dapat menentukan:

- Urutan class atau package yang akan dijalankan.
- Group atau tag test tertentu (<include name="smoke"/>, <exclude name="regression"/>).
- Parallel execution (thread).

📄 Ubah Data Dinamis pada File DataProvider.java (/src/test/java/tests/)

Pada repo ini menggunakan pom desentialize bawaan Java yang dimapping menjadi JSON untuk menyimpan data uji dinamis — misalnya payload request atau parameter API.


## 🧩 3️⃣ How to Run Tests

🔹 Run TDD (TestNG)
Menjalankan urutan test based on setting pada testng.xml per API.

- Setup di testng.xml
- Jalankan
```
mvn clean test


```


> 🧠 Gunakan urutan di atas untuk memastikan seluruh dependency, environment, dan data uji berjalan konsisten antar environment dan pipeline.


```
👨‍💻 Maintainer
Author: Afif T R
Role: QA Automation Engineer
Contact: afiftabahr@gmail.com
```
