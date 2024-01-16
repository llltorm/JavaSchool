import java.util.concurrent.atomic.AtomicLong;

public class BackSystem {
    private volatile AtomicLong amount;

    public BackSystem(AtomicLong amount) {
        this.amount = amount;
    }

    public void changingAmount(Request request) {
        RequestType requestType = request.getRequestType();
        if (requestType == RequestType.INCREASE) {
            increase(request);
        }
        if (requestType == RequestType.DECREASE) {
            decrease(request);
        }
    }

    private void increase(Request request) {
        var flag = false;
        while (!flag) {
            var oldAmount = amount.getAcquire();
            var increaseAmount = request.getAmount();
            var newAmount = oldAmount + increaseAmount;
            flag = amount.compareAndSet(oldAmount, newAmount);
        }
        System.out.println("Бэк система: Заявкка (" +
                request.getClientName() + ", " +
                request.getAmount() + ", " +
                request.getRequestType() + ")" +
                " успешно выполнена. Текущий баланс:" + amount);

    }

    private void decrease(Request request) {
        var flag = false;
        while (!flag) {
            var oldAmount = amount.getAcquire();
            var decreaseAmount = request.getAmount();
            var newAmount = oldAmount - decreaseAmount;
            if (newAmount < 0) {
                continue;
            }
            flag = amount.compareAndSet(oldAmount, newAmount);
        }
        System.out.println("Бэк система: Заявкка (" +
                request.getClientName() + ", " +
                request.getAmount() + ", " +
                request.getRequestType() + ")" +
                " успешно выполнена. Текущий баланс:" + amount);
    }
}
