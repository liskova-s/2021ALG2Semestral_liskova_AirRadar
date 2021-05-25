/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import utils.RadarInfo;
import static app.Aircraft.newAircraft;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ui.SmartUI.dashboard;
import utils.ComparatorByDestination;
import utils.FileOperations;
import static utils.FileOperations.load;
import static utils.FileOperations.save;
import utils.Scraper;

/**
 *
 * @author sarka
 */
public class LKPRRadarSector implements RadarInfo {

    public static Scraper scrp;

    private final String name = "Vaclav Havel Airport Prague";
    private final String IATA = "PRG";
    private final String ICAO = "LKPR";
    private final String SOURCEURL = "https://flightaware.com/live/airport/LKPR";
    private final String TIMEZONE = "CEST";
    private final List<String> euairports = load("EUAirports.csv", 2);
    private final List<String> czairports = load("CZAirports.csv", 0);
    private final LocalTime currentTime = LocalTime.now();
    private final LocalDate currentDate = LocalDate.now();

    private LocalTime lastrefresht;
    private LocalDate lastrefreshd;
    private List<Aircraft> airlist; //list of aircrafts in sector

    //constructor & factories_________________________________________________________________________________________________________________
    
    /**
     * Constructor for airport sector
     * @throws IOException 
     */
    public LKPRRadarSector() throws IOException {
        airlist = new ArrayList();
        scrp = new Scraper(SOURCEURL);
        scrp.scrape();
        airlist = toList(scrp.getAirlist());
        lastrefresht = LocalTime.now();
        lastrefreshd = LocalDate.now();
    }

    /**
     * 
     * @param scrape - string from scraper, url provided information
     * @return list of aircraft based on information in scrape array
     */
    public static List<Aircraft> toList(String[][] scrape) {
        List<Aircraft> airlist = new ArrayList();
        for (String[] airInfo : scrape) {
            if (airInfo[0] != null) {
                Aircraft a = newAircraft(airInfo);
                if (a != null) {
                    airlist.add(a);
                }
            }
        }
        return airlist;
    }

    //getters_________________________________________________________________________________________________________________________________
    /**
     * Name of monitored airport/airport sector 
     * @return name of monitored airport/airport sector 
     */
    @Override
    public String getSectorName() {
        return name;
    }
    
    /**
     * IATA International Air Transport Association code of monitored airport/airport sector 
     * @return IATA of monitored airport
     */
    @Override
    public String getSectorIATA() {
        return IATA;
    }

    /**
     * ICAO International Civil Aviation Organization code of monitored airport/airport sector 
     * @return ICAO of monitored airport
     */
    @Override
    public String getSectorICAO() {
        return ICAO;
    }

    /**
     * URL of website providing information.
     * @return source website URL
     */
    @Override
    public String getSourceURL() {
        return SOURCEURL;
    }

    /**
     * 
     * @return date of last refresh
     */
    @Override
    public LocalDate getLastrefreshd() {
        return lastrefreshd;
    }

    /**
     * 
     * @return current local date
     */
    @Override
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    /**
     * 
     * @return Home timezone.
     */
    @Override
    public String getTZ() {
        return TIMEZONE;
    }

    /**
     * 
     * @return current local time.
     */
    @Override
    public LocalTime currentTime() {
        return currentTime;
    }

    /**
     * 
     * @return time of last refresh
     */
    @Override
    public LocalTime getLastrefresht() {
        return lastrefresht;
    }
    
    //setters_________________________________________________________________________________________________________________________________________
    //REFRESH_________________________________________________________________________________________________________________________________________
    /**
     * Load latest data.
     */
    @Override
    public void refresh() {
        try {
            scrp.scrape();
            airlist = toList(scrp.getAirlist());
        } catch (IOException ex) {
            Logger.getLogger(LKPRRadarSector.class.getName()).log(Level.SEVERE, null, ex);
        }
        lastrefresht = LocalTime.now();
        lastrefreshd = LocalDate.now();
    }

    //list-returning functional_______________________________________________________________________________________________________________________
    /**
     * List of aircrafts in the air sector of monitored airport in current 10 minute window.
     * @return list of aircrafts currently in the airsector
     */
    @Override
    public List<Aircraft> currentAirWindow() {
        List<Aircraft> currentAW = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A")) {
                int landsecs = aircraft.getLanding().toSecondOfDay();
                int windowlower = currentTime.toSecondOfDay() - 3 * 30; //airwindow: landing -8 +- 3min  
                int windowupper = currentTime.toSecondOfDay() + 3 * 30;
                if (landsecs - 8 <= windowupper && landsecs - 8 >= windowlower) {
                    currentAW.add(aircraft);
                }
            }

