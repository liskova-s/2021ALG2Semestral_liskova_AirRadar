/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library for file operations 
 * @author sarka
 */
public class FileOperations {

    /**
     * Decides whether appendix refers to binary or text file and calls proper method
     * @param output - string to be saved to file
     * @param filename
     * @param lastRefresh
     * @param lastRef
     * @param tz
     * @throws IOException 
     */
    public static void save(String output, String filename, LocalTime lastRefresh, LocalDate lastRef, String tz) throws IOException {
        if (filename.substring(filename.length() - 4, filename.length()).equals(".txt")) {
            saveTxt(output, filename, lastRefresh, lastRef, tz);
        } else if (filename.substring(filename.length() - 4, filename.length()).equals(".bin")) {
            saveBin(output, filename, lastRefresh, lastRef, tz);
        }
    }

    /**
     * Saves as text to filename.txt file
     * @param output - string to be saved
     * @param filename
     * @param lastRefresh
     * @param lastRef
     * @param tz
     * @throws IOException 
     */
    public static void saveTxt(String output, String filename, LocalTime lastRefresh, LocalDate lastRef, String tz) throws IOException {
        File outfile = new File(filename);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outfile)))) {
            String lrt = lastRefresh.toString();
            String lrd = lastRef.toString();
            pw.print(lrt + " " + tz + " " + lrd + "\n");
            pw.println(output);
        }
    }

    /**
     * Saves as binary file to filename.bin
     * @param output
     * @param filename
     * @param lastRefresh
     * @param lastRef
     * @param tz
     * @throws IOException 
     */
    public static void saveBin(String output, String filename, LocalTime lastRefresh, LocalDate lastRef, String tz) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
           
            out.writeLong(lastRefresh.toSecondOfDay());
            out.writeUTF(tz);
            Date lastd=java.sql.Date.valueOf(lastRef);
            out.writeUTF(lastd.toString());
            out.writeUTF(output);
        }
    }

    /**
     * Loads csv to list of strings
     * @param filename
     * @param pos - position parameter of searched information
     * @return list of strings with derived content
     * @throws IOException 
     */
    public static List<String> load(String filename, int pos) throws IOException {
        List<String> codelist = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + File.separator + "ArtificialData" + File.separator + filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                codelist.add(parts[pos]);
            }
        }
        return codelist;
    }
}
