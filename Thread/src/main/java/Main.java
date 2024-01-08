public class Main {
    public static void main(String[] args) {
        BackSystem backSystem = new BackSystem(50000);
        FrontalSystem frontSystem = new FrontalSystem();

        Thread requestHandler1 = new Thread(
                new RequestHandler("Обработчик №1", frontSystem, backSystem));
        Thread requestHandler2 = new Thread(
                new RequestHandler("Обработчик №2", frontSystem, backSystem));

        Thread client1 = new Thread(new Client(new Request("Клиент 1", 20000, RequestType.INCREASE),
                        frontSystem, "Клиент 1"));

        Thread client2 = new Thread(new Client(new Request("Клиент 3", 40000, RequestType.INCREASE),
                frontSystem, "Клиент 3"));

        Thread client3 = new Thread(new Client(new Request("Клиент 2", 50000, RequestType.DECREASE),
                        frontSystem, "Клиент 2"));

        Thread client4 = new Thread(new Client(new Request("Клиент 4", 30000, RequestType.DECREASE),
                        frontSystem, "Клиент 4"));

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        requestHandler1.start();
        requestHandler2.start();
    }
}
