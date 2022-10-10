import lombok.Data;


@Data
public class Users {
    private long id;
    private String name;
    private int status;
    private String surname;
    private String login;
    private String password;
}
