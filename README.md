DemoQA-API

Designed and executed API tests for the DemoQA Book Store API using industry-standard tools and frameworks.

🧪 Project Overview

This repository contains API test suites and configurations for testing the DemoQA Book Store API. The tests are implemented using:

Postman – API test collection & environment

Rest Assured (Java) – automated REST API assertions

JMeter – basic performance measurements

The goal is to validate correctness of endpoints, verify response codes, and assess basic performance behavior for key API operations.

📂 Repository Contents
/
├── BookStoreAPI.postman_collection.json      # Postman API test collection
├── BookStoreAPI.postman_environment.json     # Postman environment variables
└── README.md                                 # This file

🚀 Getting Started
📌 Prerequisites

Before running tests, make sure you have:

Postman installed (for manual/automated collection)

Java 11+ (if running Rest Assured tests)

Maven/Gradle (depending on your test framework setup)

JMeter (for performance tests)

📥 Clone Repository
git clone https://github.com/Dejan-Budanovic/DemoQA-API.git
cd DemoQA-API

🧰 Using Postman Collection

Open Postman.

Import BookStoreAPI.postman_collection.json.

Import BookStoreAPI.postman_environment.json to configure variables.

Select the environment and run the collection.

Review test results and response assertions.

🧪 Running Rest Assured API Tests

If Java tests are included:

Ensure Java and Maven/Gradle are installed.

Navigate to the project test folder:

cd <path-to-tests>


Run tests via Maven:

mvn clean test


Or via Gradle:

gradle test


✔️ Tests perform assertions on status codes, response schemas, and data integrity.

⚡ Performance Testing with JMeter

Open Apache JMeter.

Load provided .jmx plan (if any is included).

Run the test plan and review performance metrics (response time, throughput).

📦 Tools & Frameworks
Tool	Usage
Postman	API test collection & environment
Rest Assured	Automated Java API testing
JMeter	Performance testing
Git	Version control
📌 API Endpoints Covered

Typical operations tested include (DemoQA Book Store API):

GET /Books – Retrieve all books

POST /Books – Add a book

DELETE /Books/{isbn} – Delete a book

User operations – Create user, login, etc.

The exact endpoints depend on the imported Postman collection.

🧠 Best Practices

Use environments for base URLs and user credentials.

Keep tests idempotent – reset test data where possible.

Add negative tests (invalid inputs, unauthorized access).

Integrate with CI/CD (GitHub Actions / Jenkins) for automated runs.

📜 License

This project is open source and available under the MIT License.
