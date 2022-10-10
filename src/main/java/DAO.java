import java.sql.SQLException;
import java.util.List;

public interface DAO<T extends Object> {
    T get(long id);
    void delete(long id) throws SQLException;
    void edit (long id) throws SQLException;
}