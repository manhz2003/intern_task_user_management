package vn.gt.uengine;

import java.util.Date;


public class UengineProcess {

	/**
	 *
	 * @param tthcMa
	 * @param accountCanBo
	 * @param idHoSo
	 * @param maSoHoSo
	 * @param tenDoiTuongHoSo
	 * @param ngayHenTra
	 * @return
	 */
	public static boolean initUengineProcess(String tthcMa,
			String accountCanBo, long idHoSo, String maSoHoSo,
			String tenDoiTuongHoSo, Date ngayHenTra) {
		try {
			boolean retVal = false;

//			IBrmsService brmsService = new IBrmsServiceLocator();
//			IBrms brms = brmsService.getIBrmsPort();
//
//			String instid = brms.initInstanceMaTTHC(accountCanBo, tthcMa);
//			System.out.println(instid + "  instid");
//
//			if (instid != null) {
//
//				KeyedParameter[] processVariable = new KeyedParameter[6];
//				processVariable[0] = new KeyedParameter("idHoSo", idHoSo);
//				processVariable[1] = new KeyedParameter("maHoSo", maSoHoSo);
//				processVariable[2] = new KeyedParameter("ngayTiepNhan",
//						ngayHenTra);
//				processVariable[3] = new KeyedParameter("noiNopHoSo", "00012");
//				processVariable[4] = new KeyedParameter(
//						"roleKeHoach",
//						"Nguyễn Thị Thu Trang/trangntt1@danang.gov.vn, Lê Hà Thanh Bình/binhlht@danang.gov.vn, Nguyễn Minh Quân/quannm@danang.gov.vn, Ông Thuận Ánh/anhot@danang.gov.vn");
//				processVariable[5] = new KeyedParameter(
//						"roleThuTuc",
//						"Nguyễn Thị Thu Trang/trangntt1@danang.gov.vn, Lê Hà Thanh Bình/binhlht@danang.gov.vn, Nguyễn Minh Quân/quannm@danang.gov.vn, Ông Thuận Ánh/anhot@danang.gov.vn");
//				retVal = brms.registerService(instid, processVariable);
//
//				// .initInstanceMaTTHC("", processVariable);
//			}

			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public static boolean completeWorkitemByAddProfile(String maSoHoSo, String tenDoiTuongHoSo) {
		try {
			boolean retVal = false;

//			IBrmsService brmsService = new IBrmsServiceLocator();
//			IBrms brms = brmsService.getIBrmsPort();
//
//			KeyedParameter[] processVariable = new KeyedParameter[1];
//			processVariable[0] = new KeyedParameter("isApproved", "YES");
//			retVal = brms.completeWorkitemByAddProfile(maSoHoSo, tenDoiTuongHoSo, processVariable);
			return retVal;
		} catch (Exception e) {
		}
		return false;
	}


	public static void main(String[] abc){
		try{

			UengineProcess.initUengineProcess("TBNC","anhnt", 1, "1", "1", new Date());


		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
