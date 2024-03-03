/**
 * This class is used by the elves to create a map of scattered socks across the floor.
 * Print the map to a file, and output where the file was saved in the terminal window.
 *
 * @author (Rebecca)
 * @version (Nov.20th/23)
 */
//import File IO library
import java.io.*;
public class MapCreator{
    //declare variables
    public static byte bytMapID = 0;
        //keep track of the number of maps created
        //static --> same variable across all maps created
    
    //declare 2D array of type char
    public char[][] arrMap;
    
    //constructor
    public MapCreator(){
        //initialize variables
        this.bytMapID += 1;
            //increase the map ID by one every time a new map is created
        
        //initialize a 2Darray of char
        this.arrMap = new char[6][10];
            //6 is the num of rows, 10 is the num of columns
        
        //populate the map by calling method
        CreateMap();
    }
    
    //create map
    public void CreateMap(){
        //S represent the socks, and F represent the floor
        
        //populate the 2Darray with 'F'
        for (byte Row = 0; Row < 6; Row++){
            for (byte Col = 0; Col < 10; Col++){
                this.arrMap[Row][Col] = 'F';
            }
        }

        //populate the 2D array with 'S'
        this.arrMap = HideSocks(arrMap, (byte)10);
        
        //write map to file
        WriteMap();
    }
    
    //populate the map using a recursive method
    public static char[][] HideSocks(char[][] arrMap, byte bytSocks){
        /*parameters: 
            arrMap: the array which will be populated
            bytSocks: the number of socks needed to be hidden
        */
        
        //base case
        if (bytSocks == 0){
            return arrMap;
        }
        
        //determine location of socks
            //create random numbers
            byte bytX = (byte)Math.floor(Math.random() * (5 - 0 + 1) + 0);
            byte bytY = (byte)Math.floor(Math.random() * (9 - 0 + 1) + 0);
                /*creating a random number within a range:
                (byte)Math.floor(Math.random() * (max - min + 1) + min)
                */
            
            //check if location already has sock
            if (arrMap[bytX][bytY] == 'S'){
                //if location already has a sock, no sock is placed in this run
                bytSocks += 1;
                    //increase sock count by one, meaning this sock needs to be hid again
            } else {
                //if location doesn't have a sock, populate location with sock
                arrMap[bytX][bytY] = 'S';
            }
        
        //recursive call
        return HideSocks(arrMap, (byte)(bytSocks - 1));
            //return the array, for further population
            //return the number of socks still needed to populate
    }
    
    //write map to file
    public void WriteMap(){
        try {
            //create PrintWriter to write to a file
            PrintWriter out = new PrintWriter(new FileWriter("Map#" + this.bytMapID + ".txt"));
            
            //write to the file
            //loop through the rows
            for (byte Row = 0; Row < this.arrMap.length; Row++){
                //loop through the columns
                for(byte Col = 0; Col < this.arrMap[0].length; Col++){
                   out.print(this.arrMap[Row][Col] + " ");
                }
                //start a new line
                out.println();
            }
            
            //confirmation through output panel
            System.out.println("Your map has been written to Map#" + this.bytMapID + ".txt");
           
            //close file when done to prevent runtime errors
            out.close();
        } catch (FileNotFoundException e) {
            //file not found
            System.out.println("Error: Cannot open file for writing");
        } catch (IOException e) {
            //no read and write rights for the file
            System.out.println("Error: Cannot write to file");
        }
    }
}
