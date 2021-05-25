/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Aircraft;
import java.util.Comparator;

/**
 * Comparator by destination (alphabetical) for Aircraft objects
 *
 * @author sarka
 */
public class ComparatorByDestination implements Comparator<Aircraft> {

    /**
     *
     * @param o1 - aircraft1
     * @param o2 - aircraft2
     * @return int - comparison coefficient
     */
    @Override
    public int compare(Aircraft o1, Aircraft o2) {
        if (o1.getDestination() == null) {
            o1.setDestination("");
        }
        if (o2.getDestination() == null) {
            o2.setDestination("");
        }

        return o1.getDestination().compareTo(o2.getDestination());
    }

}
