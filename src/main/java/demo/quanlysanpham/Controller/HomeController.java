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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/ViewSP", method = RequestMethod.GET)
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

    @GetMapping("/delete")
    public String del(@RequestParam("masp") String masp) {
        sanPhamServices.delete(masp);
        return "redirect:/";
    }

    @GetMapping("/update/{masp}")
    public String edit(@PathVariable(value = "masp") String masp, Model model) {
        //get sp from sevice
        SanPham sanPham = sanPhamServices.find(masp);
        model.addAttribute("sanpham", sanPham);
        return "editSP";
    }
}
