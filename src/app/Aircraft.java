/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author sarka
 */
public class Aircraft implements Comparable<Aircraft> {

    private final String code;
    private String direction;
    private LocalTime landing;
    private String landingTZ;
    private LocalTime takeOff;
    private String takeOffTZ;
    private String destination;
    private String destCode;
    private String airlines;
    private String stateflag;
    private String aircraftType;
    private String aircraftTypeCode;

    //Constructor and factories___________________________________________________________________________________________________
    /**
     * Aircraft constructor
     *
     * @param code - code of flight
     * @param time - landing for arrivals, takeoff for departures
     * @param TZ - timezone
     * @param direction - A for arriving, D for departing
     */
    public Aircraft(String code, LocalTime time, String TZ, String direction) {
        this.code = code;
        if (direction.equals("A")) {
            this.direction = "A";
            this.landing = time;
            this.landingTZ = TZ;
        }
        if (direction.equals("D")) {
            this.direction = "D";
            this.takeOff = time;
            this.takeOffTZ = TZ;
        }
    }

    /**
     * Factory method for Aircraft objects AirInfo structure: Vueling Airlines ,
     * Spain, VLG8652, Airbus A320 (twin-jet), A320, Barcelona Int'l, BCN,
     * 08:08, a, CEST, , , CEST, A
     *
     * @param airInfo - String array from parser containing essential
     * information about aircraft
     * @return Aircraft with features satisfying airInfo array
     */
    public static Aircraft newAircraft(String[] airInfo) {
        if (airInfo[13].equals("A")) {
            if (airInfo[10] != null && !"".equals(airInfo[10])) {
                String timeAppend = "AM";
                switch (airInfo[11]) {
                    case "a":
                        timeAppend = "AM";
                        break;
                    case "p":
                        timeAppend = "PM";
                        break;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
                LocalTime time = LocalTime.parse(airInfo[10] + " " + timeAppend, formatter);

                Aircraft a = new Aircraft(airInfo[2], time, airInfo[12], airInfo[13]);
                if (airInfo[0] != null) {
                    a.setAirlines(airInfo[0]);
                }
                if (airInfo[1] != null) {
                    a.setStateflag(airInfo[1]);
                }
                if (airInfo[3] != null) {
                    a.setAircraftType(airInfo[3]);
                }
                if (airInfo[4] != null) {
                    a.setAircraftTypeCode(airInfo[4]);
                }
                if (airInfo[5] != null) {
                    a.setDestination(airInfo[5]);
                }
                if (airInfo[6] != null) {
                    a.setDestCode(airInfo[6]);
                }
                if (airInfo[7] != null) {
                    switch (airInfo[8]) {
                        case "a":
                            timeAppend = "AM";
                            break;
                        case "p":
                            timeAppend = "PM";
                            break;
                    }
                    LocalTime time2 = LocalTime.parse(airInfo[7] + " " + timeAppend, formatter);
                    a.setTakeOff(time2);
                }
                if (airInfo[9] != null) {
                    a.setTakeOffTZ(airInfo[9]);
                }

                return a;
            }
        }
        if (airInfo[13].equals("D")) {
            if (airInfo[7] != null && !"".equals(airInfo[7])) {
                String timeAppend = "AM";
                switch (airInfo[8]) {
                    case "a":
                        timeAppend = "AM";
                        break;
                    case "p":
                        timeAppend = "PM";
                        break;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
                LocalTime time = LocalTime.parse(airInfo[7] + " " + timeAppend, formatter);
                Aircraft a = new Aircraft(airInfo[2], time, airInfo[9], airInfo[13]);
                if (airInfo[0] != null) {
                    a.setAirlines(airInfo[0]);
                }
                if (airInfo[1] != null) {
                    a.setStateflag(airInfo[1]);
                }
                if (airInfo[3] != null) {
                    a.setAircraftType(airInfo[3]);
                }
                if (airInfo[4] != null) {
                    a.setAircraftTypeCode(airInfo[4]);
                }
                if (airInfo[5] != null) {
                    a.setDestination(airInfo[5]);
                }
                if (airInfo[6] != null) {
                    a.setDestCode(airInfo[6]);
                }
                
                return a;
            }
        }
        return null;
    }

    //setters______________________________________________________________________________________________________________________
    /**
     * Setter
     *
     * @param landing
     */
    public void setLanding(LocalTime landing) {
        this.landing = landing;
    }

    /**
     * Setter
     *
     * @param aircraftType
     */
    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    /**
     * Setter
     *
     * @param aircraftTypeCode
     */
    public void setAircraftTypeCode(String aircraftTypeCode) {
        this.aircraftTypeCode = aircraftTypeCode;
    }

    /**
     * Setter
     *
     * @param landingTZ
     */
    public void setLandingTZ(String landingTZ) {
        this.landingTZ = landingTZ;
    }

    /**
     * Setter
     *
     * @param takeOff
     */
    public void setTakeOff(LocalTime takeOff) {
        this.takeOff = takeOff;
    }

    /**
     * Setter
     *
     * @param takeOffTZ
     */
    public void setTakeOffTZ(String takeOffTZ) {
        this.takeOffTZ = takeOffTZ;
    }

    /**
     * Setter
     *
     * @param fromDestination
     */
    public void setDestination(String fromDestination) {
        this.destination = fromDestination;
    }

    /**
     * Setter
     *
     * @param fDCode
     */
    public void setDestCode(String fDCode) {
        this.destCode = fDCode;
    }

    /**
     * Setter
     *
     * @param airlines
     */
    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    /**
     * Setter
     *
     * @param stateflag
     */
    public void setStateflag(String stateflag) {
        this.stateflag = stateflag;
    }

    //Getters________________________________________________________________________________________________________________________________
    /**
     * Getter
     *
     * @return flight code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter
     *
     * @return landing time
     */
    public LocalTime getLanding() {
        return landing;
    }

    /**
     * Getter
     *
     * @return type of aircraft
     */
    public String getAircraftType() {
        return aircraftType;
    }

    /**
     * Getter
     *
     * @return code of aircraft type
     */
    public String getAircraftTypeCode() {
        return aircraftTypeCode;
    }

    /**
     * Getter
     *
     * @return landing timezone
     */
    public String getLandingTZ() {
        return landingTZ;
    }

    /**
     * Getter
     *
     * @return take off time
     */
    public LocalTime getTakeOff() {
        return takeOff;
    }

    /**
     * Getter
     *
     * @return takeoff timezone
     */
    public String getTakeOffTZ() {
        return takeOffTZ;
    }

    /**
     * Getter
     *
     * @return destination airport name
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Getter
     *
     * @return destination airport IATA code
     */
    public String getDestCode() {
        return destCode;
    }

    /**
     * Getter
     *
     * @return provider of flight (airlines)
     */
    public String getAirlines() {
        return airlines;
    }

    /**
     * Getter
     *
     * @return state or city base of airline company
     */
    public String getStateflag() {
        return stateflag;
    }

    /**
     * Getter
     *
     * @return A for arrivals, D for departures
     */
    public String getDirection() {
        return direction;
    }

    //Functional strings_____________________________________________________________________________________________________________
    /**
     * Wraps all aircraft information available into formatted string
     *
     * @return aircraft string toString
     */
    public String fullToString() {
        // A/D Code, airlines, stateflag, type, typecode, destination, dest code, time, ampm, tz, time ampm, tz
        String landingstr;
        String takeOffstr;
        if (landing == null) {
            landingstr = "";
        } else {
            landingstr = landing.toString();
        }
        if (takeOff == null) {
            takeOffstr = "";
        } else {
            takeOffstr = takeOff.toString();
        }

        if (landingTZ == null) {
            setLandingTZ("");
        }
        switch (direction) {

            case "A":
                return (String.format("%s %10s %30s %20s %40s %7s %7s %7s %7s %7s%n", direction, code, airlines, stateflag, destination, destCode, takeOffstr, takeOffTZ, landingstr, landingTZ));

            case "D":
                return (String.format("%s %10s %30s %20s %40s %7s %7s %7s %7s %7s%n", direction, code, airlines, stateflag, destination, destCode, takeOffstr, takeOffTZ, landingstr, landingTZ));

            default:
                return "";
        }

    }

    //CompareTo______________________________________________________________________________________________________________________
    /**
     * CompareTo method from Comparable - comparing arrival/departure times
     *
     * @param o Aircraft to be compared to
     * @return int comparison coefficient
     */
    @Override
    public int compareTo(Aircraft o) {
        int reduction = 86400;
        if (direction.equals("A") && o.getDirection().equals("A")) {
            int a = landing.toSecondOfDay();
            if (a < LocalTime.now().toSecondOfDay()) {
                a += reduction;
            }
            int b = o.getLanding().toSecondOfDay();
            if (b < LocalTime.now().toSecondOfDay()) {
                b += reduction;
            }
            return a - b;
        }
        if (direction.equals("D") && o.getDirection().equals("D")) {
            int a = takeOff.toSecondOfDay();
            if (a < LocalTime.now().toSecondOfDay()) {
                a += reduction;
            }
            int b = o.getTakeOff().toSecondOfDay();
            if (b < LocalTime.now().toSecondOfDay()) {
                b += reduction;
            }
            return a - b;
        }
        return 0;
    }
}
