public class RequestHandler implements Runnable {
    private String name;
    private FrontalSystem frontalSystem;
    private BackSystem backSystem;

    public RequestHandler(String name, FrontalSystem frontalSystem, BackSystem backSystem) {
        this.name = name;
        this.frontalSystem = frontalSystem;
        this.backSystem = backSystem;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Request currentRequest = null;
            try {
                currentRequest = frontalSystem.receiveRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ": Получена заявка на обработку по клиенту - " +
                    currentRequest.getClientName());
            backSystem.changingAmount(currentRequest);
        }
    }
}
