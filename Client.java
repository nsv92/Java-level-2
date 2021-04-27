import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    public  void openConnection() throws IOException {
        int SERVER_PORT = 8189;
        String SERVER_ADDR = "localhost";
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out= new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equals("/end")) {
                        throw new Exception("Connection closed by server");
//                        closeConnection();
//                        System.out.println("Connection closed by server");
//                        break;
                    }
                    System.out.println("Console: " + strFromServer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Scanner console = new Scanner(System.in);
                String strFromClient = console.nextLine();
                if (strFromClient.equals("/end")) {
                    try {
                        out.writeUTF(strFromClient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    closeConnection();
                    System.out.println("Connection closed by client");
                    break;
                }
                try {
                    out.writeUTF(strFromClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}