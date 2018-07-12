import java.io.IOException;
import java.util.Scanner;



public class PartTwo {

    private static Scanner scan;

	public static void main(String[] args) throws IOException {
    	int a = 0;
        int b = 0;
        int bb =0;
        String c = "";
        String d = "";
        String e = "";
        String f = "";
        Double s = 0.0;
        int day,month,year;
    	scan = new Scanner(System.in);
        boolean goBack = false;
        
        Person person = null;
        Student student = null;
        Employee employee = null;
        Faculty faculty = null;
        Staff staff = null;
        
        System.out.println("What day is it? (1-31): ");
		day = scan.nextInt();
		System.out.println("What month is it? (1-12): ");
		month = scan.nextInt();
		System.out.println("What year is it?: ");
		year = scan.nextInt();
		MyDate mydate = new MyDate(year,month,day);
		
    	while(!goBack) {
    		
	        System.out.println("1-Student");
	        System.out.println("2-Employee");
	        System.out.println("3-Person");
	        System.out.println("4-View");
	        System.out.println("Anything else - Leave program");
	        System.out.println();
	        
	        a = scan.nextInt();
	        
	        if(a==1) {
	        	System.out.println("Student - Do you want to vi1ew or add information?");
	        	System.out.println("1-Add");
	        	System.out.println("Anything else - Back to main menu");
	        	System.out.println();
	        	b = scan.nextInt();
	        	
	        	if(b==1) {
		        	System.out.println("Name of student: ");
		        	c = scan.next();
		        	//System.in.read();
		        	System.out.println("Class: ");
		        	d = scan.next();
		        	//System.in.read();
		        	
	        		student = new Student(c,d);
	        	}
	        } else if (a==2) {
	        	System.out.println("Employee");
	        	System.out.println("1-Faculty");
	        	System.out.println("2-Staff");
	        	System.out.println("3-Add");
	        	System.out.println("Anything else - Back to main menu");
	        	System.out.println();
	        	
	        	b=scan.nextInt();
	        	
	        	if(b==1) {
	        		System.out.println("Faculty - Do you want to view or add information?");
		        	System.out.println("1-Add");
		        	System.out.println("Anything else - Back to main menu");
		        	System.out.println();
		        	
		        	bb=scan.nextInt();
		        	
		        	if(bb==1) {
			        	System.out.println("Name of Faculty: ");
			        	c = scan.next();
			        	//System.in.read();
			        	System.out.println("Hours: ");
			        	d = scan.next();
			        	System.out.println("Class: ");
			        	e = scan.next();
			        	
			        	faculty = new Faculty(c,d,e);
			        	//System.in.read();
		        	}
			        	
	        	} else if(b==2) {
	        		System.out.println("Staff - Add information?");
		        	System.out.println("1-Add");
		        	System.out.println("Anything else - Back to main menu");
		        	System.out.println();
		        	
		        	bb=scan.nextInt();
		        	
		        	if(bb==1) {
			        	System.out.println("Name of Staff: ");
			        	c = scan.next();
			        	//System.in.read();
			        	System.out.println("Title: ");
			        	d = scan.next();
			        	//System.in.read();
			        	
			        	staff = new Staff(c,d);
			        	
		        	}
	        		
	        	} else if(b==3) {
	        		System.out.println("Name of Employee: ");
		        	c = scan.next();
		        	//System.in.read();
		        	System.out.println("Salary: ");
		        	s = scan.nextDouble();
		        	System.out.println("Office: ");
		        	d = scan.next();
		        	//System.in.read();
		        	
		        	employee = new Employee(c,s,d,mydate);
	        	}
	        	
	        	
	        } else if (a==3) {
	        	System.out.println("Person - Add information?");
	        	System.out.println("1-Add");
	        	System.out.println("Anything else - Back to main menu");
	        	System.out.println();
	        	
	        	b=scan.nextInt();
	        	
	        	if(b==1) {
		        	System.out.println("Name of Person: ");
		        	c = scan.next();
		        	//System.in.read();
		        	System.out.println("Address: ");
		        	d = scan.next();
		        	System.out.println("Phone Number: ");
		        	e = scan.next();
		        	System.out.println("Email Address");
		        	f = scan.next();
		        	
		        	
		        	person = new Person(c);
		        	person.setAddress(d);
		        	person.setPhoneNumber(e);
		        	person.setEmail(f);
		        	
	        	}
	        } else if (a==4) {
	        	try {
	        		System.out.println(person.toString());
	        		System.out.println();
	        	} catch (NullPointerException npe) {
	        	}
	        	
	        	try {
	        		System.out.println(student.toString());
	        		System.out.println();
	        	} catch (NullPointerException npe) {
	        	}
	        	
	        	try {
	        		System.out.println(employee.toString());
	        		System.out.println();
	        	} catch (NullPointerException npe) {
	        	}
	        	
	        	try {
	        		System.out.println(faculty.toString());
	        		System.out.println();
	        	} catch (NullPointerException npe) {
	        	}
	        	
	        	try {
	        		System.out.println(staff.toString());
	        		System.out.println();
	        	} catch (NullPointerException npe) {
	        	}
	            
	        } else {
	        	System.exit(0);
	        }
    	}
    }
    
    


}