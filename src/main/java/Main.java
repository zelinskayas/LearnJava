import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final String STORAGE = "D:/Folder/";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    Request request = HttpUtils.readRequestFromInputStream(socket.getInputStream(), STORAGE.length());
                    String resource = request.getResource();
                    File file = new File(STORAGE + resource);
                    byte[] respData;
                    try (FileInputStream fin = new FileInputStream(file)) {
                        respData = new byte[fin.available()];
                        // считываем буфер
                        fin.read(respData, 0, respData.length);
                    }

                    Response response = new Response(respData);
                    response.setHeader("Content-lenght", String.valueOf(respData.length));
                    response.setStatus(200);
                    response.setStatusName("Ok");
                    socket.getOutputStream().write(response.toBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
