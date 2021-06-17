package demo.quanlysanpham.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.quanlysanpham.Model.KhachHang;
import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Services.KhachHangServices;
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

    public LoginController(SanPhamServices sanPhamServices, KhachHangServices khachHangServices) {
        this.sanPhamServices = sanPhamServices;
        this.khachHangServices = khachHangServices;
    }

    @GetMapping(value = "/ViewIndex")
    public String viewIndex(Model model, HttpSession session) {
        List<SanPham> list = sanPhamServices.getAll();
        model.addAttribute(SanPhamUtils.SAN_PHAM, list);
        if (session.getAttribute("Name") != null) {
            model.addAttribute("buy", "Thêm Vào giỏ Hàng");
            model.addAttribute("hiddenlogin", "display: none");
            model.addAttribute("logout", "Logout");
            return "ViewIndex";
        } else {
            model.addAttribute("login", "Mua Ngay!");
            model.addAttribute("hidden", "display: none");
            return "ViewIndex";
        }

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("quanly")
    public String quanly(HttpSession session) {
        if (session.getAttribute("Name") == "admin") {
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

            return "index";
        } else if (makh.equals("admin") && pass.equals("admin")) {
            List<KhachHang> list = khachHangServices.getAll();
            model.addAttribute(SanPhamUtils.KHACH_HANG, list);
            return "quanly";
        } else {
            model.addAttribute("error", "Tai Khoan Khong ton tai");
        }
        return "login";
    }

    @RequestMapping("list")
    public String getAll(Model model, HttpSession session) {
        if (session.getAttribute("Name") != null) {
            model.addAttribute(SanPhamUtils.SAN_PHAM, sanPhamServices.getAll());
            return "View";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("Name");
        return "login";
    }
}
