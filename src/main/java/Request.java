public class Request {
    private String resource;
    private int status;
    private String statusName;


    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getResource() {
        return resource;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

