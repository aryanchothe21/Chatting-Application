
💬 Java Chatting Application
A real-time Client-Server Chat Application built using Java, Sockets, and Multithreading.
This project demonstrates networking concepts and real-time communication between multiple clients.


📌 Features
🔹 Real-time messaging
🔹 Multiple clients support
🔹 Client-Server architecture
🔹 Multithreading for handling multiple users
🔹 Simple and user-friendly interface (Swing GUI / Console based)
🔹 Automatic message broadcasting


🛠️ Technologies Used
Java (Core Java)
Java Socket Programming
Multithreading
Java Swing (if GUI-based)
OOP Concepts


🏗️ Project Structure
Copy code

Chatting-Application/
│
├── Server.java
├── Client.java
├── ClientHandler.java
└── README.md
⚙️ How It Works


The Server starts and listens on a specific port.
Multiple Clients connect to the server using IP address and port number.
Each client runs on a separate thread.
Messages sent by one client are broadcasted to all connected clients.
🚀 How to Run
Step 1: Compile Files
Bash
Copy code
javac Server.java
javac Client.java
Step 2: Start Server
Bash
Copy code
java Server
Step 3: Start Client (in new terminal)
Bash
Copy code
java Client

🔌 Requirements
Java JDK 8 or above
IDE (IntelliJ / Eclipse / VS Code) or Command Prompt

📚 Concepts Covered
Socket Programming
TCP/IP Communication
Multithreading
Exception Handling
Streams (Input/Output)

🎯 Learning Outcome
Understanding of client-server architecture
Real-time communication handling
Managing multiple users with threads
Hands-on experience with networking in Java. 

🔮 Future Enhancements
🔹 Private messaging
🔹 User authentication
🔹 File sharing
🔹 Database integration
🔹 Message encryption

👨‍💻 Author
Aryan Chothe
B.Tech Student | Java & Full Stack Developer

