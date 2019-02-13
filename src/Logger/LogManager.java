/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

import Settings.Preferences;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class LogManager {
      public static Logger setLogger(Logger logger) {
        LogFormatter.HANDLER.setFormatter(LogFormatter.FORMATTER);
        logger.setUseParentHandlers(false);
        logger.addHandler(LogFormatter.HANDLER);
        logger.setLevel(Preferences.LOGGER_LEVEL);
        return logger;
    }
}
