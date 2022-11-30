import lombok.Data;

import java.sql.Date;


@Data
public class Advert {
    private long id;
    private long id_user;
    private String name;
    private int price;
    private String category;
    private long categoryid;
    private Date date;
    private String description;
}
