import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertsDAO implements DAO<Advert> {
    private DataSource dataSource;
    public AdvertsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Advert get(long id){
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement
                        ("SELECT * FROM adverts WHERE id = ?")
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Advert obj = new Advert();
                obj.setId(rs.getLong("id"));
                obj.setName(rs.getString("Name"));
                obj.setIdUser(rs.getLong("idUser"));
                obj.setPrice(rs.getInt("Price"));
                obj.setPictureRef(rs.getString("PictureRef"));
                obj.setCategory(rs.getInt("Category"));
                obj.setDate(rs.getDate("Date"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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


    public void edit(long id) throws SQLException {
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
}
