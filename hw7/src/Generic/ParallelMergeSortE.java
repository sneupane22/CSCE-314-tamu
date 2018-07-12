package Generic;



import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSortE<E> {
	public static <E> void main(String[] args) {
	final int SIZE = 7000000;
	//double d;
    
    Integer[] genericlist1 = new Integer[SIZE];
    Integer[] genericlist2 = new Integer[SIZE];
    
   for (int i = 0; i < genericlist1.length; i++) {
	   genericlist1[i] = genericlist2[i] = Integer.valueOf((int) (Math.random() * 10000000));
   }
    long startTime = System.currentTimeMillis();
    parallelMergeSort(genericlist1); // Invoke parallel merge sort
    long endTime = System.currentTimeMillis();
    System.out.println("\nParallel time with "
      + Runtime.getRuntime().availableProcessors() + 
      " processors is " + (endTime - startTime) + " milliseconds");

    startTime = System.currentTimeMillis();
    MergeSortE.mergeSort(genericlist2); // MergeSort is in Listing 24.5
    endTime = System.currentTimeMillis();
    System.out.println("\nSequential time is " + 
      (endTime - startTime) + " milliseconds");
    
  }
  

  public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
    RecursiveAction mainTask = new SortTask(list);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(mainTask);
  }

  //@SuppressWarnings("unchecked")
  private static class SortTask<E> extends RecursiveAction {
    private final int THRESHOLD = 500;
    private E[] list;

    SortTask(E[] list) {
      this.list = list;
    }

    @Override
    protected void compute() {
      if (list.length < THRESHOLD)
        java.util.Arrays.sort(list);
      else {
        // Obtain the first half
        E[] firstHalf = (E[]) new Object[list.length/2];
        System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

        // Obtain the second half
        int secondHalfLength = list.length - list.length / 2;
        E[] secondHalf = (E[]) new Object[secondHalfLength];
        System.arraycopy(list, list.length / 2, 
          secondHalf, 0, secondHalfLength);

        // Recursively sort the two halves
        invokeAll(new SortTask(firstHalf), 
          new SortTask(secondHalf));

        // Merge firstHalf with secondHalf into list
        MergeSortE.merge(firstHalf, secondHalf, list);
      }
    }
    
  }
}
  
