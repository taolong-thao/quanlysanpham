package demo.quanlysanpham.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import demo.quanlysanpham.Model.SanPham;

public class SanPhamUtils {
    public static String SAN_PHAM = "sanpham";
    public static String KHACH_HANG = "khachhang";
    public static String REDIRECT = "redirect:/";
    public static String SUCCESS = "sucesss";
    public static String Manager = "quanly";
    public static Integer idKh = 12;


    public static void WriteFile(HttpServletResponse response, List<SanPham> list, Long total) throws IOException {

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=myFile.txt");
        ServletOutputStream out = response.getOutputStream();
        out.println(String.valueOf(list));
        out.println("Total: " + total + "đ");
        out.flush();
        out.close();
    }
}
