package functions;
import java.io.*;

public class TabulatedFunctions {
    private TabulatedFunctions(){} // приватный конструктор чтобы нельзя было создать экземпляр

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if(function == null){
            throw new IllegalArgumentException("функция не может быть null"); // проверяем что функция не null
        }
        if(pointsCount < 2){
            throw new IllegalArgumentException("количество точек должно быть не менее 2"); // проверяем минимальное количество точек
        }
        if(leftX >= rightX){
            throw new IllegalArgumentException("левая граница должна быть меньше правой"); // проверяем порядок границ
        }
        if(leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder()){
            throw new IllegalArgumentException("границы табулирования выходят за область определения"); // проверяем что границы в области определения
        }

        double vals[] = new double[pointsCount]; // создаем массив для значений y
        double step = (rightX - leftX) / (pointsCount - 1); // вычисляем шаг между точками

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step; // вычисляем x координату
            vals[i] = function.getFunctionValue(x); // вычисляем значение функции в точке x
        }
        return new ArrayTabulatedFunction(leftX, rightX, vals); // создаем табулированную функцию
    }
    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException, IllegalArgumentException{
        if (function == null) {
            throw new IllegalArgumentException("функция не может быть null"); // проверяем что функция не null
        }
        if (out == null) {
            throw new IllegalArgumentException("выходной поток не может быть null"); // проверяем что поток не null
        }

        DataOutputStream dataOut = new DataOutputStream(out); // создаем поток для записи данных

        // записываем количество точек
        int pointsCount = function.getPointsCount(); // получаем количество точек
        dataOut.writeInt(pointsCount); // записываем количество точек

        // записываем координаты всех точек (x, y)
        for (int i = 0; i < pointsCount; i++) {
            double x = function.getPointX(i); // получаем x координату точки
            double y = function.getPointY(i); // получаем y координату точки

            dataOut.writeDouble(x); // записываем x координату
            dataOut.writeDouble(y); // записываем y координату
        }

        // сбрасываем буфер чтобы убедиться что данные записаны
        dataOut.flush(); // сбрасываем буфер
    }
    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException{
        if (in == null) {
            throw new IllegalArgumentException("входной поток не может быть null"); // проверяем что поток не null
        }
        DataInputStream dataIn = new DataInputStream(in); // создаем поток для чтения данных

        // считываем количество точек
        int pointsCount = dataIn.readInt(); // читаем количество точек

        // создаем массив точек
        FunctionPoint[] points = new FunctionPoint[pointsCount]; // создаем массив для точек

        // считываем и создаем точки
        for (int i = 0; i < pointsCount; i++) {
            double x = dataIn.readDouble(); // читаем x координату
            double y = dataIn.readDouble(); // читаем y координату
            points[i] = new FunctionPoint(x, y); // создаем новую точку
        }

        // используем конструктор с массивом точек
        return new ArrayTabulatedFunction(points); // создаем табулированную функцию из массива точек
    }
    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException {
        if (function == null || out == null) {
            throw new IllegalArgumentException("функция и поток не могут быть null"); // проверяем что функция и поток не null
        }

        PrintWriter writer = new PrintWriter(out); // создаем писатель для текстового вывода

        int pointsCount = function.getPointsCount(); // получаем количество точек
        writer.print(pointsCount); // записываем количество точек
        writer.print(" "); // записываем пробел

        for (int i = 0; i < pointsCount; i++) {
            writer.print(function.getPointX(i)); // записываем x координату
            writer.print(" "); // записываем пробел
            writer.print(function.getPointY(i)); // записываем y координату
            if (i < pointsCount - 1) {
                writer.print(" "); // записываем пробел между точками
            }
        }

        writer.flush(); // сбрасываем буфер
    }
    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException {

        if (in == null) {
            throw new IllegalArgumentException("поток не может быть null"); // проверяем что поток не null
        }

        StreamTokenizer tokenizer = new StreamTokenizer(in); // создаем токенизатор для чтения
        tokenizer.parseNumbers(); // обрабатывать числа как double

        // читаем количество точек
        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            throw new IOException("ожидалось количество точек"); // проверяем что следующий токен число
        }
        int pointsCount = (int) tokenizer.nval; // получаем количество точек

        // читаем координаты точек
        FunctionPoint[] points = new FunctionPoint[pointsCount]; // создаем массив для точек

        for (int i = 0; i < pointsCount; i++) {
            if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
                throw new IOException("ожидалась координата x"); // проверяем что следующий токен число
            }
            double x = tokenizer.nval; // получаем x координату

            if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
                throw new IOException("ожидалась координата y"); // проверяем что следующий токен число
            }
            double y = tokenizer.nval; // получаем y координату

            points[i] = new FunctionPoint(x, y); // создаем новую точку
        }

        return new ArrayTabulatedFunction(points); // создаем табулированную функцию из массива точек
    }
}