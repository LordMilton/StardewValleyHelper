/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stardewvalley;

/**
 * @author Bradley
 * @date 2016-04-20
 */
//Villager class for games with villagers who like to receive certain gifts
import java.util.ArrayList;
public class Villager
{
   private String name;
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
      this.name = name;
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
   public String getBirthday()
   {
      return birthday;
   }
   public boolean isSingle()
   {
      return single;
   }
   public String getName()
   {
      return name;
   }
   public ArrayList<String> getLove()
   {
      return love;
   }
   public ArrayList<String> getLike()
   {
      return like;
   }
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