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

class IceCreamSalonTest {


    @ParameterizedTest
    @MethodSource("testOrderConeFactory")
    void testOrderCone(int ballPrice, Cone.Flavor[] flavors, double expectedProfit) {
        PriceList priceList = new PriceList(ballPrice,2,4);
        IceCreamSalon iceCreamSalon = new IceCreamSalon(priceList);

        //#1 Test if ordercone return cone object
        if(flavors instanceof Cone.Flavor[] && flavors.length!=0){
            boolean containNullValue = false;
            for(Cone.Flavor f: flavors){
                if (f == null)
                    containNullValue = true;
            }
            if(!containNullValue){
                Cone cone = iceCreamSalon.orderCone(flavors);
                Assertions.assertNotNull(cone);
            }
        }

        //#2 Test if profit correct
        Assertions.assertEquals(expectedProfit, iceCreamSalon.getProfit());
    }
    static Stream<Arguments> testOrderConeFactory(){
        return Stream.of(
                Arguments.of(1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0.25),                           //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(0,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0.0),                            //priceList.getBallPrice() * balls.length * 0.25 = 0 because price is 0
                Arguments.of(-5, new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, -1.25),                          //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, 0.5),    //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(0,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, 0.0),    //priceList.getBallPrice() * balls.length * 0.25 = 0 because price is 0
                Arguments.of(-1, new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.STRAWBERRY}, -0.5),   //priceList.getBallPrice() * balls.length * 0.25
                Arguments.of(1,  new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, null}, 0.0),                      //test null value
                Arguments.of(-1, new Cone.Flavor[]{}, 0.0),                                                 //test empty array
                Arguments.of(1,  new Cone.Flavor[]{null}, 0.0),                                             //test null value
                Arguments.of(1,  null, 0.0)                                                                 //test null array
        );
    }

    @ParameterizedTest
    @MethodSource("testOrderIceRocketFactory")
    void testOrderIceRocket(int iceRocketsPrice, double expectedProfit) {
        PriceList priceList = new PriceList(1,iceRocketsPrice,4);
        IceCreamSalon iceCreamSalon = new IceCreamSalon(priceList);

        //#1 Test if orderIceRocket return icerocket object
        IceRocket iceRocket = iceCreamSalon.orderIceRocket();
        Assertions.assertNotNull(iceRocket);

        //#3 Test if profit correct
        Assertions.assertEquals(expectedProfit, iceCreamSalon.getProfit());
    }
    static Stream<Arguments> testOrderIceRocketFactory(){
        return Stream.of(
                Arguments.of(2, 0.4),  //priceList.getRocketPrice() * 0.20
                Arguments.of(0, 0.0),  //priceList.getRocketPrice() * 0.20 = 0 because stock is empty
                Arguments.of(-5, -1)   //priceList.getRocketPrice() * 0.20
        );
    }

    @ParameterizedTest
    @MethodSource("testOrderMagnumFactory")
    void testOrderMagnum(Magnum.MagnumType magnumType, int magnumStanddardPrice, double expectedProfit) {
        PriceList priceList = new PriceList(1,2,magnumStanddardPrice);
        IceCreamSalon iceCreamSalon = new IceCreamSalon(priceList);

        //#1 Test if orderMagnum return magnum object
        Magnum magnum = null;
        if (magnumType!= null){
            magnum = iceCreamSalon.orderMagnum(magnumType);
            Assertions.assertNotNull(magnum);
        }
        else
            Assertions.assertNull(magnum);

        //#3 Test if profit correct
        BigDecimal resultProfit = new BigDecimal(iceCreamSalon.getProfit());
        BigDecimal roundedResult = resultProfit.setScale(3, BigDecimal.ROUND_HALF_UP);
        Assertions.assertEquals(expectedProfit, roundedResult.doubleValue());
    }
    static Stream<Arguments> testOrderMagnumFactory(){
        return Stream.of(
                Arguments.of(Magnum.MagnumType.ALPINENUTS, 4, 0.06),            //magnumStandardPrice * 1.5 * 0.01
                Arguments.of(Magnum.MagnumType.MILKCHOCOLATE, 4, 0.044),        //magnumStandardPrice * 1.1 * 0.01
                Arguments.of(Magnum.MagnumType.WHITECHOCOLATE, 4, 0.056),       //magnumStandardPrice * 1.4 * 0.01
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, 4, 0.08),  //magnumStandardPrice * 2 * 0.01
                Arguments.of(null, 4, 0.0)                                      //test null
        );
    }
}