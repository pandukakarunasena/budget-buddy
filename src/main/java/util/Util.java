/**
 * This class contains utility methods that are used throughout the project.
 */
package util;

import java.lang.reflect.Field;
import java.util.List;

public final class Util {

    /**
     * Private constructor for Util to prevent instantiation.
     */
    private Util() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Print List of Objects.
     *
     * @param list List
     */
    public static void printList(List<?> list) {
        // TODO: Fix indentation
        System.out.println("--------------------------------------------------");
        Class<?> c = list.get(0).getClass();
        Field[] allFields = c.getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            System.out.print(field.getName() + "\t\t");
        }
        System.out.println();
        System.out.println("--------------------------------------------------");
        for (Object o : list) {
            System.out.println(o.toString());
        }
        System.out.println("--------------------------------------------------");
    }
}
