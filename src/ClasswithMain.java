import java.sql.SQLException;

public class ClasswithMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        Category form1 = new Category();

        form1.Updatetable();

        form1.setVisible(true);


    }
}
