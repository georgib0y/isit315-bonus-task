# ISIT315 Major Assignment Bonus Task
George Bull - 7252092

This is an endpoint implemented with Java Fuseki.

## Running
To run this project, clone it and then open it in Eclipse IDE

### Server
- Open `src/main/java/endpoint/Endpoint.java` and run it in Eclipse.
- This should start a server that runs on `http://localhost:3330/`.
- The Value Map Ontology dataset will be avaliable on `http://localhost:3330/valuemapontology`

### Client
- This Eclipse project also comes with a client that will attempt to query the ontology with a set of predefined SELECT, ASK, CONSTRUCT, and DESCRIBE queries when run.
- To do so, open `src/main/java/client/Client.java` and run it after the server has started.
- The Eclipse console may want to switch back to the server terminal after the client finishes, but the terminal can be swapped back with the "Display Select Consoles" button near the top right on the console pane.
