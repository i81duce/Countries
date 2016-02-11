import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class Countries {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap<>();

        Scanner input = new Scanner(System.in);

        String fileContents = readFile();
        String[] allCountryArray = fileContents.split("\n");

        for (String tempCountry : allCountryArray) {
            String firstLetter = String.valueOf(tempCountry.charAt(3));
            String[] columns = tempCountry.split("\\|");
            String abbr = columns[0];
            String name = columns[1];
            Country c = new Country(name, abbr);
            if (!countriesMap.containsKey(firstLetter)) { //if the hashmap doesn't have a key value of this current countries firstLetter
                countriesMap.put(firstLetter, new ArrayList<Country>()); //we add a new spot in the hashmap with an empty array list
            }
            countriesMap.get(firstLetter).add(c); //then we add our country object "c" to the arraylist in the hashmap
        }

        System.out.println("Please enter a letter:");
        String userInput = input.nextLine();
        if (userInput.length() != 1) {
            throw new Exception("Invalid entry");
        }
        String fileName = String.format("%s_countries.txt", userInput.toUpperCase());
        String contentsToBeWritten = "";
        for (Country tempCountry : countriesMap.get(userInput.toLowerCase())) {
            contentsToBeWritten += tempCountry.name + " " + tempCountry.abbr + "\n";
        }
        writeFile(fileName, contentsToBeWritten);








    } //end main


    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
    static String readFile() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        fileScanner.useDelimiter("\\Z");
        String fileContents;
        fileContents = fileScanner.next();
        return fileContents;
    }

}//end class



//A functional option that ignores the HashMap and just populates and ArrayList and saves that info to a .txt file
        /*File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[1], columns[0]);
            countryList.add(country);
        }
        System.out.println("Please type a letter:");
        String userInput = input.nextLine();
        String fileName = String.format("%s_countries.txt", userInput.toUpperCase());
        ArrayList<Country> tempList = new ArrayList<>();
        for (Country temp : countryList) {
           if (temp.name.startsWith(userInput)) {
              tempList.add(temp);
           }
        } */