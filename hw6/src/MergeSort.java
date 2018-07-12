import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;




public class MergeSort {
	
	private static Scanner scan;
	public static ArrayList<String> array = new ArrayList<String>();
	
	
	
	public static void startMSort(Comparable[] arr)
	{
		// Begin MSort with swap method
		// Create new Comparable
		Comparable[] temp = new Comparable[arr.length];
		swap(arr, temp,  0,  arr.length - 1);
	}

	
	private static void swap(Comparable[] arr, Comparable[] temp, int left, int right)
	{
		// Find out if number is needed for swap
		if(left<right) {
			int middle = ((left+right)/2);
			swap(arr, temp, left, middle);
			swap(arr, temp, (middle+1), right );
			mSort(arr, temp, left, (middle + 1), right);
		}
	}


    private static void mSort(Comparable[] arr, Comparable[] temp, int left, int right, int rs )
    {
        int ls = right-1;
        int i = left;
        int val = rs -left +1;
        //int temporary = rs + ls;

        while(left <= ls && right <= rs) {
            if(arr[left].compareTo(arr[right]) <= 0) {
                temp[i++] = arr[left++];
            } else {
                temp[i++] = arr[right++];
            }
        }

        
        while(left <= ls) {    
            temp[i++] = arr[left++];
        }

        while(right <= rs) { 
            temp[i++] = arr[right++];
        }

        for(int x = 0; x < val; x++, rs--)
            arr[rs] = temp[rs];
    }
	
	public static void main(String args[]){
		String line;
		// MergeSort ms = new MergeSort();
		scan = new Scanner(System.in);
		System.out.println("Running MergeSort.java.....");
		System.out.println("Keep Entering Strings. Enter 'stop' to stop reading strings");
		while(!(line =scan.nextLine()).equals("stop"))
			array.add(line);
		String[] arrayOfStrings = array.toArray(new String[array.size()]);
		
		startMSort(arrayOfStrings);
		System.out.println(Arrays.toString(arrayOfStrings));
	}

}