package demo.quanlysanpham.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.quanlysanpham.Model.KhachHang;
import demo.quanlysanpham.Repository.KhachHangRepo;

/**
 *
 * @author dfean
 */
@Service
public class KhachHangServices {
    @Autowired
    KhachHangRepo khachHangRepo;

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
        return khachHangRepo.findById(makh).get();
    }
}
