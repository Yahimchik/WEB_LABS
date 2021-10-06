public class Matrix {
    public static double[][] inverseMatrix(double[][] firstMatrix) {
        double[][] x = new double[firstMatrix.length][firstMatrix.length];
        double[][] b = new double[firstMatrix.length][firstMatrix.length];
        int[] index = new int[firstMatrix.length];

        for (int i = 0; i < firstMatrix.length; i++) {
            b[i][i] = 1;
        }

        gauss(firstMatrix, index);

        for (int i = 0; i < firstMatrix.length - 1; i++) {
            for (int j = i + 1; j < firstMatrix.length; j++) {
                for (int k = 0; k < firstMatrix.length; k++) {
                    b[index[j]][k] -= firstMatrix[index[j]][i] * b[index[i]][k];
                }
            }
        }

        for (int i = 0; i < firstMatrix.length; ++i) {
            x[firstMatrix.length - 1][i] = b[index[firstMatrix.length - 1]][i] / firstMatrix[index[firstMatrix.length - 1]][firstMatrix.length - 1];
            for (int j = firstMatrix.length - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < firstMatrix.length; ++k) {
                    x[j][i] -= firstMatrix[index[j]][k] * x[k][i];
                }
                x[j][i] /= firstMatrix[index[j]][j];
            }
        }

        return x;
    }

    /*
     *
     *  Реализация самого метода Гаусса
     *
     */

    public static void gauss(double[][] firstMatrix, int[] index) {
        double[] result = new double[firstMatrix.length];
        for (int i = 0; i < firstMatrix.length; i++) {
            index[i] = i;
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            double temp = 0;
            for (int j = 0; j < firstMatrix.length; j++) {
                double temp1 = Math.abs(firstMatrix[i][j]);
                if (temp1 > temp) {
                    temp = temp1;
                }
            }
            result[i] = temp;
        }

        int k = 0;
        for (int j = 0; j < firstMatrix.length - 1; j++) {
            double pi1 = 0;
            for (int i = j; i < firstMatrix.length; i++) {
                double pi0 = Math.abs(firstMatrix[index[i]][i]);
                pi0 /= result[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < firstMatrix.length; i++) {
                double pj = firstMatrix[index[i]][j] / firstMatrix[index[j]][j];
                firstMatrix[index[i]][j] = pj;
                for (int l = j + 1; l < firstMatrix.length; l++) {
                    firstMatrix[index[i]][l] -= pj * firstMatrix[index[j]][l];
                }
            }
        }
    }

    /*
     *
     * Реализация метода умножения матриц
     *
     */

    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] multiplyMatrix = new double[firstMatrix.length][firstMatrix.length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix.length; j++) {
                for (int k = 0; k < firstMatrix.length; k++) {
                    multiplyMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return multiplyMatrix;
    }

    /*
     *
     * Реализация метода лошического сложения матриц (дизъюнкция)
     *
     */

    public static double[][] findSum(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] sumMatrix = new double[firstMatrix.length][firstMatrix.length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix.length; j++) {
                sumMatrix[i][j] = (int) firstMatrix[i][j] | (int) secondMatrix[i][j];
            }
        }
        return sumMatrix;
    }

    /*
     *
     * Реализация метода подсчета количества единиц в матрице
     *
     */

    public static void countOfUnits(double[][] matrix) {
        int countOfUnits = 0;
        for (double[] doubles : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                if (doubles[j] == 1) {
                    countOfUnits++;
                }
            }
        }
        System.out.print(countOfUnits);
    }
}
