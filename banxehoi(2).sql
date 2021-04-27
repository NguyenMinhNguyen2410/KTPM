-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 26, 2021 lúc 06:27 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `banxehoi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonban`
--

CREATE TABLE `chitietdonban` (
  `IDDonBan` int(11) NOT NULL,
  `IDKhung` varchar(20) NOT NULL,
  `DonGia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonban`
--

INSERT INTO `chitietdonban` (`IDDonBan`, `IDKhung`, `DonGia`) VALUES
(1, 'C18004', 1499000000),
(2, 'E18003', 2049000000),
(3, 'GLC300C03', 3000000000),
(4, 'V250A03', 3232000000),
(5, 'A3503', 2259000000),
(6, 'C18004', 1510000000),
(6, 'GLC20003', 1799000000),
(7, 'MBS45002', 7469000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonnhap`
--

CREATE TABLE `chitietdonnhap` (
  `IDDonNhap` int(11) NOT NULL,
  `IDKhung` varchar(20) NOT NULL,
  `IDSanPham` int(11) NOT NULL,
  `DonGia` float NOT NULL,
  `Mau` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donban`
--

CREATE TABLE `donban` (
  `IDDonBan` int(11) NOT NULL,
  `IDKhachHang` int(11) NOT NULL,
  `IDNhanVien` int(11) NOT NULL,
  `IDKhuyenMai` int(11) NOT NULL,
  `NgayBan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donban`
--

INSERT INTO `donban` (`IDDonBan`, `IDKhachHang`, `IDNhanVien`, `IDKhuyenMai`, `NgayBan`) VALUES
(1, 1, 4, 3, '2021-04-24'),
(2, 2, 5, 1, '2021-04-26'),
(3, 3, 6, 0, '2021-03-24'),
(4, 4, 6, 3, '2021-04-11'),
(5, 5, 5, 1, '2021-04-30'),
(6, 1, 4, 1, '2021-04-28'),
(7, 4, 5, 2, '2021-06-02');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donnhap`
--

CREATE TABLE `donnhap` (
  `IDDonNhap` int(11) NOT NULL,
  `IDNhanVien` int(11) NOT NULL,
  `NgayNhap` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `IDKhachHang` int(11) NOT NULL,
  `HoTen` varchar(30) NOT NULL,
  `NgaySinh` date NOT NULL,
  `SDT` varchar(10) NOT NULL,
  `CMND` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`IDKhachHang`, `HoTen`, `NgaySinh`, `SDT`, `CMND`) VALUES
(1, 'Cao Thiên Trang', '1975-10-19', '0901234567', '012000009090'),
(2, 'Nguyễn Minh Long', '1990-03-03', '0901234568', '012000009091'),
(3, 'Triết Tiểu Tiên', '1989-04-04', '0901234569', '012000009092'),
(4, 'Lý Kim Quyên', '1995-05-05', '0901234561', '012000009093'),
(5, 'Văn Võ Hậu', '1994-08-08', '0901234562', '012000009094');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khung`
--

CREATE TABLE `khung` (
  `IDKhung` varchar(20) NOT NULL,
  `IDSanPham` int(11) NOT NULL,
  `DonGia` float NOT NULL,
  `Mau` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khung`
--

INSERT INTO `khung` (`IDKhung`, `IDSanPham`, `DonGia`, `Mau`) VALUES
('A3501', 9, 2259000000, 'Trắng'),
('A3502', 9, 2259000000, 'Đen'),
('C18001', 1, 1499000000, 'Đỏ'),
('C18002', 1, 1510000000, 'Đen'),
('C20001', 2, 1699000000, 'Trắng'),
('C20002', 2, 1750000000, 'Đen'),
('C30001', 3, 1969000000, 'Xám'),
('C30002', 3, 2000000000, 'Đen nhám'),
('E18001', 4, 2050000000, 'Trắng'),
('E18002', 4, 2049000000, 'Đen'),
('E20001', 5, 2360000000, 'Vàng'),
('E20002', 5, 2310000000, 'Đỏ'),
('E30001', 6, 2950000000, 'Đen'),
('E30002', 6, 3000000000, 'Trắng'),
('G6301', 11, 10950000000, 'Trắng'),
('G6302', 11, 10950000000, 'Đen'),
('GLB20001', 21, 1999000000, 'Xanh dương'),
('GLC20001', 13, 1799000000, 'Đen'),
('GLC20002', 13, 1799000000, 'Đen'),
('GLC200M01', 14, 2099000000, 'Trắng'),
('GLC200M02', 14, 2200000000, 'Xanh dương'),
('GLC300C01', 16, 3000000000, 'Đen'),
('GLC300C02', 16, 3010000000, 'Trắng'),
('GLC300M01', 15, 2499000000, 'Trắng'),
('GLC300M02', 15, 2550000000, 'Vàng'),
('GLE45001', 17, 4409000000, 'Xám'),
('GLS45001', 18, 4999000000, 'Đen'),
('GLS45002', 18, 5100000000, 'Đỏ'),
('GT01', 12, 6200000000, 'Xanh lá'),
('MBS45001', 10, 7469000000, 'Đen'),
('S45001', 7, 4500000000, 'Xanh dương'),
('S45002', 7, 4299000000, 'Đen'),
('S450L01', 8, 4969000000, 'Đen'),
('S450L02', 8, 5100000000, 'Đen carbon'),
('V250A01', 20, 3232000000, 'Xám'),
('V250L01', 19, 2662000000, 'Trắng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `IDKhuyenMai` int(11) NOT NULL,
  `TenChuongTrinh` varchar(30) NOT NULL,
  `ChietKhau` int(11) NOT NULL COMMENT 'Tỷ lệ phần trăm được giảm dựa trên hóa đơn',
  `MoTaChuongTrinh` varchar(100) NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`IDKhuyenMai`, `TenChuongTrinh`, `ChietKhau`, `MoTaChuongTrinh`, `NgayBatDau`, `NgayKetThuc`) VALUES
(0, 'Không', 0, 'Không khuyến mãi', '2021-01-01', '2021-12-31'),
(1, '30 tháng 4', 10, 'Mừng giải phóng miền Nam thống nhất đất nước, Chiết khấu 10% các dòng sản phẩm', '2021-04-26', '2021-05-20'),
(2, 'Ưu đãi tháng 6', 5, 'Tháng 6 bận rộn hè về. Khuyến mãi 5% các dòng xe trừ S-Class và MayBach', '2021-06-01', '2021-06-30'),
(3, 'Mùa Altcoin', 7, 'Mùa Altcoin tăng kinh khủng, hãy sắm xế iu cho bản thân nào. Giảm 7% các dòng C-Class GLC-Class', '2021-04-01', '2021-04-25');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `IDNhanVien` int(11) NOT NULL,
  `HoTen` varchar(30) NOT NULL,
  `NgaySinh` date NOT NULL,
  `SDT` varchar(10) NOT NULL,
  `CMND` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`IDNhanVien`, `HoTen`, `NgaySinh`, `SDT`, `CMND`) VALUES
(1, 'Lê Trí Nhân', '2000-06-29', '0788889378', '077200004703'),
(2, 'Nguyễn Minh Nguyên', '2000-10-24', '0327377921', '012323456789'),
(3, 'Nguyễn Thị Linh', '2000-07-07', '0909123123', '012312456789'),
(4, 'Đỗ Văn Dưỡng', '2000-06-06', '0909234234', '012334456789'),
(5, 'Bùi Thế Khang', '2000-05-05', '0909345345', '012356456789'),
(6, 'Đàm Thoại Lân', '2000-04-04', '0909456456', '012378456789'),
(7, 'Cao Quốc Hưng', '2000-01-02', '0909567567', '012391456789'),
(8, 'Nguyễn Hữu Thắng', '2000-08-08', '0909678678', '012399456789'),
(9, 'Nguyễn Tấn Phát', '2000-09-09', '0909789789', '012398456789'),
(10, 'Trương Thiện Tâm', '2000-10-10', '0909123234', '012397456789');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `IDPhanQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(30) NOT NULL,
  `MoTaQuyen` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`IDPhanQuyen`, `TenQuyen`, `MoTaQuyen`) VALUES
(1, 'Quản lý', ''),
(2, 'Bán hàng', ''),
(3, 'Nhập hàng', ''),
(4, 'Bảo hành', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `IDSanPham` int(11) NOT NULL,
  `TenSanPham` varchar(30) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `Dong` varchar(20) NOT NULL,
  `ChiTiet` varchar(2000) NOT NULL,
  `HinhAnh` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`IDSanPham`, `TenSanPham`, `SoLuong`, `Dong`, `ChiTiet`, `HinhAnh`) VALUES
(1, 'Mercedes-Benz C 180 AMG', 2, 'C Class', 'Mercedes-Benz C-Class là dòng xe Sedan cỡ nhỏ theo phong cách hiện đại, lịch lãm, khối động cơ bền bỉ cùng nhiều trang bị công nghệ đáng giá. Thiết kế C-Class mang đậm phong cách sang trọng, lịch lãm nhưng vẫn toát lên được sự mạnh mẽ và trẻ trung.', ''),
(2, 'Mercedes-Benz C 200 Exclusive', 2, 'C Class', 'Mercedes-Benz C-Class là dòng xe Sedan cỡ nhỏ theo phong cách hiện đại, lịch lãm, khối động cơ bền bỉ cùng nhiều trang bị công nghệ đáng giá. Thiết kế C-Class mang đậm phong cách sang trọng, lịch lãm nhưng vẫn toát lên được sự mạnh mẽ và trẻ trung.', ''),
(3, 'Mercedes-Benz C 300 AMG', 2, 'C Class', 'Mercedes-Benz C-Class là dòng xe Sedan cỡ nhỏ theo phong cách hiện đại, lịch lãm, khối động cơ bền bỉ cùng nhiều trang bị công nghệ đáng giá. Thiết kế C-Class mang đậm phong cách sang trọng, lịch lãm nhưng vẫn toát lên được sự mạnh mẽ và trẻ trung.', ''),
(4, 'Mercedes-Benz E 180', 2, 'E-Class', 'Dòng xe Mercedes-Benz E-Class mang phong cách hiện đại, sang trọng và dường như chưa tìm được đối thủ trong phân khúc Sedan cao cấp. Vì thế, thật không ngoa khi cho rằng đây là dòng xe hoàn hảo dành cho những doanh nhân thành đạt.', ''),
(5, 'Mercedes-Benz E 200 Exclusive', 2, 'E-Class', 'Dòng xe Mercedes-Benz E-Class mang phong cách hiện đại, sang trọng và dường như chưa tìm được đối thủ trong phân khúc Sedan cao cấp. Vì thế, thật không ngoa khi cho rằng đây là dòng xe hoàn hảo dành cho những doanh nhân thành đạt.', ''),
(6, 'Mercedes-Benz E 300 AMG', 2, 'E-Class', 'Dòng xe Mercedes-Benz E-Class mang phong cách hiện đại, sang trọng và dường như chưa tìm được đối thủ trong phân khúc Sedan cao cấp. Vì thế, thật không ngoa khi cho rằng đây là dòng xe hoàn hảo dành cho những doanh nhân thành đạt.', ''),
(7, 'Mercedes-Benz S 450 L', 2, 'S-Class', 'Sự thay đổi về vẻ ngoài của Mercedes-Benz S-Class được thể hiện rõ ràng nhất trên bộ đôi sản phẩm Mercedes-Benz S 450 L và Mercedes-Benz S 450 L Luxury. Theo đó, thay vì sử dụng lưới tản nhiệt 4 nan đơn, S-Class đã thay đổi bằng kiểu 3 nan kép đi kèm với các sọc đứng màu đen bóng.', ''),
(8, 'Mercedes-Benz S 450 L Luxury', 2, 'S-Class', 'Sự thay đổi về vẻ ngoài của Mercedes-Benz S-Class được thể hiện rõ ràng nhất trên bộ đôi sản phẩm Mercedes-Benz S 450 L và Mercedes-Benz S 450 L Luxury. Theo đó, thay vì sử dụng lưới tản nhiệt 4 nan đơn, S-Class đã thay đổi bằng kiểu 3 nan kép đi kèm với các sọc đứng màu đen bóng.', ''),
(9, 'Mercedes-AMG A 35 4MATIC', 2, 'A-Class', 'Các phiên bản xe thuộc dòng Mercedes-Benz A-Class đều có kiểu dáng thiết kế mang hơi hướng hiện đại, trẻ trung và thanh lịch. Ngoài ra, dòng xe này còn ghi điểm bởi hàng loạt trang bị sang trọng, công nghệ hỗ trợ lái thông minh, động cơ mạnh mẽ cùng giá bán hợp lý.', ''),
(10, 'Mercedes-Maybach S 450 4MATIC', 1, 'Maybach', 'Được phát triển dựa trên cảm hứng thiết kế của dòng Mercedes-Benz S-Class nhưng có nhiều nâng cấp mới lạ, các mẫu xe Mercedes-Maybach sở hữu vẻ đẹp sang trọng riêng biệt. Cụ thể có kích thước lớn, sự cải tiến tập trung chủ yếu ở phần đầu xe và đuôi xe.', ''),
(11, 'Mercedes-AMG G 63', 2, 'G-Class', 'Dòng xe Mercedes-Benz G-Class là biểu tượng có tầm ảnh hưởng lớn trong phân khúc xe Off-road. G-Class ra đời với mục tiêu là một dòng xe có độ an toàn cao, tiện nghi và khả năng vượt địa hình vượt trội.', ''),
(12, 'Mercedes-AMG GT', 1, 'G-Class', 'Dòng xe Mercedes-Benz G-Class là biểu tượng có tầm ảnh hưởng lớn trong phân khúc xe Off-road. G-Class ra đời với mục tiêu là một dòng xe có độ an toàn cao, tiện nghi và khả năng vượt địa hình vượt trội.', ''),
(13, 'Mercedes-Benz GLC 200', 2, 'GLC', 'Mercedes-Benz GLC là dòng được giới thiệu lần đầu vào năm 2015 để thay thế cho Mercedes-Benz GLK. Dòng xe thể thao đa dụng hạng sang này là tổng hòa của nét khỏe khoắn, năng động và sự thanh lịch đặc trưng, được thể hiện khéo léo từ thiết kế cho đến nội thất và ngoại thất xe. Mercedes-Benz GLC liên tục dẫn đầu doanh số bán hàng trong phân khúc, là sự lựa chọn hàng đầu của nhiều khách hàng đam mê và am hiểu xe sang.', ''),
(14, 'Mercedes-Benz GLC 200 4MATIC', 2, 'GLC', 'Mercedes-Benz GLC là dòng được giới thiệu lần đầu vào năm 2015 để thay thế cho Mercedes-Benz GLK. Dòng xe thể thao đa dụng hạng sang này là tổng hòa của nét khỏe khoắn, năng động và sự thanh lịch đặc trưng, được thể hiện khéo léo từ thiết kế cho đến nội thất và ngoại thất xe. Mercedes-Benz GLC liên tục dẫn đầu doanh số bán hàng trong phân khúc, là sự lựa chọn hàng đầu của nhiều khách hàng đam mê và am hiểu xe sang.', ''),
(15, 'Mercedes-Benz GLC 300 4MATIC', 2, 'GLC', 'Mercedes-Benz GLC là dòng được giới thiệu lần đầu vào năm 2015 để thay thế cho Mercedes-Benz GLK. Dòng xe thể thao đa dụng hạng sang này là tổng hòa của nét khỏe khoắn, năng động và sự thanh lịch đặc trưng, được thể hiện khéo léo từ thiết kế cho đến nội thất và ngoại thất xe. Mercedes-Benz GLC liên tục dẫn đầu doanh số bán hàng trong phân khúc, là sự lựa chọn hàng đầu của nhiều khách hàng đam mê và am hiểu xe sang.', ''),
(16, 'Mercedes-Benz GLC 300 Coupe', 2, 'GLC', 'Mercedes-Benz GLC là dòng được giới thiệu lần đầu vào năm 2015 để thay thế cho Mercedes-Benz GLK. Dòng xe thể thao đa dụng hạng sang này là tổng hòa của nét khỏe khoắn, năng động và sự thanh lịch đặc trưng, được thể hiện khéo léo từ thiết kế cho đến nội thất và ngoại thất xe. Mercedes-Benz GLC liên tục dẫn đầu doanh số bán hàng trong phân khúc, là sự lựa chọn hàng đầu của nhiều khách hàng đam mê và am hiểu xe sang.', ''),
(17, 'Mercedes-Benz GLE 450 4MATIC', 1, 'GLE', 'Theo thống kê từ MSNAuto, Mercedes-Benz GLE thuộc trong 10 dòng xe sang có doanh số cao nhất tại Mỹ khi có đến 54.595 sản phẩm được bán ra trong năm 2017. Tại Việt Nam, dòng SUV này của Mercedes-Benz cũng được nhiều khách hàng yêu thích và liên tục có doanh số đáng mơ ước so với các đối thủ cạnh tranh khác trong cùng phân khúc.', ''),
(18, 'Mercedes-Benz GLS 450 4MATIC', 2, 'GLS', 'Khi nhìn vào Mercedes-Benz GLS, người đối diện sẽ lập tức bị thu hút bởi sự sắc sảo cũng như sự nam tính của dòng xe này. Sở hữu các thông số Dài x Rộng x Cao lần lượt là 5130 x 1934 x 1850 (mm), chiều dài cơ sở đạt 3075mm, Mercedes-Benz GLS là dòng SUV hạng sang có kích thước lớn nhất trong phân khúc.', ''),
(19, 'Mercedes-Benz V 250 Luxury', 1, 'V-Class', 'Không chỉ sang trọng, phong cách, tiện nghi, các phiên bản thuộc Mercedes-Benz V-Class còn sở hữu không gian nội thất vô cùng rộng rãi. Vì thế, dòng xe đa dụng hạng sang này của Mercedes-Benz đã và đang là sự lựa chọn hàng đầu của nhiều gia đình và doanh nghiệp để đưa đón khách VIP.', ''),
(20, 'Mercedes-Benz V 250 AMG', 1, 'V-Class', 'Không chỉ sang trọng, phong cách, tiện nghi, các phiên bản thuộc Mercedes-Benz V-Class còn sở hữu không gian nội thất vô cùng rộng rãi. Vì thế, dòng xe đa dụng hạng sang này của Mercedes-Benz đã và đang là sự lựa chọn hàng đầu của nhiều gia đình và doanh nghiệp để đưa đón khách VIP.', ''),
(21, 'Mercedes-Benz GLB 200 AMG', 1, 'GLB', 'Mercedes-Benz GLB đã chính thức trình làng thị trường Việt Nam với mẫu xe Mercedes-Benz GLB 200 AMG 2020. Đây là phiên bản hội tụ đầy đủ những yếu tố mà bạn trông chờ ở một mẫu SUV hạng sang: Thiết kế đẳng cấp, động cơ bền bỉ và nhiều trang bị tuyệt vời.', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TaiKhoan` varchar(30) NOT NULL,
  `MatKhau` varchar(30) NOT NULL,
  `IDNhanVien` int(11) NOT NULL,
  `IDPhanQuyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`TaiKhoan`, `MatKhau`, `IDNhanVien`, `IDPhanQuyen`) VALUES
('huuthang', 'huuthang', 8, 3),
('lenhan', 'lenhan', 1, 1),
('minhnguyen', 'minhnguyen', 2, 1),
('nguyenlinh', 'nguyenlinh', 3, 3),
('quochung', 'quochung', 7, 3),
('tanphat', 'tanphat', 9, 4),
('thekhang', 'thekhang', 5, 2),
('thientam', 'thientam', 10, 4),
('thoailan', 'thoailan', 6, 2),
('vanduong', 'vanduong', 4, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonban`
--
ALTER TABLE `chitietdonban`
  ADD PRIMARY KEY (`IDDonBan`,`IDKhung`),
  ADD KEY `IDDonBan` (`IDDonBan`,`IDKhung`);

--
-- Chỉ mục cho bảng `chitietdonnhap`
--
ALTER TABLE `chitietdonnhap`
  ADD PRIMARY KEY (`IDDonNhap`,`IDKhung`),
  ADD KEY `IDSanPham` (`IDSanPham`),
  ADD KEY `IDKhung` (`IDKhung`);

--
-- Chỉ mục cho bảng `donban`
--
ALTER TABLE `donban`
  ADD PRIMARY KEY (`IDDonBan`),
  ADD KEY `IDKhachHang` (`IDKhachHang`,`IDNhanVien`,`IDKhuyenMai`),
  ADD KEY `IDNhanVien` (`IDNhanVien`),
  ADD KEY `IDKhuyenMai` (`IDKhuyenMai`);

--
-- Chỉ mục cho bảng `donnhap`
--
ALTER TABLE `donnhap`
  ADD PRIMARY KEY (`IDDonNhap`),
  ADD KEY `IDNhanVien` (`IDNhanVien`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`IDKhachHang`);

--
-- Chỉ mục cho bảng `khung`
--
ALTER TABLE `khung`
  ADD PRIMARY KEY (`IDKhung`),
  ADD KEY `IDSanPham` (`IDSanPham`),
  ADD KEY `IDKhung` (`IDKhung`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`IDKhuyenMai`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`IDNhanVien`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`IDPhanQuyen`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`IDSanPham`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TaiKhoan`),
  ADD KEY `IDPhanQuyen` (`IDPhanQuyen`),
  ADD KEY `IDNhanVien` (`IDNhanVien`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonban`
--
ALTER TABLE `chitietdonban`
  ADD CONSTRAINT `chitietdonban_ibfk_1` FOREIGN KEY (`IDDonBan`) REFERENCES `donban` (`IDDonBan`);

--
-- Các ràng buộc cho bảng `chitietdonnhap`
--
ALTER TABLE `chitietdonnhap`
  ADD CONSTRAINT `chitietdonnhap_ibfk_1` FOREIGN KEY (`IDDonNhap`) REFERENCES `donnhap` (`IDDonNhap`),
  ADD CONSTRAINT `chitietdonnhap_ibfk_2` FOREIGN KEY (`IDKhung`) REFERENCES `khung` (`IDKhung`),
  ADD CONSTRAINT `chitietdonnhap_ibfk_3` FOREIGN KEY (`IDSanPham`) REFERENCES `sanpham` (`IDSanPham`);

--
-- Các ràng buộc cho bảng `donban`
--
ALTER TABLE `donban`
  ADD CONSTRAINT `donban_ibfk_1` FOREIGN KEY (`IDKhachHang`) REFERENCES `khachhang` (`IDKhachHang`),
  ADD CONSTRAINT `donban_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`),
  ADD CONSTRAINT `donban_ibfk_3` FOREIGN KEY (`IDKhuyenMai`) REFERENCES `khuyenmai` (`IDKhuyenMai`);

--
-- Các ràng buộc cho bảng `donnhap`
--
ALTER TABLE `donnhap`
  ADD CONSTRAINT `donnhap_ibfk_1` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`);

--
-- Các ràng buộc cho bảng `khung`
--
ALTER TABLE `khung`
  ADD CONSTRAINT `khung_ibfk_1` FOREIGN KEY (`IDSanPham`) REFERENCES `sanpham` (`IDSanPham`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`IDPhanQuyen`) REFERENCES `phanquyen` (`IDPhanQuyen`) ON UPDATE CASCADE,
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
