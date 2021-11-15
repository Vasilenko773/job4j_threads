package ru.job4j.nonblocking;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.jcip.User;

import static org.hamcrest.core.Is.is;

public class CASCountTest {

    @Test
    public void CASCountLogic() {
        CASCount casCount = new CASCount(0);

        casCount.increment();
        casCount.get();
        casCount.increment();

        Assert.assertThat(casCount.get(), is(2));
    }

    @Test
    public void CASCountLogicTwo() {
        CASCount casCount = new CASCount(2);

        casCount.increment();
        casCount.get();
        casCount.increment();
        casCount.get();
        casCount.increment();
        casCount.get();
        casCount.increment();
        casCount.get();
        casCount.increment();

        Assert.assertThat(casCount.get(), is(7));
    }

    @Test
    public void CASCountLogicTree() {
        CASCount casCount = new CASCount(0);

        casCount.increment();
        casCount.increment();
        casCount.increment();

        Assert.assertThat(casCount.get(), is(3));
    }

}