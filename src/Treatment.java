import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Treatment {
    private int size;
    private int [][] matrix;
    private int[][] matrixCopy;
    private int[][] zeroMatrix;
    private int[][] result;
    private ArrayList<Integer> strHandl = new ArrayList<Integer>();
    private ArrayList<Integer> collHandl = new ArrayList<Integer>();

    public Treatment(){
        Scanner in = new Scanner(System.in);
        System.out.println(" Введите размер ");
        size = in.nextInt();
        matrix = new int[size][size];
        matrixCopy = new int[size][size];
        zeroMatrix = new int[size][size];
        result = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matrix[i][j] = in.nextInt();
                result[i][j] = matrix[i][j];
            }
        }
    }
    /*
    Редукция матрицы по строкам(Ищем минимальный элемент в каждой строке и вычитаем его из каждого элемента).
     */
    private ArrayList<Integer> strMin(){
        int strmin;
        ArrayList<Integer> min = new ArrayList<Integer>();

        for(int i=0;i<size;i++){
            strmin = 100;
            for(int j=0;j<size;j++){
                if(matrix[i][j]!=-100) {
                    if (matrix[i][j] <= strmin) {
                        strmin = matrix[i][j];
                    }
                }
            }
            min.add(strmin);
        }


        return min;
    }

    public void strMinCalculation(){
        ArrayList<Integer> min = strMin();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j]!=-100) {
                    matrix[i][j] -= min.get(i);
                }
            }
        }
    }

    /*
    Редукция матрицы по столбцам.
     */

    private ArrayList<Integer> collMin(){
        ArrayList<Integer> min = new ArrayList<Integer>();
        int collmin;

        for(int i=0;i<size;i++){
            collmin = 100;
            for(int j=0;j<size;j++){
                if(matrix[j][i]!=-100) {
                    if (matrix[j][i] <= collmin) {
                        collmin = matrix[j][i];
                    }
                }
            }
            min.add(collmin);
        }


        return min;
    }

    public void collMinCalculation(){
        ArrayList<Integer> min = collMin();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j]!=-100) {
                    matrix[j][i] -= min.get(i);
                }
            }
        }

        matrCopyPast();
    }
    private void matrCopyPast(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matrixCopy[i][j] = matrix[i][j];
            }
        }
    }
    private void zeroMatrCopyPast(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j]==0){
                    zeroMatrix[i][j] = 0;
                }else{
                    zeroMatrix[i][j] = 1;
                }
            }
        }
    }
    /*
    Проверка, может ли расположение нулевых элементов в матрице образовать систему из size-независимых нулей
     */
    public void zeroCheck(){

        int indepZero = 0;
        int zeroCounter = 0;
        ArrayList<Integer> inStr = new ArrayList<Integer>();
        ArrayList<Integer> inColl = new ArrayList<Integer>();


        for(int i=0;i<size;i++){
            //zeroCounter = 0;
            for(int j=0;j<size;j++){
                if (matrix[i][j] == 0) {
                        if (inStr.contains(i) || inColl.contains(j)) {
                            continue;
                        } else {
                            inStr.add(i);
                            inColl.add(j);
                            indepZero++;
                        }
                }
            }
        }



        if(indepZero==size){
            System.out.println("Ура");
            HashMap<Integer,Integer> ress = ending();
            for(int i=0;i<size;i++){
                System.out.println(ress.get(i) + "  ");
            }
        }else{
            /*
            Вместо кучи зирочеков сдесь будет метод, в котором будут вызываться методы удаляющие по строке и по столбцу,
            после этого будет опять проверка на нули и если они есть метод вызывается рекурсивно опять.В качестве параметра будет создан новый
            массив, который и будет передаваться.Вычёркивать строки заменяя их значения на -100
             */
            System.out.println("По новой(((");
            afterZeroDelete();
            strMinReducedMatrix();
            strMinCalculation();
            collMinCalculation();
            displayInfo();
            zeroCheck();
        }
    }

    private HashMap<Integer,Integer> ending(){
        ArrayList<Integer> inStr = new ArrayList<Integer>();
        ArrayList<Integer> inColl = new ArrayList<Integer>();
        HashMap<Integer,Integer> ress = new HashMap<Integer,Integer>();

        for(int i=0;i<size;i++){
            //zeroCounter = 0;
            for(int j=0;j<size;j++){
                if (matrix[i][j] == 0) {
                    if (inStr.contains(i) || inColl.contains(j)) {
                        continue;
                    } else {
                        inStr.add(i);
                        inColl.add(j);
                        ress.put(i,result[i][j]);
                    }
                }
            }
        }
        return ress;
    }
    //Сокращение матрицы
    public void afterZeroDelete() {
        int str = minusZeroStr();
        int coll = minusZeroColl();
        //strHandl.add(str);
        //collHandl.add(coll);
        /*
        По нормальному нужно ретюрнить хешмап из minuszero... и сравнивать где больше нулей, от
        этого и отталкиваться в выборе.
         */
        if (str == coll) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == str) matrix[i][j] = -100;
                }
            }
            strHandl.add(str);
        } else if (str != -1 && coll != -1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == str || j == coll) matrix[i][j] = -100;
                }
            }
            strHandl.add(str);
            collHandl.add(coll);
        } else if (str != -1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == str) matrix[i][j] = -100;
                }
            }
            strHandl.add(str);
        } else if (coll != -1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (j == coll) matrix[i][j] = -100;
                }
            }
            collHandl.add(coll);
        }
        int res = zeroCheckAfterTr();
        if (res == 1) afterZeroDelete();
        else return;
    }


    public int zeroCheckAfterTr(){
        int counter = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j] == 0){
                    counter++;
                }
            }
        }
        if(counter!=0)return 1;
        else return 0;
    }

    ///////////////////////////////////////

    private int minusZeroStr(){
        HashMap<Integer,Integer> zeroIndexStr = new HashMap<Integer,Integer>();
        int zeroCountrStr = 0;
        int maxStr = -1;
        int retIndex = -1;

        //По строкам
        for(int i=0;i<size;i++){
            zeroCountrStr = 0;
            for(int j=0;j<size;j++){
                if(matrix[i][j] == 0){
                    zeroCountrStr++;
                }
            }
            if(zeroCountrStr!=0){
                zeroIndexStr.put(i,zeroCountrStr);
            }
        }

        if(!zeroIndexStr.isEmpty()){
            for(Map.Entry<Integer, Integer> e: zeroIndexStr.entrySet()){
                int i = e.getKey();
                if(zeroIndexStr.get(i)!= null){
                    if(zeroIndexStr.get(i)>maxStr){
                        maxStr = zeroIndexStr.get(i);
                        retIndex = i;
                    }
                }
            }
        }
        return retIndex;
    }

    private int minusZeroColl(){
        HashMap<Integer,Integer> zeroIndexColl = new HashMap<Integer,Integer>();
        int zeroCounterColl;
        int maxColl = -1;
        int retIndex = -1;

        //По столбцам
        for(int i = 0;i<size;i++){
            zeroCounterColl = 0;
            for(int j=0;j<size;j++){
                if(matrix[j][i]==0){
                    zeroCounterColl++;
                }
            }
            if(zeroCounterColl!=0){
                zeroIndexColl.put(i,zeroCounterColl);
            }
        }

        if(!zeroIndexColl.isEmpty()){
            for(Map.Entry<Integer, Integer> e: zeroIndexColl.entrySet()){
                int i = e.getKey();
                if(zeroIndexColl.get(i)!= null){
                    if(zeroIndexColl.get(i)>maxColl){
                        maxColl = zeroIndexColl.get(i);
                        retIndex = i;
                    }
                }
            }
        }
        return retIndex;
    }

    //Вычитание минимального элемента сокращённой матрицы

    private void strMinReducedMatrix(){
        int min = strMinCalculationAfterTr();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(strHandl.contains(i) && collHandl.contains(j)){
                    matrix[i][j] = matrixCopy[i][j];
                    matrix[i][j] += min;
                }
            }
        }

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j]==-100){
                    matrix[i][j] = matrixCopy[i][j];
                }
            }
        }
    }



    //Вычисление минимального элемента сокращённой матрицы
    private int strMinAfterTr(){
        int strmin = 100;

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j] <= strmin && matrix[i][j]!=-100) {
                    strmin = matrix[i][j];
                }
            }
        }

        return strmin;
    }

    private int strMinCalculationAfterTr(){
        int min = strMinAfterTr();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(matrix[i][j]!=-100) {
                    matrix[i][j] -= min;
                }
            }
        }
        return min;
    }
    //Геттер для матрицы
    public int[][] getMatrix(){
        return matrix;
    }

    //Вывод матрицы на голубые экраны
    public void displayInfo(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(matrix[i][j] + "       ");
            }
            System.out.println();
        }
    }
    public void displayInfo2(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(matrixCopy[i][j] + "       ");
            }
            System.out.println();
        }
    }
}
