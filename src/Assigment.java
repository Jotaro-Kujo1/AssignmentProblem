import java.util.*;

public class Assigment {
    private int [][] matrix = new int[4][4];
    private int sizeX = 4;
    private int sizeY = 4;

    public Assigment(){
        matrix[0][0] = 2;
        matrix[0][1] = 10;
        matrix[0][2] = 9;
        matrix[0][3] = 7;

        matrix[1][0] = 15;
        matrix[1][1] = 4;
        matrix[1][2] = 14;
        matrix[1][3] = 8;

        matrix[2][0] = 13;
        matrix[2][1] = 14;
        matrix[2][2] = 16;
        matrix[2][3] = 11;

        matrix[3][0] = 4;
        matrix[3][1] = 15;
        matrix[3][2] = 13;
        matrix[3][3] = 19;
    }

    /*
    Первый шаг. Находим max элемент в каждой строке, его же и вычитаем из каждого элемента этой строки и
    умножаем всю матрицу на -1.
     */

    private ArrayList<Integer> strMax(){
        int maxFirstString = 0;
        int maxSecondString = 0;
        int maxThirdString = 0;
        int maxFourString = 0;
        ArrayList<Integer> max = new ArrayList<Integer>();

        for(int i=0;i<1;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j]>= maxFirstString){
                    maxFirstString = matrix[i][j];
                }
            }
        }

        for(int i=1;i<2;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j]>= maxSecondString){
                    maxSecondString = matrix[i][j];
                }
            }
        }

        for(int i=2;i<3;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j]>= maxThirdString){
                    maxThirdString = matrix[i][j];
                }
            }
        }

        for(int i=3;i<4;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j]>= maxFourString){
                    maxFourString = matrix[i][j];
                }
            }
        }

        max.add(maxFirstString);
        max.add(maxSecondString);
        max.add(maxThirdString);
        max.add(maxFourString);

        System.out.println(maxFirstString);
        System.out.println(maxSecondString);
        System.out.println(maxThirdString);
        System.out.println(maxFourString);

        return max;
    }

    public void strMaxCalculation(){
        ArrayList<Integer> max = strMax();

        for(int i=0;i<1;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = (matrix[i][j] - max.get(0)) * -1;
            }
        }

        for(int i=1;i<2;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = (matrix[i][j] - max.get(1)) * -1;
            }
        }

        for(int i=2;i<3;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = (matrix[i][j] - max.get(2)) * -1;
            }
        }

        for(int i=3;i<4;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = (matrix[i][j] - max.get(3)) * -1;
            }
        }
    }

    /*
    В каждой строке и в каждом столбце должен быть только один выбранный ноль.
    (т.е. когда выбрали ноль, то остальные нули в этой строке или в этом столбце уже не берем в расчет).
    В этом случае это сделать невозможно
    */

    /*
    Второй шаг. Редукция матрицы по строкам(Ищем минимальный элемент в каждой строке и вычитаем его из каждого элемента).
     */

    private ArrayList<Integer> strMin(){
        int minFirstString = 100;
        int minSecondString = 100;
        int minThirdString = 100;
        int minFourString = 100;
        ArrayList<Integer> min = new ArrayList<Integer>();

        for(int i=0;i<1;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j] <= minFirstString){
                    minFirstString = matrix[i][j];
                }
            }
        }

        for(int i=1;i<2;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j] <= minSecondString){
                    minSecondString = matrix[i][j];
                }
            }
        }

        for(int i=2;i<3;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j] <= minThirdString){
                    minThirdString = matrix[i][j];
                }
            }
        }

        for(int i=3;i<4;i++){
            for(int j=0;j<sizeY;j++){
                if(matrix[i][j] <= minFourString){
                    minFourString = matrix[i][j];
                }
            }
        }

        min.add(minFirstString);
        min.add(minSecondString);
        min.add(minThirdString);
        min.add(minFourString);

        System.out.println(minFirstString);
        System.out.println(minSecondString);
        System.out.println(minThirdString);
        System.out.println(minFourString);

        return min;
    }

    public void strMinCalculation(){
        ArrayList<Integer> min = strMin();

        for(int i=0;i<1;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = matrix[i][j] - min.get(0);
            }
        }

        for(int i=1;i<2;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = matrix[i][j] - min.get(1);
            }
        }

        for(int i=2;i<3;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = matrix[i][j] - min.get(2);
            }
        }

        for(int i=3;i<4;i++){
            for(int j=0;j<sizeY;j++){
                matrix[i][j] = matrix[i][j] - min.get(3);
            }
        }
    }

    /*
    Проводим редукцию по столбцам
     */

    private ArrayList<Integer> collMin(){
        ArrayList<Integer> min = new ArrayList<Integer>();
        int minFirstCollumn = 100;
        int minSecondCollumn = 100;
        int minThirdCollumn = 100;
        int minFourCollumn = 100;

        for(int i=0;i<sizeX;i++){
            for(int j=0;j<1;j++){
                if(matrix[i][j] <= minFirstCollumn){
                    minFirstCollumn = matrix[i][j];
                }
            }
        }
        for(int i=0;i<sizeX;i++){
            for(int j=1;j<2;j++){
                if(matrix[i][j] <= minSecondCollumn){
                    minSecondCollumn = matrix[i][j];
                }
            }
        }
        for(int i=0;i<sizeX;i++){
            for(int j=2;j<3;j++){
                if(matrix[i][j] <= minThirdCollumn){
                    minThirdCollumn = matrix[i][j];
                }
            }
        }
        for(int i=0;i<sizeX;i++){
            for(int j=3;j<4;j++){
                if(matrix[i][j] <= minFourCollumn){
                    minFourCollumn = matrix[i][j];
                }
            }
        }

        min.add(minFirstCollumn);
        min.add(minSecondCollumn);
        min.add(minThirdCollumn);
        min.add(minFourCollumn);

        System.out.println("minFirstCollumn:" + minFirstCollumn);
        System.out.println("minSecondCollumn:" + minSecondCollumn);
        System.out.println("minThirdCollumn:" + minThirdCollumn);
        System.out.println("minFourCollumn:" + minFourCollumn);

        return min;
    }

    public void collMinCalculation(){
        ArrayList<Integer> min = collMin();

        for(int i=0;i<sizeX;i++){
            for(int j=0;j<1;j++){
                matrix[i][j] = matrix[i][j] - min.get(0);
            }
        }
        for(int i=0;i<sizeX;i++){
            for(int j=1;j<2;j++){
                matrix[i][j] = matrix[i][j] - min.get(1);
            }
        }
        for(int i=0;i<sizeX;i++){
            for(int j=2;j<3;j++){
                matrix[i][j] = matrix[i][j] - min.get(2);
            }
        }
        for(int i=0;i<sizeX;i++){
            for(int j=3;j<4;j++){
                matrix[i][j] = matrix[i][j] - min.get(3);
            }
        }
    }

    public int[][] getMatrix(){
        return matrix;
    }

    public void printMatrix(){
        for(int i=0;i<sizeX;i++){
            for(int j=0;j<sizeY;j++){
                System.out.print(matrix[i][j] + "     ");
            }
            System.out.println();
        }
    }
}
