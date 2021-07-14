package demo.quanlysanpham.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.quanlysanpham.Model.LichBanHang;

/**
 *
 * @author dfean
 */
@Repository
public interface LichBanRepo extends JpaRepository<LichBanHang, String> {
}
