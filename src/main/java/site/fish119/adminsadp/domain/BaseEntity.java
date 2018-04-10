package site.fish119.adminsadp.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Project adminss
 * @Package site.fish119.adminss.domain
 * @Author fish119
 * @Date 2018/2/4 17:38
 * @Version V1.0
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateTime;
}
