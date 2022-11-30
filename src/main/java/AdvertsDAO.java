import javax.sql.DataSource;
import java.sql.*;
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
                obj.setDate(rs.getDate("date"));
                obj.setDescription(rs.getString("description"));
                obj.setCategoryid(rs.getLong("category"));
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
                PreparedStatement preparedStatement = connection.prepareStatement ("SELECT * FROM adverts ORDER BY name");
                ResultSet rs = preparedStatement.executeQuery();
                PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
        ) {
            while (rs.next()) {
                Advert obj = new Advert();
                obj.setId(rs.getLong("id"));
                obj.setName(rs.getString("name"));
                obj.setId_user(rs.getLong("id_user"));
                obj.setPrice(rs.getInt("price"));
                obj.setDate(rs.getDate("date"));
                obj.setCategoryid(rs.getLong("category"));
                obj.setDescription(rs.getString("description"));
                ps2.setLong(1, rs.getLong("category"));
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                obj.setCategory(rs2.getString("name"));
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



    //я хз используется ли это вообще
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

    private long insert(Advert obj) throws SQLException {
        long id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                                "INSERT INTO adverts (name, category, id_user, price, date, description) VALUES(?,?,?,?,?,?)",
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getCategoryid());
            preparedStatement.setLong(3, obj.getId_user());
            preparedStatement.setInt(4, obj.getPrice());
            preparedStatement.setDate(5, obj.getDate());
            preparedStatement.setString(6, obj.getDescription());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getLong(1);
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private long update(Advert obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("UPDATE adverts SET name = ?, category = ? , id_user = ? , price = ? , date = ? , description = ?  WHERE id = ?")
        ) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getCategoryid());
            preparedStatement.setLong(3, obj.getId_user());
            preparedStatement.setInt(4, obj.getPrice());
            preparedStatement.setDate(5, obj.getDate());
            preparedStatement.setString(6, obj.getDescription());
            preparedStatement.setLong(7, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj.getId();
    }


    public long save(Advert obj) throws SQLException {
        if (obj.getId() == 0) {
            return insert(obj);
        } else {
            return update(obj);
        }
    }
}
