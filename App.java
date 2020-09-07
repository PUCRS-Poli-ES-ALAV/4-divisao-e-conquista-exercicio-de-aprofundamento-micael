import java.util.ArrayList;
import java.util.Random;


public class App{
    public static void main(String[] args) {
        long start; 
        long elapsedTimeMillis1;
        long elapsedTimeMillis2;
        long elapsedTimeMillis3;
        ArrayList<Integer> lstSize32 = initialiseList(32);
        ArrayList<Integer> lstSize2048 = initialiseList(2048);
        ArrayList<Integer> lstSize1048576 = initialiseList(1048576);
        final Object[][] table = new String[5][];
        table[0] = new String[] {"","Size 1: Iterations", "Size 1: Time (ms)","Size 2: Iterations", "Size 2: Time (ms)","Size 3: Iterations", "Size 3: Time (ms)"};
        //System.out.println("Lista pre-sort");
        //lstSize32.forEach((n) -> System.out.println(n));

        //Exercicio 1
        start = System.currentTimeMillis(); 
        Par<Integer,ArrayList<Integer>> resultMergeSort = mergeSort(lstSize32);
        int nIteration1 = resultMergeSort.getFirst();
        elapsedTimeMillis1 = System.currentTimeMillis()-start;


        start = System.currentTimeMillis(); 
        resultMergeSort = mergeSort(lstSize2048);
        int nIteration2 = resultMergeSort.getFirst();
        elapsedTimeMillis2 = System.currentTimeMillis()-start;

        start = System.currentTimeMillis(); 
        resultMergeSort = mergeSort(lstSize1048576);
        int nIteration3 = resultMergeSort.getFirst();
        elapsedTimeMillis3 = System.currentTimeMillis()-start;

        table[1] = new String[] {"Merge Sort", Integer.toString(nIteration1), Long.toString(elapsedTimeMillis1),Integer.toString(nIteration2), Double.toString(elapsedTimeMillis2),Integer.toString(nIteration3), Double.toString(elapsedTimeMillis3)};


        //Exercicio 2
        start = System.currentTimeMillis(); 
        Par<Integer,Integer> resultMaxVal1 = maxVal1(lstSize32);
        nIteration1 = resultMaxVal1.getFirst();
        elapsedTimeMillis1 = System.currentTimeMillis()-start;

        start = System.currentTimeMillis(); 
        resultMaxVal1 = maxVal1(lstSize2048);
        nIteration2 = resultMaxVal1.getFirst();
        elapsedTimeMillis2 = System.currentTimeMillis()-start;

        start = System.currentTimeMillis(); 
        resultMaxVal1 = maxVal1(lstSize1048576);
        nIteration3 = resultMaxVal1.getFirst();
        elapsedTimeMillis3 = System.currentTimeMillis()-start;

        table[2] = new String[] {"Max Value 1", Integer.toString(nIteration1), Long.toString(elapsedTimeMillis1),Integer.toString(nIteration2), Long.toString(elapsedTimeMillis2),Integer.toString(nIteration3), Long.toString(elapsedTimeMillis3)};
        
        //Exercicio 3
        start = System.currentTimeMillis(); 
        Par<Integer,Integer> resultMaxVal2 = maxVal2(lstSize32, 0, lstSize32.size()-1);
        nIteration1 = resultMaxVal2.getFirst();
        elapsedTimeMillis1 = System.currentTimeMillis()-start;

        start = System.currentTimeMillis(); 
        resultMaxVal2 = maxVal2(lstSize2048, 0, lstSize2048.size()-1);
        nIteration2 = resultMaxVal2.getFirst();
        elapsedTimeMillis2 = System.currentTimeMillis()-start;

        start = System.currentTimeMillis(); 
        resultMaxVal2 = maxVal2(lstSize1048576, 0, lstSize1048576.size()-1);
        nIteration3 = resultMaxVal2.getFirst();
        elapsedTimeMillis3 = System.currentTimeMillis()-start;

        table[3] = new String[] {"Max Value 2", Integer.toString(nIteration1), Long.toString(elapsedTimeMillis1),Integer.toString(nIteration2), Long.toString(elapsedTimeMillis2),Integer.toString(nIteration3), Long.toString(elapsedTimeMillis3)};
        

        

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
        int nIteration = Math.max(A.getFirst(),B.getFirst())+1;
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

        return new Par<Integer,ArrayList<Integer>>(nIteration,lstSorted); 
    }

    public static Par<Integer,Integer> maxVal1(ArrayList<Integer> lstMax1){
        int max = lstMax1.get(0);
        int nIteration = 0;

        for(int i = 1; i < lstMax1.size(); i++){
            if(lstMax1.get(i)> max){
                max = lstMax1.get(i);
            }
            nIteration++;
        }

        return new Par<Integer,Integer>(nIteration, max);
    }

    public static Par<Integer,Integer> maxVal2(ArrayList<Integer> lstMax2, int nStart, int nEnd){
        if((nEnd -nStart) <=1){
            return new Par<Integer,Integer>(1, Math.max(lstMax2.get(nStart), lstMax2.get(nEnd)));
        }
        else{
            int nMid = (nStart+nEnd)/2;
            Par<Integer,Integer> v1 = maxVal2(lstMax2, nStart, nMid);
            Par<Integer,Integer> v2 = maxVal2(lstMax2, nMid+1, nEnd);

            int nIteration = Math.max(v1.getFirst(),v2.getFirst())+1;
            int nMax       = Math.max(v1.getSecond(), v2.getSecond());
            return new Par<Integer,Integer>(nIteration, nMax);
        }
    }
}