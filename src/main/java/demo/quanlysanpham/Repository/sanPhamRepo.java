package demo.quanlysanpham.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.quanlysanpham.Model.SanPham;

/**
 *
 * @author dfean
 */
@Repository
public interface sanPhamRepo extends JpaRepository<SanPham, String> {

}

