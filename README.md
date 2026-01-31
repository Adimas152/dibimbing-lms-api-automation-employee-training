# 📘 Dibimbing LMS – API Automation (Employee Training)

API Automation Testing project for **Dibimbing LMS – Employee Training** using **Java, TestNG, GraphQL**, and **Gradle**, completed with **CI/CD integration** and **Slack notification**.

---

## 🚀 Tech Stack

- **Language**: Java 17  
- **Test Framework**: TestNG  
- **API Type**: GraphQL  
- **Build Tool**: Gradle  
- **Reporting**:
  - TestNG XML Report
  - Extent Report (HTML)
- **CI/CD**: GitHub Actions  
- **Notification**: Slack Webhook  

---

## 📂 Project Structure

```bash
dibimbing-lms-api-automation-employee-training
├── .github
│   └── workflows
│       └── api-automation-ci.yml      # CI pipeline + Slack notification
├── src
│   └── test
│       ├── java
│       │   └── qa
│       │       ├── client              # GraphQL client
│       │       ├── listener            # Extent Test Listener
│       │       ├── models
│       │       │   ├── requests         # GraphQL variables
│       │       │   └── responses        # Response mapping
│       │       ├── services             # API service layer
│       │       ├── tests                # Test cases (TestNG)
│       │       └── utils                # Helper & utilities
│       └── resources
│           └── graphql
│               ├── mutations            # GraphQL mutations
│               └── queries              # GraphQL queries
├── build.gradle
├── testng.xml
└── README.md
```

---

## 🧪 Test Coverage

### 👤 Employee Module
- Add Employee (Positive)
- Failed add employee when email empty
- Failed add employee when email invalid
- Failed add employee when employee ID duplicate

### 🎓 Training / Program Module
- Add Training successfully
- Failed add training when training name empty

### 📘 Chapter Module
- Add Chapter successfully
- Failed add chapter when chapter name empty

### 📄 Content Module
- Add Content (Article)
- Add Content (Video)
- Failed add content when:
  - Invalid duration
  - Empty title
  - Empty description

### 🏢 Division Module
- Add Division successfully
- Failed add division when required field empty

---

## ▶️ How to Run Test (Local)

```bash
./gradlew clean test
```

---

## 📊 Test Report

### 📄 Extent Report

Extent Report akan otomatis dibuat setelah test dijalankan.

**Location:**
```bash
build/reports/tests/test/index.html
```

### 📄 JUnit XML Report (CI Purpose)

Digunakan untuk parsing hasil test di CI/CD.

**Location:**
```bash
build/test-results/test
```

---

## 🔐 Environment Variables

| Variable    | Description          |
|------------|----------------------|
| BASE_URL   | GraphQL API base URL |
| USERNAME   | Login username       |
| PASSWORD   | Login password       |
| COMPANY_ID | Company ID           |
| IS_DEBUG   | Debug mode flag      |

```bash
export BASE_URL=https://lmsb2b.do.dibimbing.id/graphql
export USERNAME=your_username
export PASSWORD=your_password
export COMPANY_ID=your_company_id
export IS_DEBUG=true
```

---

## 🤖 CI/CD – GitHub Actions

CI/CD berjalan otomatis saat:
- Push ke branch **main / master**
- Pull request ke **main / master**

Pipeline:
- Checkout repository
- Setup Java 17
- Run automation tests
- Generate report
- Parse test result
- Send Slack notification

Workflow:
```bash
.github/workflows/api-automation-ci.yml
```

---

## 🔔 Slack Notification

Informasi yang dikirim:
- Status (PASSED / FAILED)
- Total Tests
- Passed / Failed / Skipped
- Pass Rate
- Branch
- Triggered By

---

## ⚠️ Important Notes

- CI tetap berjalan walaupun test gagal:
```bash
./gradlew clean test || true
```

- Data Slack diambil dari **JUnit XML**
```bash
build/test-results/test
```

Bukan dari Extent Report.

---

## ✅ Author

**Adimas**  
API Automation – Dibimbing LMS Employee Training
