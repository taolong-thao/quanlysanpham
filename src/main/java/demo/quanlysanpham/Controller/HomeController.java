package demo.quanlysanpham.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Services.sanPhamServices;

/**
 *
 * @author dfean
 */
@Controller
public class HomeController {

    @Autowired
    sanPhamServices sanPhamServices;

    @RequestMapping(value = "/longpro", method = RequestMethod.GET)
    public String view(Model model) {
        List<SanPham> list = sanPhamServices.getall();
        model.addAttribute("sanpham", list);
        return "View";
    }

    @GetMapping(value = "/addSP")
    public String whos(Model model) {
        SanPham sanPham = new SanPham();
        model.addAttribute("sanpham", sanPham);
        return "addSP";
    }

    @PostMapping(value = "/addSP")
    public String savesp(@ModelAttribute("sanpham") SanPham sanPham) {
        sanPhamServices.save(sanPham);
        return "redirect:/";
    }
}
