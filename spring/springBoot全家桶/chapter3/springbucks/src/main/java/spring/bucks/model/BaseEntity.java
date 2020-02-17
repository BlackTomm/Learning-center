package spring.bucks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**@MappedSuperclass 用在父类上面。当这个类肯定是父类时，加此标注。
 *      如果改成@Entity，则继承后，多个类继承，只会生成一个表，而不是多个继承，生成多个表
 * @NoArgsConstructor 创建无参构造函数
 * @AllArgsConstructor 创建包含全部参数的构造函数
 * @Data Generates getters for all fields, a useful toString method, and hashCode
 *      and equals implementations that check all non-transient fields.
 * 参考 https://projectlombok.org/api/lombok/package-summary.html
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
