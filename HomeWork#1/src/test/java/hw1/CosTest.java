package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*Была найдена ошибка в реализации метода библиотеки Calculator public double cos(double a).
* Метод cos(double a) библиотеки Calculator возвращает Math.sin(a), что привело к тому,
* что ни один тест для метода cos(double a) библиотеки Calculator не проходит проверку с Math.cos(alpha).
*/


public class CosTest {
    static Calculator calculator = new Calculator();

    @DataProvider
    public static Object[][] doubleCosDataProvider() {
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
    @Test(testName = "cosTestDouble",
        dataProvider = "doubleCosDataProvider")
    public void cosTestDouble(double alpha){
        Assert.assertEquals(calculator.cos(alpha), Math.cos(alpha));
    }

}
