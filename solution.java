import java.io.*;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class solution {
        public static void main(String []args) throws Exception {
            solvepart2();
        }

        private static void solvepart1() throws Exception {
            Dictionary<String, Integer> coloramountsDict = new Hashtable<>();
            coloramountsDict.put("red", 12);
            coloramountsDict.put("green", 13);
            coloramountsDict.put("blue", 14);

            int sum = 0;
            String[] data = fileRead("input.txt");

            for(int lineNum = 0; lineNum < data.length; lineNum++) {
                Boolean validGame = true;
                String[] splitLine = data[lineNum].split(":");
                int gameID = Integer.valueOf(splitLine[0].split(" ")[1]);
                
                String[] sets = splitLine[1].split(";");
                for(int setNum = 0; setNum < sets.length; setNum++) {
                    
                    String[] colorAmounts = sets[setNum].split(",");
                    for(int colorAmountNum = 0; colorAmountNum < colorAmounts.length; colorAmountNum++) {
                        String[] colorAmountVals = colorAmounts[colorAmountNum].trim().split(" ");
                        int amount = Integer.valueOf(colorAmountVals[0]);
                        String currentColor = colorAmountVals[1];

                        Enumeration colors = coloramountsDict.keys();
                        while (colors.hasMoreElements()) {
                            String color = colors.nextElement().toString();
                            if (currentColor.equals(color)) {
                                int maxamount = coloramountsDict.get(color);
                                if (amount > maxamount) {
                                    validGame = false;
                                }
                            }
                        }
                    }
                }
                if (validGame) {
                    sum = sum + gameID;
                }
            }
            System.out.println(sum);
        }

        private static void solvepart2() throws Exception {
            
            int sum = 0;
            String[] data = fileRead("input.txt");

            for(int lineNum = 0; lineNum < data.length; lineNum++) {
                Dictionary<String, Integer> coloramountsDict = new Hashtable<>();
                coloramountsDict.put("red", 0);
                coloramountsDict.put("green", 0);
                coloramountsDict.put("blue", 0);

                String[] splitLine = data[lineNum].split(":");
                int gameID = Integer.valueOf(splitLine[0].split(" ")[1]);
                
                String[] sets = splitLine[1].split(";");
                for(int setNum = 0; setNum < sets.length; setNum++) {
                    
                    String[] colorAmounts = sets[setNum].split(",");
                    for(int colorAmountNum = 0; colorAmountNum < colorAmounts.length; colorAmountNum++) {
                        String[] colorAmountVals = colorAmounts[colorAmountNum].trim().split(" ");
                        int amount = Integer.valueOf(colorAmountVals[0]);
                        String currentColor = colorAmountVals[1];

                        Enumeration colors = coloramountsDict.keys();
                        while (colors.hasMoreElements()) {
                            String color = colors.nextElement().toString();
                            if (currentColor.equals(color)) {
                                int currentamount = coloramountsDict.get(color);
                                if (amount > currentamount) {
                                    coloramountsDict.put(color, amount);
                                }
                            }
                        }
                    }
                }
                int powers = coloramountsDict.get("red") * coloramountsDict.get("green") * coloramountsDict.get("blue");
                sum = sum + powers;
            }
            System.out.println(sum);
        }

        private static String[] fileRead(String name) throws Exception {
            File file = new File(name);
            Scanner scanner = new Scanner(file);
            String data = scanner.nextLine();
            while (scanner.hasNextLine()) {
                data = data + "\n" + scanner.nextLine();
            }
            scanner.close();
            String[] splitdata = data.split(System.lineSeparator());
            return splitdata;
        }

}