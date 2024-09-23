package vn.gt.portlet.phuongtien;


public final class CommandTypes
{
    public static final int Undefined = 0;

    public static final int CreateInvoiceMT = 100; // Tạo Hóa đơn, eHD tự cấp InvoiceForm, InvoiceSerial; InvoiceNo = 0 (tạo Hóa đơn mới)
    public static final int CreateInvoiceTR = 101; // Tạo Hóa đơn, eHD tự cấp InvoiceForm, InvoiceSerial và cấp InvoiceNo (tạo Hóa đơn Trống)
    public static final int CreateInvoiceWithFormSerial = 110; // Tạo Hóa đơn, Client tự cấp InvoiceForm, InvoiceSerial; InvoiceNo = 0 (tạo Hóa đơn mới)
    public static final int CreateInvoiceWithFormSerialNo = 111; // Tạo Hóa đơn, Client tự cấp InvoiceForm, InvoiceSerial, InvoiceNo (tạo Hóa đơn mới, có sẵn Số Hóa đơn)
    public static final int CreateInvoiceWithFormSerialBkavSetInvNo = 112; // Tạo Hóa đơn, Client tự cấp InvoiceForm, InvoiceSerial, eHD cấp luôn số HĐ

    public static final int CreateInvoiceReplace = 120; // Tạo Hóa đơn thay thế cho 1 Hoá đơn khác
    public static final int CreateInvoiceAdjust = 121; // Tạo Hóa đơn điều chỉnh cho 1 Hoá đơn khác
    public static final int CreateInvoiceAdjustDiscount = 122; // Tạo Hóa đơn điều chỉnh chiết khấu
    public static final int CreateInvoiceAdjustDiscountSetInvoiceNo = 126; // Tạo Hóa đơn điều chỉnh chiết khấu, Hệ thống cấp số luôn

    public static final int CreateInvoiceAdjustForInvPaper = 125; // Tạo Hóa đơn điều chỉnh cho Hóa đơn giấy
    public static final int CreateInvoiceAdjustForInvPaperSetInvoiceNo = 127; // Tạo Hóa đơn điều chỉnh cho Hóa đơn giấy HT cấp số luôn

    public static final int CreateInvoiceReplaceSetInvoiceNo = 123; // Tạo Hóa đơn thay thế cấp số luôn
    public static final int CreateInvoiceAdjustSetInvoiceNo = 124; // Tạo Hóa đơn điều chỉnh cấp số luôn

    public static final int UpdateInvoiceByPartnerInvoiceID = 200; // Cập nhật thông tin của Hoá đơn
    public static final int UpdateInvoiceByInvoiceForm_Serial_No = 203; // Cập nhật thông tin của Hoá đơn
    public static final int UpdateInvoiceByInvoiceGUID = 204; // Cập nhật thông tin của Hoá đơn

    public static final int CancelInvoiceByInvoiceGUID = 201; // Hủy hóa đơn
    public static final int CancelInvoiceByPartnerInvoiceID = 202; // Hủy hóa đơn
                                                            // 3xx: Xóa
    public static final int DeleteInvoice = 301; // Xoá hóa đơn chưa phát hành
    public static final int DeleteInvoiceByInvoiceGUID = 303; // Xoá hóa đơn chưa phát hành

    public static final int UploadFile = 500;//Upload file excel dữ liệu Hóa đơn

    public static final int ViewInvoice = 600;//Trả về cho Partner file PDF bản thể hiện của Hoá đơn theo trạng thái
    public static final int ViewInvoiceConversion = 601;//Trả về cho Partner file PDF chuyển đổi

    public static final int GetInvoiceDataWS = 800; // Lấy thông tin của tờ Hóa đơn
    public static final int GetInvoiceStatusID = 801; // Lấy trạng thái của tờ Hóa đơn
    public static final int GetInvoiceHistory = 802; // Lấy lịch sử thay đổi của 1 tờ Hóa đơn
    public static final int GetInvoiceLink = 804;//Lấy link để tải file hóa đơn in chuyển đổi
    public static final int GetListInvoiceWS_CK = 806;//Lấy ds thông tin của Hóa đơn chờ ký
    public static final int CreateAccount = 902;//Tạo tài khoản Demo trên eHD demo
    public static final int UpdateAccount = 903; // Cập nhật thông tin tài khoản
    public static final int GetUnitInforByTaxCode = 904; // Lấy thông tin doanh nghiệp từ thuế

}
