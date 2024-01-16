import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystem backSystem = new BackSystem(new AtomicLong(0));

        List<WarmingUp> listWarmingUpSystem = new ArrayList<>(List.of(
                new WarmingUp("System 1", backSystem, 20000, 6000),
                new WarmingUp("System 2", backSystem, 5000, 8000),
                new WarmingUp("System 3", backSystem, 15000, 10000)
        ));

        FrontalSystem frontSystem = new FrontalSystem();

        List<RequestHandler> listRequestHandler = new ArrayList<>(List.of(
                new RequestHandler("Обработчик №1", frontSystem, backSystem),
                new RequestHandler("Обработчик №2", frontSystem, backSystem)
        ));

        List<Client> listClient = new ArrayList<>(List.of(
                new Client(new Request("Клиент 1", 20000, RequestType.INCREASE),
                        frontSystem, "Клиент 1"),
                new Client(new Request("Клиент 2", 50000, RequestType.DECREASE),
                        frontSystem, "Клиент 2"),
                new Client(new Request("Клиент 3", 30000, RequestType.DECREASE),
                        frontSystem, "Клиент 3"),
                new Client(new Request("Клиент 4", 40000, RequestType.INCREASE),
                        frontSystem, "Клиент 4")

        ));

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.invokeAll(listWarmingUpSystem);
        for (RequestHandler requestHandler : listRequestHandler)
        {
            executorService.submit(requestHandler);
        }
        for (Client client : listClient)
        {
            executorService.submit(client);
        }
    }
}
