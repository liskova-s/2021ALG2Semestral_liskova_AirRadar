/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.LKPRRadarSector;
import java.io.BufferedReader;                         //essential for test main()
import java.io.File;                                   //essential for test main()
import java.io.FileReader;                             //essential for test main()
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utils.FileOperations;
import java.time.LocalTime;                            //essential for test main()

/**
 *
 * @author sarka
 */
public class SmartUI {

    public static LKPRRadarSector sector;
    public static boolean exit = false;
    public static Scanner sc;

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        try {
            sector = new LKPRRadarSector();
            sc = new Scanner(System.in);
            System.out.println(pause());
            System.out.println(dashboard());
            while (!exit) {
                    execute();
            }
        } catch (IOException ex) {
            System.out.println("Unable to initialize new sector.");
        }
    }

    /**
     * TESTING MAIN
     *
     * @param args
     */
    /*
    public static void main(String[] args) {
        String testFile = "TestData.csv";
        LocalTime time = LocalTime.of(19, 18);
        try{
        File sourcefile = new File(System.getProperty("user.dir") + File.separator + "TESTING" + File.separator + testFile);
        String[][] airlist = new String[21][14];
        try (BufferedReader br = new BufferedReader(new FileReader(sourcefile))) {
            String line;
            for (int i = 0; i < airlist.length; i++) {
                if ((line = br.readLine()) != null) {
                    airlist[i] = line.split(",");
                }
            }
        }
        try {
            sector = new LKPRRadarSector(airlist, time);
            sc = new Scanner(System.in);
            System.out.println(pause());
            System.out.println(dashboard());
            while (!exit) {
                    execute();
            }
        } catch (IOException ex) {
            System.out.println("Unable to initialize new sector.");
        }
        }catch(IOException e){
            System.out.println("File not found.");
        }

    }
     */
    /**
     * *
     * Smart manager for user input, process and execute.
     *
     * @throws IOException
     */
    private static void execute(){
        String[] scan;
        scan = sc.nextLine().split(" ");
        switch (scan[0]) {
            case "-home":
                System.out.println(pause());
                System.out.println(dashboard());
                break;
            case "-h":
                System.out.println(pause());
                System.out.println(help());
                break;
            case "-r":
                sector.refresh();
                System.out.println(pause());
                System.out.println(dashboard());
                break;
            case "-about":
                System.out.println(pause());
                System.out.println(about());
                break;
            case "-exit":
                exit = true;
                break;
            case "-arrivals":
                arrivalsSwitch(scan);
                break;
            case "-departures":
                departuresSwitch(scan);
                break;
            case "-airtypes":
                System.out.println(pause());
                System.out.println(sector.createPrintAircraftTypes(sector.currentAirwindowAircraftTypes()));
                break;
            case "-airtypesf":
                System.out.println(pause());
                System.out.println(sector.createPrintAircraftTypes(sector.scheduledAircraftTypes()));
                break;
            case "-scw":
                if (scan.length < 2) {
                    System.out.println(">>> Can not operate without filename.");
                } else if ((!scan[1].substring(scan[1].length() - 4, scan[1].length()).equals(".txt")) && (!scan[1].substring(scan[1].length() - 4, scan[1].length()).equals(".bin"))) {
                    System.out.println("Invalid file format.");
                } else {
                    sector.saveCurrentAirWindow(scan[1]);
                }
                break;
            case "-sfr":
                if (scan.length < 2) {
                    System.out.println(">>> Can not operate without filename.");
                } else if ((!scan[1].substring(scan[1].length() - 4, scan[1].length()).equals(".txt")) && (!scan[1].substring(scan[1].length() - 4, scan[1].length()).equals(".bin"))) {
                    System.out.println("Invalid file format.");
                } else {
                    sector.saveFullReport(scan[1]);
                }
                break;
            case "-s":
                saveCommandSwitch(scan);
                break;
            default:
                break;
        }
    }

    /**
     * Switch for command -arrivals
     *
     * @param scan - String array with user input sequence
     */
    public static void arrivalsSwitch(String[] scan) {
        switch (scan.length) {
            case 1:
                System.out.println(pause());
                System.out.println(sector.createPrint(sector.scheduledArrivalsByTime(), "A"));
                break;
            case 2:
                switch (scan[1]) {
                    case "-t":
                        System.out.println(pause());
                        System.out.println(sector.createPrint(sector.scheduledArrivalsByTime(), "A"));
                        break;
                    case "-d":
                        System.out.println(pause());
                        System.out.println(sector.createPrint(sector.scheduledArrivalsByDestination(), "A"));
                        break;
                    default:
                        System.out.println("Invalid command combination.");
                        break;
                }
                break;
        }
        if (scan.length > 2) {
            switch (scan[1]) {
                case "-t":
                    switch (scan[2]) {
                        case "-EU":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledEUArrivalsByTime(), "A"));
                            break;
                        case "-CZ":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledCZArrivalsByTime(), "A"));
                            break;
                        default:
                            System.out.println("Invalid command combination.");
                            break;
                    }
                    break;
                case "-d":
                    switch (scan[2]) {
                        case "-EU":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledEUArrivalsByDestination(), "A"));
                            break;
                        case "-CZ":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledCZArrivalsByDestination(), "A"));
                            break;
                        default:
                            System.out.println("Invalid command combination.");
                            break;
                    }
                default:
                    System.out.println("Invalid command combination.");
                    break;

            }
        }
    }

    /**
     * Switch for command -arrivals
     *
     * @param scan - String array with user input sequence
     */
    public static void departuresSwitch(String[] scan) {
        switch (scan.length) {
            case 1:
                System.out.println(pause());
                System.out.println(sector.createPrint(sector.scheduledDeparturesByTime(), "D"));
                break;
            case 2:
                switch (scan[1]) {
                    case "-t":
                        System.out.println(pause());
                        System.out.println(sector.createPrint(sector.scheduledDeparturesByTime(), "D"));
                        break;
                    case "-d":
                        System.out.println(pause());
                        System.out.println(sector.createPrint(sector.scheduledDeparturesByDestination(), "D"));
                        break;
                    default:
                        System.out.println("Invalid command combination.");
                        break;
                }
                break;
            default:
                break;
        }
        if (scan.length > 2) {
            switch (scan[1]) {
                case "-t":
                    switch (scan[2]) {
                        case "-EU":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledEUDeparturesByTime(), "D"));
                            break;
                        case "-CZ":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledCZDeparturesByTime(), "D"));
                            break;
                        default:
                            System.out.println("Invalid command combination.");
                            break;
                    }
                    break;
                case "-d":
                    switch (scan[2]) {
                        case "-EU":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledEUDeparturesByDestination(), "D"));
                            break;
                        case "-CZ":
                            System.out.println(pause());
                            System.out.println(sector.createPrint(sector.scheduledCZDeparturesByDestination(), "D"));
                            break;
                        default:
                            System.out.println("Invalid command combination.");
                            break;
                    }
                default:
                    System.out.println("Invalid command combination.");

                    break;
            }
        }
    }

    /**
     * Switch for command -s
     *
     * @param scan - String array with user input sequence
     */
    public static void saveCommandSwitch(String[] scan) {
        if (scan.length < 3) {
            System.out.println("Can not operate without filename and command.");
        } else if ((!scan[1].substring(scan[1].length() - 4, scan[1].length()).equals(".txt")) && (!scan[1].substring(scan[1].length() - 4, scan[1].length()).equals(".bin"))) {
            System.out.println("Invalid file format.");
        } else {
            ArrayList<String> commands = new ArrayList(Arrays.asList("-home", "-about", "-h", "-arrivals", "-departures", "-airtypes", "-airtypesf"));                   //nedokonceno
            if (!commands.contains(scan[2])) {
                System.out.println("Unsaveable command.");
            } else {
                try {
                    switch (scan[2]) {
                        case "-home":
                            FileOperations.save(dashboard(), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                            break;
                        case "-h":
                            FileOperations.save(help(), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                            break;

                        case "-about":
                            FileOperations.save(about(), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                            break;

                        case "-arrivals":
                            if (scan.length == 3) {
                                FileOperations.save(sector.createPrint(sector.scheduledArrivalsByTime(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                break;
                            }
                            if (scan.length == 4) {
                                if (scan[3].equals("-t")) {
                                    FileOperations.save(sector.createPrint(sector.scheduledArrivalsByTime(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                    break;
                                }
                                if (scan[3].equals("-d")) {
                                    FileOperations.save(sector.createPrint(sector.scheduledArrivalsByDestination(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                    break;
                                }
                                if (scan.length > 4) {
                                    if (scan[3].equals("-t") && scan[4].equals("-EU")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledEUArrivalsByTime(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                    if (scan[3].equals("-d") && scan[4].equals("-EU")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledEUArrivalsByDestination(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                    if (scan[3].equals("-t") && scan[4].equals("-CZ")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledCZArrivalsByTime(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                    if (scan[3].equals("-d") && scan[4].equals("-CZ")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledCZArrivalsByDestination(), "A"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                }
                            }
                        case "-departures":
                            if (scan.length == 3) {
                                FileOperations.save(sector.createPrint(sector.scheduledDeparturesByTime(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                break;
                            }
                            if (scan.length == 4) {
                                if (scan[1].equals("-t")) {
                                    FileOperations.save(sector.createPrint(sector.scheduledDeparturesByTime(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                    break;
                                }
                                if (scan[3].equals("-d")) {
                                    FileOperations.save(sector.createPrint(sector.scheduledDeparturesByDestination(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                    break;
                                }
                                if (scan.length > 4) {
                                    if (scan[3].equals("-t") && scan[4].equals("-EU")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledEUDeparturesByTime(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                    if (scan[3].equals("-d") && scan[4].equals("-EU")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledEUDeparturesByDestination(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                    if (scan[3].equals("-t") && scan[4].equals("-CZ")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledCZDeparturesByTime(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                    if (scan[3].equals("-d") && scan[4].equals("-CZ")) {
                                        FileOperations.save(sector.createPrint(sector.scheduledCZDeparturesByDestination(), "D"), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                                        break;
                                    }
                                }
                            }
                        case "-airtypes":
                            FileOperations.save(sector.createPrintAircraftTypes(sector.currentAirwindowAircraftTypes()), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                            break;
                        case "-airtypesf":
                            FileOperations.save(sector.createPrintAircraftTypes(sector.scheduledAircraftTypes()), scan[1], sector.getLastrefresht(), sector.getLastrefreshd(), sector.getTZ());
                            break;

                        default:
                            break;
                    }
                } catch (IOException ex) {
                    System.out.println("File could not be saved.");
                }
            }
        }
    }

    /**
     * Returns string representation of interactive dashboard.
     *
     * @return String dashboard
     */
    public static String dashboard() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object[]> map = new HashMap<>(sector.currentAirwindowAircraftTypes());
        List<String> keys = new ArrayList<>(map.keySet());

        sb.append(String.format("%20s %6s %6s %53s %8s     %8s %n", sector.getSectorName(), sector.getSectorIATA(), sector.getSectorICAO(), sector.currentTime().toString(), sector.getTZ(), sector.getCurrentDate().toString()));

        sb.append("_______________________________________________________________________________________________________________________");

        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("%64s%n", "SECTOR RADAR"));
        sb.append(System.getProperty("line.separator"));
        sb.append("         Current Air window");
        sb.append(System.getProperty("line.separator"));
        sb.append(sector.printCurrentAirWindow());

        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("         _____________________________________________________________________").append(System.getProperty("line.separator"));
        sb.append(String.format(String.format("           %10s %50s %5s", "Type code", "Aircraft type", "Num"))).append(System.getProperty("line.separator"));

        for (String key : keys) {
            sb.append(String.format("           %10s %50s %5d", key, map.get(key)[0], map.get(key)[1])).append(System.getProperty("line.separator"));
        }
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("-h for help");
        sb.append(System.getProperty("line.separator"));
        sb.append("_______________________________________________________________________________________________________________________");
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("source: %s %56s %s", sector.getSourceURL(), "last refresh:", sector.getLastrefresht().toString()));
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

    /**
     * Returns string representation of user help.
     *
     * @return String help
     */
    private static String help() {
        StringBuilder sb = new StringBuilder();
        sb.append("                        AIR SECTOR RADAR HELP");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                -about                  Shows introduction to airsector radar program");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -home                   Shows home dashboard display.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -r                      Refresh data.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -exit                   Exit the program.");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                -arrivals -t            Shows all scheduled arrivals available sorted by arrival time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -arrivals -d            Shows all scheduled arrivals available sorted by destination name.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -arrivals -t -EU        Shows available scheduled arrivals from Europe sorted by arrival time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -arrivals -d -EU        Shows available scheduled arrivals from Europe sorted by arrival time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -arrivals -t -CZ        Shows available scheduled interstate(CZ) arrivals sorted by arrival time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -arrivals -d -CZ        Shows available scheduled interstate(CZ) arrivals sorted by arrival time.");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                -departures -t          Shows all scheduled departures available sorted by time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -departures -d          Shows all scheduled departures available sorted by destination name.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -departures -t -EU      Shows available scheduled departures available from Europe sorted by time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -departures -d -EU      Shows available scheduled departures available from Europe sorted by destination name.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -departures -t -CZ      Shows available scheduled interstate(CZ) departures available sorted by time.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -departures -d -CZ      Shows available scheduled interstate(CZ) departures available sorted by destination name.");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                -airtypes               Shows list of aircraft types in current air window");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -airtypesf              Shows  list of aircraft types of scheduled flights.");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                -scw <filename>         Saves current time window report to <filename> file (.txt).     ");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -sfr <filename>         Saves full report with all information available at current time to <filename> (.txt).");
        sb.append(System.getProperty("line.separator"));
        sb.append("                -s <filename> <command> Any output of the above mentioned commands might be saved to .txt or to .bin as binary by -s prefix. ");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

    /**
     * Returns string representation of about section.
     *
     * @return string about
     */
    private static String about() {
        StringBuilder sb = new StringBuilder();
        sb.append("                Welcome to Vaclav Havel Airport Prague Sector Radar.");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                This program provides up to date current information about aircrafts occuring in the air sector");
        sb.append(System.getProperty("line.separator"));
        sb.append("                above LKPR/PRG airport (CZE).");
        sb.append(System.getProperty("line.separator"));
        sb.append("                All flight information are provided by flightaware.com. Departure-to-take-off time windows and take-off-window");
        sb.append(System.getProperty("line.separator"));
        sb.append("                sizes were estimated based on information provided by PRG airport traffic control.");
        sb.append(System.getProperty("line.separator"));
        sb.append("                @05/2021");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("                More about functions: -h");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

    /**
     * Returns empty space (console cleaning)
     *
     * @return String pause
     */
    private static String pause() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
