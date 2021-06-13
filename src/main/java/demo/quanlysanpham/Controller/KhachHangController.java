package demo.quanlysanpham.Controller;

import java.util.List;
import java.util.Random;

import demo.quanlysanpham.utils.SanPhamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.quanlysanpham.Model.KhachHang;
import demo.quanlysanpham.Services.KhachHangServices;

/**
 * @author dfean
 */
@Controller
public class KhachHangController {
    @Autowired
    KhachHangServices khachHangServices;

    @GetMapping("/ViewKH")
    public String viewKh(Model model) {
        List<KhachHang> list = khachHangServices.getAll();
        model.addAttribute(SanPhamUtils.KHACH_HANG, list);
        return "ViewKh";
    }

    @GetMapping("/addKH")
    public String addkh(Model model) {
        model.addAttribute(SanPhamUtils.KHACH_HANG, new KhachHang());
        return "addKH";
    }

    @PostMapping("/addKH")
    public String addKH(@ModelAttribute("khachhang") KhachHang khachHang) {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        khachHang.setSoTK(generatedString);
        khachHangServices.saveKH(khachHang);
        return SanPhamUtils.REDIRECT;
    }

    @PostMapping("/EditKh")
    public String edit(@ModelAttribute("khachhang") KhachHang khachHang) {
        khachHangServices.saveKH(khachHang);
        return SanPhamUtils.REDIRECT;
    }

    @RequestMapping("/deleteKh")
    public String del(@RequestParam("makh") String khachHang) {
        khachHangServices.delete(khachHang);
        return SanPhamUtils.REDIRECT;
    }

    @GetMapping("/UpdateKh/{maKh}")
    public String update(@PathVariable("maKh") String makh, Model model) {
        KhachHang khachHang = khachHangServices.find(makh);
        model.addAttribute(SanPhamUtils.KHACH_HANG, khachHang);
        return "UpdateKh";
    }

    @GetMapping("/searchkh")
    public String search(Model model) {
        model.addAttribute(SanPhamUtils.KHACH_HANG, new KhachHang());
        return "searchkh";
    }

    @PostMapping("/searchkh")
    public String searchbyID(@RequestParam("maKh") String makh, Model model) {
        model.addAttribute(SanPhamUtils.KHACH_HANG, khachHangServices.find(makh));
        return "searchkh";
    }

    @GetMapping("/addMoney")
    public String addmoney(Model model) {
        model.addAttribute(SanPhamUtils.KHACH_HANG, new KhachHang());
        return "addMoney";
    }

    @PostMapping("/addtien")
    public String saveMoney(@RequestParam("sotien") long sotien, @ModelAttribute("maKh") String maKh) {
        KhachHang khachHang = khachHangServices.find(maKh);
        Long temp = khachHang.getSoDuTK() + sotien;
        khachHang.setSoDuTK(temp);
        khachHangServices.saveKH(khachHang);
        return SanPhamUtils.REDIRECT;
    }
}
