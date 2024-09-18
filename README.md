ECHO Message - Multi-Client Chat System in Java
This project is a simple multi-client chat system built in Java using the Socket class. The application allows multiple clients to connect to a single server and exchange messages. The server listens for incoming client connections and handles each connected client in its own thread using a ClientHandler class.

Features
Multi-Client Support: Multiple clients can connect to the server simultaneously.
Server-Client Architecture: A central server relays messages between connected clients.
ECHO Functionality: Each client can send a message that will be echoed back by the server.
Threaded Client Handling: The server handles each client connection in a separate thread, ensuring simultaneous communication with multiple clients.
Components
Server Class: Manages client connections and distributes messages to connected clients.
ClientHandler Class: Runs in a separate thread for each connected client, handling message input and output.
Client Class: Represents the clients that connect to the server, allowing users to send and receive messages.
How to Run
Run the Server: Start the Server class to begin listening for incoming client connections.
Run Clients: Start the Client class to connect to the server. You can run multiple instances of the client to simulate a multi-client environment.
Send Messages: Type messages in the client console, and the server will echo the messages back to all connected clients.
Requirements
Java 8 or higher
Basic knowledge of Java sockets and multithreading
