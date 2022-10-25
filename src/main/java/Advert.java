import lombok.Data;

import java.sql.Date;


@Data
public class Advert {
    private long id;
    private long id_user;
    private String name;
    private int price;
    private String picture_ref;
    private String category;
    private Date date;
}
