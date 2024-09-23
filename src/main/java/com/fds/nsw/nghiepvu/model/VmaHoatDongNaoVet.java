package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_hoatdong_naovet", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaHoatDongNaoVet {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "ma_hoat_dong_nao_vet", length = 50)
    private String maHoatDongNaoVet;

    @Lob
    @Column(name = "ten_cong_trinh")
    private String tenCongTrinh;

    @Lob
    @Column(name = "chu_dau_tu")
    private String chuDauTu;

    @Lob
    @Column(name = "nha_thau_thi_cong")
    private String nhaThauThiCong;

    @Lob
    @Column(name = "tu_van_giam_sat")
    private String tuVanGiamSat;

    @Lob
    @Column(name = "quyet_dinh_phe_duyet")
    private String quyetDinhPheDuyet;

    @Lob
    @Column(name = "vi_tri_do")
    private String viTriDo;

    @Lob
    @Column(name = "ten_phuong_tien")
    private String tenPhuongTien;

    @Column(name = "so_dkhc", length = 50)
    private String soDkhc;

    @Column(name = "cap_phuong_tien", length = 50)
    private String capPhuongTien;

    @Lob
    @Column(name = "suc_cho")
    private String sucCho;

    @Lob
    @Column(name = "cong_dung")
    private String congDung;

    @Column(name = "so_dang_kiem", length = 50)
    private String soDangKiem;

    @Column(name = "ngay_cap_dang_kiem")
    private Date ngayCapDangKiem;

    @Column(name = "ngay_het_han_dang_kiem")
    private Date ngayHetHanDangKiem;

    @Column(name = "ngay_den_cang")
    private Date ngayDenCang;

    @Column(name = "so_giay_phep_tau_den", length = 50)
    private String soGiayPhepTauDen;

    @Column(name = "ngay_roi_cang")
    private Date ngayRoiCang;

    @Column(name = "so_giay_phep_roi", length = 50)
    private String soGiayPhepRoi;

    @Column(name = "ngay_cap_phep_hoat_dong_thi_cong")
    private Date ngayCapPhepHoatDongThiCong;

    @Column(name = "han_cap_phep_hoat_dong")
    private Date hanCapPhepHoatDong;

    @Lob
    @Column(name = "tong_thoi_gian_thi_cong")
    private String tongThoiGianThiCong;

    @Lob
    @Column(name = "so_luot_hoat_dong")
    private String soLuotHoatDong;

    @Lob
    @Column(name = "khoi_luong_thi_cong")
    private String khoiLuongThiCong;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}