import java.util.ArrayDeque;

public class FrontalSystem {
    private ArrayDeque<Request> dequeRequest;
    private final int capasity = 4;

    public FrontalSystem() {
        this.dequeRequest = new ArrayDeque<>();
    }

    public void acceptRequest(Request request) {
        synchronized (dequeRequest) {
            while (dequeRequest.size() >= capasity) {
                try {
                    dequeRequest.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            dequeRequest.add(request);
            System.out.println("Фронтальная система получила заявку на обработку по клиенту - " +
                    request.getClientName());
            dequeRequest.notifyAll();
        }
    }

    public Request receiveRequest() {
        synchronized (dequeRequest) {
            while (dequeRequest.size() == 0) {
                try {
                    dequeRequest.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Request request = dequeRequest.pop();
            System.out.println("Фронтальная система отправила заявку по клиенту -" +
                    request.getClientName());
            dequeRequest.notifyAll();
            return request;
        }
    }
}
