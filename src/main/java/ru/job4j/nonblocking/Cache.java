package ru.job4j.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {

        return memory.computeIfPresent(model.getId(),
                (i, b) -> {
                    if (b.getVersion() != model.getVersion()) {
                        throw new OptimisticException("У сравниваемых моделей не равны версии");
                    }
                    Base rsl = new Base(model.getId(), model.getVersion() + 1);
                    rsl.setName(model.getName());
                    return rsl;
                }
        ) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}

