package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class SumTest {
    static Calculator calculator = new Calculator();

    @DataProvider
    public static Object[][] doubleSumDataProvider(){
        return new Object[][]{
                {4.2, 0.9},
                {0.4,  0.1},
                {7.003, 3.007},
                {-45.0, -58.0},
                {1002.0, 2003.0},
                {-25.0003, -16.001},
                {-0.000078, 0.000097}

        };
    }

    @DataProvider
    public static Object[][] longSumDataProvider(){
        return new Object[][]{
                {2L, 4L},
                {74L, 12L},
                {1001L, 0L},
                {-0L, 800L},
                {-88L, -44},
                {100L, -55L},
                {1402L, 123L}
        };
    }

    @Test(testName = "sumDoubleTest",
        dataProvider = "doubleSumDataProvider")
    public void sumDoubleTest(double a, double b){
        Assert.assertEquals(calculator.sum(a, b), a+b);
    }
    @Test(testName = "sumLongTest",
        dataProvider = "longSumDataProvider")
    public void sumLongTest(long a, long b){
        Assert.assertEquals(calculator.sum(a, b), a+b);
    }

}
