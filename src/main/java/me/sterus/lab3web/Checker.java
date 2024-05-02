package me.sterus.lab3web;

public class Checker {
    public static boolean check(double x, double y, double r){
        if(r <= 0){
            throw new ArithmeticException("Радиус должен быть положительным");
        }
        if(Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(r)){
            throw new ArithmeticException("Числа не должны быть NaN (Поняли да, типа числа должны быть числа, смекаете?)");
        }
        if(Double.isInfinite(x) || Double.isInfinite(y)){
            throw new ArithmeticException("Координаты должны быть конечными числами");
        }
        return (x <= 0 && y >= 0 && y <= x + r/2) || (x <= 0 && y <= 0 && x >= -r && y >= -r/2) || (x >= 0 && y <= 0 && x * x + y * y <= r * r / 4);
    }
}
