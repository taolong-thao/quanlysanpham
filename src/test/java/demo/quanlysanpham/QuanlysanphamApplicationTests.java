package demo.quanlysanpham;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import demo.quanlysanpham.Model.SanPham;
import demo.quanlysanpham.Repository.SanPhamRepo;
import demo.quanlysanpham.Services.SanPhamServices;

@DataJpaTest
//@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuanlysanphamApplicationTests {
    @Mock
    private SanPhamRepo sanPhamRepo;

    @Autowired
    private SanPhamServices sanPhamServices;

    @Test
    @Rollback(false)
    public void testCreateSp() {
        SanPham sanPham = new SanPham("", "Hoa hẹ", 12, 1231);
        SanPham savesp = sanPhamServices.save(sanPham);
        assertNotNull(savesp);
//        when(sanPhamRepo.save(ArgumentMatchers.any(SanPham.class))).thenReturn(sanPham);
//        assertThat(savesp.getTenSp()).isSameAs(sanPham.getTenSp());
//        verify(sanPhamRepo).save(sanPham);
    }

    @Test

    public void getAllTest() {
        List<SanPham> list = new ArrayList<>();
        list.add(new SanPham());
        given(sanPhamRepo.findAll()).willReturn(list);
        List<SanPham> expected = sanPhamServices.getAll();
        assertEquals(expected, list);
        verify(sanPhamRepo).findAll();
    }
}
