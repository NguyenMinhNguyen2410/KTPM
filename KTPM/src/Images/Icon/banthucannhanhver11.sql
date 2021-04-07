-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 24, 2020 lúc 07:32 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `banthucannhanh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `IDHoaDon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDMonAn` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(15) UNSIGNED NOT NULL,
  `DonGia` float UNSIGNED NOT NULL,
  `ThanhTien` float UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`IDHoaDon`, `IDMonAn`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
('HD01', 'MA01', 1, 70000, 70000),
('HD01', 'MA02', 1, 30000, 30000),
('HD01', 'MA03', 3, 50000, 150000),
('HD01', 'MA04', 1, 50000, 50000),
('HD02', 'MA02', 3, 30000, 90000),
('HD03', 'MA03', 1, 50000, 50000),
('HD03', 'MA04', 1, 50000, 50000),
('HD04', 'MA09', 1, 35000, 35000),
('HD04', 'MA10', 1, 35000, 35000),
('HD04', 'MA15', 1, 160000, 160000),
('HD04', 'MA29', 2, 10000, 20000),
('HD05', 'MA13', 1, 150000, 150000),
('HD05', 'MA24', 1, 30000, 30000),
('HD05', 'MA35', 1, 15000, 15000),
('HD05', 'MA36', 1, 15000, 15000),
('HD06', 'MA04', 2, 50000, 100000),
('HD06', 'MA29', 2, 10000, 20000),
('HD07', 'MA06', 1, 80000, 80000),
('HD07', 'MA26', 1, 30000, 30000),
('HD08', 'MA02', 3, 30000, 90000),
('HD09', 'MA02', 2, 30000, 60000),
('HD10', 'MA01', 1, 70000, 70000),
('HD11', 'MA07', 1, 80000, 80000),
('HD12', 'MA05', 2, 30000, 60000),
('HD13', 'MA02', 1, 30000, 30000),
('HD13', 'MA06', 2, 80000, 160000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadonnhap`
--

