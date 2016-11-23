package jasper.dev;

import jasper.dev.config.InitEnvironment;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by ubu on 23.11.16.
 */
@Qualifier("jaspercontroller")
@Component
public class JasperController implements InitEnvironment {



    @Autowired
    private DataSource dataSource;

    @PostConstruct
    void init() {
        createReportOutputDirectory();
    }

    public void runJasperChartFlow() throws IOException, SQLException, JRException {
        Resource  res = new ClassPathResource(IN_DIR);

        System.out.println(res.getURL().getPath());

        File[] files = getFiles( res);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("MaxOrderID", new Integer(12500));

        Connection conn =  dataSource.getConnection();
        System.out.println(conn.getCatalog());
        for(int i = 0; i < files.length; i++) {
            File reportFile = files[i];
            System.out.println(reportFile.getName());
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, String.format("%s.%s", String.format("chartpdf/%s", reportFile.getName()), "pdf" ));

        }


    }


    protected File[] getFiles(Resource  res ) throws IOException {
        List<File> fileList = new ArrayList<>();

        //uses try-with-resources pattern ensures stream will be closed.
        try(Stream<Path> paths = Files.walk(Paths.get(res.getURL().getPath()))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {

                    fileList.add(filePath.toFile());
                }
            });
        }
        return  fileList.toArray(new File[fileList.size()]);
    }



}
