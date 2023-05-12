package sg.edu.nus.iss.task01.src;

import java.io.Console;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {

        Console cons = System.console();
        String input = "";
        String first = "";
        String second = "";
        String op = "";
        Integer result = 0;

        System.out.println("Welcome.");

        while (!input.equals("exit".trim().toLowerCase())) {

            input = cons.readLine("> ");

            if (input.startsWith("exit")) {
                System.out.println("Bye bye");
            }

            else {

                Scanner scan = new Scanner (input);
                while (scan.hasNext()) {
                    first = scan.next();
                    op = scan.next();
                    second = scan.next();
                    if (first.equals("$last")) {
                        first = Integer.toString(result);
                    }          
                    if (second.equals("$last")) {
                        second = Integer.toString(result);
                    }          
                    switch (op) {
                        case "+" :
                            result = Integer.parseInt(first) + Integer.parseInt(second);
                            System.out.println(result);
                            break;
                        case "-" :
                            result = Integer.parseInt(first) - Integer.parseInt(second);
                            System.out.println(result);
                            break;
                        case "*" :
                            result = Integer.parseInt(first) * Integer.parseInt(second);
                            System.out.println(result);
                            break;
                        case "/" :
                            result = Integer.parseInt(first) / Integer.parseInt(second);
                            System.out.println(result);
                            break;
                    }

                }
            }



        }
    }
}
