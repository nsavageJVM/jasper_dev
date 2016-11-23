package jasper.dev.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by ubu on 23.11.16.
 */
public interface InitEnvironment {

    Logger logger = LoggerFactory.getLogger("AppConfig");

     String OUT_DIR = "chartpdf";
     String IN_DIR = "jasper/charts";

    default void createReportOutputDirectory() {

        File file = new File(OUT_DIR);
        if (!file.exists()) {
            if (file.mkdir()) {
                logger.debug("OUT Directory is created!");
            } else {
                logger.debug("Failed to create OUT directory!");
            }
        }
    }
}
