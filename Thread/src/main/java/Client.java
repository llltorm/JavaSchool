public class Client implements Runnable {
    private Request request;
    private FrontalSystem frontalSystem;
    private String name;

    public Client(Request request, FrontalSystem frontalSystem, String name) {
        this.request = request;
        this.frontalSystem = frontalSystem;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + ": Заявка (" +
                request.getClientName() + ", " +
                request.getAmount() + ", " +
                request.getRequestType() + ") отправлена в банк");
        frontalSystem.acceptRequest(request);

    }
}
