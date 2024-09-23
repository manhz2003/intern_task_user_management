package vn.gt.utils;

import vn.gt.utils.config.ConfigurationManager;


public class KeyParams {
	
	/* Search Key Ke Hoach*/
	public static final String MARITIME_CODE = "maritimeCode";
	public static final String SHIP_NAME = "shipName";
	public static final String POSITION_CODE = "positionCode";
	public static final String PORT_REGION_CODE = "portRegionCode";
	public static final String MA_BAN_KHAI = "documentName";
	public static final String STATE_CODE = "stateCode";
	
	public static final String SHIP_DATE_FROM_START = "shipDateFromStart";
	public static final String SHIP_DATE_FROM_END = "shipDateFromEnd";
	
	public static final String SHIP_DATE_TO_START = "shipDateToStart";
	public static final String SHIP_DATE_TO_END = "shipDateToEnd";
	
	public static final String REQUEST_STATUS = "requestState";
	public static final String CALL_SIGN = "callSign";
	public static final String IMO = "imo";
	
	/* Search Key Thu Tuc*/
	
	//issueShifting Order
	public static final String VERSION_NO = "VersionNo";
	public static final String ID = "ID";
	
	//temp NoticeShipMessage
	public static final String CONFIRM_TIME = "ConfirmTime"; 
	
	public static final boolean ORDER_BY_ASC = true;
	public static final boolean ORDER_BY_DESC = false;
	
	public static final String N_LAN = "N_LAN";
	public static final String MOT_LAN = "1_LAN";
	
	public static final String CAP_NHAP_PORTCLEAN_LAN_MOT = "CAP_NHAP_PORTCLEAN_LAN_MOT";
	public static final String CAP_NHAP_PERFORTRANSIT_LAN_MOT = "CAP_NHAP_PERFORTRANSIT_LAN_MOT";
	public static final String DUYET_OR_SUA_PORTCLEAN = "duyet_or_sua_portclean";
	public static final String DUYET_OR_SUA_PERFORTRANSIT = "duyet_or_sua_perFortransit";
	public static final String CAP_NHAP_PORTCLEAN_LAN_N = "cap_nhap_portclean_lan_n";
	public static final String CAP_NHAP_PERFORTRANSIT_LAN_N = "cap_nhap_perfortransit_lan_n";
	
	public static final String CAP_NHAP_ACCEPTANCE_LAN_MOT = "CAP_NHAP_ACCEPTANCE_LAN_MOT";
	public static final String DUYET_OR_SUA_ACCEPTANCE = "duyet_or_sua_ACCEPTANCE";
	public static final String CAP_NHAP_ACCEPTANCE_LAN_N = "cap_nhap_ACCEPTANCE_lan_n";
	public static final String GIAM_DOC = ConfigurationManager.getStrProp("vn.gt.label.representative.giam.doc", "");
	public static final String PHO_GIAM_DOC = ConfigurationManager.getStrProp("vn.gt.label.representative.pho.giam.doc", "");
	public static final String VAN_THU = ConfigurationManager.getStrProp("vn.gt.label.representative.van.thu", "");	
	
	public static final Integer HAI_QUAN = 1;
	public static final Integer BIEN_PHONG = 2;
	public static final Integer YTE_QUOCTE = 3;
	public static final Integer THUC_VAT = 4;
	public static final Integer DONG_VAT = 5;
	public static final Integer HANG_HAI = 6;
	
	
}
