package remote;

import userManagement.User;
import userManagement.UserAlreadyExistsException;
import userManagement.UserManagerAdministrator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RemoteUserAdmin extends UserManagerAdministrator {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;


    RemoteUserAdmin(String filename) throws IOException, ClassNotFoundException {
        socket = new Socket("localhost", 5200);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        output.writeObject(Protocol.INIT_PERSISTENT);
        Object response = input.readObject();
        if (response != Protocol.CONTINUE) {
            throw new IOException("Provided invalid data to server");
        }
        output.writeObject(filename);
        response = input.readObject();
        if (response != Protocol.CONTINUE) {
            if (response instanceof IOException) {
                throw (IOException) response;
            } else if (response instanceof ClassNotFoundException) {
                throw (ClassNotFoundException) response;
            } else {
                throw new IOException("Provided invalid data to server", (Throwable) response);
            }
        }
    }

    RemoteUserAdmin() throws IOException, ClassNotFoundException {
        socket = new Socket("localhost", 5200);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        output.writeObject(Protocol.INIT_MEMORY);
        Object response = input.readObject();
        if (response != Protocol.CONTINUE) {
            throw new IOException("Provided invalid data to server", (Throwable) response);
        }
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException, IOException, ClassNotFoundException {
        output.writeObject(Protocol.ADD_USER);
        Object response = sendUserIfValid(user);
        if (response != Protocol.CONTINUE) {
            if (response instanceof IOException) {
                throw (IOException) response;
            } else if (response instanceof ClassNotFoundException) {
                throw (ClassNotFoundException) response;
            } else if (response instanceof UserAlreadyExistsException) {
                throw (UserAlreadyExistsException) response;
            } else {
                throw new IOException("Provided invalid data to server", (Throwable) response);
            }
        }
    }

    @Override
    public boolean verifyUser(User user) throws IOException, ClassNotFoundException {
        output.writeObject(Protocol.VERIFY_USER);
        Object response = input.readObject();
        if (response != Protocol.CONTINUE) {
            throw new IOException("Provided invalid data to server", (Throwable) response);
        }
        output.writeObject(user);
        response = input.readObject();
        if (response instanceof Exception) {
            if (response instanceof IOException) {
                throw (IOException) response;
            } else {
                throw (ClassNotFoundException) response;
            }
        } else if (response instanceof Boolean) {
            return (boolean) response;
        } else {
            throw new ClassNotFoundException("Expected Boolean, got " + response.getClass());
        }
    }

    @Override
    public void removeUser(User user) throws IOException, ClassNotFoundException {
        output.writeObject(Protocol.REMOVE_USER);
        Object response = sendUserIfValid(user);
        if (response instanceof Exception) {
            if (response instanceof IOException) {
                throw (IOException) response;
            } else {
                throw (ClassNotFoundException) response;
            }
        }
    }

    private Object sendUserIfValid(User user) throws IOException, ClassNotFoundException {
        Object response = input.readObject();
        if (response != Protocol.CONTINUE) {
            throw new IOException("Provided invalid data to server", (Throwable) response);
        }
        output.writeObject(user);
        return input.readObject();
    }
}
