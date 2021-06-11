
create database QuanLyBanHang;
use QuanLyBanHang;

drop database quanlybanhang;
create TABLE SANPHAM 
(
	MASP VARCHAR(10) primary key,
	TENSP nvarchar(30),
    QUYCACH INT,
    GIAGOC FLOAT
);
drop table khachhang;
create TABLE KHACHHANG 
(
	MAKH VARCHAR(10),
    TENKH nvarchar(30),
    DIACHI nvarchar(40),
	SDT VARCHAR(15),
    SOTAIKHOAN varchar(100) unique,
    SODUTK FLOAT,
    PRIMARY KEY (MAKH) 
);
create TABLE HOADON 
(
	MAHD VARCHAR(10) primary key,
    MAKH VARCHAR(10),
    NGAYLAP DATE,
    TONGTIEN FLOAT
);
create TABLE CHITIETHOADON 
(
	MA_CTHD VARCHAR(10) primary key,
	MAHD VARCHAR(10),
    MASP VARCHAR(10),
    MACK varchar(10),
    SOLUONG INT,
    THANHTIEN FLOAT
);
create TABLE LOAICHIETKHAU 
(
	MALOAICK VARCHAR(10) primary key,
    TENLOAICK nvarchar(30)
);
create TABLE CHIETKHAU 
(
	MACK VARCHAR(10) primary key,
    MALOAICK varchar(10),
    TENCK NVARCHAR(40),
    NGAYQUYDINH varchar(10),
    CHIETKHAU FLOAT
);
create TABLE TAIKHOAN 
(
	MAKH VARCHAR(10),
	ID VARCHAR(10),
    PASS varchar(10)
);
ALTER TABLE TAIKHOAN
ADD CONSTRAINT fk_TK_KH FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH);
ALTER TABLE CHITIETHOADON
ADD CONSTRAINT fk_CK_CTHD FOREIGN KEY (MACK) REFERENCES CHIETKHAU(MACK);
ALTER TABLE CHIETKHAU
ADD CONSTRAINT fk_CK FOREIGN KEY (MALOAICK) REFERENCES LOAICHIETKHAU(MALOAICK);
ALTER TABLE HOADON
ADD CONSTRAINT fk_HD FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH);
ALTER TABLE CHITIETHOADON
ADD CONSTRAINT fk_SP_CTHD FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP);
ALTER TABLE CHITIETHOADON
ADD CONSTRAINT fk_HD_CTHD FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD);


insert into KHACHHANG
values
('admin',null,null,null,'0000',0),
('KH001',N'Nguyễn Quỳnh Gia Thư',N'330 Trường Chinh, Q.Tân Bình, TPHCM','0903991415','123456789123',20000000),
('KH002',N'Dương Lệ Hương',N'13/5 Nguyễn Thị Thập, Q7, TPHCM','012605988','123456789127',1000000),
('KH003',N'Nguyễn Hoàng Long',N'1083 CMT8, QTB, TPHCM','0987655231','123456789128',500000),
('KH004',N'Hoàng Hải Yến',N'139 Bành Văn Trân, TPHCM','0989326115','123456789129',3000000),
('KH005',N'Hoàng Văn Lương',N'144 Nguyễn Trãi, Q5, TPHCM','0785918442','123456781127',12000),
('KH006',N'Trần Minh Tuấn',N'238/10 Hai Bà Trưng, Q1, TPHCM','077227719','123456782127',1500000),
('KH007',N'Trần Lê Đại Tâm',N'18 Lê Thúc Hoạch, Q. Tân Phú, TPHCM','0129163595','124456789127',1700000),
('KH008',N'Đinh Ngọc Diệp',N'30/5/3 Lê Đại Hành, Q10, TPHCM','0946263152','123456587127',1800000),
('KH009',N'Lại Tấn Phát',N'125/13 Nguyễn Sơn, Q.Tân Phú, TPHCM','0933918563','11456789127',500000),
('KH010',N'Hoàng Ngọc Thái Bảo',N'89 Trần Bình Trọng, PHCM','0167328553','173456789127',7000000),
('KH011',N'Phạm Hoàng Bảo',N'1754 Nguyễn Văn Linh, Q7, TPHCM','0913683616','153456789127',8000000);

insert into TAIKHOAN
values('admin','admin','admin'),
('KH001','001','001'),
('KH002','002','002'),
('KH003','003','003'),
('KH004','004','004'),
('KH005','005','005'),
('KH006','006','006'),
('KH007','007','007'),
('KH008','008','008'),
('KH009','009','009'),
('KH010','010','010'),
('KH011','011','011');

insert into HOADON
values('HD01','KH001','2021-01-01',0),
('HD02','KH002','2021-02-01',0),
('HD03','KH003','2021-03-01',0),
('HD04','KH004','2021-04-01',0),
('HD05','KH005','2020-05-01',0),
('HD06','KH006','2020-06-03',0),
('HD07','KH007','2020-06-5',0),
('HD08','KH008','2020-07-12',0),
('HD09','KH009','2020-08-11',0),
('HD010','KH010','2020-09-10',0),
('HD011','KH011','2020-09-11',0);

insert into LOAICHIETKHAU
values('L1',N'Chiết khấu Khuyến mại' ),
	('L2',N'Chiết khấu Thương mại');

insert into SANPHAM
values('SP001',N'Phân bón A',100,500),
	('SP002',N'Phân bón A',200,1000),
    ('SP003',N'Phân bón A',500,5000),
	('SP004',N'Phân bón B',100,200),
    ('SP005',N'Phân bón B',200,400),
	('SP006',N'Phân bón B',500,900),
	('SP007',N'Phân bón C',100,1000),
    ('SP008',N'Phân bón C',200,1800);

insert into CHIETKHAU
values
('CK001','L1',N'Lễ Noel','24/12',0.5),
('CK002','L1',N'Ngày thành lập công ty','1/1',3),
('CK003','L1',N'Giải phóng miền Nam','30/4',2),
('CK004','L1',N'Ngày Quốc Khánh','2/9',2),
('CK005','L1',N'Lễ Tình Nhân','14/2',0.5),
('CK006','L1',N'Quốc tế phụ nữ','8/3',1),
('CK007','L1',N'Quốc tế lao động','1/2',0.5),
('CK008','L2',N'Hóa đơn số lượng trên 10',null,1),
('CK009','L2',N'Hóa đơn số lượng trên 20',null,2),
('CK010','L2',N'Hóa đơn số lượng trên 30',null,3),
('CK011','L2',N'Hóa đơn số lượng trên 40',null,4),
('CK012','L2',N'Hóa đơn số lượng trên 50',null,5),
('CK013','L2',N'Bình thường',null,0);

insert into CHITIETHOADON
values
('CT_HD001','HD01','SP001','CK002',2,0),
('CT_HD002','HD02','SP002','CK013',5,0),
('CT_HD003','HD03','SP003','CK013',7,0),
('CT_HD004','HD04','SP004','CK013',3,0),
('CT_HD005','HD05','SP005','CK009',1,0),
('CT_HD006','HD06','SP006','CK008',10,0),
('CT_HD007','HD07','SP007','CK008',9,0),
('CT_HD008','HD08','SP008','CK009',21,0),
('CT_HD009','HD09','SP002','CK013',31,0),
('CT_HD011','HD01','SP002','CK002',6,0),
('CT_HD012','HD01','SP003','CK002',6,0),
('CT_HD013','HD02','SP007','CK013',4,0);