package demo.quanlysanpham.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.util.StringUtils;

import demo.quanlysanpham.Model.KhachHang;
import demo.quanlysanpham.Model.LichBanHang;
import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Services.KhachHangServices;
import demo.quanlysanpham.Services.LichBanService;
import demo.quanlysanpham.Services.SanPhamServices;
import demo.quanlysanpham.utils.SanPhamUtils;

/**
 *
 * @author dfean
 */
@Controller
public class LoginController {
    private SanPhamServices sanPhamServices;
    private KhachHangServices khachHangServices;
    private LichBanService lichBanService;

    public LoginController(SanPhamServices sanPhamServices, KhachHangServices khachHangServices, LichBanService lichBanService) {
        this.sanPhamServices = sanPhamServices;
        this.khachHangServices = khachHangServices;
        this.lichBanService = lichBanService;
    }

    @GetMapping(value = "/ViewIndex")
    public String viewIndex(Model model, HttpSession session) {
        List<SanPham> list = sanPhamServices.getAll();
        model.addAttribute(SanPhamUtils.SAN_PHAM, list);
        if (session.getAttribute("Name") != null) {
            model.addAttribute("buy", "Mua Sản Phẩm");
            model.addAttribute("hiddenlogin", "display: none");
            model.addAttribute("logout", "Logout");
            return "ViewIndex";
        } else {
            model.addAttribute("login", "Mua Ngay!");
            model.addAttribute("hidden", "display: none");
            return "ViewIndex";
        }

    }

    @PostMapping("/ViewIndex")
    public String oder(@ModelAttribute("sanpham") SanPham sanPham, Model model, HttpServletResponse response, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
        if (!StringUtils.isNullOrEmpty(sanPham.getMaSp())) {
            List<SanPham> list = new ArrayList<>();
            String[] str = sanPham.getMaSp().split(",");
            Long total = 0L;
            String tensp = "";
            String masp = "";
            Long giagoc = 0L;
            LichBanHang lich = new LichBanHang();
            LocalDate localDate = LocalDate.now();
            Object e = session.getAttribute("Name");
            String name = e.toString();
            SanPhamUtils.WriteFile(response, list, total);
            lich.setNgayLap(localDate.toString());
            lich.setMaKh(name);
            for (String t : str) {
                SanPham sp = new SanPham();
                sp = sanPhamServices.find(t);
                total += sp.getGiaGoc();
                masp = sp.getMaSp();
                tensp = sp.getTenSp();
                giagoc = sp.getGiaGoc();
                lich.setMaSp(masp);
                lich.setTenSp(tensp);
                lich.setGiaGoc(giagoc);
                list.add(sp);
            }
            total = (sanPham.getGiaGoc() / sanPham.getQuyCach());
            lich.setTongTien(total);
            lichBanService.Save(lich);
            redirectAttributes.addFlashAttribute("error", "Mua Sản Phẩm Thành Công!");
        } else {
            model.addAttribute("error", "Bạn chưa chọn sản phẩm!");
        }
        return SanPhamUtils.REDIRECT + "ViewIndex";
    }

    @GetMapping("ViewLich")
    public String viewLich(Model model) {
        model.addAttribute("hoadon", lichBanService.GetAll());
        return "ViewLich";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("quanly")
    public String quanly(HttpSession session) {
        if (session.getAttribute("admin") == "admin") {
            return "quanly";
        }
        return "login";
    }

    @PostMapping("/check")
    public String check(Model model, @RequestParam("username") String makh,
                        @RequestParam("password") String pass,
                        HttpSession session) {
        if (khachHangServices.Checklogin(makh, pass)) {
            session.setAttribute("Name", makh);
            List<SanPham> list = sanPhamServices.getAll();
            model.addAttribute(SanPhamUtils.SAN_PHAM, list);
            model.addAttribute("logout", "Logout");
            model.addAttribute("login", "display: none");
            model.addAttribute("profile", "Profile");
            if (makh.equals("admin")) {
                session.setAttribute("admin", makh);
                List<KhachHang> list2 = khachHangServices.getAll();
                model.addAttribute(SanPhamUtils.KHACH_HANG, list2);
                return "quanly";
            }
            return "index";
        } else {
            model.addAttribute("error", "Tai Khoan Khong ton tai!");
        }
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("Name");
        return "login";
    }
}
