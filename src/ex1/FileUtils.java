/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Lluís Cobos Aumatell
 */
public class FileUtils {
    
    /**
     * Checks if a string is a directory 
     * @param path -> path to check
     * @return -> is a directory?
     */
    public static boolean isDirectory(final String path) {
        File f = new File(path);

        return f.isDirectory();
    }

    /**
     * Returns a list of files (and directories if selected) that starts with the string provided
     * 
     * @param path -> path where the files are in
     * @param startsWith -> matching string
     * @param includeDirectories -> include directories into the list?
     * @param includeHidden -> include hidden files into the list?
     * @return -> list of {@link File}
     */
    public static File[] getFileListByName(final String path, final String startsWith, boolean includeDirectories,
            boolean includeHidden) {
        File f = new File(path);
        FileFilter filter = (file) -> {
            // System.out.println("Path from method: " + file.getName());
            if ((includeDirectories == false && file.isDirectory()) ||
                    includeHidden == false && file.isHidden() ||
                    file.canRead() == false) {
                return false;
            }
            // regex includes an optonial point at the start 'cause on linux the 
            // hidden folders-files starts with a point
            return file.getName().matches("^\\.?" + startsWith + ".*");
        };

        return f.listFiles(filter);
    }

    /**
     * Returns an array of files (and directories, if selected) between a minimun and a max size in bytes
     * 
     * @param path -> path where the files (and directories) are
     * @param minSize -> minimun size of the file to be included in the list
     * @param maxSize -> max size of the file to be included in the list
     * @param includeDirectories -> include directories in the list?
     * @param includeHidden -> include hidden in the list?
     * @return -> an array of files
     */
    public static File[] getFileListBySize(final String path, long minSize, long maxSize, boolean includeDirectories, boolean includeHidden) {
        // first get files and directories
        File f = new File(path);

        FileFilter filter = (file) -> {
            if ((includeDirectories == false && file.isDirectory()) ||
                 includeHidden == false && file.isHidden()) {
                return false;
            }

            return file.length() >= minSize && file.length() <= maxSize;
        };

        return f.listFiles(filter);
    }

    /**
     * Prints the files on the console with format
     * <name> <(D) if it's a directory> <(H) if it's hidden> <size> B
     * @param files -> files to print
     */
    public static void printFiles(final File[] files) {
        int numDir = 0;
        int numFiles = 0;
        long totalSize = 0;
        String toPrint;
        final String endMessage = "\n%d fitxers i %d directoris, %d B";

        System.out.println("Llistat de fitxers:");
        for (File file : files) {
            if (file.isDirectory()) {
                numDir++;
            } else {
                numFiles++;
            }
            
            toPrint = String.format("  - %s %s%s %d B", file.getName(), directorySuffix(file), hiddenSuffix(file), file.length());
            totalSize += file.length();
            System.out.println(toPrint);
        }

        System.out.println(String.format(endMessage, numFiles, numDir, totalSize));
    }

    /**
     * remove the files, not the directories, in the array and prints at the console
     * format:
     * Fitxer: "<path> <message>"
     * 
     * @param files
     */
    public static void removeFiles(final File[] files) {
        final String successMessage = "Fitxer: \"%s\" esborrat amb èxit";
        final String failureMessage = "Fitxer: \"%s\" no té permís d'escriptura";
        final String endMessage = "\n%d fitxer/s esborrat/s, %d B alliberats";
        int count = 0;
        long totalSize = 0;

        for (File file : files) {            
            if (file.canWrite()) {
                long size = file.length();
                if (file.delete()) {
                    System.out.println(String.format(successMessage, file.getAbsolutePath()));
                    count++;
                    totalSize += size;
                } else {
                    System.err.println(String.format(failureMessage, file.getAbsolutePath()));
                }
            } else {
                System.out.println(String.format(failureMessage, file.getAbsolutePath()));
            }
        }

        System.out.println(String.format(endMessage, count, totalSize));
    }

    /**
     * suffix for the directories when printing.
     * checks if a file is a directory and, if so, prints the suffix
     * @param f -> file to check
     * @return -> (D) as suffix if it's a directory, otherwise empty
     */
    private static String directorySuffix(final File f) {
        if (f.isDirectory()) {
            return "(D)";
        }

        return "";
    }

    /**
     * suffix for the hidden files when printing.
     * checks if a file is hidden and, if so, prints the suffix
     * @param f -> file to check
     * @return -> (H) as suffix if it's hidden, otherwise empty
     */
    private static String hiddenSuffix(final File f) {
        if (f.isHidden()) {
            return "(H)";
        }

        return "";
    }
}
