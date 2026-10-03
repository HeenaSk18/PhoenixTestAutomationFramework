
# Phoenix Test Automation Framework

![Java](https://img.shields.io/badge/Java-16+-orange)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![REST Assured](https://img.shields.io/badge/API-REST%20Assured-green)
![TestNG](https://img.shields.io/badge/Runner-TestNG-red)
![CI](https://img.shields.io/badge/CI-GitHub%20Actions-black)

A Java-based API test automation framework for **Phoenix**, an **After Sales Services** application (B2X domain). It uses **REST Assured** and **TestNG** to validate the backend APIs behind the complete repair-job lifecycle: from creating a job at the front desk to delivering the repaired device back to the customer.

---

## Table of Contents

- [About the Application](#about-the-application)
- [Business Flow](#business-flow)
- [APIs Covered](#apis-covered)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Configuration](#configuration)
- [Running the Tests](#running-the-tests)
- [Framework Features](#framework-features)
- [CI/CD](#cicd)
- [Reporting](#reporting)
- [Author](#author)

---

## About the Application

Phoenix manages **after-sales service** for products such as phones, laptops, TVs and cars. When a product has a problem, the customer visits a service centre, and the application tracks the device through every stage until it is fixed and handed back.

Four user roles take part in the workflow:

| Role | Short name | Responsibility |
|------|-----------|----------------|
| Front Desk | `FD` | Creates the job and captures customer and device details |
| Supervisor | `SUP` | Assigns jobs to engineers |
| Engineer | `ENG` | Diagnoses and repairs the device |
| Quality Check | `QC` | Verifies the repair and accepts or rejects it |

---

## Business Flow

```
Front Desk (FD)  ->  Supervisor (SUP)  ->  Engineer (ENG)  ->  QC  ->  Front Desk (FD)
 Create Job          Assign Job            Repair Device      Accept /   Deliver Device
 (Ticket / Job No)                                            Reject     (Close Job)
```

1. **Create Job (FD):** Log in, fill in the customer details, device details (unique IMEI, date of purchase, issue, warranty status) and submit. A unique **Job Number** is generated. The action status is *Pending for Job Assignment*.
2. **Assign Job (SUP):** The supervisor opens the pending jobs and assigns one to an engineer.
3. **Repair (ENG):** The engineer opens the job from *My Jobs*, begins the repair, records the actual problem and completes the repair. The status moves to *Pending for QC*.
4. **Quality Check (QC):** QC checks the device and either **accepts** (job moves on) or **rejects** (job goes back to the team lead or supervisor).
5. **Delivery (FD):** The front desk delivers the device to the customer and closes the job. Repairs on in-warranty devices are free of charge, and out-of-warranty repairs are chargeable.

**Business rule:** the IMEI number must be unique, so a second job cannot be created with an IMEI that already exists.

---

## APIs Covered

The framework was built from an analysis of the API calls the Phoenix UI makes (browser DevTools, Network tab, Fetch/XHR).

| Area | API | Used by |
|------|-----|---------|
| Authentication | `POST /v1/login` | All roles |
| User | `userdetails` | All roles after login |
| Dashboard | `dashboard/count` (job counts such as *created today*) | All roles |
| Master data | `master` (all master/lookup data) | FD |
| Auto-suggest | `auto?input=` (landmark / customer details) | FD |
| Create Job | `create` | FD |
| Job details | `details` | All roles |
| Pending / Assigned | pending and assign APIs | SUP |
| My Jobs | `myjobs` | ENG |
| Repair complete | `repaircomplete` | ENG |
| QC | QC accept / reject APIs | QC |
| Delivery | `delivery` | FD |

> Exact endpoint paths and payloads live in the request specs and POJOs in the `src` folder.

---

## Tech Stack

| Purpose | Library | Version |
|---------|---------|---------|
| Language | Java | 16+ |
| Build tool | Maven | 3.8+ |
| API testing | REST Assured | 5.5.6 |
| Test runner | TestNG | 7.11.0 |
| JSON schema validation | REST Assured `json-schema-validator` | 5.5.6 |
| JSON (de)serialization | Jackson Databind | 2.20.0 |
| Test data (CSV) | OpenCSV | 5.12.0 |
| Test data (Excel) | Apache POI, POIJI | 5.4.1, 4.9.0 |
| Fake data | JavaFaker | 1.0.2 |
| Database validation | MySQL Connector/J, HikariCP | 9.5.0, 7.0.2 |
| Boilerplate reduction | Lombok | 1.18.42 |
| Environment variables | dotenv-java | 3.2.0 |
| CI | GitHub Actions | n/a |

---

## Project Structure

```
PhoenixTestAutomationFramework
├── .github/workflows     # GitHub Actions CI pipeline
├── src                   # Framework code and tests (API clients, models, utilities, tests)
├── .env.example          # Template for environment variables
├── env.txt               # Environment notes
├── pom.xml               # Maven dependencies and Surefire configuration
├── testng.xml            # TestNG suite
└── README.md
```

---

## Prerequisites

- JDK 16 or higher
- Maven 3.8 or higher
- Git
- Network access to the Phoenix application
- (Optional) A MySQL instance, if you run the database validation tests

Verify your setup:

```bash
java -version
mvn -version
```

---

## Setup

```bash
# 1. Clone the repository
git clone https://github.com/HeenaSk18/PhoenixTestAutomationFramework.git
cd PhoenixTestAutomationFramework

# 2. Create your environment file from the template
cp .env.example .env

# 3. Fill in the values in .env (see Configuration)

# 4. Download dependencies and compile
mvn clean compile
```

---

## Configuration

Environment-specific values, such as the base URL and user credentials, are **not hard-coded**. They are read from a `.env` file using `dotenv-java`.

1. Copy `.env.example` to `.env`.
2. Fill in the values for the environment you want to test.
3. `.env` is git-ignored, so never commit real credentials.

Test users cover the four roles (FD, SUP, ENG, QC). Credentials for the Phoenix demo environment are shared during project KT and should be kept only in your local `.env`.

---

## Running the Tests

The Surefire plugin takes the suite file as a parameter, so pass `suiteXmlFile`:

```bash
# Run the default suite
mvn clean test -DsuiteXmlFile=testng.xml
```

Run from an IDE (Eclipse or IntelliJ) by right-clicking `testng.xml` and choosing **Run As > TestNG Suite**.

---

## Framework Features

- **REST Assured request and response specifications** for reusable base URI, headers and logging
- **Role-based authentication:** log in as FD, SUP, ENG or QC and reuse the token across calls
- **End-to-end API flow tests:** create job, assign, repair, QC and deliver
- **POJOs with Jackson and Lombok** for clean request and response handling
- **JSON schema validation** of API responses
- **Data-driven testing** from CSV and Excel (OpenCSV, Apache POI, POIJI)
- **Dynamic test data** (names, addresses, phone numbers, unique IMEI) using JavaFaker
- **Database validation** with MySQL and a HikariCP connection pool
- **Environment configuration** through `.env` (dotenv-java)
- **TestNG** groups, listeners and suite-level execution
- **CI pipeline** with GitHub Actions

---

## CI/CD

The workflow in `.github/workflows` runs the test suite on GitHub Actions. Environment values and credentials should be stored as **repository secrets** (Settings > Secrets and variables > Actions), not in the repo.

---

## Reporting

After a run, Maven Surefire and TestNG write results to:

```
target/surefire-reports/
```

Open `index.html` or `emailable-report.html` in a browser for a summary.

---

## Author

**Heena Shaikh**
Senior QA Automation Engineer (SDET)
GitHub: [@HeenaSk18](https://github.com/HeenaSk18)


---

## Disclaimer

This project is built for learning and portfolio purposes against a demo application. It is not affiliated with any real after-sales provider.
