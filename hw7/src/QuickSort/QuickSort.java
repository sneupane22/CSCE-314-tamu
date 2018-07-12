package QuickSort;

public class QuickSort { 
    private static int arr[];
 
    public static void sort(int[] array) {
        
    	// This is what was messing me up!!!!
        if (array == null || array.length == 0) {
            return;
        }
        arr = array;
        quickSort(0, arr.length-1);
    }
 
    private static void quickSort(int bottom, int top) {
         
        int x = bottom;
        int y = top;
        //int k = midIndex;
        int middle = arr[bottom+(top-bottom)/2];
        while (x<=y) {
            
            while (arr[x]< middle) {
                x++;
            }
            
            while (arr[y]>middle) {
                y--;
            }
            
            if (x <= y) {
                swap(x, y);
                x++;
                y--;
            }
        }
        
        if (bottom < y) {
            quickSort(bottom, y);
        }
        
        if (x < top) {
            quickSort(x, top);
        }
    }
    
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
          if (list1[current1] < list2[current2])
            temp[current3++] = list1[current1++];
          else
            temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
          temp[current3++] = list1[current1++];

        while (current2 < list2.length)
          temp[current3++] = list2[current2++];
      }
 
    private static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
     
}