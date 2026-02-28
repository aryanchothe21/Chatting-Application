# 💬 Java Chatting Application

A **real-time Client–Server Chat Application** built using **Java Socket Programming** and **Multithreading**.  
The application enables multiple clients to communicate simultaneously through a centralized server, demonstrating core networking and concurrent programming concepts.

---

## 📌 Project Overview

This project implements a real-time messaging system using Java’s networking capabilities.  
A server listens for incoming client connections, and each connected client communicates through dedicated threads, allowing simultaneous message exchange.

The system demonstrates how real-world chat applications handle multiple users efficiently.

---

## 🚀 Features

- 💬 Real-time messaging
- 👥 Multiple client support
- 🖥 Client–Server architecture
- ⚡ Multithreading for concurrent users
- 📡 Message broadcasting to all connected clients
- 🧩 Simple console-based interface
- 🔄 Continuous communication without restarting server

---

## 🛠 Technologies Used

### 🔹 Programming Language
- Java (Core Java)

### 🔹 Concepts & Technologies
- Java Socket Programming
- TCP/IP Communication
- Multithreading
- Input/Output Streams
- Object-Oriented Programming (OOP)
- Client–Server Architecture

### 🔹 Development Tools
- IntelliJ IDEA / Eclipse / VS Code
- Command Prompt / Terminal
- Java JDK 8+

---

## 📁 Project Structure

- Chatting-Application/
│
├── Server.java # Server program
├── Client.java # Client program
├── ClientHandler.java # Handles multiple clients using threads
└── README.md


---

## ⚙️ How It Works

1. The **Server** starts and listens on a specific port.
2. Multiple **Clients** connect using server IP address and port number.
3. Each client connection is handled using a separate thread.
4. Messages sent by one client are broadcasted to all connected clients.
5. Communication continues in real time until users disconnect.

---

## ▶️ How to Run the Project

### ✅ Step 1: Compile Files

Open terminal inside project folder:

```bash
javac Server.java
javac Client.java

## ✅ Step 2: Start Server
java Server

## ✅ Step 3: Start Clients

Open new terminal windows for each client:

java Client

🎯 Learning Outcomes

Understanding client–server communication

Implementing real-time networking applications

Managing multiple users using threads

Practical experience with Java sockets

Designing scalable communication systems

## Future Enhancements

-  User authentication system

-  Private messaging

-  File sharing support

-  Database integration

-  GUI interface using Java Swing/JavaFX

-  Online deployment

##  Applications

- Messaging systems

- Collaborative tools

- Online multiplayer communication

- Distributed systems learning

- Networking education projects

##  Author

Aryan Chothe
B.Tech Student | Java Developer | Full Stack & AI Enthusiast
