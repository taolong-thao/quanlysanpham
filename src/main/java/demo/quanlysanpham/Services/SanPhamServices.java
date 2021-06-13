package demo.quanlysanpham.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Repository.SanPhamRepo;

/**
 * @author dfean
 */
@Service("Services")
public class SanPhamServices {
    @Autowired
    SanPhamRepo sanPhamRepo;


    public List<SanPham> getAll() {
        return sanPhamRepo.findAll();
    }

    public SanPham find(String temp) {
        Optional<SanPham> optional = sanPhamRepo.findById(temp);
        SanPham sanPham = null;
        if (optional.isPresent()) {
            sanPham = optional.get();
        }
        return sanPham;
    }

    public SanPham save(SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    public void delete(String temp) {
        sanPhamRepo.deleteById(temp);
    }

}
