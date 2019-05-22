import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

/*мой убогий кусок, хотела в байты файл
                    try(FileInputStream fin = new FileInputStream(file))
                    {
                        byte[] RespData = new byte[fin.available()];
                        // считываем буфер
                        fin.read(RespData, 0, RespData.length);
                    }
tttyy*/
                    Response response = new Response(RespData);
                    response.setHeader("Content-lenght", String.valueOf(RespData.length));
                    request.setStatus(200);
                    request.SetStatusName("Ok");
                    socket.getOutputStream().write(response.toBytes);
                }
            }

        catch(IOException e){
                System.out.println(e);
            }
        }
}
}
