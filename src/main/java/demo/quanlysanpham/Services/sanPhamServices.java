package demo.quanlysanpham.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Repository.sanPhamRepo;

/**
 *
 * @author dfean
 */
@Service("Services")
public class sanPhamServices implements Services {
    @Autowired
    sanPhamRepo sanPhamRepo;

    @Override

    public List<SanPham> getall() {
        return sanPhamRepo.findAll();
    }

    @Override
    public SanPham find(String temp) {
        Optional<SanPham> optional = sanPhamRepo.findById(temp);
        SanPham sanPham = null;
        if (optional.isPresent()) {
            sanPham = optional.get();
        } else {
            throw new RuntimeException("Khong tim thay masp : " + temp);
        }
        return sanPham;
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    @Override
    public void delete(String temp) {
        sanPhamRepo.deleteById(temp);
    }

    @Override
    public SanPham Search(String masp) {
        return sanPhamRepo.findById(masp).get();
    }
}
