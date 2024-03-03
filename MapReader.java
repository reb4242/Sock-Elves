    /**
     * This class is used by the human to search for all the sock locations.
     * Output a list of all the locations [row][column] to the terminal window.
     *
     * @author (Rebecca)
     * @version (Nov.21st/23)
     */
    //import File IO library
    import java.io.*;
    //import all java util library
    import java.util.*;
        // Needed for use of Scanner and NoSuchElementException
    public class MapReading{
        //declare variables
        String strFile;
            //the file which needs to be read
        
        //constructors
        public MapReading(String f){
            //parameter: f is the file being read
            
            //initialize variables
            this.strFile = f;
            
            //read map from file
            ReadMap();
        }
        //read the file by using scanners
        public void ReadMap(){
            //map reading
            try {
                //create scanner
                Scanner in = new Scanner(new FileReader(this.strFile));
                
                //read from file
                //loop through the rows
                System.out.println("In " + this.strFile + ", socks are found at: ");
                for (byte Row = 0; Row < 6; Row++){
                    String strRow = in.nextLine();
                    //loop through the columns
                    for(byte Col = 0; Col < 10; Col++){
                        if (strRow.charAt(Col * 2) == 'S'){
                            System.out.println("[" + Row + "]" + "[" + Col + "]");
                        }
                    }
                    //reset value read from each row
                    strRow = "";
                }
                
                //close file when done to prevent runtime errors
                in.close();
            } catch (FileNotFoundException e){
                //file not found
                System.out.println("Error: Cannot open file for reading");
            } catch (NoSuchElementException e){
                //file found, cannot be read
                System.out.println("Error: EOF encountered, file may be corrupt");
            } catch (IOException e){
                //no read and write rights for the file
                System.out.println("Error: Cannot read from file");
            }
        }
    }
