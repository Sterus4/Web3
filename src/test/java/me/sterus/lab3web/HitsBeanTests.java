package me.sterus.lab3web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

public class HitsBeanTests {

    @Test
    public void testHit2quarter(){
        var r = 4d;
        for(double x = -1.9; x < 0; x += 0.1){
            for (double y = 0; y < x + 2; y += 0.1){
                assertTrue(Checker.check(x, y, r));
            }
        }
    }
    @Test
    public void testHit3quarter(){
        var r = 4d;
        for(double x = -3.9; x < 0; x += 0.1){
            for (double y = -1.9; y < 0; y += 0.1){
                assertTrue(Checker.check(x, y, r));
            }
        }
    }
    @Test
    public void testHit4quarter(){
        var r = 4d;
        for(double x = 0.1; x < 1.9; x += 0.1){
            for (double y = -sqrt(4 - x * x) + 0.1; y < 0; y += 0.1){
                assertTrue(Checker.check(x, y, r));
            }
        }
    }
    @Test
    public void testNoHit1quarter(){
        var r = 1d;
        for(double x = 0.1; x < 5; x += 0.1){
            for (double y = 0.1; y < 5; y += 0.1){
                assertFalse(Checker.check(x, y, r));
            }
        }
    }
    @Test
    public void testNoHit2quarter(){
        var r = 4d;
        for(double x = -5; x < 0; x += 0.1){
            for (double y = max(0.1, x + 2) + 0.1; y < 5; y += 0.1){
                assertFalse(Checker.check(x, y, r));
            }
        }
    }
    @Test
    public void testNoHit3quarter(){
        var r = 4d;
        for(double x = -5; x < -4; x += 0.1){
            for (double y = -5; y < 0; y += 0.1){
                assertFalse(Checker.check(x, y, r));
            }
        }
        for(double x = -5; x < 0; x += 0.1){
            for (double y = -5; y < -2; y += 0.1){
                assertFalse(Checker.check(x, y, r));
            }
        }
    }
    @Test
    public void testNoHit4quarter(){
        var r = 4d;
        for(double x =0.1; x < 54; x += 0.1){
            for (double y = -5; y < min(-sqrt(4 - x * x), -0.1); y += 0.1){
                assertFalse(Checker.check(x, y, r));
            }
        }
    }

    @Test
    public void testEdges1quarter(){
        var r = 4d;
        var y = 0d;
        for(double x = 2.1; x < 5; x += 0.1){
            assertFalse(Checker.check(x, y, r));
        }
        var x = 0d;
        for(y = 2.1; y < 5; y += 0.1){
            assertFalse(Checker.check(x, y, r));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, 2",
            "0, 1",
            "-2, 0",
            "-1, 1",
    })
    public void testEdges2quarter(double x, double y){
        assertTrue(Checker.check(x, y, 4));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-4, 0",
            "0, 0",
            "-2, 0",
            "-4, -1",
            "-4, -2",
            "-2, -2",
            "0, -2",
            "0, -1"
    })
    public void testEdges3quarter(double x, double y){
        assertTrue(Checker.check(x, y, 4));
    }
    @ParameterizedTest
    @CsvSource(value = {
            "2, 0",
            "0, -2",
            "1, 0",
            "0, -1",
            "1, -1",
            "1.4, -1.4"
    })
    public void testEdges4quarter(double x, double y){
        assertTrue(Checker.check(x, y, 4));
    }

    @Test
    public void negativeRadius(){
        assertThrows(ArithmeticException.class, ()-> {
            Checker.check(1, 1, -1);
        });
    }

    @Test
    public void notANumber(){
        assertThrows(ArithmeticException.class, ()-> {
            Checker.check(Double.NaN, 1, -1);
        });
        assertThrows(ArithmeticException.class, ()-> {
            Checker.check(0.5, 1, sqrt(-1));
        });
    }

    @Test
    public void infinities(){
        assertTrue(Checker.check(-1, -1, Double.POSITIVE_INFINITY));
        assertFalse(Checker.check(1, 1, Double.POSITIVE_INFINITY));
        assertThrows(ArithmeticException.class, ()->{
            assertTrue(Checker.check(-1, -1, Double.NEGATIVE_INFINITY));
        });
        assertThrows(ArithmeticException.class, ()->{
            assertTrue(Checker.check(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,1));
        });
    }
}
