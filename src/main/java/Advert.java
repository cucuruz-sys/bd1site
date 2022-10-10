import lombok.Data;

import java.sql.Date;


@Data
public class Advert {
    private long id;
    private long idUser;
    private String name;
    private int price;
    private String pictureRef;
    private int category;
    private Date date;
}
