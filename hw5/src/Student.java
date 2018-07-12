

public class Student extends Person {

    protected String status;

    public Student(String name, String status) {
        super(name);
        this.status = status;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    public String getStatus() {
    	return this.status;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + " Class: " + getStatus();
    }
}


