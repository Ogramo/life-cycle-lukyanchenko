package entity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LifeCycleStarter {

    ExecutorService service;

    public LifeCycleStarter() {
        service = Executors.newSingleThreadExecutor();
        service.submit(GameField.getInstance());
    }
}
