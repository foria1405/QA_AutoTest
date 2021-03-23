package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MulTest {
    static Calculator calculator = new Calculator();

    @DataProvider
    public static Object[][] doubleMulDataProvider() {
        return new Object[][]{
                {0.2, 0.9},
                {8.0, 3.0},
                {-7.0, 0.3},
                {0.078, 3.0},
                {-.02, 300.0},
                {-10.0, -0.08},
                {0.0, -8500.0}

        };
    }

    @DataProvider
    public static Object[][] longMulDataProvider() {
        return new Object[][]{
                {6L, 4L},
                {-7L, 6L},
                {-12L, -6L},
                {1001L, 0L},
                {-0L, 800L},
                {1402L, 123L},

        };
    }

    @Test(testName = "mulDoubleTest",
            dataProvider = "doubleMulDataProvider")
    public void mulDoubleTest(double a, double b){
        Assert.assertEquals(calculator.mult(a, b), Math.floor(a*b));
    }
    @Test(testName = "mulLongTest",
        dataProvider = "longMulDataProvider")
    public void mulLongTest(long a, long b){
        Assert.assertEquals(calculator.mult(a, b), a*b);
    }
}
