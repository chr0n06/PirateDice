/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class LogManager {
      public static Logger setLogger(Logger logger, Level level) {
        LogFormatter.HANDLER.setFormatter(LogFormatter.FORMATTER);
        logger.setUseParentHandlers(false);
        logger.addHandler(LogFormatter.HANDLER);
        logger.setLevel(level);
        return logger;
    }
}