CREATE TABLE `chitiethoadonnhap` (
  `IDHoaDonNhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDNguyenLieu` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(15) UNSIGNED NOT NULL,
  `GiaNhap` float UNSIGNED NOT NULL,
  `ThanhTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadonnhap`
--

INSERT INTO `chitiethoadonnhap` (`IDHoaDonNhap`, `IDNguyenLieu`, `SoLuong`, `GiaNhap`, `ThanhTien`) VALUES
('HDN01', 'NL01', 30, 40000, 1200000),
('HDN01', 'NL17', 30, 23000, 690000),
('HDN02', 'NL06', 10, 40000, 400000),
('HDN02', 'NL18', 30, 23000, 690000),
('HDN02', 'NL20', 29, 23000, 667000),
('HDN03', 'NL13', 20, 40000, 800000),
('HDN04', 'NL04', 10, 40000, 400000),
('HDN04', 'NL19', 15, 30000, 450000),
('HDN05', 'NL01', 10, 20000, 200000),
('HDN05', 'NL09', 10, 40000, 400000),
('HDN05', 'NL13', 6, 50000, 300000),
('HDN06', 'NL02', 1, 70000, 70000),
('HDN06', 'NL16', 4, 120000, 480000),
('HDN06', 'NL19', 1, 20000, 20000),
('HDN06', 'NL26', 2, 40000, 80000),
('HDN07', 'NL06', 1, 70000, 70000),
('HDN07', 'NL10', 5, 40000, 200000),
('HDN07', 'NL17', 2, 65000, 130000),
('HDN07', 'NL21', 10, 15000, 150000),
('HDN07', 'NL22', 10, 15000, 150000),
('HDN07', 'NL23', 10, 15000, 150000),
('HDN07', 'NL24', 10, 15000, 150000),
('HDN08', 'NL15', 5, 90000, 450000),
('HDN08', 'NL20', 8, 25000, 400000),
('HDN08', 'NL27', 50, 7000, 350000),
('HDN08', 'NL28', 50, 10000, 500000),
('HDN9', 'NL07', 2, 30000, 60000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietnguyenlieu`
--

CREATE TABLE `chitietnguyenlieu` (
  `IDCongThuc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDNguyenLieu` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(15) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietnguyenlieu`
--

INSERT INTO `chitietnguyenlieu` (`IDCongThuc`, `IDNguyenLieu`, `SoLuong`) VALUES
('CT01', 'NL01', 1),
('CT01', 'NL02', 2),
('CT03', 'NL06', 4),
('CT03', 'NL17', 1),
('CT10', 'NL14', 3),
('CT17', 'NL12', 1),
('CT18', 'NL13', 1),
('CT21', 'NL27', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `congthuc`
--

CREATE TABLE `congthuc` (
  `IDCongThuc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDMonAn` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MoTaCongThuc` text COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `congthuc`
--

INSERT INTO `congthuc` (`IDCongThuc`, `IDMonAn`, `MoTaCongThuc`, `TrangThai`) VALUES
('CT01', 'MA01', 'gạo, thịt bò, trứng, rau củ', 'Hiện'),
('CT02', 'MA02', 'gạo, thịt bò, rau củ', 'Hiện'),
('CT03', 'MA03', 'gạo, trứng, rau củ', 'Hiện'),
('CT04', 'MA04', 'gạo, thịt gà, rau củ', 'Hiện'),
('CT05', 'MA05', 'gạo, thịt heo, kim chi, rau củ', 'Hiện'),
('CT06', 'MA06', 'thịt gà, gia vị ướp', 'Hiện'),
('CT07', 'MA07', 'thịt gà, ớt, gia vị ướp', 'Hiện'),
('CT08', 'MA08', 'thịt gà, đậu hà lan, gia vị ướp', 'Hiện'),
('CT09', 'MA09', 'mì, thịt gà, rau củ', 'Hiện'),
('CT10', 'MA10', 'mì, thịt heo, rau củ', 'Hiện'),
('CT11', 'MA11', 'mì, trứng, xúc xích, rau củ', 'Hiện'),
('CT12', 'MA12', 'bột mì, bột chiên giòn, cá', 'Hiện'),
('CT13', 'MA13', 'bột mì, men nở, tôm, mực, rau củ, phô mai', 'Hiện'),
('CT14', 'MA14', 'bột mì, men nở, rau củ, phô mai', 'Hiện'),
('CT15', 'MA15', 'bột mì, men nở, thịt nguội, nấm, phô mai', 'Hiện'),
('CT16', 'MA16', 'mì, cà chua, thịt heo', 'Hiện'),
('CT17', 'MA17', 'bột mì, bột gạo, hương vị đào', 'Hiện'),
('CT18', 'MA18', 'bột mì, bột gạo, thịt gà', 'Hiện'),
('CT19', 'MA19', 'bột mì, bột chiên giòn, thịt gà', 'Hiện'),
('CT20', 'MA20', 'whipping cream, hương vị dâu', 'Hiện'),
('CT21', 'MA21', 'whipping cream, hương vị socola', 'Hiện'),
('CT22', 'MA22', 'whipping cream, hương vị việt quất', 'Hiện'),
('CT23', 'MA23', 'bột chiên giòn, khoai tây', 'Hiện'),
('CT24', 'MA24', 'bột mì, bột gạo, khoai môn, phô mai', 'Hiện'),
('CT25', 'MA25', 'bột mì, bột chiên giòn, mực', 'Hiện'),
('CT26', 'MA26', 'bột mì, bột chiên giòn, phô mai, trứng', 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `IDHoaDon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDKhachHang` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDKhuyenMai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayLap` date NOT NULL,
  `TienGiamGia` float UNSIGNED NOT NULL,
  `TongTien` float UNSIGNED NOT NULL,
  `TrangThai` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`IDHoaDon`, `IDNhanVien`, `IDKhachHang`, `IDKhuyenMai`, `NgayLap`, `TienGiamGia`, `TongTien`, `TrangThai`) VALUES
('HD01', 'NV01', 'KH03', 'KM02', '2020-06-02', 30000, 270000, 'Hiện'),
('HD02', 'NV04', 'KH04', 'KM01', '2020-05-20', 0, 90000, 'Hiện'),
('HD03', 'NV04', 'KH02', 'KM03', '2020-05-17', 50000, 50000, 'Hiện'),
('HD04', 'NV06', 'KH04', 'KM01', '2020-04-05', 0, 250000, 'Hiện'),
('HD05', 'NV05', 'KH02', 'KM01', '2020-05-22', 0, 200000, 'Hiện'),
('HD06', 'NV03', 'KH03', 'KM02', '2020-06-01', 30000, 90000, 'Hiện'),
('HD07', 'NV06', 'KH10', 'KM03', '2020-06-04', 50000, 60000, 'Hiện'),
('HD08', 'NV01', 'KH01', 'KM01', '2020-06-21', 0, 90000, 'Hiện'),
('HD09', 'NV01', 'KH02', 'KM01', '2020-06-21', 0, 60000, 'Hiện'),
('HD10', 'NV01', 'KH01', 'KM02', '2020-06-18', 30000, 40000, 'Hiện'),
('HD11', 'NV01', 'KH10', 'KM01', '2020-06-23', 0, 80000, 'Hiện'),
('HD12', 'NV01', 'KH05', 'KM01', '2020-06-23', 0, 60000, 'Hiện'),
('HD13', 'NV01', 'KH04', 'KM01', '2020-06-24', 0, 190000, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadonnhap`
--

CREATE TABLE `hoadonnhap` (
  `IDHoaDonNhap` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `IDNhanVien` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `IDNhaCungCap` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhap` date NOT NULL,
  `TongTien` float UNSIGNED NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadonnhap`
--

INSERT INTO `hoadonnhap` (`IDHoaDonNhap`, `IDNhanVien`, `IDNhaCungCap`, `NgayNhap`, `TongTien`, `TrangThai`) VALUES
('HDN01', 'NV01', 'NCC5', '2020-05-06', 1890000, 'Hiện'),
('HDN02', 'NV06', 'NCC3', '2020-05-28', 1757000, 'Hiện'),
('HDN03', 'NV02', 'NCC6', '2020-05-03', 800000, 'Hiện'),
('HDN04', 'NV05', 'NCC3', '2020-05-01', 850000, 'Hiện'),
('HDN05', 'NV04', 'NCC5', '2020-04-01', 700000, 'Hiện'),
('HDN06', 'NV03', 'NCC5', '2020-04-17', 650000, 'Hiện'),
('HDN07', 'NV02', 'NCC2', '2020-05-23', 1000000, 'Hiện'),
('HDN08', 'NV06', 'NCC1', '2020-02-01', 1500000, 'Hiện'),
('HDN9', 'NV01', 'NCC4', '2020-06-23', 60000, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `IDKhachHang` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `HoKhachHang` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Gmail` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TongChiTieu` float UNSIGNED NOT NULL,
  `TrangThai` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`IDKhachHang`, `HoKhachHang`, `TenKhachHang`, `Gmail`, `GioiTinh`, `SoDienThoai`, `TongChiTieu`, `TrangThai`) VALUES
('KH01', 'Lê', 'Lợi', 'LeLoi@gmail.com', 'Nam', '0985444326', 200000, 'Hiện'),
('KH02', 'Phan Bội', 'A', 'BoiChau@gmail.com', 'Nam', '0903121212', 5000000, 'Hiện'),
('KH03', 'Lê Văn', 'Long', 'VanLong@gmail.com', 'Nam', '0323232323', 1000000, 'Hiện'),
('KH04', 'Nguyễn Thị', 'A', 'ThiA@gmail.com', 'Nữ', '0902030401', 690000, 'Hiện'),
('KH05', 'Noor A Kim', 'Lam', 'BanThucAnNhanh@gmail.com', 'Nữ', '0377010101', 60000, 'Hiện'),
('KH06', 'Nguyễn Minh Phương', 'Nam', 'BanThucAnNhanh@gmail.com', 'Nam', '0367010101', 0, 'Hiện'),
('KH07', 'Nguyễn Hữu', 'Nhân', 'BanThucAnNhanh@gmail.com', 'Nam', '0367010102', 0, 'Hiện'),
('KH08', 'Đặng Thị Kiều', 'Oanh', 'BanThucAnNhanh@gmail.com', 'Nữ', '0367017105', 0, 'Hiện'),
('KH09', 'Nguyễn Thị Ngọc', 'Giàu', 'BanThucAnNhanh@gmail.com', 'Nữ', '0367010324', 0, 'Hiện'),
('KH10', 'Đặng Cao Phúc', 'Hòa', 'BanThucAnNhanh@gmail.com', 'Nữ', '0367010132', 80000, 'Hiện'),
('KH11', 'Trần Ngọc Huy', 'Hoàng', 'BanThucAnNhanh@gmail.com', 'Nam', '0377018963', 0, 'Hiện'),
('KH12', 'Nguyễn Mai Kim ', 'Ngân', 'BanThucAnNhanh@gmail.com', 'Nữ', '0377075320', 0, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `IDKhuyenMai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenChuongTrinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TienGiam` int(10) NOT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL,
  `NoiDungGiamGia` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`IDKhuyenMai`, `TenChuongTrinh`, `TienGiam`, `NgayBatDau`, `NgayKetThuc`, `NoiDungGiamGia`, `TrangThai`) VALUES
('KM01', 'Không khuyến mãi', 0, '2020-06-01', '2020-06-01', 'Áp dụng cho những ngày thường', 'Hiện'),
('KM02', 'Lễ 30/4', 30000, '2020-04-27', '2020-05-03', 'Mừng ngày lễ 30 tháng 4', 'Hiện'),
('KM03', 'Tết Dương lịch', 50000, '2019-12-27', '2020-01-04', 'Nhân ngày Tết Dương lịch ', 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monan`
--

CREATE TABLE `monan` (
  `IDMonAn` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenMonAn` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DonViTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` int(15) UNSIGNED NOT NULL,
  `HinhAnh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Loai` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(15) UNSIGNED NOT NULL,
  `TrangThai` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `monan`
--

INSERT INTO `monan` (`IDMonAn`, `TenMonAn`, `DonViTinh`, `DonGia`, `HinhAnh`, `Loai`, `SoLuong`, `TrangThai`) VALUES
('MA01', 'Cơm Bò Trứng', 'Phần', 70000, 'Cơm Bò Trứng.jpg', 'Món chính', 11, 'Hiện'),
('MA02', 'Cơm Bò xào Đậu que', 'Dĩa', 30000, 'Cơm Bò xào Đậu que.jpg', 'Món chính', 12, 'Hiện'),
('MA03', 'Cơm chiên Trứng', 'Dĩa', 50000, 'Cơm chiên Trứng.jpg', 'Món chính', 31, 'Hiện'),
('MA04', 'Cơm gà Sốt Cà chua', 'Dĩa', 50000, 'Cơm gà Sốt Cà chua.jpg', 'Món chính', 112, 'Hiện'),
('MA05', 'Cơm Thịt heo xào Kim Chi', 'Dĩa', 30000, 'Cơm Thịt heo xào Kim Chi.jpg', 'Món chính', 29, 'Hiện'),
('MA06', 'Gà Nướng', 'Phần', 80000, 'Gà Nướng.jpg', 'Món chính', 8, 'Hiện'),
('MA07', 'Gà sốt Cay', 'Phần', 80000, 'Gà sốt Cay.jpg', 'Món chính', 11, 'Hiện'),
('MA08', 'Gà sốt Đậu', 'Phần', 80000, 'Gà sốt Đậu.jpg', 'Món chính', 40, 'Hiện'),
('MA09', 'Mì gà quay', 'Dĩa', 35000, 'Mì gà quay.jpg', 'Món chính', 32, 'Hiện'),
('MA10', 'Mì trộn thịt heo xào', 'Dĩa', 35000, 'Mì trộn thịt heo xào.jpg', 'Món chính', 22, 'Hiện'),
('MA11', 'Mì trộn Trứng Xúc xích', 'Dĩa', 35000, 'Mì trộn Trứng Xúc xích.jpg', 'Món chính', 4, 'Hiện'),
('MA12', 'Phi lê Cá rán', 'Phần', 30000, 'Phi lê Cá rán.jpg', 'Món chính', 30, 'Hiện'),
('MA13', 'Pizza Hải sản', 'Phần', 150000, 'Pizza Hải sản.jpg', 'Món chính', 20, 'Hiện'),
('MA14', 'Pizza Rau củ', 'Phần', 140000, 'Pizza Rau củ.jpg', 'Món chính', 20, 'Hiện'),
('MA15', 'Pizza Thịt nguội và Nấm', 'Phần', 160000, 'Pizza Thịt nguội và Nấm.jpg', 'Món chính', 20, 'Hiện'),
('MA16', 'Spagghetti', 'Dĩa', 30000, 'Spagghetti.jpg', 'Món chính', 35, 'Hiện'),
('MA17', 'Bánh Pie vị Đào', 'Phần', 25000, 'Bánh Pie vị Đào.jpg', 'Món phụ', 30, 'Hiện'),
('MA18', 'Bánh xếp Gà quay', 'Phần', 25000, 'Bánh xếp Gà quay.jpg', 'Món phụ', 30, 'Hiện'),
('MA19', 'Gà nướng 3 vị nhân nhồi', 'Phần', 30000, 'Gà nướng 3 vị nhân nhồi.jpg', 'Món phụ', 30, 'Hiện'),
('MA20', 'Kem Dâu', 'Phần', 20000, 'Kem Dâu.jpg', 'Món phụ', 50, 'Hiện'),
('MA21', 'Kem Socola', 'Phần', 20000, 'Kem Socola.jpg', 'Món phụ', 50, 'Hiện'),
('MA22', 'Kem Việt quất', 'Phần', 20000, 'Kem Việt quất.jpg', 'Món phụ', 50, 'Hiện'),
('MA23', 'Khoai tây chiên', 'Phần', 15000, 'Khoai tây chiên.jpg', 'Món phụ', 35, 'Hiện'),
('MA24', 'Khoai viên Phô mai', 'Phần', 30000, 'Khoai viên Phô mai.jpg', 'Món phụ', 29, 'Hiện'),
('MA25', 'Mực rán', 'Phần', 30000, 'Mực rán.jpg', 'Món phụ', 19, 'Hiện'),
('MA26', 'Phô mai que', 'Phần', 30000, 'Phô mai que.jpg', 'Món phụ', 30, 'Hiện'),
('MA27', 'Cà phê Đen', 'Ly', 15000, 'Cà phê Đen.jpg', 'Nước uống', 40, 'Hiện'),
('MA28', 'Cà phê Sữa', 'Ly', 15000, 'Cà phê Sữa.jpg', 'Nước uống', 30, 'Hiện'),
('MA29', 'CocaCola', 'Ly', 10000, 'CocaCola.jpg', 'Nước uống', 80, 'Hiện'),
('MA30', 'Nestea', 'Ly', 15000, 'Nestea.jpg', 'Nước uống', 70, 'Hiện'),
('MA31', 'Nước Sâm', 'Ly', 20000, 'Nước Sâm.jpg', 'Nước uống', 40, 'Hiện'),
('MA32', 'Soda Blue Sky', 'Ly', 20000, 'Soda Blue Sky.jpg', 'Nước uống', 76, 'Hiện'),
('MA33', 'Trà Chanh', 'Ly', 7000, 'Trà Chanh.jpg', 'Nước uống', 100, 'Hiện'),
('MA34', 'Trà đá', 'Ly', 2000, 'Trà đá.jpg', 'Nước uống', 200, 'Hiện'),
('MA35', 'Trà Đào', 'Ly', 15000, 'Trà Đào.jpg', 'Nước uống', 50, 'Hiện'),
('MA36', 'Trà Vải', 'Ly', 15000, 'Trà Vải.jpg', 'Nước uống', 50, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguyenlieu`
--

CREATE TABLE `nguyenlieu` (
  `IDNguyenLieu` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNguyenLieu` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` float UNSIGNED NOT NULL,
  `HinhAnh` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Loai` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DonViTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(15) NOT NULL,
  `TrangThai` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nguyenlieu`
--

INSERT INTO `nguyenlieu` (`IDNguyenLieu`, `TenNguyenLieu`, `DonGia`, `HinhAnh`, `Loai`, `DonViTinh`, `SoLuong`, `TrangThai`) VALUES
('NL01', 'Gạo', 20000, 'Gạo.jpg', 'a', 'Quả', 10, 'Hiện'),
('NL02', 'Thịt bò', 70000, 'Thịt bò.jpg', '', 'Kg', 10, 'Hiện'),
('NL03', 'Thịt heo', 50000, 'Thịt heo.jpg', '', 'Kg', 40, 'Hiện'),
('NL04', 'Trứng', 4000, 'Trứng.jpg', '', 'Quả', 101, 'Hiện'),
('NL05', 'Rau củ', 40000, 'Rau củ.jpg', '', 'Kg', 21, 'Hiện'),
('NL06', 'Thịt gà', 70000, 'Thịt gà.jpg', 'a', 'Kg', 40, 'Hiện'),
('NL07', 'Kim chi', 30000, 'Kim chi.jpg', 'a', 'Kg', 7, 'Hiện'),
('NL08', 'Gia vị ướp', 15000, 'Gia vị ướp.jpg', 'a', 'Kg', 30, 'Hiện'),
('NL09', 'Mì', 40000, 'Mì.jpg', 'a', 'Kg', 100, 'Hiện'),
('NL10', 'Xúc xích', 40000, 'Xúc xích.jpg', 'a', 'Kg', 123, 'Hiện'),
('NL11', 'Bột mì', 12000, 'Bột mì.jpg', 'a', 'Kg', 52, 'Hiện'),
('NL12', 'Bột chiên giòn', 14000, 'Bột chiên giòn.jpg', 'a', 'Bịch', 20, 'Hiện'),
('NL13', 'Cá', 50000, 'Cá.jpg', 'a', 'Kg', 30, 'Hiện'),
('NL14', 'Men nở', 21000, 'Men nở.jpg', 'a', 'Kg', 3, 'Hiện'),
('NL15', 'Tôm', 90000, 'Tôm.jpg', 'a', 'Kg', 13, 'Hiện'),
('NL16', 'Mực', 120000, 'Mực.jpg', 'a', 'Kg', 41, 'Hiện'),
('NL17', 'Phô mai', 65000, 'Phô mai.jpg', 'a', 'Kg', 21, 'Hiện'),
('NL18', 'Thịt nguội', 60000, 'Thịt nguội.jpg', 'a', 'Kg', 14, 'Hiện'),
('NL19', 'Nấm', 20000, 'Nấm.jpg', 'a', 'Kg', 100, 'Hiện'),
('NL20', 'Bột gạo', 25000, 'Bột gạo.jpg', 'a', 'Kg', 100, 'Hiện'),
('NL21', 'Hương vị đào', 15000, 'Hương vị đào.jpg', 'a', 'Kg', 10, 'Hiện'),
('NL22', 'Hương vị dâu', 15000, 'Hương vị dâu.jpg', 'a', 'Kg', 10, 'Hiện'),
('NL23', 'Hương vị socola', 15000, 'Hương vị socola.jpg', 'a', 'Kg', 10, 'Hiện'),
('NL24', 'Hương vị việt quất', 15000, 'Hương vị việt quất.jpg', 'a', 'Kg', 10, 'Hiện'),
('NL26', 'Whipping cream', 40000, 'Whipping cream.jpg', 'a', 'Kg', 20, 'Hiện'),
('NL27', 'Khoai môn', 7000, 'Khoai môn.jpg', 'a', 'Kg', 40, 'Hiện'),
('NL28', 'Khoai tây', 10000, 'Khoai tây.jpg', 'a', 'Kg', 38, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `IDNhaCungCap` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNhaCungCap` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Gmail` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`IDNhaCungCap`, `TenNhaCungCap`, `SoDienThoai`, `Gmail`, `DiaChi`, `TrangThai`) VALUES
('NCC1', 'Circle K', '0923515615', 'Circle_K@gmail.com', 'a', 'Hiện'),
('NCC2', 'GS25', '0351565488', 'GS25@gmail.com', 'a', 'Hiện'),
('NCC3', 'FamilyMart', '0365166515', 'FamilyMart@gmail.com', 'a', 'Hiện'),
('NCC4', '7- Eleven', '0316155649', '7- Eleven@gmail.com', 'a', 'Hiện'),
('NCC5', 'Ministop', '0321564168', 'Ministop@gmail.com', 'a', 'Hiện'),
('NCC6', 'Cheers', '0935165165', 'Cheers@gmail.com', 'a', 'Hiện'),
('NCC7', 'Co.op Smile', '0916544845', 'Co.opSmile@gmail.com', 'a', 'Hiện'),
('NCC8', 'Speed L', '0351515684', 'SpeedL@gmail.com', 'a', 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `IDNhanVien` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `HoNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Gmail` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ChucVu` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`IDNhanVien`, `HoNhanVien`, `TenNhanVien`, `Gmail`, `GioiTinh`, `SoDienThoai`, `ChucVu`, `TrangThai`) VALUES
('NV01', 'Nguyễn Minh ', 'Nguyên', 'MinhNguyen@gmail.com', 'Nam', '0327377921', 'Giám đốc', 'Hiện'),
('NV02', 'Nguyễn Quốc ', 'Tuấn', 'QuocTuan@gmail.com', 'Nam', '0909000888', 'Nhân viên', 'Hiện'),
('NV03', 'Lê Trí', 'Nhân', 'TriNhan@gmail.com', 'chưa biết', '0304050621', 'Quản lý', 'Hiện'),
('NV04', 'Nguyễn Tấn ', 'Phát', 'TanPhat@gmail.com', 'Nam', '0903221155', 'Nhân viên', 'Hiện'),
('NV05', 'Nguyễn', 'Thịnh', 'NguyenThinh@gmail.com', 'Nam', '0203040101', 'Nhân viên', 'Hiện'),
('NV06', 'Cao Quốc', 'Hưng', 'QuocHung@gmail.com', 'Nam', '0530125124', 'Nhân viên', 'Hiện'),
('NV10', 'Mai Đăng', 'Khôi', 'khoi@gmail.com', 'Nữ', '0788889378', 'Nhân viên', 'Hiện'),
('NV11', 'xàm', 'qua', 'abcde@gmail.com', 'Nam', '0982521142', 'Quản lý', 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `IDPhanQuyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenQuyen` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MoTaQuyen` text COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`IDPhanQuyen`, `TenQuyen`, `MoTaQuyen`, `TrangThai`) VALUES
('PQ0', 'admin', 'QLBanHangQLNhapHangQLMonAnQLNguyenLieuQLCongThucQLHoaDonQLHDNhapQLKhuyenMaiQLKhachHangQLNhanVienQLNhaCungCapQLTaiKhoanQLPhanQuyenQLThongKe', 'Hiện'),
('PQ1', 'Quản lý', 'QLBanHangQLNhapHangQLMonAnQLNguyenLieuQLCongThucQLHoaDonQLHDNhapQLKhuyenMaiQLKhachHangQLNhanVienQLNhaCungCapQLThongKe', 'Hiện'),
('PQ2', 'Bán hàng', 'QLBanHangQLHoaDonQLKhachHang', 'Hiện'),
('PQ3', 'Nhập hàng', 'QLNhapHangQLHDNhap', 'Hiện'),
('PQ4', 'Bán và nhập hàng', 'QLBanHangQLNhapHangQLHoaDonQLHDNhapQLKhachHang', 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoannhanvien`
--

CREATE TABLE `taikhoannhanvien` (
  `TaiKhoan` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `IDNhanVien` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `IDPhanQuyen` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoannhanvien`
--

INSERT INTO `taikhoannhanvien` (`TaiKhoan`, `IDNhanVien`, `IDPhanQuyen`, `MatKhau`, `TrangThai`) VALUES
('admin', 'NV01', 'PQ0', 'admin', 'Hiện'),
('MaiDangKhoi', 'NV10', 'PQ2', '123456', 'Hiện'),
('QuocHung', 'NV06', 'PQ2', '123456', 'Hiện'),
('QuocTuan', 'NV02', 'PQ2', '123456', 'Hiện'),
('TanPhat', 'NV04', 'PQ4', '123456', 'Hiện'),
('Thinh', 'NV05', 'PQ2', '123456', 'Hiện'),
('TriNhan', 'NV03', 'PQ1', '123456', 'Hiện'),
('xamqua', 'NV11', 'PQ1', '123456', 'Hiện');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD UNIQUE KEY `IDHoaDon_3` (`IDHoaDon`,`IDMonAn`,`SoLuong`,`DonGia`,`ThanhTien`),
  ADD KEY `IDHoaDon` (`IDHoaDon`),
  ADD KEY `IDMonAn` (`IDMonAn`),
  ADD KEY `IDHoaDon_2` (`IDHoaDon`);

--
-- Chỉ mục cho bảng `chitiethoadonnhap`
--
ALTER TABLE `chitiethoadonnhap`
  ADD UNIQUE KEY `IDHoaDonNhap_2` (`IDHoaDonNhap`,`IDNguyenLieu`,`SoLuong`,`GiaNhap`,`ThanhTien`),
  ADD KEY `IDHoaDonNhap` (`IDHoaDonNhap`),
  ADD KEY `IDNguyenLieu` (`IDNguyenLieu`);

--
-- Chỉ mục cho bảng `chitietnguyenlieu`
--
ALTER TABLE `chitietnguyenlieu`
  ADD UNIQUE KEY `IDCongThuc_2` (`IDCongThuc`,`IDNguyenLieu`,`SoLuong`),
  ADD KEY `IDCongThuc` (`IDCongThuc`),
  ADD KEY `IDNguyenLieu` (`IDNguyenLieu`);

--
-- Chỉ mục cho bảng `congthuc`
--
ALTER TABLE `congthuc`
  ADD PRIMARY KEY (`IDCongThuc`),
  ADD KEY `IDMonAn` (`IDMonAn`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`IDHoaDon`),
  ADD KEY `IDNhanVien` (`IDNhanVien`),
  ADD KEY `IDKhachHang` (`IDKhachHang`),
  ADD KEY `IDGiamGia` (`IDKhuyenMai`);

--
-- Chỉ mục cho bảng `hoadonnhap`
--
ALTER TABLE `hoadonnhap`
  ADD PRIMARY KEY (`IDHoaDonNhap`),
  ADD KEY `IDNhanVien` (`IDNhanVien`),
  ADD KEY `IDNhaCungCap` (`IDNhaCungCap`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`IDKhachHang`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`IDKhuyenMai`);

--
-- Chỉ mục cho bảng `monan`
--
ALTER TABLE `monan`
  ADD PRIMARY KEY (`IDMonAn`);

--
-- Chỉ mục cho bảng `nguyenlieu`
--
ALTER TABLE `nguyenlieu`
  ADD PRIMARY KEY (`IDNguyenLieu`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`IDNhaCungCap`);

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
-- Chỉ mục cho bảng `taikhoannhanvien`
--
ALTER TABLE `taikhoannhanvien`
  ADD PRIMARY KEY (`TaiKhoan`),
  ADD KEY `IDPhanQuyen` (`IDPhanQuyen`),
  ADD KEY `IDNhanVien` (`IDNhanVien`) USING BTREE;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`IDMonAn`) REFERENCES `monan` (`IDMonAn`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`IDHoaDon`) REFERENCES `hoadon` (`IDHoaDon`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitiethoadonnhap`
--
ALTER TABLE `chitiethoadonnhap`
  ADD CONSTRAINT `chitiethoadonnhap_ibfk_1` FOREIGN KEY (`IDHoaDonNhap`) REFERENCES `hoadonnhap` (`IDHoaDonNhap`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadonnhap_ibfk_2` FOREIGN KEY (`IDNguyenLieu`) REFERENCES `nguyenlieu` (`IDNguyenLieu`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietnguyenlieu`
--
ALTER TABLE `chitietnguyenlieu`
  ADD CONSTRAINT `chitietnguyenlieu_ibfk_1` FOREIGN KEY (`IDNguyenLieu`) REFERENCES `nguyenlieu` (`IDNguyenLieu`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietnguyenlieu_ibfk_2` FOREIGN KEY (`IDCongThuc`) REFERENCES `congthuc` (`IDCongThuc`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `congthuc`
--
ALTER TABLE `congthuc`
  ADD CONSTRAINT `congthuc_ibfk_1` FOREIGN KEY (`IDMonAn`) REFERENCES `monan` (`IDMonAn`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`IDKhuyenMai`) REFERENCES `khuyenmai` (`IDKhuyenMai`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`IDKhachHang`) REFERENCES `khachhang` (`IDKhachHang`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadonnhap`
--
ALTER TABLE `hoadonnhap`
  ADD CONSTRAINT `hoadonnhap_ibfk_1` FOREIGN KEY (`IDNhaCungCap`) REFERENCES `nhacungcap` (`IDNhaCungCap`),
  ADD CONSTRAINT `hoadonnhap_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `taikhoannhanvien`
--
ALTER TABLE `taikhoannhanvien`
  ADD CONSTRAINT `taikhoannhanvien_ibfk_1` FOREIGN KEY (`IDPhanQuyen`) REFERENCES `phanquyen` (`IDPhanQuyen`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `taikhoannhanvien_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
