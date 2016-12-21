package ua.goit.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;


/**
 * Created by Main Server on 16.12.2016.
 */
public class ConsoleHelper {

    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String line = bufferedReader.readLine();
        return line;
     }

    public static int readInt() throws NumberFormatException,IOException {
        int number = Integer.parseInt(bufferedReader.readLine());
        return number;
    }

    public static Date readDate() throws IllegalArgumentException,IOException {
        String str = bufferedReader.readLine();
        Date date = Date.valueOf(str);
        return date;
    }

}
