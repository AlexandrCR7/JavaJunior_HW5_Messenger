package junior.prog.chat.client.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void runServer(){
        while (!serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый клиент!");
                ClientManager clientManager = new ClientManager(socket);
                Thread thread = new Thread(clientManager);
                thread.start();
            } catch (IOException e){
                closeSocket();
            }
        }
    }


    private void closeSocket(){
        try {
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
