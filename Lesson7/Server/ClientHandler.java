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

        new Thread(()-> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/login")) {
                        String usernameFromLogin = msg.split("\\s")[1];
                        if (server.isNickBusy(usernameFromLogin)) {
                            sendMessage("/login_failed Current nickname has already been occupied");
                            continue;
                        }
                        username = usernameFromLogin;
                        sendMessage("/login_ok " + username);
                        server.subscribe(this);
                        break;
                    }


                }
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/who_am_i")) {
                        sendMessage("Your nickname: " + getUsername() + "\n");
                    } else if (msg.startsWith("/exit")) {
                        sendMessage("/exit");
                        System.out.printf("Client %s has been disconnected%n", getUsername());
                        server.broadcastMessage(username + " has been disconnected!\n");
                        break;
                    } else server.broadcastMessage(username + ": " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    private void disconnect() {
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