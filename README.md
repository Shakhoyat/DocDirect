# DocDirect - Healthcare Appointment Management System

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

> **A comprehensive desktop healthcare management system designed to streamline doctor appointments, patient management, and healthcare service delivery.**

## 📋 Table of Contents
- [Overview](#overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [System Architecture](#system-architecture)
- [Installation Guide](#installation-guide)
- [API Integration](#api-integration)
- [Application Screenshots](#application-screenshots)
- [Live Demo](#live-demo)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🎯 Overview

**DocDirect** is a full-stack desktop application that modernizes healthcare appointment management. Built with JavaFX and MySQL, it provides an intuitive interface for patients to browse healthcare providers, schedule appointments, and manage subscriptions with integrated payment processing.

### 🚀 Project Inspiration
This project draws inspiration from [Praava Health's](https://www.praavahealth.com/) patient-centered approach to healthcare delivery, incorporating modern UI/UX principles and efficient service management workflows.

### 🎯 Problem Statement
Traditional healthcare appointment systems often suffer from:
- Complex booking processes
- Limited payment options
- Poor user experience
- Lack of subscription flexibility

**DocDirect** addresses these challenges through streamlined workflows and modern technology integration.

## ✨ Key Features

### 🏥 Core Functionality
- **Doctor Directory Management** - Comprehensive provider listings with detailed profiles
- **Smart Appointment Scheduling** - Real-time availability checking and instant booking confirmation
- **Multi-tier Subscription System** - Flexible yearly, quarterly, and bi-yearly healthcare packages
- **Secure Payment Gateway** - Integrated support for bKash, Nagad, Rocket, and online banking via SSL Commerz

### 🔐 Security & User Management
- **Robust Authentication System** - Secure user registration and login workflows
- **Data Encryption** - Protected patient information and transaction data
- **Session Management** - Secure user session handling

### 📊 Data Management
- **Real-time Data Synchronization** - Live updates from external APIs
- **Efficient Database Operations** - Optimized MySQL queries for performance
- **JSON Data Processing** - Dynamic doctor schedules and availability management

## 🛠️ Technology Stack

### **Frontend**
- **JavaFX** - Modern, responsive desktop UI framework
- **FXML** - Declarative UI design and layout management
- **CSS** - Custom styling and responsive design

### **Backend**
- **Java SE** - Core application logic and business rules
- **MySQL** - Relational database management
- **JDBC** - Database connectivity and operations

### **External Integrations**
- **REST API** - External healthcare provider data integration
- **SSL Commerz** - Secure payment processing
- **JSON Processing** - Data parsing and manipulation

### **Development Tools**
- **IntelliJ IDEA Ultimate** - Primary IDE with advanced debugging
- **Git & GitHub** - Version control and collaborative development
- **Maven/Gradle** - Dependency management and build automation

## 🏗️ System Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │    │   Business      │    │   Data Access   │
│   Layer (FXML)  │◄──►│   Logic (Java)  │◄──►│   Layer (MySQL) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   UI Components │    │   Service Layer │    │   Database      │
│   - Login/Auth  │    │   - Appointment │    │   - Users       │
│   - Dashboard   │    │   - Payment     │    │   - Doctors     │
│   - Booking     │    │   - User Mgmt   │    │   - Appointments│
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🚀 Installation Guide

### Prerequisites
- **Java JDK 11+** - Required for JavaFX compatibility
- **MySQL Server 8.0+** - Database management system
- **IntelliJ IDEA Ultimate** - Recommended IDE (Community Edition also supported)

### Step-by-Step Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Shakhoyat/DocDirect.git
   cd DocDirect
   ```

2. **Database Configuration**
   ```sql
   CREATE DATABASE docdirect_db;
   -- Import the provided SQL schema file
   SOURCE database/schema.sql;
   ```

3. **Application Configuration**
   ```properties
   # Update database connection settings
   db.url=jdbc:mysql://localhost:3306/docdirect_db
   db.username=your_username
   db.password=your_password
   ```

4. **Build and Run**
   ```bash
   # Using Maven
   mvn clean compile exec:java
   
   # Or run directly from IntelliJ IDEA
   # Right-click on Main.java → Run
   ```

## 🔗 API Integration

### Healthcare Provider Data API
```
Endpoint: http://nayeemcode.pythonanywhere.com/data?file=sujon&key=jhbfhsdjdjsadbjsd
Method: GET
Response: JSON format with doctor details and schedules
```

### Payment Gateway Integration
- **SSL Commerz Sandbox/Live** - Secure transaction processing
- **Multiple Payment Methods** - Mobile banking, cards, net banking
- **Real-time Transaction Status** - Instant payment confirmation

## 📱 Application Screenshots

<details>
<summary><strong>🔐 Authentication System</strong></summary>

### Login Interface
![Login Page](ReadMe_Images/1.png)
*Secure user authentication with input validation and error handling*

### User Registration
![Registration Page](ReadMe_Images/2.png)
*Comprehensive user onboarding with data validation*

</details>

<details>
<summary><strong>🏠 Dashboard & Navigation</strong></summary>

### Main Dashboard
![Dashboard](ReadMe_Images/3.png)
*Intuitive dashboard with quick access to all major features*

</details>

<details>
<summary><strong>🏥 Healthcare Services</strong></summary>

### Standard Healthcare Packages
![Healthcare Packages](ReadMe_Images/4.png)
*Comprehensive healthcare service offerings*

### Premium Healthcare Plans
![Special Packages](ReadMe_Images/5.png)
*Advanced healthcare packages with additional benefits*

### Membership Tiers
![Membership Packages](ReadMe_Images/6.png)
*Flexible subscription options for different user needs*

</details>

<details>
<summary><strong>👨‍⚕️ Doctor Management</strong></summary>

### Doctor Directory
![Doctor Listings](ReadMe_Images/7.png)
*Browse available healthcare providers with filtering options*

### Doctor Profile Details
![Doctor Details](ReadMe_Images/8.png)
*Comprehensive provider information including qualifications and specializations*

### Appointment Booking Interface
![Appointment Booking](ReadMe_Images/8_2.png)
*Smart scheduling system with real-time availability*

</details>

<details>
<summary><strong>💳 Payment & Checkout</strong></summary>

### Checkout Process
![Checkout Page](ReadMe_Images/9.png)
*Streamlined checkout with transparent pricing*

### Shopping Cart
![My Cart](ReadMe_Images/10.png)
*User-friendly cart management with item modification options*

### Payment Summary
![Price Summary](ReadMe_Images/11.png)
*Dynamic pricing calculation with SSL Commerz integration*

### Payment Gateway - Card Options
![SSL Cards](ReadMe_Images/12.png)
*Secure card payment processing through SSL Commerz*

### Payment Gateway - Mobile Banking
![SSL Mobile Banking](ReadMe_Images/13.png)
*Integrated mobile financial services (bKash, Nagad, Rocket)*

### OTP Verification
![OTP Verification](ReadMe_Images/14.png)
*Two-factor authentication for enhanced security*

</details>

## 🎥 Live Demo

📹 **[Watch Full Application Demo](https://drive.google.com/file/d/197sZgBH5CF77WtfC4CavYzLGptnw0jvs/view?usp=drive_link)**

*Experience the complete user journey from registration to appointment booking and payment processing*

## 🏆 Technical Achievements

- **Responsive UI Design** - Adaptive layouts for different screen resolutions
- **Real-time Data Processing** - Live API integration with error handling
- **Secure Payment Integration** - PCI-compliant transaction processing
- **Scalable Architecture** - Modular design supporting future enhancements
- **Performance Optimization** - Efficient database queries and caching strategies

## 🤝 Contributing

We welcome contributions to enhance DocDirect! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write comprehensive unit tests
- Update documentation for new features
- Ensure backward compatibility

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Developer

**Shakhoyat Rahman**
- 📧 Email: [skt104.shujon@gmail.com](mailto:skt104.shujon@gmail.com)
- 🔗 GitHub: [@Shakhoyat](https://github.com/Shakhoyat)
- 💼 LinkedIn: [Connect with me](https://linkedin.com/in/your-profile)

---

### 🚀 Ready to revolutionize healthcare management? 

**DocDirect** - *Where technology meets healthcare excellence*

---

<div align="center">

**⭐ If you found this project helpful, please give it a star! ⭐**

*Built with ❤️ for better healthcare accessibility*

</div>
