import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AdvertsDAO implements DAO<Advert> {
    private DataSource dataSource;
    public AdvertsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Advert get(long id){
        System.out.println("get " + id);
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM adverts WHERE id = ?");
                PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Advert obj = new Advert();
                obj.setId(rs.getLong("id"));
                obj.setName(rs.getString("name"));
                obj.setId_user(rs.getLong("id_user"));
                obj.setPrice(rs.getInt("price"));
                obj.setPicture_ref(rs.getString("picture_ref"));
                System.out.println(obj.getPicture_ref());
                obj.setDate(rs.getDate("date"));
                ps2.setLong(1, rs.getLong("category"));
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                obj.setCategory(rs2.getString("name"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Advert> list() {
        List<Advert> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement ("SELECT * FROM adverts ORDER BY name");
                                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Advert obj = new Advert();
                obj.setId(rs.getLong("id"));
                obj.setName(rs.getString("name"));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM adverts WHERE id = ?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public long edit(Advert obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("UPDATE adverts SET name = ? WHERE id = ?")
        ) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj.getId();
    }
}
