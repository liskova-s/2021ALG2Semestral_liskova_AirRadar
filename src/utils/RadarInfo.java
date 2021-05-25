/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Aircraft;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sarka
 */
public interface RadarInfo {
    /**
     * Load latest data.
     */
    public void refresh();
    
    //Getters___________________________________________________________________________________________________________
    /**
     * Name of monitored airport/airport sector 
     * @return name of monitored airport/airport sector 
     */
    public String getSectorName();
    
    /**
     * IATA International Air Transport Association code of monitored airport/airport sector 
     * @return IATA of monitored airport
     */
    public String getSectorIATA ();
    
    /**
     * ICAO International Civil Aviation Organization code of monitored airport/airport sector 
     * @return ICAO of monitored airport
     */
    public String getSectorICAO();
    
    /**
     * URL of website providing information.
     * @return source website URL
     */
    public String getSourceURL();
    
    /**
     * 
     * @return Home timezone.
     */
    public String getTZ(); 
    
    /**
     * 
     * @return current local time.
     */
    public LocalTime currentTime();
   
    /**
     * 
     * @return current local date
     */
    public LocalDate getCurrentDate();
    /**
     * 
     * @return time of last refresh
     */
    public LocalTime getLastrefresht();
    
    /**
     * 
     * @return date of last refresh
     */
    public LocalDate getLastrefreshd();
    //Information Lists________________________________________________________________________________________________
    /**
     * List of aircrafts in the air sector of monitored airport in current 10 minute window.
     * @return list of aircrafts currently in the airsector
     */
    public List<Aircraft> currentAirWindow();
    
    /**
     * 
     * @return aircraft list of departures sorted by departure
     */
    public List<Aircraft> scheduledDeparturesByTime();
    
    /**
     * 
     * @return aircraft list of departures sorted by destination
     */
    public List<Aircraft> scheduledDeparturesByDestination();
    
    /**
     * 
     * @return aircraft list of departures to Europe sorted by departure
     */
    public List<Aircraft> scheduledEUDeparturesByTime();
    
    /**
     * 
     * @return aircraft list of departures to Europe sorted by destination
     */
    public List<Aircraft> scheduledEUDeparturesByDestination();
    
    /**
     * 
     * @return aircraft list of departures to Czech Republic sorted by departure
     */
    public List<Aircraft> scheduledCZDeparturesByTime();
    
    /**
     * 
     * @return aircraft list of departures to Czech Republic sorted by destination
     */
    public List<Aircraft> scheduledCZDeparturesByDestination();
    
    
    /**
     * 
     * @return aircraft list of arrivals sorted by arrival
     */
    public List<Aircraft> scheduledArrivalsByTime();
    
    /**
     * 
     * @return aircraft list of arrivals sorted by destination
     */
    public List<Aircraft> scheduledArrivalsByDestination();
    
    /**
     * 
     * @return aircraft list of arrivals from Europe sorted by arrival
     */
    public List<Aircraft> scheduledEUArrivalsByTime();
    
    /**
     * 
     * @return aircraft list of arrivals from Europe sorted by destination
     */
    public List<Aircraft> scheduledEUArrivalsByDestination();
    
    /**
     * 
     * @return aircraft list of arrivals from Czech Republic sorted by arrival
     */
    public List<Aircraft> scheduledCZArrivalsByTime();
    
    /**
     * 
     * @return aircraft list of arrivals from Czech Republic sorted by destination
     */
    public List<Aircraft> scheduledCZArrivalsByDestination();
    
    /**
     * 
     * @return map of aircraft types in current airwindow  including number of occurence
     */
    public Map<String,Object[]>  currentAirwindowAircraftTypes();
    
    /**
     * 
     * @return map of aircraft types scheduled including number of occurence
     */
    public Map<String,Object[]> scheduledAircraftTypes();
    
    //Functional strings________________________________________________________________________________________________
    /**
     * Returns string with full current air window report.
     * @return string representation of current aircrafts above prg airport
     */
    public String printCurrentAirWindow();
     
    /**
     * Returns string representation of aircraft type map
     * @param map
     * @return String representation of aircraft type map
     */
    public String createPrintAircraftTypes(Map<String,Object[]>map);
    
    /**
     * Returns string representation of aircraft list
     * @param a -aircraft
     * @param command 
     * @return string representation of aircraft list
     */
    public String createPrint(List<Aircraft> a,String command);
    
    //Saving voids______________________________________________________________________________________________________
    /**
     * Saves full report about current air window.
     * @param filename 
     */
    public void saveCurrentAirWindow(String filename);
    
    /**
     * Creates file filename with all information available.
     * @param filename
     */
    public void saveFullReport(String filename);
    
}
