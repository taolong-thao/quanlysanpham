package demo.quanlysanpham.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.quanlysanpham.Model.LichBanHang;
import demo.quanlysanpham.Repository.LichBanRepo;

/**
 *
 * @author dfean
 */
@Service
public class LichBanService {
    @Autowired
    LichBanRepo lichBanRepo;

    public List<LichBanHang> GetAll() {
        return lichBanRepo.findAll();
    }

    public void Save(LichBanHang lichBanHang) {
        this.lichBanRepo.save(lichBanHang);
    }
}