            if (aircraft.getDirection().equals("D")) {
                int landsecs = aircraft.getTakeOff().toSecondOfDay();
                int windowlower = currentTime.toSecondOfDay() - 3 * 30; //airwindow: landing -8 +- 3min  
                int windowupper = currentTime.toSecondOfDay() + 3 * 30;
                if (landsecs + 8 <= windowupper && landsecs + 8 >= windowlower) {
                    currentAW.add(aircraft);
                }
            }
        }
        return currentAW;
    }

    /**
     * 
     * @return aircraft list of departures sorted by departure
     */
    @Override
    public List<Aircraft> scheduledEUDeparturesByTime() {
        List<Aircraft> departures = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("D") && euairports.contains(aircraft.getDestCode())) {
                departures.add(aircraft);
            }
        }
        Collections.sort(departures);
        return departures;
    }

    /**
     * 
     * @return aircraft list of departures to Europe sorted by destination
     */
    @Override
    public List<Aircraft> scheduledEUDeparturesByDestination() {
        List<Aircraft> departures = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("D") && euairports.contains(aircraft.getDestCode())) {
                departures.add(aircraft);
            }
        }
        ComparatorByDestination cbd = new ComparatorByDestination();
        Collections.sort(departures, cbd);
        return departures;
    }

    /**
     * 
     * @return aircraft list of departures to Europe sorted by departure
     */
    @Override                                                                   
    public List<Aircraft> scheduledDeparturesByTime() {
        List<Aircraft> departures = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("D")) {
                departures.add(aircraft);
            }
        }
        Collections.sort(departures);
        return departures;
    }

    /**
     * 
     * @return aircraft list of departures sorted by destination
     */
    @Override                                                                   
    public List<Aircraft> scheduledDeparturesByDestination() {
        List<Aircraft> departures = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("D")) {
                departures.add(aircraft);
            }
        }
        ComparatorByDestination cbd = new ComparatorByDestination();
        Collections.sort(departures, cbd);
        return departures;
    }

    /**
     * 
     * @return aircraft list of departures to Czech Republic sorted by departure
     */
    @Override
    public List<Aircraft> scheduledCZDeparturesByTime() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("D") && czairports.contains(aircraft.getDestCode())) {
                arrivals.add(aircraft);
            }
        }
        Collections.sort(arrivals);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of departures to Czech Republic sorted by destination
     */
    @Override
    public List<Aircraft> scheduledCZDeparturesByDestination() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("D") && czairports.contains(aircraft.getDestCode())) {
                arrivals.add(aircraft);
            }
        }
        ComparatorByDestination cbd = new ComparatorByDestination();
        Collections.sort(arrivals, cbd);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of arrivals sorted by arrival
     */
    @Override                                                                   
    public List<Aircraft> scheduledArrivalsByTime() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A")) {
                arrivals.add(aircraft);
            }
        }
        Collections.sort(arrivals);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of arrivals sorted by destination
     */
    @Override                                                                   
    public List<Aircraft> scheduledArrivalsByDestination() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A")) {
                arrivals.add(aircraft);
            }
        }
        ComparatorByDestination cbd = new ComparatorByDestination();
        Collections.sort(arrivals, cbd);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of arrivals from Europe sorted by arrival
     */
    @Override
    public List<Aircraft> scheduledEUArrivalsByTime() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A") && euairports.contains(aircraft.getDestCode())) {
                arrivals.add(aircraft);
            }
        }
        Collections.sort(arrivals);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of arrivals from Europe sorted by destination
     */
    @Override
    public List<Aircraft> scheduledEUArrivalsByDestination() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A") && euairports.contains(aircraft.getDestCode())) {
                arrivals.add(aircraft);
            }
        }
        ComparatorByDestination cbd = new ComparatorByDestination();
        Collections.sort(arrivals, cbd);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of arrivals from Czech Republic sorted by arrival
     */
    @Override
    public List<Aircraft> scheduledCZArrivalsByTime() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A") && czairports.contains(aircraft.getDestCode())) {
                arrivals.add(aircraft);
            }
        }
        Collections.sort(arrivals);
        return arrivals;
    }

    /**
     * 
     * @return aircraft list of arrivals from Czech Republic sorted by destination
     */
    @Override
    public List<Aircraft> scheduledCZArrivalsByDestination() {
        List<Aircraft> arrivals = new ArrayList();
        for (Aircraft aircraft : airlist) {
            if (aircraft.getDirection().equals("A") && czairports.contains(aircraft.getDestCode())) {
                arrivals.add(aircraft);
            }
        }
        ComparatorByDestination cbd = new ComparatorByDestination();
        Collections.sort(arrivals, cbd);
        return arrivals;
    }

    /**
     * 
     * @return map of aircraft types in current airwindow  including number of occurence
     */
    @Override
    public Map<String, Object[]> currentAirwindowAircraftTypes() {
        Map<String, Object[]> map = new HashMap<>();
        for (Aircraft a : currentAirWindow()) {
            String code = a.getAircraftTypeCode();
            if (!map.containsKey(code)) {
                Object[] arr = {a.getAircraftType(), 1};
                map.put(code, arr);
            } else {
                int count = (int) map.get(code)[1] + 1;
                Object[] arr = {a.getAircraftType(), count};
                map.put(code, arr);
            }
        }
        return map;
    }

    /**
     * 
     * @return map of aircraft types scheduled including number of occurence
     */
    public Map<String, Object[]> scheduledAircraftTypes() {
        Map<String, Object[]> map = new HashMap<>();
        for (Aircraft a : airlist) {
            String code = a.getAircraftTypeCode();
            if (!map.containsKey(code)) {
                Object[] arr = {a.getAircraftType(), 1};
                map.put(code, arr);
            } else {
                int count = (int) map.get(code)[1] + 1;
                Object[] arr = {a.getAircraftType(), count};
                map.put(code, arr);
            }
        }
        return map;
    }

    //string-returning processing_____________________________________________________________________________________________________________________
    /**
     * Returns string with full current air window report.
     * @return string representation of current aircrafts above prg airport
     */
    @Override
    public String printCurrentAirWindow() {
        StringBuilder sb = new StringBuilder();
        for (Aircraft a : currentAirWindow()) {
            sb.append("         ");
            sb.append(a.fullToString());
            sb.append(System.getProperty("Line.separator"));
        }
        if (sb.toString().equals("")) {
            return "         Currently empty.";
        }
        return sb.toString();
    }

    /**
     * Returns string representation of aircraft list
     * @param a -aircraft
     * @param command 
     * @return string representation of aircraft list
     */
    @Override
    public String createPrint(List<Aircraft> a, String command) {
        StringBuilder sb = new StringBuilder();
        switch (command) {
            case "A":
                sb.append(String.format("%64s", "ARRIVALS")).append(System.getProperty("line.separator"));
                sb.append("______________________________________________________________________________________________________________________________________________");
                sb.append(System.getProperty("line.separator"));
                sb.append(String.format("%s %10s %30s %20s %40s %7s %7s %3s %7s %7s %3s %7s%n", "", "Flight", "Provider", "", "Airport", "", "Departure", "", "", "Arrival", "", "")).append(System.getProperty("line.separator"));

                for (Aircraft aircraft : a) {
                    sb.append(aircraft.fullToString());
                }
                return sb.toString();
            case "D":
                sb.append(String.format("%64s", "DEPARTURES")).append(System.getProperty("line.separator"));
                sb.append("__________________________________________________________________________________________________________________________");
                sb.append(System.getProperty("line.separator"));
                sb.append(String.format("%s %10s %30s %20s %40s %7s %7s %3s %7s %7s %3s %7s%n", "", "Flight", "Provider", "", "Airport", "", "Departure", "", "", "", "", "")).append(System.getProperty("line.separator"));
                for (Aircraft aircraft : a) {
                    sb.append(aircraft.fullToString());
                }
                return sb.toString();
            default:
                return "";
        }
    }

    /**
     * Returns string representation of aircraft type map
     * @param map
     * @return String representation of aircraft type map
     */
    @Override
    public String createPrintAircraftTypes(Map<String, Object[]> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("           %64s", "AIRCRAFT TYPES")).append(System.getProperty("line.separator"));
        sb.append("         ______________________________________________________________________________________________________");
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("           %10s %50s %5s", "Type code", "Aircraft type", "Num")).append(System.getProperty("line.separator"));
        List<String> keys = new ArrayList<>(map.keySet());
        for (String key : keys) {
            sb.append(String.format("           %10s %50s %5d", key, map.get(key)[0], map.get(key)[1])).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    //saving voids____________________________________________________________________________________________________________________________________
    /**
     * Saves full report about current air window.
     * @param filename 
     */
    @Override
    public void saveCurrentAirWindow(String filename) {
        try {
            FileOperations.save(printCurrentAirWindow(), filename, lastrefresht, lastrefreshd, TIMEZONE);
        } catch (IOException ex) {
            Logger.getLogger(LKPRRadarSector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates file filename with all information available.
     * @param filename
     */
    @Override
    public void saveFullReport(String filename) {
        try {
            String pause=System.getProperty("line.separator")+System.getProperty("line.separator");
            save(dashboard()+pause+createPrint(scheduledDeparturesByTime(),"D")+pause+createPrint(scheduledArrivalsByTime(),"A")+pause+createPrintAircraftTypes(scheduledAircraftTypes()),filename,lastrefresht, lastrefreshd, TIMEZONE);
        } catch (IOException ex) {
            Logger.getLogger(LKPRRadarSector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
