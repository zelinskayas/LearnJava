import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final String FOLDER = "D://Folder//";

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    Request request = HttpUtils.readRequestFromInputStream(socket.getInputStream());
                    String resource = request.getResource();
                    File file = new File(FOLDER + resource);
                    byte[] respData;
                    try (FileInputStream fin = new FileInputStream(file)) {
                        respData = new byte[fin.available()];
                        // считываем буфер
                        fin.read(respData, 0, respData.length);
                    }

                    Response response = new Response(respData);
                    response.setHeader("Content-lenght", String.valueOf(respData.length));
                    request.setStatus(200);
                    request.setStatusName("Ok");
                    socket.getOutputStream().write(response.toBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
