import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;




public class GameShow extends Thread{

	public Scanner scan = new Scanner(System.in);
	public String answer ;
	public String realanswer = "na";
	public String question;
	public int num;
	public boolean event = false;
	public boolean restart = false;
	public int points = 0;
	public final Runnable typepq;
	public final Runnable typert;
	public final Runnable typett;
	public Timer timer = new Timer();
	public static GameShow gs;
	//private final GameShow monitor = new GameShow();
	
	public GameShow() {
		typepq = new Runnable() {
			public void run() {
				try {
					GameShow.this.pq();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            
		};
            typert = new Runnable() {
            	
            	public void run() {
            		try {
						GameShow.this.rt();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            };  
            
            typett = new Runnable(){
           	 
    			@Override
    			public void run() {
    				try {
						GameShow.this.tt();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
            	 
            };
       
        
        
	}
	public void pq() throws InterruptedException{
		synchronized(this) {
			while(true) {
			event = false;
        	System.out.println(randomQuestion((int) (Math.random() * 5)));
            while(!event){
                wait();
            }
            if(realanswer.equals(answer)) {
            	points++;
            	System.out.println("Good Job. You have " + points + " points.");
            } else {
            	System.out.println("Try again, answer was " + answer);
            }
            //System.out.println("test1");
            //notifyAll();
			}
		    //Thread.interrupt();
			
		}
       
      
        
    }

    public void rt() throws InterruptedException{
    	synchronized(this) {
    		while(true) {
        	realanswer = scan.nextLine();
        	notifyAll();
        	event= true;
    		//System.out.println("test2");
    		wait();
    		
    		}
    		
    	}
       
    }
    
    public void tt() throws InterruptedException{
    	synchronized(this) {
   	 		while(true) {
   	 			//Thread.sleep(10000);
   	 			wait();
   	 			//System.out.println("Restarting... Or Time Ran out");
   	 			notifyAll();
   	 		}
   	 	
   	 		
    	}
   	 	
    }
    
    
    
    public static void main(String[] args) {
    	gs = new GameShow();
    	new Thread(gs.typepq).start();
    	new Thread(gs.typert).start();
    	new Thread(gs.typett).start();
    	
    	
        
    }
    
    public String randomQuestion(int x) {
    	if(x == 1) {
    		answer = "austin";
    		return "What is the capital of Texas";
    	} else if(x==2) {
    		answer = "ankara";
    		return "What is the capital of Turkey";
    	} else if (x==3) {
    		answer = "washington";
    		return "What is the capital of the US";
    	} else if(x==4) {
    		answer = "madrid";
    		return "What is the capital of Spain";
    	} else {
    		answer = "berlin";
    		return "What is the capital of Germany";
    		
    	}
    	
    }
}














