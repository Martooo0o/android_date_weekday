
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;  // Import the File class
import java.io.IOException;
import java.nio.file.*;
class GenDates{
    static DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    static DateFormat df2 = new SimpleDateFormat("EEEE", Locale.GERMANY);

    static String fileName = "gen_dates_german.txt";

    public static void main(String args[]){  
        //String date = "09.07.253";

        String startDateStr = "01.01.200";
        Date startDate = null;
        try{
            startDate = df.parse(startDateStr);    
        }catch(Exception e){
            e.printStackTrace();
        }

        String endDateStr = "02.01.2020";
        Date endDate = null;
        try{
            endDate = df.parse(endDateStr);    
        }catch(Exception e){
            e.printStackTrace();
        }

        deleteFile(fileName);
        createNewfile();

        do{ 
            writeToFile(df.format(startDate) + ": " + englishToGerman(getDayOfTheWeek(startDate)) + "\n", true);
            startDate = addDay(startDate);

        }while(startDate.before(endDate));
       
    }

    public static Date addDay(Date date){
       Calendar cal = Calendar.getInstance();
       cal.setTime (date);
       cal.add (Calendar.DATE, 1);
       return cal.getTime();
    }

    private static String getDayOfTheWeek(Date date1){
        String day="";
        if(date1!=null){
            day = new SimpleDateFormat("EEEE").format(date1);    
        }
        return day;
    }

    private static void deleteFile(String filename){
        Path path
            = Paths.get(filename);
        try {
  
            Files.deleteIfExists(path);
        }
        catch (IOException e) {
  
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void createNewfile(){
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeToFile(String str, boolean appendStr){
        try {
          FileWriter myWriter = new FileWriter(fileName, appendStr);
          myWriter.write(str);
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }

    private static String englishToGerman(String day){
        switch (day) {
            case "Monday":
                return "Montag";
            case "Tuesday":
                return "Dienstag";
            case "Wednesday":
                return "Mittwoch";
            case "Thursday":
                return "Donnerstag";
            case "Friday":
                return "Freitag";
            case "Saturday":
                return "Samstag";
            case "Sunday":
                return "Sonntag";
        }
        return "";
    }

}  