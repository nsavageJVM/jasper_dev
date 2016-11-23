package jasper.dev;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class JaspDev  implements CommandLineRunner {

	@Qualifier("jaspercontroller")
	@Autowired
	private JasperController jasperController;

	public static void main(String[] args) {
		SpringApplication.run(JaspDev.class, args);
	}

	@Override
	public void run(String... args) {

		try {

			jasperController.runJasperChartFlow();

		} catch (IOException | SQLException | JRException e)  {
			e.printStackTrace();
		}
	}


	public static final Date truncateToMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		return calendar.getTime();
	}



}
