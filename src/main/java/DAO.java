import java.sql.SQLException;
import java.util.List;

public interface DAO<T extends Object> {
    public List<T> list();
    T get(long id);
    void delete(long id) throws SQLException;
    public long edit(Advert obj) throws SQLException;
}