public class Main {
    public static void main(String[] args){
        /*
        Assigment assigment = new Assigment();
        assigment.strMaxCalculation();
        assigment.strMinCalculation();
        assigment.collMinCalculation();


        int [][] matrix = new int[4][4];
        matrix = assigment.getMatrix();
        assigment.printMatrix();
         */

        Treatment tr = new Treatment();
        tr.displayInfo();
        System.out.println();
        tr.strMinCalculation();
        tr.displayInfo();
        System.out.println();
        tr.collMinCalculation();
        tr.displayInfo();
        tr.zeroCheck();
        //tr.displayInfo();
        System.out.println();
        //tr.strMinReducedMatrix();
        //tr.displayInfo2();

    }
}
