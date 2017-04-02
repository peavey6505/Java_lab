import java.io.IOException;
import javafx.application.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main {

	public static void main(String[] args) throws InterruptedException, CrawlerException, IOException {
		// TODO Auto-generated method stub
		

		
		
		Crawler crawler = new Crawler("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");
				
	//	Crawler crawler = new Crawler("url"); //wyjatek
		
		
		crawler.addIterationStartedListener((iteration)->System.out.println("Rozpoczeto iteracje "+iteration));
		crawler.addIterationCompletedListener(iteration->System.out.println("Zakonczono iteracje "));
		crawler.addStudentAddedListener((Student)->Student.toString());
		crawler.addStudentRemovedListener((Student)->Student.toString());
		crawler.run();
		
	}

	

}
