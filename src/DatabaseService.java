import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService  implements DatabaseInterface{

    Connection con;

    public DatabaseService(Connection con) {
        this.con = con;
    }

    @Override
    public void add(Category cat) throws ClassNotFoundException, SQLException {

    }

    @Override
    public Category edit(Category cat, String catcode) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(String catcode) throws SQLException {

    }

    @Override
    public List<CategoryModel> display() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<CategoryModel> Catlist = new ArrayList<CategoryModel>();

        String quer1 = "Select * from category";
        PreparedStatement query = con.prepareStatement(quer1);
        ResultSet rs = query.executeQuery();

        CategoryModel obj1;

        //display records if there is data;

        while (rs.next()) {

            obj1 = new CategoryModel(rs.getString("catcode"), rs.getString("catdesc"));

            Catlist.add(obj1);
        }


        return Catlist;
    }
}
