package ru.job4j.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        Base exp = memory.get(model.getId());
        if (exp.getVersion() == model.getVersion()) {
            model.setName("Super-puper");
            return memory.replace(model.getId(), new Base(model.getId(), model.getVersion() + 1)) != null;
        } else {
            throw new OptimisticException("У сравниваемых моделей не равны версии");
        }
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    static class OptimisticException extends Throwable {

        public OptimisticException(String string) {
            super(string);
        }
    }
}