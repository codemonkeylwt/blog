package ink.casual.user.common.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lwt
 * @date 2019/4/12 14:11
 */
@Data
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email")
    private String email;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "password")
    private String password;
    @Column(name = "email_status")
    private Boolean emailStatus;
    @Column(name = "account_status")
    private Boolean accountStatus;
    @Column(name = "register_date")
    private Date registerDate;
    @Column(name = "lcd")
    private Date lcd;


}
