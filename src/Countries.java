import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class Countries {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap<>();
        ArrayList<Country> countryList = new ArrayList<>();

        File f = new File("countries.txt");
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
        String fileContent = "";
        for (Country temp : countryList) {
           if (temp.name.startsWith(userInput)) {
               fileContent += temp.name+"\n";
           }
        }
        writeFile(fileName, fileContent);


    } //end main


    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }

}//end class