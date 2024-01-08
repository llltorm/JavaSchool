public class Request {
    private String clientName;
    private int amount;
    private RequestType requestType;

    public String getClientName() {
        return clientName;
    }

    public int getAmount() {
        return amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Request(String clientName, int amount, RequestType requestType) {
        this.clientName = clientName;
        this.amount = amount;
        this.requestType = requestType;
    }
}
