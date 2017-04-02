

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class MailLogger implements Logger{

	SendMail s = new SendMail();

	@Override
	public void log(String status, Student student) {
		String subject = "Crawler notification (" + status + " person)";
		String text = "Student " +student + "- was";
		if(status == "ADDED")
			text +=" added to a list.";
		else if(status == "REMOVED")
			text += " removed from a list.";
		
		s.Send(subject, text);
	
	}
	

}
