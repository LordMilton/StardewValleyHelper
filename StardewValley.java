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
import java.io.*;
import java.util.*;

public class StardewValley
{
   static final String FILENAME = "Characters.txt";
   static final int NCHARACTERS = 33;
   static ArrayList<String> miscInfo = new ArrayList<String>();
   public static int findPerson(String name, Villager[] charList)
   {
      for(int i = 0; i < charList.length; i++)
      {
         if(charList[i].getName().equalsIgnoreCase(name))
            return i;
      }
      return(-1);
   }
   public static void doAThing(Villager[] charList, int choice)
   {
      Scanner scan = new Scanner(System.in);
      switch(choice)
      {
         //Add a 'loved' gift?
         case 1:
            System.out.print("Whom does this apply to? ");
            String person = scan.next();
            System.out.print("\nWhat is the gift? ");
            String gift = scan.next();
            charList[findPerson(person, charList)].addLove(gift);
            writeToFile(charList);
            break;
         //Add a 'liked' gift?
         case 2:
            System.out.print("Whom does this apply to? ");
            person = scan.next();
            System.out.print("\nWhat is the gift? ");
            gift = scan.next();
            charList[findPerson(person, charList)].addLike(gift);
            writeToFile(charList);
            break;
         //Add a 'disliked' gift?
         case 3:
            System.out.print("Whom does this apply to? ");
            person = scan.next();
            System.out.print("\nWhat is the gift? ");
            gift = scan.next();
            charList[findPerson(person, charList)].addDislike(gift);
            writeToFile(charList);
            break;
         //See all information about a certain gift??
         case 4:
            System.out.print("What is the gift? ");
            gift = scan.next(); scan.nextLine();
            ArrayList<Villager> lovers = new ArrayList<Villager>(0);
            for(Villager v: charList)
            {
               for(String g: v.getLove())
               {
                  if(g.equalsIgnoreCase(gift))
                     lovers.add(v);
               }
            }
            if(lovers.isEmpty())
               System.out.println("No one appears to love "+ gift +".");
            else
            {
               for(Villager v: lovers)
                  System.out.print(v.getName() +", ");
               System.out.print("love receiving "+ gift +".");
               System.out.println();
            }
            //***************************************************************
            //Like
            ArrayList<Villager> likers = new ArrayList<Villager>(0);
            for(Villager v: charList)
            {
               for(String g: v.getLike())
               {
                  if(g.equalsIgnoreCase(gift))
                     likers.add(v);
               }
            }
            if(likers.isEmpty())
               System.out.println("No one appears to like "+ gift +".");
            else
            {
               for(Villager v: likers)
                  System.out.print(v.getName() +", ");
               System.out.print("like receiving "+ gift +".");
               System.out.println();
            }
            //***************************************************************
            //Dislike
            ArrayList<Villager> dislikers = new ArrayList<Villager>(0);
            for(Villager v: charList)
            {
               for(String g: v.getDislike())
               {
                  if(g.equalsIgnoreCase(gift))
                     dislikers.add(v);
               }
            }
            if(dislikers.isEmpty())
               System.out.println("No one appears to dislike "+ gift +".");
            else
            {
               for(Villager v: dislikers)
                  System.out.print(v.getName() +", ");
               System.out.print("dislike receiving "+ gift +".");
               System.out.println();
            }
            System.out.println("[Enter to move on]");
            scan.nextLine();
            break;
         //See all information about a certain person?
         case 5:
            System.out.print("To whom does this apply? ");
            person = scan.next(); scan.nextLine();
            if(charList[findPerson(person, charList)].getLove().isEmpty())
               System.out.print(person +" seems to love nothing.");
            else
            {
               for(String g: charList[findPerson(person, charList)].getLove())
                  System.out.print(g +", ");
               System.out.print("are loved by "+ person);
            }
            System.out.println();
            //*********************************************************************
            //Like
            if(charList[findPerson(person, charList)].getLike().isEmpty())
               System.out.print(person +" seems to like nothing.");
            else
            {
               for(String g: charList[findPerson(person, charList)].getLike())
                  System.out.print(g +", ");
               System.out.print("are liked by "+ person);
            }
            System.out.println();
            //*********************************************************************
            //Dislike
            if(charList[findPerson(person, charList)].getDislike().isEmpty())
               System.out.print(person +" seems to dislike nothing.");
            else
            {
               for(String g: charList[findPerson(person, charList)].getDislike())
                  System.out.print(g +", ");
               System.out.print("are disliked by "+ person);
            }
            System.out.println();
            System.out.println("[Enter to move on]");
            scan.nextLine();
            break;
         //Check if a person is single?
         case 6:
            System.out.print("To whom does this apply? ");
            person = scan.next(); scan.nextLine();
            if(charList[findPerson(person, charList)].isSingle())
               System.out.println(person +" is single.");
            else
               System.out.println(person +" is not single.");
            System.out.println("[Enter to move on]");
            scan.nextLine();
            break;
         //Set a person's birthday?
         case 7:
            System.out.print("To whom does this apply? ");
            person = scan.next(); scan.nextLine();
            System.out.print("What is said person's birthday?" );
            String date = scan.nextLine();
            charList[findPerson(person, charList)].setBirthday(date);
            break;
         //Check if there are any birthdays today?
         case 8:
            System.out.print("What is the date? ");
            date = scan.nextLine();
            boolean birthdayFound = false;
            for(Villager person8:charList)
            {
               if((person8.getBirthday().equals(date)) && !birthdayFound)
               {
                  System.out.println(person8.getName() +"'s birthday is today, "+ date +".");
                  birthdayFound = true;
               }
            }
            if(!birthdayFound)
               System.out.println("No one's birthday is today, "+ date +".");
            System.out.println("[Enter to move on]");
            scan.nextLine();
            break;
         //Add any miscellaneous information?
         case 9:
            System.out.println("What information?");
            miscInfo.add(scan.nextLine());
            break;
         //View all miscellaneous information?
         case 10:
            System.out.println("Keyword?");
            String keyword = scan.nextLine();
            for(String info:miscInfo)
            {
               if(info.toUpperCase().indexOf(keyword.toUpperCase()) >= 0)
                  System.out.println(info);
            }
            System.out.println();
            System.out.println("[Enter to move on]");
            scan.nextLine();
            break;
         //Save and quit?
         case 11:
            writeToFile(charList);
            System.exit(0);
         default:
            System.out.println("That was not an applicable choice");   
      }
   }
   public static void printMenu()
   {
      System.out.println("Would you like to:");
      System.out.println("1) Add a 'loved' gift?");
      System.out.println("2) Add a 'liked' gift?");
      System.out.println("3) Add a 'disliked' gift?");
      System.out.println("4) See all information about a certain gift?");
      System.out.println("5) See all information about a certain person?");
      System.out.println("6) Check if a person is single?");
      System.out.println("7) Set a person's birthday?");
      System.out.println("8) Check if there are any birthdays today?");
      System.out.println("9) Add any miscellaneous information");
      System.out.println("10) View all miscellaneous information?");
      System.out.println("11) Save and quit?");
   }
   public static void updateCharList(String info, Villager[] charList, int charNum)
   {
      String[] charInfo = info.split(" ");
      String birthday = charInfo[charInfo.length - 2] +" "+ charInfo[charInfo.length - 1];
      String name = charInfo[0];
      boolean single = false;
      if(charInfo[charInfo.length - 3].equals("true"))
         single = true;
      Villager villager = new Villager(name, single, birthday);
      int position = 2;
      while(!charInfo[position].equals("like"))
      {
         villager.addLove(charInfo[position]);
         position++;
      }
      position++;
      while(!charInfo[position].equals("dislike"))
      {
         villager.addLike(charInfo[position]);
         position++;
      }
      position++;
      for(position = position; position < charInfo.length-3; position++)
         villager.addDislike(charInfo[position]);
      charList[charNum-1] = villager;
   }
   public static void writeToFile(Villager[] charList)
   {
      try
      {
         FileWriter write = new FileWriter(FILENAME);
         BufferedWriter writer = new BufferedWriter(write);
         for(Villager c: charList)
         {
            writer.write(c.getName() +" love ");
            for(String g: c.getLove())
               writer.write(g +" ");
            writer.write("like ");
            for(String g: c.getLike())
               writer.write(g +" ");
            writer.write("dislike ");
            for(String g: c.getDislike())
               writer.write(g+" ");
            writer.write(c.isSingle() +" ");
            writer.write(c.getBirthday());
            writer.newLine();
         }
         for(String info:miscInfo)
         {
            writer.write(info);
            writer.newLine();
         }
         writer.close();
      }
      catch(Exception e)
      {
         System.err.println("Failed to write to file");
         System.exit(0);
      }
   }
   //***********************************************************************
   //***********************************************************************
   public static void main(String[] args)throws Exception
   {
      //Filling array from file before accepting user input
      Scanner scan = new Scanner(new java.io.File(FILENAME));
      Villager[] charList = new Villager[NCHARACTERS];
      for(int i = 1; i <= NCHARACTERS; i++)
      {
         String nextLine = scan.nextLine();
         updateCharList(nextLine, charList, i);
      }
      while(scan.hasNextLine())
      {
         miscInfo.add(scan.nextLine());
      }
      //User input
      scan = new Scanner(System.in);
      do
      {
         printMenu();
         int answer = scan.nextInt();
         doAThing(charList, answer);
      }
      while(true);
   }
   //***********************************************************************
   //***********************************************************************
}