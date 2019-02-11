/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

import Settings.Preferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author Maxime
 */
public class LogFormatter extends Formatter {

    public static ConsoleHandler HANDLER = new ConsoleHandler();
    public static Formatter FORMATTER = new LogFormatter();

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();

        builder.append(getTime(record));
        builder.append(getClassName(record));
        builder.append(getLevel(record));
        builder.append(getMessage(record));

        Object[] params = record.getParameters();

        if (params != null) {
            builder.append("\t");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i < params.length - 1) {
                    builder.append(", ");
                }
            }
        }

        builder.append(Preferences.ANSI_RESET);
        builder.append("\n");
        return builder.toString();
    }

    private String getTime(LogRecord record) {
        return textColorTerminal(record) + "[" + calcDate(record.getMillis()) + "]" + Preferences.ANSI_RESET;
    }

    private String getClassName(LogRecord record) {
        return textColorTerminal(record) + " [" + record.getSourceClassName() + "]" + Preferences.ANSI_RESET;
    }

    private String getLevel(LogRecord record) {
        return textColorTerminal(record) + " [" + record.getLevel().getName() + "]: " + Preferences.ANSI_RESET;
    }

    private String getMessage(LogRecord record) {
        return Preferences.ANSI_WHITE + record.getMessage() + Preferences.ANSI_RESET;
    }
    
    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    private String textColorTerminal(LogRecord record) {
        String color = Preferences.ANSI_WHITE;
        switch (record.getLevel().getName()) {
            case "INFO":
                color = Preferences.ANSI_GREEN;
                break;
            case "SEVERE":
                color = Preferences.ANSI_RED;
                break;
            case "WARNING":
                color = Preferences.ANSI_YELLOW;
                break;
            default:
                color = Preferences.ANSI_WHITE;
                break;
        }//switch
        return color;
    }//textColorTerminal

}
