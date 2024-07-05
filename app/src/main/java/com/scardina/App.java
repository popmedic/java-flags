/*
 * This source file was generated by the Gradle 'init' task
 */
package com.scardina;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {
    public static void main(String[] args) {
        Options opts = new Options()
        .addOption("registered", false, null)
        .addOption("userDisabled", false, null)
        .addOption("suspended", false, null)
        .addOption("wifi", false, null)
        .addOption("airlytics", false, null)
        .addOption("sim", false, null)
        .addOption("eventsLogging", false, null)
        .addOption("networkCount", true, null)
        .addOption("networkSuggestedCount", true, null)
        .addOption("batteryPercentage", true, null)
        .addOption("value", true, null);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(opts, args);
            Flags flags = null;
            if (line.hasOption("value")) {
                flags = new Flags(Integer.parseInt(line.getOptionValue("value", "0")));
            } else {
                flags = new Flags(
                    line.hasOption("registered"), 
                    line.hasOption("userDisabled"), 
                    line.hasOption("suspended"), 
                    line.hasOption("wifi"),
                    line.hasOption("airlytics"), 
                    line.hasOption("sim"), 
                    line.hasOption("eventsLogging"), 
                    Integer.parseInt(line.getOptionValue("networkCount", "0")), 
                    Integer.parseInt(line.getOptionValue("networkSuggestedCount", "0")), 
                    Integer.parseInt(line.getOptionValue("batteryPercentage", "0"))
                );
            }
            System.out.println("Value: "+flags.getValue());
            System.out.println("JSON: "+flags.toJSON());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Error e) {
            System.err.println(e.getMessage());
        }
    }
}
