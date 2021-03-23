package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DivTest {
    static Calculator calculator = new Calculator();

    @DataProvider
    public static Object[][] doubleDivDataProvider() {
        return new Object[][]{
                {0.0, 0.0},
                {0.09, 0.5},
                {90.0, 3.0},
                {78.0, 0.0},
                {-12.0, 0.0},
                {0.05, 0.001},
                {120.0, 2450.0}
        };
    }
    @DataProvider
    public static Object[][] longDivDataProvider() {
        return new Object[][] {
                {1L, -8L},
                {14L, 14L},
                {0L, 123L},
                {105L, 25L},
                {-50L, -9L},
                {-210L, 1L}
        };
    }

    @Test(testName = "divDoubleTest",
        dataProvider = "doubleDivDataProvider")
    public void divDoubleTest(double a, double b){
        Assert.assertEquals(calculator.div(a, b), a/b);
    }

    @Test(testName = "divLongTest",
        dataProvider = "longDivDataProvider")
    public void divLongTest(long a, long b){
        Assert.assertEquals(calculator.div(a, b), a/b);
    }
}
