import functions.*;

public class Main {
    public static void main(String[] args) {
        // создаем тестовые точки
        FunctionPoint[] points1 = {new FunctionPoint(0.0, 1.0), new FunctionPoint(1.0, 3.0), new FunctionPoint(2.0, 5.0)};
        FunctionPoint[] points2 = {new FunctionPoint(0.0, 1.0), new FunctionPoint(1.0, 3.0), new FunctionPoint(2.0, 5.0)};
        FunctionPoint[] points3 = {new FunctionPoint(0.0, 1.0), new FunctionPoint(1.0, 3.5),new FunctionPoint(2.0, 5.0)}; // отличается y

        // тестирование toString()
        System.out.println("=== toString() ===");
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);
        LinkedListTabulatedFunction listFunc1 = new LinkedListTabulatedFunction(points1);

        System.out.println("Array: " + arrayFunc1.toString());
        System.out.println("LinkedList: " + listFunc1.toString());
        System.out.println();

        // тестирование equals()
        System.out.println("=== equals() ===");
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points2);
        LinkedListTabulatedFunction listFunc2 = new LinkedListTabulatedFunction(points2);
        ArrayTabulatedFunction arrayFunc3 = new ArrayTabulatedFunction(points3);

        System.out.println("array1 == array2: " + arrayFunc1.equals(arrayFunc2)); // true
        System.out.println("array1 == list1: " + arrayFunc1.equals(listFunc1));   // true
        System.out.println("array1 == array3: " + arrayFunc1.equals(arrayFunc3)); // false
        System.out.println("list1 == list2: " + listFunc1.equals(listFunc2));     // true
        System.out.println();

        // тестирование hashCode()
        System.out.println("=== hashCode() ===");
        System.out.println("array1 hashCode: " + arrayFunc1.hashCode());
        System.out.println("array2 hashCode: " + arrayFunc2.hashCode());
        System.out.println("list1 hashCode: " + listFunc1.hashCode());
        System.out.println("list2 hashCode: " + listFunc2.hashCode());
        System.out.println("array3 hashCode: " + arrayFunc3.hashCode());

        // проверка согласованности equals и hashCode
        System.out.println("hashCode equals check:");
        System.out.println("array1.hash == array2.hash: " + (arrayFunc1.hashCode() == arrayFunc2.hashCode()));
        System.out.println("array1.hash == list1.hash: " + (arrayFunc1.hashCode() == listFunc1.hashCode()));
        System.out.println();

        // изменение объекта и проверка изменения хэша
        System.out.println("=== hashCode() after modification ===");
        int originalHash = arrayFunc1.hashCode();
        arrayFunc1.setPointY(1, 3.001); // незначительное изменение
        int modifiedHash = arrayFunc1.hashCode();
        System.out.println("Original hash: " + originalHash);
        System.out.println("Modified hash: " + modifiedHash);
        System.out.println("Hash changed: " + (originalHash != modifiedHash));
        System.out.println();

        // тестирование clone()
        System.out.println("=== clone() ===");
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc1.clone();
        LinkedListTabulatedFunction listClone = (LinkedListTabulatedFunction) listFunc1.clone();

        System.out.println("Array original: " + arrayFunc1.toString());
        System.out.println("Array clone: " + arrayClone.toString());
        System.out.println("LinkedList original: " + listFunc1.toString());
        System.out.println("LinkedList clone: " + listClone.toString());

        // проверка глубокого клонирования
        System.out.println("=== Deep clone test ===");
        arrayFunc1.setPointY(0, 500); // изменяем оригинал
        listFunc1.setPointY(0, 676);  // изменяем оригинал

        System.out.println("After modification:");
        System.out.println("Array original: " + arrayFunc1.toString());
        System.out.println("Array clone: " + arrayClone.toString()); // не должен измениться
        System.out.println("LinkedList original: " + listFunc1.toString());
        System.out.println("LinkedList clone: " + listClone.toString()); // не должен измениться

        System.out.println("Array clone unchanged: " + (arrayClone.getPointY(0) != 500));
        System.out.println("LinkedList clone unchanged: " + (listClone.getPointY(0) != 676));

    }
}