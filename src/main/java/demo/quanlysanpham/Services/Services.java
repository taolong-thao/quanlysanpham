package demo.quanlysanpham.Services;

import java.util.List;

import demo.quanlysanpham.Model.SanPham;

/**
 *
 * @author dfean
 */
public interface Services {
    public List<SanPham> getall();

    public SanPham find(String temp);

    public SanPham save(SanPham sanPham);

    public void delete(String temp);
}
