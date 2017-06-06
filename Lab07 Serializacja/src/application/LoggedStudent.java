package application;

public class LoggedStudent extends Student {

	public Status status;
	long time;
	
	public LoggedStudent(Student student, Status status, Long time)
	{
		super(student);
		this.status = status;
		this.time = time;
		
	}
	
	public LoggedStudent()
	{
		super();
	}
	
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public long getTime()
	{
		return time;
	}
	public Status getStatus()
	{
		return status;
	}
	
	public String toString()
	{
		return   getStatus().toString()+"   " +super.toString();
	}
	
	public void setTime(Long time)
	{
		this.time = time;
	}
}
