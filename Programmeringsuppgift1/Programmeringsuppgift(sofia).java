package Programeringsuppgift;

public class Programmeringsuppgift {
    private static final int M = 1000;

    public void mergeSort(byte[] array, int lo, int hi) {
        if(lo < hi) {
        
        	//Byt till insertionsort för små delar eftersm att det är snabbare
          
        	if (hi - lo <= M) {
                p.insertionSort(array, lo, hi);
              
                //Kolla om hi inte är större än lo.
                //Annars forstsätt med att sätta mid till mittpunkten.
                
            } else {
                int mid = (lo + hi) / 2;
                mergeSort(array, lo, mid); 			//Kör mergesort rekursivt på delen från lo till mid
                mergeSort(array, mid + 1, hi);	    //Kör mergesort rekursivt på delen från mid+1 till hi
                merge(array, lo, mid, hi); 		    // Nu har vi två sorterade halvor. Mergea dem.
            }
        }
    }

    public void merge(byte[] array, int lo, int mid, int hi) {
        
    	//Kopiera över allt - från position lo till hi - till en extra array
        
    	byte[] extra = new byte[array.length];
        for(int i = lo; i <= hi; i ++ ){
            extra[i] = array[i];
        }
     
        
        //Låt i börja på lo(början på den vänstra)
        //och j på mid+1(början på den högra)
         
      
        int j = mid + 1;
        int i = lo;
        int position = lo;
       
        
        //Så länge i eller j har tagit slut
        //flytta det mindre elementet till resultatet
         
        
        while (i <= mid && j <= hi) {
            if(extra[i] <= extra[j]){
                array[position] = extra[i];
                i++;

            }else{
                array[position] = extra[j];
                j++;
            }
            position ++;
        }
       
        // Kopiera vad som finns kvar 
       
        int remaining = mid - i;
        for (int k = 0; k <= remaining; k++) {
            array[position+k] = extra[i + k];
        }
    }
}
