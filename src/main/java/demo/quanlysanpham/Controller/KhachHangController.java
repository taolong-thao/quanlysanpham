package demo.quanlysanpham.Controller;

import java.util.List;

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
 *
 * @author dfean
 */
@Controller
public class KhachHangController {
    @Autowired
    KhachHangServices khachHangServices;

    @GetMapping("/ViewKH")
    public String viewKh(Model model) {
        List<KhachHang> list = khachHangServices.getAll();
        model.addAttribute("khachhang", list);
        return "ViewKh";
    }

    @GetMapping("/addKH")
    public String addkh(Model model) {
        model.addAttribute("khachhang", new KhachHang());
        return "addKH";
    }

    @PostMapping("/addKH")
    public String addKH(@ModelAttribute("khachhang") KhachHang khachHang) {
        khachHangServices.saveKH(khachHang);
        return "redirect:/";
    }

    @RequestMapping("/deleteKh")
    public String del(@RequestParam("makh") String khachHang) {
        khachHangServices.delete(khachHang);
        return "redirect:/";
    }

    @GetMapping("/UpdateKh/{maKh}")
    public String update(@PathVariable("maKh") String makh, Model model) {
        KhachHang khachHang = khachHangServices.find(makh);
        model.addAttribute("khachhang", khachHang);
        return "UpdateKh";
    }

    @GetMapping("/searchkh")
    public String search(Model model) {
        model.addAttribute("khachhang", new KhachHang());
        return "searchkh";
    }

    @PostMapping("/searchkh")
    public String searchbyID(@RequestParam("maKh") String makh, Model model) {
        model.addAttribute("khachhang", khachHangServices.find(makh));
        return "searchkh";
    }
}
