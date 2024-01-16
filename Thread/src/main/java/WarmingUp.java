import java.util.concurrent.Callable;

public class WarmingUp implements Callable<Void> {
    private final String name;
    private final BackSystem backSystem;
    private final int amount;
    private final int timeout;

    public WarmingUp(String name, BackSystem backSystem, int amount, int timeout) {
        this.name = name;
        this.backSystem = backSystem;
        this.amount = amount;
        this.timeout = timeout;
    }

    @Override
    public Void call() throws Exception {
        try {
            System.out.println("Прогрев {" + name + " " + amount + " " + timeout + "}");
            Thread.sleep(timeout);
            backSystem.changingAmount(new Request(name, amount, RequestType.INCREASE));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
