import java.util.HashMap;
import java.util.Map;

public class Response {
    private byte[] body;
    private Map<String, String> headers = new HashMap<>();
    private int status;
    private String statusName;
    public Response(byte[] data) {
        body = data;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }


    public void setHeader(String headerName, String value) {
        headers.put(headerName, value);
    }

    public byte[] toBytes() {
        return this.toBytes();
    }
}
