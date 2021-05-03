package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    if(msg.startsWith("/")) {
                        executeCommand(msg);
//                        continue;
                    } else
                    server.broadcastMessage(username + ": " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void executeCommand(String cmd) throws IOException {
        if(cmd.startsWith("/w ")) {
            String[] tokens = cmd.split("\\s", 3);
            server.sendPrivateMessage(this, tokens[1], tokens[2]);
            return;
        }

        if(cmd.startsWith("/login ")) {
            String usernameFromLogin = cmd.split("\\s")[1];
            if (server.isUserOnline(usernameFromLogin)) {
                sendMessage("/login_failed Current nickname has already been occupied");
            } else {
                username = usernameFromLogin;
                sendMessage("/login_ok " + username);
                server.subscribe(this);
            }
        }

        if (cmd.startsWith("/who_am_i")) {
            sendMessage("Your nickname: " + getUsername());
        }

        if (cmd.startsWith("/logout")) {
            sendMessage("/logout");
        }
    }

    private void disconnect() throws IOException {
        server.unsubscribe(this);
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String msg) throws IOException {
        out.writeUTF(msg);
    }

    public String getUsername() {
        return username;
    }
}