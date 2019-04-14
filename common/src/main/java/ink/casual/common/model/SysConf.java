package ink.casual.common.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lwt
 * @date 2019/4/14 13:41
 */
@Data
@Table(name = "sys_conf")
public class SysConf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;
    @Column(name = "description")
    private String description;
    @Column(name = "fcd")
    private Date fcd;
    @Column(name = "lcd")
    private Date lcd;
    @Column(name = "enable")
    private boolean enable = true;

    public SysConf(String key,String value,String description){
        this.key = key;
        this.value = value;
        this.description = description;
    }
}
