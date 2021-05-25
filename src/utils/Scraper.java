/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Web scraper providing scheduled arrival and departure information, designed
 * for https://flightaware.com/live/airport/LKPR website
 *
 * @author sarka
 */
public class Scraper {

    private final String url;
    private String[][] airlist;

    /**
     * Constructor
     *
     * @param url
     */
    public Scraper(String url) {
        this.url = url;
    }

    /**
     *
     * @return string with loaded html code
     * @throws IOException
     */
    private String loadDown() throws IOException {
        Document doc = Jsoup.connect(url).get();
        String loadString = doc.toString();
        return loadString;
    }

    /**
     * Splits scraped string into basic compounds, calling next processing
     * Setter of this.airlist
     *
     * @throws IOException
     */
    public void scrape() throws IOException {
        String s = loadDown();
        s = s.replaceAll("\\n", "");                                            // minus new lines
        s = s.replaceAll("  ", "");                                             // minus double whitespace

        String[] splitScheduled = s.split("h2>En Route");
        String[] splitDirection = splitScheduled[1].split("Departures");
        String[] arrivals = splitDirection[0].split("<");                       //list of arrival snippets
        String[] departures = splitDirection[1].split("<");                     //list of departure snippets

        this.airlist = globalAirlist(airlist(arrivals, "A"), airlist(departures, "D"));

    }

    /**
     * Transforms arrival and departure deep string arrays to one
     *
     * @param arrivals
     * @param departures
     * @return double string array with all aircraft inromation
     * (arrivals+departures)
     */
    private String[][] globalAirlist(String[][] arrivals, String[][] departures) {
        String[][] glob = new String[arrivals.length + departures.length][14];
        System.arraycopy(arrivals, 0, glob, 0, arrivals.length);
        System.arraycopy(departures, 0, glob, arrivals.length, departures.length);
        return glob;
    }

    /**
     * Specific parsing
     *
     * @param arr
     * @param direction
     * @return double string array with arrival/departure information
     */
    private String[][] airlist(String[] arr, String direction) {

        String[][] localAirlist = new String[20][14];
        int aircount = -1;
        int timecount = 7;
        boolean noPort = false;

        for (String piece : arr) {
            Pattern p1 = Pattern.compile("td class=\\\".*\\\">.+");             //td class=".*">this, 
            Matcher matchp1 = p1.matcher(piece);                                //time
            Pattern p1a = Pattern.compile("i>\\d+.*");
            Matcher matchp1a = p1a.matcher(piece);
            if (matchp1.matches() || matchp1a.matches()) {
                if (aircount > -1) {
                    localAirlist[aircount][13] = direction;
                }
                if (noPort) {
                    if (aircount > -1) {
                        localAirlist[aircount][5] = "";
                        localAirlist[aircount][6] = "";
                        noPort = false;
                    }
                }
                String subpiece = piece.split(">")[1];
                String subrep = subpiece.replace("&nbsp;", "");                 //casp&nbsp
                String ampm;
                String time;
                if (!subrep.equals(" ")) {
                    ampm = Character.toString(subrep.charAt(5));
                    time = subrep.substring(0, 5);
                } else {
                    ampm = "";
                    time = "";
                }
                if (aircount > -1) {
                    if (localAirlist[aircount][timecount] == null && localAirlist[aircount][timecount + 1] == null) {
                        localAirlist[aircount][timecount] = time;
                        localAirlist[aircount][timecount + 1] = ampm;
                    }

                }

            }

            Pattern p2 = Pattern.compile("span title=\\\".*\\\">");             //span title="this">, /aerolinka, typ letadla
            Matcher matchp2 = p2.matcher(piece);
            if (matchp2.matches()) {
                noPort = true;
                piece = piece.substring(12, piece.length() - 2);                //bud FlyDubai &quot;&quot; (United Arab Emirates) nebo Boeing 737-800 (twin-jet)
                String[] piecearr = piece.split("&quot");
                Pattern unknownOwn = Pattern.compile("Unknown Owner \\(.*\\)");
                Matcher matchUn = unknownOwn.matcher(piece);
                if (piecearr.length == 1 && !(matchUn.matches())) {
                    piece = piecearr[0];                                        //pripad typ letadla
                    String replace = piece.replace(";|| ", "");
                    if (aircount > -1) {
                        localAirlist[aircount][3] = replace;
                    }
                } else {
                    aircount++;                                                 ////////////////////reset array, new flight
                    timecount = 7;                                              ////////////////////reset array, new flight
                    if (matchUn.matches()) {                                    //Unknown owner
                        localAirlist[aircount][0] = "Unknown owner";
                        localAirlist[aircount][1] = piece.substring(15, piece.length() - 1);
                    } else {
                        String piece1 = piecearr[0];
                        localAirlist[aircount][0] = piece1;                          //airline
                        String piece2 = piecearr[piecearr.length - 1];          //State
                        if (piece2.equals(";")) {
                            localAirlist[aircount][1] = "";
                        } else {
                            String stateflag = piece2.substring(3, piece2.length() - 1);
                            localAirlist[aircount][1] = stateflag;
                        }
                    }
                }
            }

            Pattern p3 = Pattern.compile("a href=\\\"\\/live\\/flight.*\\\">.*");//Flight code functioning
            Matcher matchp3 = p3.matcher(piece);
            if (matchp3.matches()) {
                piece = piece.split(">")[piece.split(">").length - 1];
                String replace = piece.replace(";", "");
                localAirlist[aircount][2] = replace;
            }

            Pattern p4 = Pattern.compile("a href=\\\"\\/live\\/aircrafttype.*\\\">.+");//Aircraft type - a href issue
            Matcher matchp4 = p4.matcher(piece);

            if (matchp4.matches()) {

                piece = piece.split(">")[piece.split(">").length - 1];
                String replace = piece.replace(";", "");
                localAirlist[aircount][4] = replace;
            }

            Pattern p5 = Pattern.compile("span dir=\"ltr\">\\w+.*");            //Airport name functioning
            Matcher matchp5 = p5.matcher(piece);
            if (matchp5.matches()) {
                noPort = false;
                piece = piece.split(">")[piece.split(">").length - 1];
                String replace = piece.replace(";", "");
                localAirlist[aircount][5] = replace;
            }
            Pattern p6 = Pattern.compile("a href=\\\"\\/live\\/airport.*\\\">.*"); //airport code alright
            Matcher matchp6 = p6.matcher(piece);
            if (matchp6.matches()) {
                if (aircount > -1) {
                    if (noPort) {
                        noPort = false;
                        localAirlist[aircount][5] = "";
                    }
                    piece = piece.split(">")[piece.split(">").length - 1];
                    String replace = piece.replace(";", "").replace("Buy PRG / LKPR Excel flight history", "");
                    localAirlist[aircount][6] = replace;
                }
            }

            Pattern p7 = Pattern.compile("span class=\\\"tz\\\">.*");           //timezone 
            Matcher matchp7 = p7.matcher(piece);
            if (matchp7.matches()) {
                piece = piece.split(">")[piece.split(">").length - 1];
                String replace = piece.replace(";", "");
                localAirlist[aircount][timecount + 2] = replace;
                timecount = 10;

            }
        }
        return localAirlist;
    }

    /**
     * Getter
     *
     * @return String [][] with all flight information
     */
    public String[][] getAirlist() {
        return airlist;
    }

    /*
    public static void main(String[] args) throws IOException {
        Scraper sc = new Scraper("https://flightaware.com/live/airport/LKPR");
        sc.scrape();
        System.out.println(Arrays.deepToString(sc.getAirlist()));
    }*/
}
