package functions;
import functions.meta.*;

public class Functions {
    private Functions(){} // приватный конструктор чтобы нельзя было создать экземпляр

    public static Function shift(Function f, double shiftX, double shiftY){
        return new Shift(f,shiftX, shiftY); // создаем функцию сдвига
    }

    public static Function scale(Function f, double scaleX, double scaleY){
        return new Scale(f,scaleX,scaleY); // создаем функцию масштабирования
    }
    public static Function power(Function f, double power){
        return new Power(f, power); // создаем функцию возведения в степень
    }

    public static Function sum(Function f1, Function f2){
        return new Sum(f1, f2); // создаем функцию суммы
    }

    public static Function mult(Function f1, Function f2){
        return new Mult(f1, f2); // создаем функцию произведения
    }

    public static Function composition(Function f1, Function f2){
        return new Composition(f1, f2); // создаем композицию функций
    }
}