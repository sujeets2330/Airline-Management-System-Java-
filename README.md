#  Airline Management System

A comprehensive **Airline Management System** built using **Java Swing**, **MySQL**, and **JDBC**. This desktop application provides a complete solution for managing airline operations including flight management, customer records, ticket booking, cancellations, and boarding pass generation.

---

##  Table of Contents
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [System Architecture](#-system-architecture)
- [Database Schema](#-database-schema)
- [Installation Guide](#-installation-guide)
- [How to Use](#-how-to-use)
- [Screenshots](#-screenshots)
- [Future Enhancements](#-future-enhancements)

---

##  Features

###  Admin Module
- Secure Login System
- Flight Management
- Customer Management
- Ticket Booking with PNR Generation
- Ticket Cancellation
- Boarding Pass Generation

###  Customer Features
- Quick Flight Booking using Aadhar
- Journey Details using PNR
- Ticket Cancellation
- Boarding Pass with Gate & Seat

---

##  Technology Stack

| Component | Technology |
|-----------|------------|
| Frontend | Java Swing, AWT |
| Backend | Java (JDK 8+) |
| Database | MySQL |
| Connectivity | JDBC |

---

##  System Architecture

    ┌─────────────────┐ ┌─────────────────┐ ┌─────┐
    │ Java Swing │────▶│ Business │────▶│ MySQL │
    │ (UI Layer) │ │ Logic Layer │ │ Database │
    └─────────────────┘ └─────────────────┘ └─────┘
    │ │ │
    ▼ ▼ ▼

- Home Frame • Validation • Tables:
- Login Form • PNR Generation • login
- Booking Form • Data Processing • passenger
- Cancellation Form • Transaction Mgmt • flight
- Boarding Pass • Error Handling • reservation
- Flight Info • cancel

---

##  Database Schema

### Tables Structure

| Table | Purpose | Key Fields |
|-------|---------|------------|
| **login** | Store admin credentials | username (PK), password |
| **passenger** | Store customer information | aadhar (PK), name, nationality, phone, address, gender |
| **flight** | Store flight details | f_code (PK), f_name, source, destination |
| **reservation** | Store booked tickets | PNR (PK), ticket_number, aadhar (FK), flightcode (FK), name, src, des, ddate |
| **cancel** | Store cancelled tickets | cancellation_no (PK), pnr_number (FK), name, fcode, date |

### Relationships
- `reservation.aadhar` → `passenger.aadhar`
- `reservation.flightcode` → `flight.f_code`
- `cancel.pnr_number` → `reservation.PNR` (with ON DELETE CASCADE)

---

##  Module Breakdown

### 1️⃣ **Login Module**
- Admin authentication interface
- Secure password field with hidden characters
- Reset button to clear fields
- Close button to exit application
- Redirects to Home dashboard on successful login

### 2️⃣ **Home Dashboard**
- Main navigation hub with menu bar
- Quick access buttons for all features
- Professional UI with background image
- Organized menus: DETAILS, BOOKING, TICKET, ADMIN, HELP
- About dialog with system information
- Exit confirmation dialog

### 3️⃣ **Customer Module**
- Form to add new customer details
- Input fields: Name, Nationality, Aadhar, Address, Gender, Phone
- Radio buttons for gender selection
- Data validation before insertion
- Success confirmation messages
- Prevents duplicate Aadhar entries

### 4️⃣ **Flight Module**
- Display all flights in tabular format
- Real-time data from database
- Scrollable table view
- Columns: Flight Code, Flight Name, Source, Destination

### 5️⃣ **Booking Module**
- Fetch customer details using Aadhar number
- Select source and destination from dropdown
- Available flights display based on route
- Date selection using JDateChooser calendar
- Automatic PNR and ticket number generation
- Booking confirmation with all details
- Transaction management for data integrity

### 6️⃣ **Journey Details**
- Search bookings by PNR number
- Display complete journey information
- Table view of reservation details
- Error handling for invalid PNR
- Shows all booked tickets

### 7️⃣ **Cancellation Module**
- Fetch booking details using PNR
- Verify if ticket is already cancelled
- Generate unique cancellation number
- Transaction management (commit/rollback)
- Remove from reservation table
- Add to cancel table with timestamp
- Confirmation dialog before cancellation

### 8️⃣ **Boarding Pass Module**
- Generate boarding pass using PNR
- Professional boarding pass design
- Random gate and seat allocation
- Print preview functionality
- Check for cancelled tickets
- Reset button to clear form
- Barcode simulation for authenticity

---

##  Installation Guide

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 8.0 or higher
- MySQL Connector JAR (mysql-connector-java-8.0.33.jar)
- Any Java IDE or text editor
- Git (optional)

### Step-by-Step Setup

#### 1. Clone or Download the Repository
```bash
git clone https://github.com/yourusername/AirlineManagementSystem.git
cd AirlineManagementSystem
```

#### 2. Set Up MySQL Database
```bash 
-- Run the SQL script provided in database.sql
CREATE DATABASE airlinemanagementsystem;
USE airlinemanagementsystem;
-- Execute all table creation and sample data insertion queries
```

#### 3. Configure Database Connection
Edit Conn.java and update with your MySQL credentials:
```bash
c = DriverManager.getConnection("jdbc:mysql:///airlinemanagementsystem", "root", "your_password");
```
#### 4.Add MySQL Connector JAR
- Download mysql-connector-java-8.0.33.jar
- Add to project classpath or lib folder

#### 5.Compile the Project
```bash
cd src
javac -cp ".;../lib/*" airlinemanagementsystem/*.java
```

#### 6.Run the Application
```bash
java -cp ".;../lib/*" airlinemanagementsystem.Login
```

## How to Use
Default Login Credentials
```bash
Username: admin
Password: admin123
```

## Workflow
### 1. Login to the system using admin credentials

### 2. Add Customer (if new customer)
 - Navigate to Details → Add Customer Details
 - Fill all required fields
 - Click Save

### 3. Book Flight
- Go to Booking → Book Flight
- Enter Aadhar number and click "Fetch User"
- Select Source and Destination
- Click "Fetch Flights"
- Select Date and click "Book Flight"
- Note down the generated PNR
- View Journey Details
- Go to Booking → Journey Details
- Enter PNR and click "Show Details"

### 4.Generate Boarding Pass
- Go to Ticket → Boarding Pass
- Enter PNR and click "GENERATE"
- View gate and seat allocation
- Click "PRINT BOARDING PASS"

### 5.Cancel Ticket
- Go to Ticket → Cancel Ticket
- Enter PNR and click "Show Details"
- Verify details and click "Cancel Ticket"
- Confirm cancellation
  

##  Screenshots

###  Login Page
![Login Page](src/airlinemanagementsystem/icons/login_page.png)

---

###  Home Dashboard
![Home Dashboard](src/airlinemanagementsystem/icons/home_page.png)

---

###  Add Customer Details
![Add Customer](src/airlinemanagementsystem/icons/customer_details.png)

---

###  Book Flight
![Book Flight](src/airlinemanagementsystem/icons/book_flight.png)

---

###  Ticket Cancellation
![Cancel Ticket](src/airlinemanagementsystem/icons/Cancel_ticket.png)

---

###  Boarding Pass
![Boarding Pass](src/airlinemanagementsystem/icons/Boarding_pass.png)

---

###  Database View – Passengers
![Passengers Table](src/airlinemanagementsystem/icons/Passengers.png)

---

###  Database View – Reservations
![Reservations Table](src/airlinemanagementsystem/icons/Reservations.png)

## Developer

Sujeet M A
- Email : sujeetmalagundi999@gmail.com
- LinkedIn : https://www.linkedin.com/in/sujeet-m-a-39a86b2b9/
