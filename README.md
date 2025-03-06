# Paramx - Gamify Your Life

**Paramx** is a personal project designed to gamify the process of tracking and improving various aspects of your life. The application allows users to define different parameters they want to monitor, such as caffeine intake, sleep quality, exercise, or productivity. Instead of relying on static tools like Excel, Paramx provides dynamic insights and graphics based on your daily entries, enabling you to make data-driven decisions about your well-being.

## State
  First model iteration is finished, basic functionality for server side. The idea is use this proof of concept to iterate over
  more added features.


## Features

- **Personalized Tracking**: Define and track various parameters relevant to your daily life.
- **Dynamic Insights**: Visualize the impact of your habits over time with charts and graphs.
- **Cloud-based Storage**: All data is securely stored in the cloud using **AWS RDS**.
- **Serverless Compute**: AWS Lambda for event-driven processing.

## Tech Stack

- **Backend**:
    - Java 21
    - Spring Boot 3
    - Hibernate ORM
- **Database**: AWS RDS (Relational Database Service) for cloud-based data storage.
- **Serverless Compute**: AWS Lambda for event-driven logic.
- **Deployment**: AWS services for deployment and scaling.
- **CICD**: Also leveraging automatic deployment on push to master, and pre-commit hook to ensure coverage
