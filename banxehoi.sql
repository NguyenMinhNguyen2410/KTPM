-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 29, 2021 lúc 11:23 AM
-- Phiên bản máy phục vụ: 10.4.16-MariaDB
-- Phiên bản PHP: 7.4.12

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
  `IDKhung` int(11) NOT NULL,
  `DonGia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `IDPhanQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(30) NOT NULL,
  `MoTaQuyen` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `IDSanPham` int(11) NOT NULL,
  `TenSanPham` varchar(30) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `Dong` varchar(20) NOT NULL,
  `ChiTiet` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonban`
--
ALTER TABLE `chitietdonban`
  ADD PRIMARY KEY (`IDDonBan`,`IDKhung`);

--
-- Chỉ mục cho bảng `chitietdonnhap`
--
ALTER TABLE `chitietdonnhap`
  ADD PRIMARY KEY (`IDDonNhap`,`IDKhung`);

--
-- Chỉ mục cho bảng `donban`
--
ALTER TABLE `donban`
  ADD PRIMARY KEY (`IDDonBan`);

--
-- Chỉ mục cho bảng `donnhap`
--
ALTER TABLE `donnhap`
  ADD PRIMARY KEY (`IDDonNhap`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`IDKhachHang`);

--
-- Chỉ mục cho bảng `khung`
--
ALTER TABLE `khung`
  ADD PRIMARY KEY (`IDKhung`);

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
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`IDPhanQuyen`) REFERENCES `phanquyen` (`IDPhanQuyen`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
