import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * @author Mukul Sharma
 */
public class SqliteDb {
    Connection c = null;
    Statement statement = null;

    /**
     * Constructor to establish connection with the Student DB
     */
    SqliteDb() {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:Student.db");
            System.out.println("Connected to Student DB.");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Function to list all Student records in Student DB
     */
    public void listStudents() {
        try {
            this.statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Students");
            System.out.println("ID" + "\t" + "NAME" + "\t" + "DEP" + "\t" + "DOB" + "\n");
            while (rs.next()) {
                int id = rs.getInt("studentId");
                String name = rs.getString("name");
                String dep = rs.getString("departmentId");
                String dob = rs.getString("dob");
                System.out.println(id + "\t" + name + "\t" + dep + "\t" + dob);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    /**
     * @param id   - studentId of new Student
     * @param dep  - departmentId of new Student
     * @param name - name of new Student
     * @param date - dob of new Student
     */
    public void insertStudent(int id, String dep, String name, String date) {
        try {
            this.statement = c.createStatement();
            String query = "INSERT INTO Students VALUES (" + id + "," + "\'" + dep + "\'" + "," + "\'" + name + "\'"
                    + "," + "\'" + date + "\'" + ")";

            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * @param oid  - old studentId
     * @param id   - updated studentId, leave blank if unchanged
     * @param dep  - updated departmenId, leave blank if unchanged
     * @param name - updated name, leave blank if unchanged
     * @param dob  - updated dob, leave blank if unchanged
     */
    public void updateStudent(int oid, String id, String dep, String name, String dob) {
        try {
            this.statement = c.createStatement();
            if (!id.equals("")) {
                statement.executeUpdate("UPDATE Students  SET studentId=" + id + " WHERE studentId=" + oid);
            } else if (!dep.equals("")) {
                statement.executeUpdate(
                        "UPDATE Students  SET departmentId=" + "\'" + dep + "\'" + " WHERE studentId=" + oid);
            } else if (!name.equals("")) {
                statement.executeUpdate("UPDATE Students  SET name=" + "\'" + name + "\'" + " WHERE studentId=" + oid);
            } else if (!dob.equals("")) {
                statement.executeUpdate("UPDATE Students  SET dob=" + "\'" + dob + "\'" + " WHERE studentId=" + oid);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * @param id - studentId of Student record to be deleted
     */
    public void deleteStudent(int id) {
        try {
            this.statement = c.createStatement();
            String query = "DELETE FROM Students WHERE studentId=" + Integer.toString(id);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (c != null) {
                c.close();
            }
            System.out.println("Disconnected from Student DB.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
