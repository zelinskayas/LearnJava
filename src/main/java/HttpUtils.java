import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtils {
    public static Request readRequestFromInputStream(InputStream inputStream, int storageNameLength) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String body = null;
        while (reader.ready()) {
            body = reader.readLine();
        }
        return new Request(body.substring(storageNameLength));
    }
}
