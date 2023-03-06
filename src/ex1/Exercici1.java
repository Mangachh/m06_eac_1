package ex1;

import java.io.File;
/**
 *
 * @author Lluís Cobos Aumatell
 */
public class Exercici1 {

    private static final int ARGS_MIN_SIZE = 4;
    private static final int ARGS_MAX_SIZE = 6;
    private static final String ARGS_DELETE_CHAR = "E";
    private static final String ARGS_LIST_CHAR = "L";
    private static final String ARGS_SIZE_CHAR = "M";
    private static final String ARGS_NAME_CHAR = "N";
    private static final String ARGS_HIDDEN_CHAR = "H";

    private static final int ARGS_PATH_POS = 0;
    private static final int ARGS_DELETE_POS = 1;
    private static final int ARGS_LIST_POS = 1;

    private static final int ARGS_SIZE_POS = 2;
    private static final int ARGS_NAME_POS = 2;
    
    // structure -> n <name> h 
    private static final int ARGS_HIDDEN_NEXT_TO_NAME_POS = ARGS_NAME_POS + 2;
    
    // structure -> s <min_size> <max_size> h
    private static final int ARGS_HIDDEN_NEXT_TO_SIZE_POS = ARGS_NAME_POS + 3;

    private static final String ERROR_POS_FORMAT = "L'Argument a posició %d ha de ser o bé \"%s\" o bé \"%s\"";
    private static final String ERROR_INT_FORMAT = "Els arguments a la posició %d i %d han de ser números enters";
    
    
    /**
     * L'aplicació agafa tres arguments on:
     *      1er - ruta d'una carpeta
     *      2n - 'E' ó 'L', ignoreCase:
     *              - si 'E', llavors eliminarem els arxius
     *              - si 'L', llistarem tant arxius com carpetes
     *      3er - 'M' ó 'N', ignoreCase
     * Si cap d'aquestes tres condicions es cumpleix, es registra
     * el missatge d'error a {@link System.err} i surt de l'aplicació
     * amb codi dos.
     * 
     * Si el tercer argument és 'M', aquest ha d'anar seguit d'un quart i cinqué arguments enters
     * que son la mida mínima i màxima del fitxers a considerar. Si volem llegir també els fitxers ocults
     * el sisé argument serà una 'H' ignoreCase
     * 
     * Si el tercer argument és 'N', llavors aquest ha d'anar seguit amb una cadena de text que
     * indicarà l'inici del arxius-directoris a considerar. Si volem llegir els fitxers ocults, el 
     * cinqué argument será una 'H'
     * 
     * Per tant, a l'hora de llistar o eliminar arxius que comencin per <start>
     * <path> E/L N <start> H (optativa)
     * 
     * A l'hora de llistar o eliminar arxius entre un <minSize> i un <maxSize>
     * <path> E/L M <minSize> <maxSize> H (optativa).
     * 
     * Si els arguments son insuficients {@link ARGS_MIN_SIZE} o sobrepassen la màxima quantitat requerida {@link ARGS_MAX_SIZE}
     * s'escriu missatge d'error i surt del programa amb codi 2.
     * @param args 
     */
    public static void main(String[] args) {        
        final String path = args[ARGS_PATH_POS];
        
        // list(l) or delete(d)
        final String secondArg = args[1];
        
        // name (n) or size(m)
        final String thirdArg = args[2];
        
        boolean includeDirectories;
        File[] files;
        
        // check path and pos, return if wrong
        if(hasArgumentLengthErrors(args.length) || hasArgumentPathErrors(path)){
            return;
        }
        
        // check second argument, return if wrong
        if(secondArg.equalsIgnoreCase(ARGS_LIST_CHAR)){
            includeDirectories = true; // list includes directories
        }else if(secondArg.equalsIgnoreCase(ARGS_DELETE_CHAR)){
            includeDirectories = false; // we only delete files, so don't need directories
        }else{
            System.err.println(String.format(ERROR_POS_FORMAT, ARGS_LIST_POS + 1, ARGS_LIST_CHAR, ARGS_DELETE_CHAR));
            return; 
        }       
        
        // check third argument and get files
        if(thirdArg.equalsIgnoreCase(ARGS_NAME_CHAR)){
            boolean hidden = (args.length >= 5 && args[ARGS_HIDDEN_NEXT_TO_NAME_POS].equalsIgnoreCase(ARGS_HIDDEN_CHAR));            
            files = FileUtils.getFileListByName(path, args[ARGS_NAME_POS + 1], includeDirectories, hidden);  
            
        }else if (thirdArg.equalsIgnoreCase(ARGS_SIZE_CHAR)){            
            boolean hidden = (args.length == 6 && args[ARGS_HIDDEN_NEXT_TO_SIZE_POS].equalsIgnoreCase(ARGS_HIDDEN_CHAR));
            
            try{ 
                long minSize = Long.parseLong(args[ARGS_SIZE_POS + 1]);
                long maxSize = Long.parseLong(args[ARGS_SIZE_POS + 2]);
                files = FileUtils.getFileListBySize(path, minSize, maxSize, includeDirectories, hidden);
            }catch(NumberFormatException e){
                System.err.println(String.format(ERROR_INT_FORMAT, ARGS_SIZE_POS + 2, ARGS_SIZE_POS + 3));
                return;
            }            
        }else{
            System.err.println(String.format(ERROR_POS_FORMAT, ARGS_SIZE_POS + 1, ARGS_SIZE_CHAR, ARGS_NAME_CHAR)); 
            return;
        }
        
        //we have the files, delete or list
        if(secondArg.equalsIgnoreCase(ARGS_LIST_CHAR)){
            FileUtils.printFiles(files);
        }else{
            FileUtils.removeFiles(files);
        }
    }        
   
    
    /**
     * Comprova els erros de quantitat dels arguments  
     * 
     * @param length -> quantitat d'arguments
     * @return -> si length >= {@link ARGS_MIN_SIZE} && <= {@link ARGS_MAX_SIZE} retorna true;
     *            otherwise false;
     */
    private static boolean hasArgumentLengthErrors(int length) {
        if (length < ARGS_MIN_SIZE || length > ARGS_MAX_SIZE) {
            System.err.println(
                    String.format("El nombre de paràmetres ha de ser entre %d i %d", ARGS_MIN_SIZE, ARGS_MAX_SIZE));
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param path
     * @return 
     */
    private static boolean hasArgumentPathErrors(final String path) {        
        if (FileUtils.isDirectory(path) == false) {
            System.err.println("El primer paràmetre no és un directori");
            return true;
        }
        return false;
    }     
}
