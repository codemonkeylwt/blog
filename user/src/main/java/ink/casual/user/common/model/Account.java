package ink.casual.user.common.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author lwt
 * @date 2019/4/12 14:11
 */
@Data
@Document(collection = "account")
public class Account {

    private ObjectId id;
    private Long accountId;
    private String mobile;
    private String email;
    private String accountName;
    private String password;
    private Boolean emailStatus;
    private Boolean accountStatus;
    private Date registerDate;
    private Date lcd;

}
