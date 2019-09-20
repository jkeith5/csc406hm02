import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.*;

public class Main {//each of the trios below hold the total amount of both data and print chars.
    public static int finData=0;
    public static int finPrint=0;
    public static int marData=0;
    public static int marPrint=0;
    public static int proData=0;
    public static int proPrint=0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        char[] branchArray= {'P','P','P','F','F','F','M','M','M'};
        int[] portArray= {1,2,3,1,2,3,1,2,3};
        char[] typeArray= {'D', 'D','P','P','D','P','P','D','P'};
        int[] numberOfChar= {60000,75000,100000,30000,150000,89000,200000,140000,135000};

        for (int i=0;i<9;i++){
            executorService.execute(new Job(branchArray[i],portArray[i],typeArray[i],numberOfChar[i]));
            System.out.println("Launching and creating thread: "+ i);
        }
        executorService.shutdown();
        while (!executorService.isTerminated());
        System.out.println("\t\t---Marketing Cost and Amount Processed---");
        System.out.printf("Number of data characters processed: %d \t\t Cost of processing: %7.2f\n" +
                "Number of print characters processed: %d \t\t Cost of processing: %7.2f\n",marData, marData*.0082,marPrint,marPrint*.0095);
        System.out.println("\t\t---Financial Cost and Amount Processed---");
        System.out.printf("Number of data characters processed: %d \t\t Cost of processing: %7.2f\n" +
                "Number of print characters processed: %d \t\t Cost of processing: %7.2f\n",finData, finData*.007,finPrint,finPrint*.009);
        System.out.println("\t\t---Production Cost and Amount Processed---");
        System.out.printf("Number of data characters processed: %d \t\t Cost of processing: %7.2f\n" +
                "Number of print characters processed: %d \t\t Cost of processing: %7.2f\n",proData, proData*.008,proPrint,proPrint*.007);
    }
}

class Job implements Runnable{

    private Character branch;
    private int port;
    private char type;
    private int numberPrint;
    private static Lock lock = new ReentrantLock();

    public Job(char branch, int port, char type, int numberofChars){
        this.branch=branch;
        this.port=port;
        this.type=type;
        this.numberPrint=numberofChars;
    }

    public void run() {
        if (this.branch.equals('F')){//finiancial branch
            lock.lock();
            switch (port){
                case 1:
                    for (int i=0; i<this.numberPrint; i++){
                        Main.finPrint++;
                    }
                    break;
                case 2:
                    for (int i=0; i<this.numberPrint;i++){
                        Main.finData++;
                    }
                    break;
                case 3:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.finPrint++;
                    }
                    break;
            }
            lock.unlock();
        }
        if (this.branch.equals('M')){//marketing
            lock.lock();
            switch (port){
                case 1:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.marPrint++;
                    }
                    break;
                case 2:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.marData++;
                    }
                    break;
                case 3:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.marPrint++;
                    }
                    break;
            }
            lock.unlock();
        }
        if (this.branch.equals('P')){//production
            lock.lock();
            switch (port){
                case 1:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.proData++;
                    }
                    break;
                case 2:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.proData++;
                    }
                    break;
                case 3:
                    for (int i=0; i< this.numberPrint; i++){
                        Main.proPrint++;
                    }
                    break;
            }
            lock.unlock();
        }
    }
}