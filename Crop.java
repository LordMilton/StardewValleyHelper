/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stardewvalley;

import java.util.ArrayList;
/**
 * @author Bradley Dufour
 * @date 2016-12-03
 */
public class Crop {
    private int sellPrice;
    private String name;
    private int seedCost;
    private ArrayList<Season> growSeason;
    private int timeToGrow;
    
    public Crop(int sellPrice, String name, int seedCost, ArrayList<Season> growSeason,
                int timeToGrow)
    {
        this.sellPrice = sellPrice;
        this.name = name;
        this.seedCost = seedCost;
        this.growSeason = growSeason;
        this.timeToGrow = timeToGrow;
    }
}
