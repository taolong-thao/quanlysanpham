package demo.quanlysanpham.Controller;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import demo.quanlysanpham.Model.KhachHang;
import demo.quanlysanpham.Services.KhachHangServices;
import demo.quanlysanpham.utils.SanPhamUtils;

/**
 * @author dfean
 */
@Controller
public class KhachHangController {

    private KhachHangServices khachHangServices;

    public KhachHangController(KhachHangServices khachHangServices) {
        this.khachHangServices = khachHangServices;
    }

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
    public String addKH(@ModelAttribute("khachhang") KhachHang khachHang, RedirectAttributes redirectAttributes) {
        if (khachHang.getTenKh() == null || khachHang.getDiaChi() == null || khachHang.getSDT() == null || khachHang.getSoDuTK() == null) {
            redirectAttributes.addFlashAttribute("error", "Thêm Khách hàng thất bại");
            return SanPhamUtils.REDIRECT;
        } else {
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
            redirectAttributes.addFlashAttribute("sucesss", "Thêm Khách hàng thành Công");
            return SanPhamUtils.REDIRECT;
        }
    }

    @PostMapping("/EditKh")
    public String edit(@ModelAttribute("khachhang") KhachHang khachHang, RedirectAttributes redirectAttributes) {
        khachHangServices.saveKH(khachHang);
        redirectAttributes.addFlashAttribute("sucesss", "Edit Khách hàng " + khachHang.getTenKh() + " thành Công");
        return SanPhamUtils.REDIRECT;
    }

    @RequestMapping("/deleteKh")
    public String del(@RequestParam("makh") String khachHang, RedirectAttributes redirectAttributes) {
        khachHangServices.delete(khachHang);
        redirectAttributes.addFlashAttribute("sucesss", "Xóa Khách hàng " + khachHang + " thành Công");
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
    public String searchbyID(@RequestParam("maKh") String makh, Model model, RedirectAttributes redirectAttributes) {
        if (khachHangServices.find(makh) == null) {
            redirectAttributes.addFlashAttribute("error", makh + "không tồn tại");
            return "searchkh";
        } else {
            model.addAttribute(SanPhamUtils.KHACH_HANG, khachHangServices.find(makh));
            return "searchkh";
        }
    }

    @GetMapping("/addMoney")
    public String addmoney(Model model) {
        model.addAttribute(SanPhamUtils.KHACH_HANG, new KhachHang());
        return "addMoney";
    }

    @PostMapping("/addtien")
    public String saveMoney(@RequestParam("sotien") long sotien, @ModelAttribute("maKh") String maKh, RedirectAttributes redirectAttributes) {
        KhachHang khachHang = khachHangServices.find(maKh);
        if (khachHang != null) {
            Long temp = khachHang.getSoDuTK() + sotien;
            khachHang.setSoDuTK(temp);
            khachHangServices.saveKH(khachHang);
        }
        return SanPhamUtils.REDIRECT;
    }
}
