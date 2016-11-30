//Bradley Dufour
//2016-08-25
//Crop class for games with consistently growing crops
import java.util.Arrays;
public class Crop
{
   private String name;
   private int growTime;
   private int plantDate;
   private int[] season;
   public Crop(String name, int growTime, int[] season)
   {
      this.name = name;
      this.growTime = growTime;
      this.season = season;
      Arrays.sort(season);
   }
   //-------------------------------------------------
   //Resets plantDate of crop in the appropriate month
   //-------------------------------------------------
   public void resetPlantDate(int currSeason)
   {
      if(season[season.length-1] == currSeason || (currSeason == 1 && season[0] + season[season.length-1] == 5 && !(season[1] == 2)))
      {
         plantDate = 0;
      }
   }
   //-------------------------------------------------
   //Adds a plantable season to the information of this Crop
   //-------------------------------------------------
   public int[] addSeason(int new)
   {
      int[] temp = season;
      season = new int[temp.length+1];
   }
   //-------------------------------------------------
   //Override from String
   //Prints relevant information about this Crop
   //-------------------------------------------------
   public String toString()
   {
      //TODO Fill in information needing to be printed
      String
      return ("Information about "+ this.name +":\nIt grows in ");
   }
}