

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class parallelMultiplyMatrix {
  public static void main(String[] args) {
    double[][] list1 = new double[2000][2000];
    double[][] list2 = new double[2000][2000];
    double[][] list3 = new double[2000][2000];
    double[][] list4 = new double[2000][2000];
    for(int i = 0; i < 1999; i++) {
    	for(int k = 0; k < 1999; k++) {
    		list1[i][k]= list2[i][k] = list3[i][k] = list4[i][k] = (double)(Math.random() * 1000000);
    	}
    }
    long startTime = System.currentTimeMillis();
    normalMultiplyMatrix(list3,list4);
    long endTime = System.currentTimeMillis();
    System.out.println("Normal multiply matrix takes " + (endTime - startTime) + " milliseconds");
    
    startTime = System.currentTimeMillis();
    parallelMultiplyMatrix(list3, list4);
    endTime = System.currentTimeMillis();
    System.out.println("Parallel multiply matrix takes " + (endTime - startTime) + " milliseconds");
  }

  public static void parallelMultiplyMatrix (double[][] list1, double[][] list2) {
    RecursiveAction mainTask = new SortTask(list1,list2);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(mainTask);
  }

  private static class SortTask extends RecursiveAction {
    private double[][] list;
    private double[][] list1;
    private boolean hey=true;

    SortTask(double[][] list, double[][] list1) {
      this.list = list;
      this.list1 = list1;
      
    }

    @Override
    protected void compute() {
    	
    if(hey==true) {
    	normalMultiplyMatrix(list,list1);
    } else {
    	//System.out.println("lol");
    	int alength = list.length - (list.length / 2);
  	  int blength = list1.length - (list1.length / 2);
  	//System.out.println("lol");
  	  double[][]a1 = new double[list.length/2][list.length/2];
  	//System.out.println("lol");
  	  double[][]b1 = new double[list1.length/2][list1.length/2];
  	//System.out.println("lol");
  	  double[][]a2 = new double[alength][alength];
  //	System.out.println("lol");
	  double[][]b2 = new double[blength][blength];
	  //System.out.println("lol");
	  
  	  System.arraycopy(list, 0, 
  	          a1, 0, list.length/2);
  	  
  	//System.out.println("lo22l");
  	  
  	  System.arraycopy(list1, 0, 
  	          b1, 0, list1.length/2);
  	  
  	  System.arraycopy(list, list.length/2, 
	          a2, 0, alength);
	  
	  System.arraycopy(list1, list1.length/2, 
	          b2, 0, blength);
  	  
	  //System.out.println("lo22l");
	  
	  hey = true;
	  
	invokeAll(new SortTask(a1,b1), new SortTask(a2,b2));
  	  
    } 	
  	  
  		  }
  	  }
      
  
  public static double[][] normalMultiplyMatrix(double[][] a, double[][] b) {
	  
	  double[][] num = new double[a.length][a.length];
	  
	  for(int i = 0; i< a.length-1; i++) {
		  for(int j = 0; j<a[i].length-1; j++) {
			  num[i][j] = a[i][j] * b[i][j];
		  }
	  }
	  
	  return num;
  }
}
