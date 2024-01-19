package be.intecbrussel.sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IceCreamCarTest {

    @ParameterizedTest
    @MethodSource("testOrderConeFactory")
    void testOrderCone(int ballPrice, int stockCones, int stockBalls, Cone.Flavor[] flavors, int expectedBallStock, int expectedConeStock, double expectedProfit) {
        PriceList priceList = new PriceList(ballPrice,2,4);
        Stock stock = new Stock(5,stockCones,stockBalls,5);
        IceCreamCar iceCreamCar = new IceCreamCar(priceList, stock);

        //#1 Test if ordercone return cone object
        Cone cone = null;
        if (flavors != null && flavors.length != 0){
            boolean containsNullValue = false;
            for (Cone.Flavor f : flavors){
                if (f == null)
                    containsNullValue = true;
            }
            if (! containsNullValue) {
                cone = iceCreamCar.orderCone(flavors);
                if (stockCones > 0 && stockBalls >= flavors.length)
                    Assertions.assertNotNull(cone);
            }
        }
        else
            Assertions.assertNull(cone);


        //#2 Test if stock balls correct
        Assertions.assertEquals(expectedBallStock, stock.getBalls());

        //#3 Test if stock cones correct
        Assertions.assertEquals(expectedConeStock, stock.getCones());

        //#4 Test if profit correct
        Assertions.assertEquals(expectedProfit, iceCreamCar.getProfit());
    }
    static Stream<Arguments> testOrderConeFactory(){
        return Stream.of(
                Arguments.of(1, 1, 1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 0, 0.25),  //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(0, 1, 1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 0, 0.0),   //priceList.getBallPrice() * balls.length * 0.25 = 0 because price is 0
                Arguments.of(1, 0, 1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 1, 0, 0.0),   //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of cones is empty
                Arguments.of(1, 1, 0,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 1, 0.0),   //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of balls is empty
                Arguments.of(1, 0, 0,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 0, 0.0),   //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of cones&balls is empty
                Arguments.of(0, 0, 0,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 0, 0.0),   //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of cones&balls is empty
                Arguments.of(-5, 0, 0, new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 0, 0.0),   //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of cones&balls is empty
                Arguments.of(-5, 1, 1, new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0, 0, -1.25), //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(1, 1, 2,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, 0, 0, 0.5),    //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(1, 0, 2,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, 2, 0, 0.0),    //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of cones is empty
                Arguments.of(1, 1, 1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, 1, 1, 0.0),    //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of balls is not enough for 2 balls
                Arguments.of(1, -1, -1,new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, -1, -1, 0.0),  //priceList.getBallPrice() * balls.length * 0.25 = 0 because stock of balls&cones is empty
                Arguments.of(1, 1, 2,  null, 2, 1, 0.0),                                                                //test null array
                Arguments.of(1, 1, 2,  new Cone.Flavor[]{}, 2, 1, 0.0),                                                 //test empty array
                Arguments.of(1, 1, 2,  new Cone.Flavor[]{null}, 2, 1, 0.0),                                             //test null value
                Arguments.of(1, 1, 2,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, null}, 2, 1, 0.0)                       //test null value
                );
    }

    @ParameterizedTest
    @MethodSource("testOrderIceRocketFactory")
    void testOrderIceRocket(int iceRocketsPrice, int iceRocketsStock, int expectedIceRocketsStock, double expectedProfit) {
        PriceList priceList = new PriceList(1,iceRocketsPrice,4);
        Stock stock = new Stock(iceRocketsStock,5,5,5);
        IceCreamCar iceCreamCar = new IceCreamCar(priceList, stock);

        //#1 Test if orderIceRocket return icerocket object
        IceRocket iceRocket = iceCreamCar.orderIceRocket();
        if (iceRocketsStock>0)
            Assertions.assertNotNull(iceRocket);
        else
            Assertions.assertNull(iceRocket);

        //#2 Test if stock icerocket correct
        Assertions.assertEquals(expectedIceRocketsStock, stock.getIceRockets());

        //#3 Test if profit correct
        Assertions.assertEquals(expectedProfit, iceCreamCar.getProfit());
    }
    static Stream<Arguments> testOrderIceRocketFactory(){
        return Stream.of(
                Arguments.of(2, 1, 0, 0.4),  //priceList.getRocketPrice() * 0.20
                Arguments.of(0, 1, 0, 0.0),  //priceList.getRocketPrice() * 0.20 = 0 because stock is empty
                Arguments.of(2, 2, 1, 0.4),  //priceList.getRocketPrice() * 0.20
                Arguments.of(-5, 1, 0, -1),  //priceList.getRocketPrice() * 0.20
                Arguments.of(0, 0, 0, 0.0),  //priceList.getRocketPrice() * 0.20 = 0 because stock is empty
                Arguments.of(2, -2, -2, 0.0) //priceList.getRocketPrice() * 0.20 = 0 because stock is empty -2
        );
    }

    @ParameterizedTest
    @MethodSource("testOrderMagnumFactory")
    void testOrderMagnum(Magnum.MagnumType magnumType, int magnumStanddardPrice, int magnumStock, int expectedMagnumStock, double expectedProfit) {
        PriceList priceList = new PriceList(1,2,magnumStanddardPrice);
        Stock stock = new Stock(5,5,5,magnumStock);
        IceCreamCar iceCreamCar = new IceCreamCar(priceList, stock);

        //#1 Test if orderMagnum return magnum object
        Magnum magnum = null;
        if (magnumStock>0 && magnumType!= null){
            magnum = iceCreamCar.orderMagnum(magnumType);
            Assertions.assertNotNull(magnum);
        }
        else
            Assertions.assertNull(magnum);

        //#2 Test if stock magnum correct
        Assertions.assertEquals(expectedMagnumStock, stock.getMagni());

        //#3 Test if profit correct
        BigDecimal resultProfit = new BigDecimal(iceCreamCar.getProfit());
        BigDecimal roundedResult = resultProfit.setScale(3, BigDecimal.ROUND_HALF_UP);
        Assertions.assertEquals(expectedProfit, roundedResult.doubleValue());
    }
    static Stream<Arguments> testOrderMagnumFactory(){
        return Stream.of(
                Arguments.of(Magnum.MagnumType.ALPINENUTS, 4, 1, 0, 0.06),             //magnumStandardPrice * 1.5 * 0.01
                Arguments.of(Magnum.MagnumType.MILKCHOCOLATE, 4, 1, 0, 0.044),         //magnumStandardPrice * 1.1 * 0.01
                Arguments.of(Magnum.MagnumType.WHITECHOCOLATE, 4, 1, 0, 0.056),        //magnumStandardPrice * 1.4 * 0.01
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, 4, 1, 0, 0.08),   //magnumStandardPrice * 2 * 0.01
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, 4, 0, 0, 0.0),    //magnumStandardPrice * 2 * 0.01 = 0 because empty stock
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, 4, -1, -1, 0.0),  //magnumStandardPrice * 2 * 0.01 = 0 because empty stock
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, -4, 1, 0, -0.08), //magnumStandardPrice * 2 * 0.01
                Arguments.of(null, 4, 1, 1, 0.0)                                       //try null value
        );
    }
}