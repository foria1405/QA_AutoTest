package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SinTest {
    static Calculator calculator = new Calculator();

    @DataProvider
    public static Object[][] doubleSinDataProvider() {
        return new Object[][]{
                {0.0},
                {1.0},
                {34.2},
                {-8.3},
                {90.0},
                {270.0},
                {Math.PI / 2},
                {(3 * Math.PI) / 2},
                {Double.MAX_VALUE},
                {Double.MIN_VALUE}
        };
    }
    @Test(testName = "sinTestDouble",
            dataProvider = "doubleSinDataProvider")
    public void sinTestDouble(double alpha){
        Assert.assertEquals(calculator.sin(alpha), Math.sin(alpha));
    }
}
