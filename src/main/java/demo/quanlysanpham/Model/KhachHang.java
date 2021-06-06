package demo.quanlysanpham.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dfean
 */
@Entity
@Table(name = "khachhang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KhachHang implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKH")
    private String maKh;
    @Column(name = "TENKH")
    private String tenKh;
    @Column(name = "DIACHI")
    private String diaChi;
    @Column(name = "SDT")
    private String SDT;
    @Column(name = "SOTAIKHOAN")
    private String soTK;
    @Column(name = "SODUTK")
    private Long soDuTK;
}
