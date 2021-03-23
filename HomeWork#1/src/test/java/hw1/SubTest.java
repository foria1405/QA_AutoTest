package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubTest {
    static Calculator calculator = new Calculator();

    @DataProvider
    public static Object[][] doubleSubDataProvider() {
        return new Object[][]{
                {8.0, 3.0},
                {0.8, -0.8},
                {55.0, 114.0},
                {110.3, 78.2},
                {0.009, 0.009},
                {1.0, -0.0003},
                {-90.00008, 0.00004},
                {30000000.0, 1000000.0}

        };
    }

    @DataProvider
    public static Object[][] longSubDataProvider() {
        return new Object[][]{
                {8L, -8L},
                {0L, 45L},
                {36L, 15L},
                {35L, 42L},
                {-72L, 72L},
                {345L, -345L},
                {-456L, -789L},
                {1234L, 12341L}
        };

    }

    @Test(testName = "subDoubleTest",
            dataProvider = "doubleSubDataProvider")
    public void subDoubleTest(double a, double b) {
        Assert.assertEquals(calculator.sub(a, b), a - b);
    }

    @Test(testName = "subLongTest",
            dataProvider = "longSubDataProvider")
    public void subLongTest(long a, long b) {
        Assert.assertEquals(calculator.sub(a, b), a - b);
    }
}

