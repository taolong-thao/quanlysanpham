package demo.quanlysanpham.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.quanlysanpham.Model.KhachHang;
import demo.quanlysanpham.Repository.KhachHangRepo;

/**
 * @author dfean
 */
@Service
public class KhachHangServices {
    private KhachHangRepo khachHangRepo;

    public KhachHangServices(KhachHangRepo khachHangRepo) {
        this.khachHangRepo = khachHangRepo;
    }

    public List<KhachHang> getAll() {
        return khachHangRepo.findAll();
    }

    public void saveKH(KhachHang khachHang) {
        this.khachHangRepo.save(khachHang);
    }

    public void delete(String khachHang) {
        this.khachHangRepo.deleteById(khachHang);
    }

    public KhachHang find(String makh) {
        Optional<KhachHang> optional = khachHangRepo.findById(makh);
        KhachHang khachHang = null;
        if (optional.isPresent()) {
            khachHang = optional.get();
        }
        return khachHang;
    }

}
