package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class CompressedLogger implements Logger{

	TextLogger textLogger = new TextLogger();
	

	@Override
	public void log(String status, Student student) throws FileNotFoundException {
		Date date = new Date();
		
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH);
		    int day = cal.get(Calendar.DAY_OF_MONTH);
		    int hour = cal.get(Calendar.HOUR);
		    int minute = cal.get(Calendar.MINUTE);
		    int second = cal.get(Calendar.SECOND);
		    int mili = cal.get(Calendar.MILLISECOND);

		    
	        String fileName = Integer.toString(year)+"."+Integer.toString(month)+"."+Integer.toString(day)+"_"
					+Integer.toString(hour)+"."+Integer.toString(minute)+"."+Integer.toString(second)+"."
					+Integer.toString(mili)+".";
	        String content = "["+status +"]\t"+ student.toString();


		Map<String, String> env = new HashMap<>(); 
		env.put("create", "true");
		Path path = Paths.get("logZip.zip");
		URI uri = URI.create("jar:" + path.toUri());
		try (FileSystem fs = FileSystems.newFileSystem(uri, env))
		{
		    Path nf = fs.getPath(fileName+".txt");
		    try (Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
		        writer.write(content);
		    } catch (IOException e) {
				System.out.println("B³¹d przy pliku.");
				System.exit(0);
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Blad przy otwieraniu archiwum");
			System.exit(0);
		}
	        

	
	}
	
	
	private static final Map<String, String> myMap;
    static
    {
        myMap = (Map<String, String>) new HashMap<String, String>();
        ((HashMap<String, String>) myMap).put("a", "b");
        ((HashMap<String, String>) myMap).put("c", "d");
    }

	
	
}
