package demo.quanlysanpham.Model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dfean
 */
@Table(name = "SANPHAM")
@Entity
@Generated
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPham implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MASP")
    private String maSp;
    @Column(name = "TENSP")
    private String tenSp;
    @Column(name = "QUYCACH")
    private int quyCach;
    @Column(name = "GIAGOC")
    private long giaGoc;

}
