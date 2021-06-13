package demo.quanlysanpham.Controller;

import java.util.List;

import demo.quanlysanpham.utils.SanPhamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Services.SanPhamServices;

/**
 * @author dfean
 */
@Controller
public class HomeController {

    @Autowired
    SanPhamServices sanPhamServices;

    @RequestMapping(value = "/ViewSP", method = RequestMethod.GET)
    public String view(Model model) {
        List<SanPham> list = sanPhamServices.getAll();
        model.addAttribute(SanPhamUtils.SAN_PHAM, list);
        return "View";
    }

    @GetMapping(value = "/addSP")
    public String whos(Model model) {
        SanPham sanPham = new SanPham();
        model.addAttribute(SanPhamUtils.SAN_PHAM, sanPham);
        return "addSP";
    }

    @PostMapping(value = "/addSP")
    public String savesp(@ModelAttribute("sanpham") SanPham sanPham) {
        sanPhamServices.save(sanPham);
        return SanPhamUtils.REDIRECT;
    }

    @GetMapping("/delete")
    public String del(@RequestParam("masp") String masp) {
        sanPhamServices.delete(masp);
        return SanPhamUtils.REDIRECT;
    }

    @GetMapping("/update/{masp}")
    public String edit(@PathVariable(value = "masp") String masp, Model model) {
        //get sp from sevice
        SanPham sanPham = sanPhamServices.find(masp);
        model.addAttribute(SanPhamUtils.SAN_PHAM, sanPham);
        return "editSP";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        SanPham sanPham = new SanPham();
        model.addAttribute(SanPhamUtils.SAN_PHAM, sanPham);
        return "search";
    }

    @PostMapping("/search")
    public String searchsp(@RequestParam("maSp") String masp, Model model) {
        model.addAttribute(SanPhamUtils.SAN_PHAM, sanPhamServices.find(masp));
        return "search";
    }
}
