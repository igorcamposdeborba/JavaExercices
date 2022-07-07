package application;

import model.entities.EmptyString;
import model.entities.OutboundIntervalNumber;
import model.entities.Validate;
import model.student.Student;

public class Program {
	public static void main (String [] args) {
		
		try {
		Student student = new Student("Igor", -7);
		
		Validate.student(student);
		
		} catch (EmptyString e) { // Personalized exception
			System.err.println("Error: " + e.getMessage()); // Show only error message with getMessage
		} catch (OutboundIntervalNumber e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		System.out.println("End");
		
	}
}
