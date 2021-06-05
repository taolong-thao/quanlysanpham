package demo.quanlysanpham.Services;

import java.util.List;

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
        return sanPhamRepo.findById(temp).get();
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    @Override
    public void delete(String temp) {
        sanPhamRepo.deleteById(temp);
    }
}