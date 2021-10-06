import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Объявление клааса сканер, для считывания из консоли
        booleanMatrix boolean_matrix = new booleanMatrix(); // Объявление объекта класса (матрицы)

        System.out.println("""
                    ______________________________________________
                    |           Выберите номер пункта            |
                    |1.Вывести первую матрицу.                   |
                    |2.Вывести вторую матрицу.                   |
                    |3.Выполнить логическое сложение матриц.     |
                    |4.Выполнить умножение матриц.               |
                    |5.Вывести обратную матрицу.                 |
                    |6.Вывести количество единиц в матрице.      |
                    |7.Выход.                                    |
                    |____________________________________________|""");

        while (true){
            System.out.print("\nВведите номер пункта: ");
            int choice = scanner.nextInt();
            if(choice == 7){
                break;
            }
            if(choice < 1 || choice > 7){
                System.out.println("Выберите существующий пункт!");
            }
            switch (choice){
                case 1:
                    System.out.println("\nПервая матрица:\n");
                    showMatrix(boolean_matrix.getBooleanMatrix());
                    break;
                case 2:
                    System.out.println("\nВторая матрица:\n ");
                    showMatrix(boolean_matrix.getBooleanMatrix());
                    break;
                case 3:
                    System.out.println("\nРезультат логического сложения:\n ");
                    double[][] sumMatrix = Matrix.findSum(boolean_matrix.getBooleanMatrix(), boolean_matrix.getBooleanMatrix());
                    showMatrix(sumMatrix);
                    break;
                case 4:
                    System.out.println("\nРезультат умножения:\n ");
                    double[][] multiplyMatrix = Matrix.multiply(boolean_matrix.getBooleanMatrix(), boolean_matrix.getBooleanMatrix());
                    showMatrix(multiplyMatrix);
                    break;
                case 5:
                    System.out.println("\nОбратная матрица:\n ");
                    double[][] res = Matrix.inverseMatrix(boolean_matrix.getBooleanMatrix());
                    showMatrix(res);
                    break;
                case 6:
                    System.out.print("\nКоличество единиц в матрице: ");
                    Matrix.countOfUnits(boolean_matrix.getBooleanMatrix());
                    System.out.println();
                    showMatrix(boolean_matrix.getBooleanMatrix());
                    break;
                default:
                    break;
            }
        }
    }

    /*
    *
    * Реализация метода вывода матриц
    *
    */

    public static void showMatrix(double[][] matrix) {

        for (double[] doubles : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print("[" + doubles[j] + "] ");
            }
            System.out.println();
        }
    }
}
