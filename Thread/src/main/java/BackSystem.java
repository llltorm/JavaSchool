public class BackSystem {
    private volatile long amount;

    public BackSystem(long amount) {
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

    public synchronized void increase(Request request) {
        amount = amount + request.getAmount();
        System.out.println("Бэк система: Заявкка (" +
                request.getClientName() + ", " +
                request.getAmount() + ", " +
                request.getRequestType() + ")" +
                " успешно выполнена. Текущий баланс:" + amount);
        notifyAll();
    }

    public synchronized void decrease(Request request) {
        while (amount - request.getAmount() <= 0) {
            try {
                System.out.println("Бэк система: Заявкка (" +
                        request.getClientName() + ", " +
                        request.getAmount() + ", " +
                        request.getRequestType() + ")" +
                        " не выполнена. Сумма больше баланса. Текущий баланс:" + amount);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        amount = amount - request.getAmount();
        System.out.println("Бэк система: Заявкка (" +
                request.getClientName() + ", " +
                request.getAmount() + ", " +
                request.getRequestType() + ")" +
                " успешно выполнена. Текущий баланс:" + amount);
        notifyAll();
    }
}
