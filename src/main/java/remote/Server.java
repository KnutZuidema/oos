package remote;

import userManagement.User;
import userManagement.UserAlreadyExistsException;
import userManagement.UserManagerAdministrator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    UserManagerAdministrator userAdmin;

    Server() throws IOException {
        serverSocket = new ServerSocket(5200, 10, InetAddress.getByName("localhost"));
    }

    public void start() throws IOException {
        while (true) {
            Socket client = serverSocket.accept();
            Thread thread = new Thread(() -> {
                try {
                    handle(client);
                } catch (IOException ignored) {
                }
            });
            thread.setName("Socket Handler");
            thread.start();
        }
    }

    private void handle(Socket clientSocket) throws IOException {
        Protocol action = null;
        ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
        while (action != Protocol.SHUTDOWN) {
            try {
                action = (Protocol) input.readObject();
                output.writeObject(Protocol.CONTINUE);
            } catch (ClassCastException | ClassNotFoundException e) {
                output.writeObject(Protocol.INVALID_DATA);
                continue;
            }
            System.out.println("Server: " + action);
            switch (action) {
                case INIT_MEMORY:
                    setUserAdminMemory(output);
                    break;
                case INIT_PERSISTENT:
                    setUserAdminPersistent(output, input);
                    break;
                case ADD_USER:
                    addUser(output, input);
                    break;
                case REMOVE_USER:
                    removeUser(output, input);
                    break;
                case VERIFY_USER:
                    verifyUser(output, input);
                    break;
            }
        }
    }

    private void setUserAdminMemory(ObjectOutputStream output) throws IOException {
        userAdmin = new UserManagerAdministrator();
    }

    private void setUserAdminPersistent(ObjectOutputStream output, ObjectInputStream input) throws IOException {
        try {
            String filename = (String) input.readObject();
            userAdmin = new UserManagerAdministrator(filename);
        } catch (IOException | ClassNotFoundException e) {
            output.writeObject(e);
            return;
        }
        output.writeObject(Protocol.CONTINUE);
    }

    private void addUser(ObjectOutputStream output, ObjectInputStream input) throws IOException {
        try {
            User user = (User) input.readObject();
            userAdmin.addUser(user);
        } catch (IOException | ClassNotFoundException | UserAlreadyExistsException e) {
            output.writeObject(e);
            return;
        }
        output.writeObject(Protocol.CONTINUE);
    }

    private void removeUser(ObjectOutputStream output, ObjectInputStream input) throws IOException {
        try {
            User user = (User) input.readObject();
            userAdmin.removeUser(user);
        } catch (IOException | ClassNotFoundException e) {
            output.writeObject(e);
            return;
        }
        output.writeObject(Protocol.CONTINUE);
    }

    private void verifyUser(ObjectOutputStream output, ObjectInputStream input) throws IOException {
        try {
            User user = (User) input.readObject();
            output.writeObject(userAdmin.verifyUser(user));
        } catch (IOException | ClassNotFoundException e) {
            output.writeObject(e);
        }
    }
}
