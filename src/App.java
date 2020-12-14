import java.util.Scanner;

/**
 * @author Mukul Sharma
 */
public class App {

    /**
     * @param args
     * @throws Exception The main function establishes connection with the Student
     *                   DB and takes user input for its manipulation
     */
    public static void main(String[] args) throws Exception {
        // connect to Student DB
        SqliteDb db = new SqliteDb();
        // open Stdin Scanner for user input
        Scanner inp = new Scanner(System.in);
        // Menu for data manipulation queries
        System.out.println("\nenter command: \n 1-list\n 2-insert\n 3-update\n 4-delete\n other keys to exit: ");

        // Read input while valid
        while (true) {
            String input = inp.nextLine();
            // LIST command
            if (input.equals("1")) {
                db.listStudents();
            }
            // INSERT command
            else if (input.equals("2")) {
                System.out.println("enter ID: ");
                String id = inp.nextLine();
                System.out.println("enter Department: ");
                String dep = inp.nextLine();
                System.out.println("enter Name: ");
                String name = inp.nextLine();
                System.out.println("enter DOB(YYYY-MM-DD): ");
                String dob = inp.nextLine();
                db.insertStudent(Integer.parseInt(id), dep, name, dob);
            }
            // UPDATE command
            else if (input.equals("3")) {
                System.out.println("enter current student ID: ");
                String oid = inp.nextLine();
                System.out.println("Leave field blank if unchanged.");
                System.out.println("new ID: ");
                String id = inp.nextLine();
                System.out.println("new Department: ");
                String dep = inp.nextLine();
                System.out.println("new Name: ");
                String name = inp.nextLine();
                System.out.println("new DOB(YYYY-MM-DD): ");
                String dob = inp.nextLine();
                db.updateStudent(Integer.parseInt(oid), id, dep, name, dob);
            }
            // DELETE command
            else if (input.equals("4")) {
                System.out.println("enter ID: ");
                String id = inp.nextLine();
                db.deleteStudent(Integer.parseInt(id));
            }
            // EXIT command, stop reading input
            else
                break;
            // Get the next input
            System.out.println("\n==========");
            System.out.println("enter command: 1-list 2-insert 3-update 4-delete, any other key to exit:");
        }
        // close Scanner
        inp.close();
        // close DB connection
        db.closeConnection();
    }
}
