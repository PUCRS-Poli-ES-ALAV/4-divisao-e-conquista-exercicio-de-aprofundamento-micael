import java.util.ArrayList;
import java.util.Random;


public class App{
    public static void main(String[] args) {
        long start; 
        long elapsedTimeMillis;
        ArrayList<Integer> lstSize32 = initialiseList(32);
        ArrayList<Integer> lstSize2048 = initialiseList(2048);
        ArrayList<Integer> lstSize1048576 = initialiseList(1048576);
        final Object[][] table = new String[5][];
        table[0] = new String[] {"","Size 1: Iterations", "Size 1: Time (s)","Size 2: Iterations", "Size 2: Time (s)","Size 3: Iterations", "Size 3: Time (s)"};
        //System.out.println("Lista pre-sort");
        //lstSize32.forEach((n) -> System.out.println(n));

        //Exercicio 1
        start = System.currentTimeMillis(); 
        Par<Integer,ArrayList<Integer>> resultMergeSort = mergeSort(lstSize32);
        int nIteration1 = resultMergeSort.getFirst();
        elapsedTimeMillis = System.currentTimeMillis()-start;
        double elapsedTimeSec1 = elapsedTimeMillis/1000.0;

        start = System.currentTimeMillis(); 
        resultMergeSort = mergeSort(lstSize2048);
        int nIteration2 = resultMergeSort.getFirst();
        elapsedTimeMillis = System.currentTimeMillis()-start;
        double elapsedTimeSec2 = elapsedTimeMillis/1000.0;

        start = System.currentTimeMillis(); 
        resultMergeSort = mergeSort(lstSize1048576);
        int nIteration3 = resultMergeSort.getFirst();
        elapsedTimeMillis = System.currentTimeMillis()-start;
        double elapsedTimeSec3 = elapsedTimeMillis/1000.0;

        table[1] = new String[] {"Merge Sort", Integer.toString(nIteration1), Double.toString(elapsedTimeSec1),Integer.toString(nIteration2), Double.toString(elapsedTimeSec2),Integer.toString(nIteration3), Double.toString(elapsedTimeSec3)};

        //ArrayList<Integer> lstSize32Sorted = resultMergeSort.getSecond(); 
        //System.out.println("Lista pos-sort");
        //lstSize32Sorted.forEach((n) -> System.out.println(n));

        


        

        for (final Object[] row : table) {
            System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", row);
        }
    }

    public static ArrayList<Integer> initialiseList(int nSize){
        ArrayList<Integer> lstTeste = new ArrayList<Integer>(nSize);
        Random rndGenerator = new Random();
        int rnd;
        for(int i = 0; i < nSize; i++){
            rnd = rndGenerator.nextInt();
            lstTeste.add(rnd);
        }
        return lstTeste;
    }

    public static Par<Integer,ArrayList<Integer>> mergeSort(ArrayList<Integer> lstSort){
        if (lstSort.size() <= 1){
            return new Par<Integer,ArrayList<Integer>>(0,lstSort);
        }
        ArrayList<Integer> lstA      = new ArrayList<Integer>();
        ArrayList<Integer> lstB      = new ArrayList<Integer>();
        ArrayList<Integer> lstSorted = new ArrayList<Integer>(lstSort.size());

        for(int i = 0; i < lstSort.size()/2; i++){
            lstA.add(lstSort.get(i));
        }

        for(int i = lstSort.size()/2; i < lstSort.size();i++){
            lstB.add(lstSort.get(i));
        }

        Par<Integer,ArrayList<Integer>> A = mergeSort(lstA);
        Par<Integer,ArrayList<Integer>> B = mergeSort(lstB);
        lstA = A.getSecond();
        lstB = B.getSecond();
        int nIteration = Math.max(A.getFirst(),B.getFirst());
        int nIndexA = 0;
        int nIndexB = 0;
        while((nIndexA < lstA.size())&&(nIndexB < lstB.size())){
            if(lstA.get(nIndexA)< lstB.get(nIndexB)){
                lstSorted.add(lstA.get(nIndexA)); 
                nIndexA++; 
            }
            else{
                lstSorted.add(lstB.get(nIndexB));  
                nIndexB++;  
            }
        }
        
        if(nIndexA < lstA.size()) {
            while (nIndexA < lstA.size()){
                lstSorted.add(lstA.get(nIndexA)); 
                nIndexA++;    
            }
        }

        if(nIndexB < lstB.size()) {
            while (nIndexB < lstB.size()){
                lstSorted.add(lstB.get(nIndexB)); 
                nIndexB++;    
            }
        }        

        return new Par<Integer,ArrayList<Integer>>(nIteration+1,lstSorted); 
    }
}