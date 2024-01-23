package util;

import java.lang.reflect.Field;
import java.util.List;

public class Util {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Print a list of given class in a table format
    public static void printTable(List<?> list) {
        // Get the class of the first element in the list
        Class<?> c = list.get(0).getClass();
        // Get the declared fields of the class
        Field[] fields = c.getDeclaredFields();
        // Get the number of fields
        int numberOfFields = fields.length;
        // Get the number of elements in the list
        int numberOfElements = list.size();
        // Create a 2D array to store the values of the fields
        String[][] values = new String[numberOfElements][numberOfFields];
        // Get the values of the fields
        for (int i = 0; i < numberOfElements; i++) {
            for (int j = 0; j < numberOfFields; j++) {
                try {
                    // Get the value of the field
                    Field f = fields[j];
                    f.setAccessible(true);
                    Object value = c.getDeclaredField(f.getName()).get(list.get(i));
                    // Store the value in the 2D array
                    values[i][j] = value.toString();
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        // Get the length of the longest value in each column
        int[] columnLengths = new int[numberOfFields];
        for (int i = 0; i < numberOfFields; i++) {
            int maxLength = 0;
            for (int j = 0; j < numberOfElements; j++) {
                if (values[j][i].length() > maxLength) {
                    maxLength = values[j][i].length();
                }
            }
            columnLengths[i] = maxLength;
        }
        // Print the table
        for (int i = 0; i < numberOfElements; i++) {
            for (int j = 0; j < numberOfFields; j++) {
                System.out.print(values[i][j]);
                for (int k = 0; k < columnLengths[j] - values[i][j].length() + 1; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
