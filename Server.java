import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        Socket socket;

        try (ServerSocket serverSocket = new ServerSocket(8189)){
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Пользователь подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                while (true) {
                    Scanner console = new Scanner(System.in);
                    String strFromConsole = console.nextLine();
                    if (strFromConsole.equals("/end")) {
                        System.out.println("Connection closed by server");
                        try {
                            out.writeUTF(strFromConsole);
                            in.close();
                            out.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    try {
                        out.writeUTF(strFromConsole);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    throw new Exception("Connection closed by client");
                }
                System.out.println("Client: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}