/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stardewvalley;

/**
 * @author Bradley Dufour
 * @date 2016-04-20
 */
//Villager class for games with villagers who like to receive certain gifts
import java.util.ArrayList;
public class Villager
{
   private String name; //The constructor will always capitalize the first char
                        //of this for sorting purposes
   private ArrayList<String> love;
   private ArrayList<String> like;
   private ArrayList<String> dislike;
   private String birthday;
   private boolean single;
   //------------------------------------------------
   //3-arg Constructor
   //@param name Villager's name
   //@param single true if Villager is single (and the wanted sex)
   //@param birthday Villager's birthday ([season] [day])
   //------------------------------------------------
   public Villager(String name, boolean single, String birthday)
   {
      this.name = capitalizeName(name);
      this.love = new ArrayList<String>(0);
      this.like = new ArrayList<String>(0);
      this.dislike = new ArrayList<String>(0);
      this.birthday = birthday;
      this.single = single;
   }
   //------------------------------------------------
   //1-arg Constructor
   //@param name Villager's name
   //------------------------------------------------
   public Villager(String name)
   {
       this(name, false, "Not Known");
   }
   
   /**
    * capitalizeName - Capitalizes the first letter of a String
    * @helper
    * 
    * @param name String to be capitalized
    * @return Capitalized version of the given String
    */
   private String capitalizeName(String name)
   {
       String capitalized = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
       return capitalized;
   }
   
   /**
    * getBirthday - Returns String birthday of Villager
    * @accessor
    * 
    * @return Villager's birthday
    */
   public String getBirthday()
   {
      return birthday;
   }
   
   /**
    * isSingle - Returns Villager's boolean single value
    * @accessor
    * 
    * @return Villager's single status
    */
   public boolean isSingle()
   {
      return single;
   }
   
   /**
    * getName - Returns Villager's name as a String
    * @accessor
    * 
    * @return Villager's name
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * getLove - Returns an ArrayList<String> containing all of Villager's known
    * loved items
    * @accessor
    * 
    * @return Villager's love ArrayList
    */
   public ArrayList<String> getLove()
   {
      return love;
   }
   
   /**
    * getLike - Returns an ArrayList<String> containing all of Villager's known
    * liked items
    * @accessor
    * 
    * @return Villager's like ArrayList
    */
   public ArrayList<String> getLike()
   {
      return like;
   }
   
   /**
    * getDislike - Returns an ArrayList<String> containing all of Villager's known
    * disliked items
    * @accessor
    * 
    * @return Villager's dislike ArrayList
    */
   public ArrayList<String> getDislike()
   {
      return dislike;
   }
   
   //------------------------------------------------
   //setBirthday
   //@param String
   //------------------------------------------------
   public void setBirthday(String date)
   {
      birthday = date;
   }
   
   //------------------------------------------------
   //addLove
   //@param Name of new gift the Villager loves receiving
   //------------------------------------------------
   public void addLove(String gift)
   {
      love.add(gift);
   }
   //------------------------------------------------
   //addLike
   //@param Name of new gift the Villager likes receiving
   //------------------------------------------------
   public void addLike(String gift)
   {
      like.add(gift);
   }
   
   //------------------------------------------------
   //addDislike
   //@param Name of new gift the Villager dislikes receiving
   //------------------------------------------------
   public void addDislike(String gift)
   {
      dislike.add(gift);
   }
}