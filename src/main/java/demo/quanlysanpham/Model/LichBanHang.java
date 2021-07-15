package demo.quanlysanpham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dfean
 */
@Entity
@Table(name = "lichbanhang")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LichBanHang {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAKH")
    @Column(name = "MAKH")
    private String maKh;
    @Column(name = "MAHD")
    private String maHd;
    @Column(name = "MASP")
    private String maSp;
    @Column(name = "TENSP")
    private String tenSp;
    @Column(name = "GiAGOC")
    private Long giaGoc;
    @Column(name = "GIADAILY")
    private Long giaDaiLy;
    @Column(name = "NGAYLAP")
    private String ngayLap;
    @Column(name = "TONGTIEN")
    private Long tongTien;


}
