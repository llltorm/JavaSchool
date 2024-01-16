import java.util.concurrent.ArrayBlockingQueue;

public class FrontalSystem {
    private ArrayBlockingQueue<Request> queueRequest;
    private final int capasity = 4;

    public FrontalSystem() {
        this.queueRequest = new ArrayBlockingQueue<>(capasity);
    }

    public void acceptRequest(Request request) {
            queueRequest.add(request);
            System.out.println("Фронтальная система получила заявку на обработку по клиенту - " +
                    request.getClientName());
    }

    public Request receiveRequest() throws InterruptedException {
            Request request = queueRequest.take();
            System.out.println("Фронтальная система отправила заявку по клиенту -" +
                    request.getClientName());
            return request;
    }
}
