package ru.job4j.pool;

import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import org.junit.Test;

public class RolColSumTest {

    @Test
    public void whenSum() {
        RolColSum.Sums[] rsl = RolColSum.sum(new int[][]{
                        {2, 2, 3},
                        {1, 4, 7},
                        {6, 2, 5}
                }
        );
        Assert.assertThat(rsl[0].getRowSum(), is(7));
        Assert.assertThat(rsl[0].getColSum(), is(9));
        Assert.assertThat(rsl[1].getColSum(), is(8));
        Assert.assertThat(rsl[1].getRowSum(), is(12));
        Assert.assertThat(rsl[2].getColSum(), is(15));
        Assert.assertThat(rsl[2].getRowSum(), is(13));
    }

    @Test
    public void whenAsyncSum() {
        RolColSum.Sums[] rsl = RolColSum.asyncSum(new int[][]{
                        {0, 2, 3},
                        {1, 0, 1},
                        {2, 1, 3}
                }
        );
        Assert.assertThat(rsl[0].getRowSum(), is(5));
        Assert.assertThat(rsl[0].getColSum(), is(3));
        Assert.assertThat(rsl[1].getColSum(), is(3));
        Assert.assertThat(rsl[1].getRowSum(), is(2));
        Assert.assertThat(rsl[2].getColSum(), is(7));
        Assert.assertThat(rsl[2].getRowSum(), is(6));
    }


}