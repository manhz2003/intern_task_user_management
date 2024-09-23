/**
 * 
 */
package vn.gt.tichhop.message;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmGtBusinessType;
import com.fds.nsw.nghiepvu.model.DmGtBusinessType;
import vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.ConvertUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import java.util.Date ;

import vn.nsw.Header;
import vn.nsw.model.CargoDeclaration;
import vn.nsw.model.ConfirmationOfArrival;
import vn.nsw.model.CrewEffectsDeclaration;
import vn.nsw.model.CrewLists;
import vn.nsw.model.DangerousGoodsManifest;
import vn.nsw.model.DeclarationForAnimalQuarantine;
import vn.nsw.model.DeclarationForPlantQuarantine;
import vn.nsw.model.GeneralDeclaration;
import vn.nsw.model.HealthQuanrantineDeclare;
import vn.nsw.model.NoticeOfArrival;
import vn.nsw.model.PTTNDGeneralDeclaration;
import vn.nsw.model.PassengerList;
import vn.nsw.model.ShipSecurityNotification;
import vn.nsw.model.ShipsStoresDeclaration;
import vn.nsw.model.inland.InlandCrewLists;
import vn.nsw.model.inland.InlandGeneralDeclaration;
import vn.nsw.model.inland.InlandNoticeOfArrival;
import vn.nsw.model.inland.InlandPassengerList;
import vn.nsw.model.inland.PTTNDPassengerList;
import vn.nsw.model.inland.InlandCrewCallCenter;





/**
 * @author win_64
 */
import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class BusinessInsertTableResultUtils
 {
	
	
	public static String remark = "";
	

	public static String getDateOfArrival(List<Object> liObjects) {
		String result = null;
		for (Object object : liObjects) {
			if (object instanceof GeneralDeclaration) {
				result = ((GeneralDeclaration) object).getDateOfArrival();
				break;
			}
			if (object instanceof InlandGeneralDeclaration) {
				result = ((InlandGeneralDeclaration) object).getDateOfArrival();
				break;
			}
			if (object instanceof PTTNDGeneralDeclaration) {
				result = ((PTTNDGeneralDeclaration) object).getDateOfArrival();
				break;
			}
		}
		return result;
	}
	
	public static void insertInlandResultNotification(Header header, List<Object> liObjects) {
		try {
			log.debug("==insertResultNotification===" + liObjects.size() + "==Subject==Type==" + header.getSubject().getType());
			String dateOfArrival = "";
			
			if (header.getSubject().getType() == MessageType.HO_SO) {
				dateOfArrival = getDateOfArrival(liObjects);
			} 
			
			log.debug("==insertResultNotification==DateOfArrival==" + dateOfArrival + "==header==Subject==Type==" + header.getSubject().getType());
			for (Object object : liObjects) {
				log.info("insertInlanResultNotification********************"+object.getClass().getName());
				if (object instanceof ShipSecurityNotification) {
					try {
						if (object != null) {
							ShipSecurityNotification shipSecurity = (ShipSecurityNotification) object;
							if (shipSecurity != null && shipSecurity.getDocumentName() != null && shipSecurity.getDocumentName().length() > 0) {
								
								createResultDeclarationWithShipSecurity(header, object);
								
								checkShipDateFormShipSecurityNotificationNCvaQC(header, object);
								
								checkTempDocumentFromShipSecurityNotificationKeHoachNCvaQC(header, object);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof CargoDeclaration) {
					try {
						if (object != null) {
							CargoDeclaration cargoDeclaration = (CargoDeclaration) object;
							if (cargoDeclaration != null && cargoDeclaration.getDocumentName() != null && cargoDeclaration.getDocumentName().length() > 0) {
								createResultDeclarationCargoDeclaration(header);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof CrewLists) {
					try {
						if (object != null) {
							CrewLists crewList = (CrewLists) object;
							if (crewList != null && crewList.getDocumentName() != null && crewList.getDocumentName().length() > 0) {
								createResultDeclarationCrewLists(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof InlandCrewLists) {
					try {
						if (object != null) {
							InlandCrewLists inslandcrewList = (InlandCrewLists) object;
							if (inslandcrewList != null && inslandcrewList.getDocumentName() != null && inslandcrewList.getDocumentName().length() > 0) {
								createResultDeclarationInlandCrewLists(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof DangerousGoodsManifest) {
					try {
						if (object != null) {
							DangerousGoodsManifest dangerousGoodsManifest = (DangerousGoodsManifest) object;
							if (dangerousGoodsManifest != null && dangerousGoodsManifest.getDocumentName() != null
									&& dangerousGoodsManifest.getDocumentName().length() > 0) {
								createResultDeclarationDangerousGoodsManifest(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof CrewEffectsDeclaration) {
					try {
						if (object != null) {
							CrewEffectsDeclaration crewEffects = (CrewEffectsDeclaration) object;
							if (crewEffects != null && crewEffects.getDocumentName() != null && crewEffects.getDocumentName().length() > 0) {
								createResultDeclarationCrewEffectsDeclaration(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof NoticeOfArrival) {
					try {
						if (object != null) {
							NoticeOfArrival noticeOfArrival = (NoticeOfArrival) object;
							if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null
									&& noticeOfArrival.getDocumentName().length() > 0) {
								createResultDeclarationNoticeOfArrival(header, object);
								
								checkShipDateFormXacBaoThongBaoNCvaQC(header, object);
								
								// truong hop xuat canh, GUI THONG BAO sau, thi cai thread truoc no lay DateOfArrial update vao TempDoc
								// check truong hop nay, lay trong Ban khai chung update nguoc lai
								checkShipDateToXuatCanh(header, object);
								
								//TODO issue 89
								checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC(header, object);
							}
						}
					} catch (Exception e) {
					}
				}else if (object instanceof InlandNoticeOfArrival) {
					try {
						if (object != null) {
							InlandNoticeOfArrival inlandnoticeOfArrival = (InlandNoticeOfArrival) object;
							if (inlandnoticeOfArrival != null && inlandnoticeOfArrival.getDocumentName() != null
									&& inlandnoticeOfArrival.getDocumentName().length() > 0) {
								createResultDeclarationInlandNoticeOfArrival(header, object);
								
								checkShipDateFormXacBaoThongBaoNCvaQCInland(header, object);
								
								// truong hop xuat canh, GUI THONG BAO sau, thi cai thread truoc no lay DateOfArrial update vao TempDoc
								// check truong hop nay, lay trong Ban khai chung update nguoc lai
								checkShipDateToXuatCanh(header, object);
								
								//TODO issue 89
								checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQCInland(header, object);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof PassengerList) {
					try {
						if (object != null) {
							PassengerList passengerList = (PassengerList) object;
							if (passengerList != null && passengerList.getDocumentName() != null && passengerList.getDocumentName().length() > 0) {
								createResultDeclarationPassengerList(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				}else if (object instanceof InlandPassengerList) {
					try {
						if (object != null) {
							InlandPassengerList passengerList = (InlandPassengerList) object;
							if (passengerList != null && passengerList.getDocumentName() != null && passengerList.getDocumentName().length() > 0) {
								createResultDeclarationPassengerListInland(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof ShipsStoresDeclaration) {
					try {
						if (object != null) {
							ShipsStoresDeclaration shipsStores = (ShipsStoresDeclaration) object;
							if (shipsStores != null && shipsStores.getDocumentName() != null && shipsStores.getDocumentName().length() > 0) {
								createResultDeclartionShipStore(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof DeclarationForAnimalQuarantine) {
					try {
						if (object != null) {
							DeclarationForAnimalQuarantine forAnimalQuarantine = (DeclarationForAnimalQuarantine) object;
							if (forAnimalQuarantine != null && forAnimalQuarantine.getDocumentName() != null
									&& forAnimalQuarantine.getDocumentName().length() > 0) {
								createResultDeclarationForAnimalQuarantine(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof DeclarationForPlantQuarantine ) {
					try {
						if (object != null) {
							DeclarationForPlantQuarantine forPlantQuarantine = (DeclarationForPlantQuarantine) object;
							if (forPlantQuarantine != null && forPlantQuarantine.getDocumentName() != null
									&& forPlantQuarantine.getDocumentName().length() > 0) {
								createResultDeclarationForPlantQuarantine(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof GeneralDeclaration) {
					try {
						if (object != null) {
							GeneralDeclaration generalDeclaration = (GeneralDeclaration) object;
							if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
									&& generalDeclaration.getDocumentName().length() > 0) {
								createResultGeneralDeclaration(header, object);
								// check Nhap Canh,Qua Canh shipDateForm
								checkShipDateFormGeneralDeclarationNCvaQC(header, object);
								
								// check Xuat canh shipDateTo
								checkShipDateToXuatCanh(header, object);
							}
						}
					} catch (Exception e) {
					}
					
			// ////////////////		
				}else if (object instanceof InlandGeneralDeclaration) {
					try {
						if (object != null) {
							InlandGeneralDeclaration generalDeclaration = (InlandGeneralDeclaration) object;
							if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
									&& generalDeclaration.getDocumentName().length() > 0) {
								createResultGeneralDeclarationInland(header, object);
								// check Nhap Canh,Qua Canh shipDateForm
								checkShipDateFormGeneralDeclarationNCvaQCInland(header, object);
								
								// check Xuat canh shipDateTo
								checkShipDateToXuatCanhInland(header, object);
							}
						}
					} catch (Exception e) {
					}
					
				} else if (object instanceof HealthQuanrantineDeclare ) {
					try {
						if (object != null) {
							HealthQuanrantineDeclare healthQuanrantine = (HealthQuanrantineDeclare) object;
							if (healthQuanrantine != null && healthQuanrantine.getDeclarationOfHealth() != null && healthQuanrantine.getDeclarationOfHealth().size() > 0) {
								createResultHealthQuanrantineDeclare(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				}else if (object instanceof PTTNDGeneralDeclaration) {
					System.out
							.println("PTTNDGeneralDeclaration.PTTNDGeneralDeclaration()");
					try {
						if (object != null) {
							PTTNDGeneralDeclaration generalDeclaration = (PTTNDGeneralDeclaration) object;
							if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
									&& generalDeclaration.getDocumentName().length() > 0) {
								createResultGeneralDeclarationInland(header, object);								
							}
						}
					} catch (Exception e) {
						
					}
					
				} else if (object instanceof PTTNDPassengerList) {
					try {
						if (object != null) {
							PTTNDPassengerList passengerList = (PTTNDPassengerList) object;
							if (passengerList != null && passengerList.getDocumentName() != null && passengerList.getDocumentName().length() > 0) {
								createResultDeclarationPassengerListInland(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof InlandCrewCallCenter) {
					try {
						if (object != null) {
							InlandCrewCallCenter crewCallCenter = (InlandCrewCallCenter) object;
							if (crewCallCenter != null && header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_BAO)) {
								createResultDeclarationGeneralCallCenterInland(header, object);
								createResultDeclarationCrewCallCenterInland(header, object);
							}
						}
					} catch (Exception e) {
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	
	//TODO ISSUE 89
	public static void insertResultNotification(Header header, List<Object> liObjects) {
		try {
			log.debug("==insertResultNotification===" + liObjects.size() + "==Subject==Type==" + header.getSubject().getType());
			String dateOfArrival = "";
			if (header.getSubject().getType() == MessageType.HO_SO) {
				dateOfArrival = getDateOfArrival(liObjects);
			}
			log.debug("==insertResultNotification==DateOfArrival==" + dateOfArrival + "==header==Subject==Type==" + header.getSubject().getType());
			for (Object object : liObjects) {
				if (object instanceof ShipSecurityNotification) {
					try {
						if (object != null) {
							ShipSecurityNotification shipSecurity = (ShipSecurityNotification) object;
							if (shipSecurity != null && shipSecurity.getDocumentName() != null && shipSecurity.getDocumentName().length() > 0) {
								
								createResultDeclarationWithShipSecurity(header, object);
								
								checkShipDateFormShipSecurityNotificationNCvaQC(header, object);
								
								checkTempDocumentFromShipSecurityNotificationKeHoachNCvaQC(header, object);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof CargoDeclaration) {
					try {
						if (object != null) {
							CargoDeclaration cargoDeclaration = (CargoDeclaration) object;
							if (cargoDeclaration != null && cargoDeclaration.getDocumentName() != null && cargoDeclaration.getDocumentName().length() > 0) {
								createResultDeclarationCargoDeclaration(header);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof CrewLists) {
					try {
						if (object != null) {
							CrewLists crewList = (CrewLists) object;
							if (crewList != null && crewList.getDocumentName() != null && crewList.getDocumentName().length() > 0) {
								createResultDeclarationCrewLists(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof DangerousGoodsManifest) {
					try {
						if (object != null) {
							DangerousGoodsManifest dangerousGoodsManifest = (DangerousGoodsManifest) object;
							if (dangerousGoodsManifest != null && dangerousGoodsManifest.getDocumentName() != null
									&& dangerousGoodsManifest.getDocumentName().length() > 0) {
								createResultDeclarationDangerousGoodsManifest(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof CrewEffectsDeclaration) {
					try {
						if (object != null) {
							CrewEffectsDeclaration crewEffects = (CrewEffectsDeclaration) object;
							if (crewEffects != null && crewEffects.getDocumentName() != null && crewEffects.getDocumentName().length() > 0) {
								createResultDeclarationCrewEffectsDeclaration(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof NoticeOfArrival) {
					try {
						if (object != null) {
							NoticeOfArrival noticeOfArrival = (NoticeOfArrival) object;
							if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null
									&& noticeOfArrival.getDocumentName().length() > 0) {
								createResultDeclarationNoticeOfArrival(header, object);
								
								checkShipDateFormXacBaoThongBaoNCvaQC(header, object);
								
								// truong hop xuat canh, GUI THONG BAO sau, thi cai thread truoc no lay DateOfArrial update vao TempDoc
								// check truong hop nay, lay trong Ban khai chung update nguoc lai
								checkShipDateToXuatCanh(header, object);
								
								//TODO issue 89
								checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC(header, object);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof PassengerList) {
					try {
						if (object != null) {
							PassengerList passengerList = (PassengerList) object;
							if (passengerList != null && passengerList.getDocumentName() != null && passengerList.getDocumentName().length() > 0) {
								createResultDeclarationPassengerList(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof ShipsStoresDeclaration) {
					try {
						if (object != null) {
							ShipsStoresDeclaration shipsStores = (ShipsStoresDeclaration) object;
							if (shipsStores != null && shipsStores.getDocumentName() != null && shipsStores.getDocumentName().length() > 0) {
								createResultDeclartionShipStore(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof DeclarationForAnimalQuarantine) {
					try {
						if (object != null) {
							DeclarationForAnimalQuarantine forAnimalQuarantine = (DeclarationForAnimalQuarantine) object;
							if (forAnimalQuarantine != null && forAnimalQuarantine.getDocumentName() != null
									&& forAnimalQuarantine.getDocumentName().length() > 0) {
								createResultDeclarationForAnimalQuarantine(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof DeclarationForPlantQuarantine) {
					try {
						if (object != null) {
							DeclarationForPlantQuarantine forPlantQuarantine = (DeclarationForPlantQuarantine) object;
							if (forPlantQuarantine != null && forPlantQuarantine.getDocumentName() != null
									&& forPlantQuarantine.getDocumentName().length() > 0) {
								createResultDeclarationForPlantQuarantine(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof GeneralDeclaration) {
					try {
						if (object != null) {
							GeneralDeclaration generalDeclaration = (GeneralDeclaration) object;
							if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
									&& generalDeclaration.getDocumentName().length() > 0) {
								createResultGeneralDeclaration(header, object);
								// check Nhap Canh,Qua Canh shipDateForm
								checkShipDateFormGeneralDeclarationNCvaQC(header, object);
								
								// check Xuat canh shipDateTo
								checkShipDateToXuatCanh(header, object);
							}
						}
					} catch (Exception e) {
					}
					
				} else if (object instanceof HealthQuanrantineDeclare) {
					try {
						if (object != null) {
							HealthQuanrantineDeclare healthQuanrantine = (HealthQuanrantineDeclare) object;
							if (healthQuanrantine != null && healthQuanrantine.getDeclarationOfHealth() != null && healthQuanrantine.getDeclarationOfHealth().size() > 0) {
								createResultHealthQuanrantineDeclare(header, dateOfArrival);
							}
						}
					} catch (Exception e) {
					}
				} else if (object instanceof ConfirmationOfArrival) {
					try {
						if (object != null) {
							ConfirmationOfArrival confirmationOfArrival = (ConfirmationOfArrival) object;
							if (confirmationOfArrival != null && confirmationOfArrival.getDocumentName() != null
									&& confirmationOfArrival.getDocumentName().length() > 0) {
								createResultDeclarationNoticeOfArrival(header, object);
								
								checkShipDateFormXacBaoThongBaoNCvaQC(header, object);
								
								// truong hop xuat canh, GUI THONG BAO sau, thi cai thread truoc no lay DateOfArrial update vao TempDoc
								// check truong hop nay, lay trong Ban khai chung update nguoc lai
								checkShipDateToXuatCanh(header, object);
								
								//TODO issue 89
								checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC(header, object);
							}
						}
					} catch (Exception e) {
					}
				} 
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	

	//TODO minhhandsome issue = 109
	private static void createResultHealthQuanrantineDeclare(Header header, String dateOfArrival) {
		log.info("==createQuanrantineDeclare==Header==" + header);
		
		ResultDeclaration declaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			declaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI);
			declaration.setLatestSend(1);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	//TODO minhhandsome issue = 109
	private static void createResultGeneralDeclaration(Header header, Object object) {
		log.debug("==createResultGeneralDeclaration==RequestCode==" + header.getReference().getMessageId());
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_CHUNG, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((GeneralDeclaration) object).getDateOfArrival()));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(((GeneralDeclaration) object).getDateOfArrival())));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
				log.info("==createResultGeneralDeclaration==addResultDeclaration==DocumentName==" + header.getSubject().getReference());
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((GeneralDeclaration) object).getDateOfArrival()));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(((GeneralDeclaration) object).getDateOfArrival())));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((GeneralDeclaration) object).getDateOfArrival()));
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
			
		}
	}
	
	//
	private static void createResultGeneralDeclarationInland(Header header, Object object) {
		log.info("==createResultGeneralDeclaration==RequestCode==" + header.getReference().getMessageId());
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_CHUNG, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		log.info("resultDeclaration"+resultDeclaration);
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			if(object instanceof InlandGeneralDeclaration){
				resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((InlandGeneralDeclaration) object).getDateOfArrival()));
				resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
						FormatData.parseStringToDate(((InlandGeneralDeclaration) object).getDateOfArrival())));
			}else if(object instanceof PTTNDGeneralDeclaration){
				resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((PTTNDGeneralDeclaration) object).getDateOfArrival()));
				resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
						FormatData.parseStringToDate(((PTTNDGeneralDeclaration) object).getDateOfArrival())));
			}
			
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
				log.info("==createResultInlandGeneralDeclaration==addResultDeclaration==DocumentName==" + header.getSubject().getReference());
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			
			if(object instanceof InlandGeneralDeclaration){
				resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((InlandGeneralDeclaration) object).getDateOfArrival()));
				resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
						FormatData.parseStringToDate(((InlandGeneralDeclaration) object).getDateOfArrival())));
			}else if(object instanceof PTTNDGeneralDeclaration){
				resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(((PTTNDGeneralDeclaration) object).getDateOfArrival()));
				resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
						FormatData.parseStringToDate(((PTTNDGeneralDeclaration) object).getDateOfArrival())));
			}
			
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	//
	private static void createResultDeclarationGeneralCallCenterInland(Header header, Object object) {
		log.info("==createResultDeclaration==RequestCode==" + header.getReference().getMessageId());
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_CHUNG, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		log.info("resultDeclaration"+resultDeclaration);
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemainingTime("");
			
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
				log.info("==createResultDeclaration==addResultDeclaration==DocumentName==" + header.getSubject().getReference());
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_CHUNG);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemainingTime("");
			
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclarationCrewCallCenterInland(Header header, Object object) {
		log.info("==createResultDeclaration==RequestCode==" + header.getReference().getMessageId());
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		log.info("resultDeclaration"+resultDeclaration);
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemainingTime("");
			
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
				log.info("==createResultDeclaration==addResultDeclaration==DocumentName==" + header.getSubject().getReference());
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemainingTime("");
			
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	//
	private static void createResultDeclarationForPlantQuarantine(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationPlantQuarantine==Header==" + header);
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclarationForAnimalQuarantine(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationAnimalQuarantine==Header==" + header);
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclartionShipStore(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationShipStoreDeclartion==Header==" + header);
		
		ResultDeclaration declaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DU_TRU_CUA_TAU, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			declaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DU_TRU_CUA_TAU);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_DU_TRU_CUA_TAU);
			declaration.setLatestSend(1);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_DU_TRU_CUA_TAU);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclarationPassengerList(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationPassengerList==Header==" + header);
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	//ml
	private static void createResultDeclarationPassengerListInland(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationPassengerList==Header==" + header);
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	//
	
	private static void createResultDeclarationNoticeOfArrival(Header header, Object object) {
		log.info("==createResulDeclaration===" + object.getClass().getName() + "==Header==" + header.getSubject().getFunction() + "==Type==" + header.getSubject().getType());
		ResultDeclaration declaration = null;
		int messateType = MessageType.THONG_BAO_TAU_DEN_CANG;
		
		// Truong hop messageType la 99.
		
		if (MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST == header.getSubject().getType()) {
			log.info("==Truong hop messageType la 99==FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST");
			// Voi ban khai nhap canh.
			if (header.getSubject().getDocumentType() == MessageType.NHAP_CANH) {
				messateType = MessageType.THONG_BAO_TAU_DEN_CANG;
				
			} else if (header.getSubject().getDocumentType() == MessageType.XUAT_CANH) {
				// Voi ban khai xuat canh.
				messateType = MessageType.THONG_BAO_TAU_ROI_CANG;
				
			} else if (header.getSubject().getDocumentType() == MessageType.QUA_CANH) {
				// Voi ban khai qua canh
				messateType = MessageType.THONG_BAO_TAU_QUA_CANH;
				
			}
		} else {
			log.info("==Truong hop messageType KHONG LA== ! 99==");
			messateType = header.getSubject().getType();
		}
		log.info("==header==Subject==Type==" + header.getSubject().getType());
		
		List<ResultDeclaration> lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				messateType, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		
		if (lstDeclaration != null && lstDeclaration.size() > 0) {
			declaration = lstDeclaration.get(0);
		}
		
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(messateType);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		
		Date timeOfArrival = null;
		
		
		if(object instanceof NoticeOfArrival) {
			timeOfArrival = FormatData.parseStringToDate(((NoticeOfArrival) object).getTimeOfArrival());
		} else if(object instanceof ConfirmationOfArrival) {
			timeOfArrival = FormatData.parseStringToDate(((ConfirmationOfArrival) object).getTimeOfArrival());
		}
			
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(messateType);
			declaration.setLatestSend(1);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setArrivalDepartureTime(timeOfArrival);
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					timeOfArrival));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(messateType);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			declaration.setArrivalDepartureTime(timeOfArrival);
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					timeOfArrival));
			if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	//inland
	private static void createResultDeclarationInlandNoticeOfArrival(Header header, Object object) {
		log.info("==createResulDeclarationInlandNoticeOfArrival==Header==" + header + "==Type==" + header.getSubject().getType());
		ResultDeclaration declaration = null;
		int messateType = MessageType.THONG_BAO_TAU_DEN_CANG;
		
		// Truong hop messageType la 99.
		
		if (MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST == header.getSubject().getType()) {
			log.info("==Truong hop messageType la 99==FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST");
			// Voi ban khai nhap canh.
			if (header.getSubject().getDocumentType() == MessageType.NHAP_CANH) {
				messateType = MessageType.THONG_BAO_TAU_DEN_CANG;
				
			} else if (header.getSubject().getDocumentType() == MessageType.XUAT_CANH) {
				// Voi ban khai xuat canh.
				messateType = MessageType.THONG_BAO_TAU_ROI_CANG;
				
			} else if (header.getSubject().getDocumentType() == MessageType.QUA_CANH) {
				// Voi ban khai qua canh
				messateType = MessageType.THONG_BAO_TAU_QUA_CANH;
				
			}
		} else {
			log.info("==Truong hop messageType KHONG LA== ! 99==");
			messateType = header.getSubject().getType();
		}
		log.info("==header==Subject==Type==" + header.getSubject().getType());
		
		List<ResultDeclaration> lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				messateType, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		
		if (lstDeclaration != null && lstDeclaration.size() > 0) {
			declaration = lstDeclaration.get(0);
		}
		
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(messateType);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(messateType);
			declaration.setLatestSend(1);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(((InlandNoticeOfArrival) object).getTimeOfArrival()));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(((InlandNoticeOfArrival) object).getTimeOfArrival())));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(messateType);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(((InlandNoticeOfArrival) object).getTimeOfArrival()));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(((InlandNoticeOfArrival) object).getTimeOfArrival())));
if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	
	
	private static void createResultDeclarationCrewEffectsDeclaration(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationCrewEffectsDeclaration==Header==" + header);
		
		ResultDeclaration declaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			declaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN);
			declaration.setLatestSend(1);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclarationDangerousGoodsManifest(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationDangerousGoodsManifest==Header==" + header);
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			resultDeclaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			resultDeclaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclarationInlandCrewLists(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationINLANDCrewLists==Header==" + header);
		
		ResultDeclaration declaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			declaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			declaration.setLatestSend(1);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	} //aaa
	
	private static void createResultDeclarationCrewLists(Header header, String dateOfArrival) {
		log.debug("==createResultDeclarationCrewLists==Header==" + header);
		
		ResultDeclaration declaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			declaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			declaration.setLatestSend(1);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(dateOfArrival));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(dateOfArrival)));
if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	
	private static void createResultDeclarationCargoDeclaration(Header header) {
		log.debug("==createResultDeclarationCargoDeclaration==Header==" + header);
		
		ResultDeclaration resultDeclaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_HANG_HOA, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			resultDeclaration = listResultDeclarations.get(0);
		}
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_HANG_HOA);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (resultDeclaration == null) {
			resultDeclaration = new ResultDeclaration();
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setDocumentName(header.getSubject().getReference());
			resultDeclaration.setDocumentYear(header.getSubject().getDocumentYear());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_HANG_HOA);
			resultDeclaration.setLatestSend(1);
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			resultDeclaration.setRequestCode(header.getReference().getMessageId());
			resultDeclaration.setBusinessTypeCode(MessageType.BAN_KHAI_HANG_HOA);
			resultDeclaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			resultDeclaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			resultDeclaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			resultDeclaration.setLatestSend(resultDeclaration.getLatestSend() + 1);
if(resultDeclaration.getRequestState() == 9) {
				
			} else {
				resultDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			// Thieu truong ArrivalDate vs RemainningTime
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void createResultDeclarationWithShipSecurity(Header header, Object object) {
		log.debug("==createResultDeclarationWithShipSecurity==Header==" + header);
		
		ResultDeclaration declaration = null;
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, header.getSubject().getReference(), header.getSubject().getDocumentYear());
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			declaration = listResultDeclarations.get(0);
		}
		
		DmGtBusinessType dmGTBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN);
		if (Validator.isNull(dmGTBusinessType)) {
			dmGTBusinessType = new DmGtBusinessType();
		}
		if (declaration == null) {
			declaration = new ResultDeclaration();
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setDocumentName(header.getSubject().getReference());
			declaration.setDocumentYear(header.getSubject().getDocumentYear());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN);
			declaration.setLatestSend(1);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(((ShipSecurityNotification) object).getETA()));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(((ShipSecurityNotification) object).getETA())));
			declaration.setBusinessOrder(dmGTBusinessType.getBusinessTypeOrder());
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			try {
				ResultDeclarationLocalServiceUtil.addResultDeclaration(declaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		} else {
			declaration.setRequestCode(header.getReference().getMessageId());
			declaration.setBusinessTypeCode(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN);
			declaration.setDeclarationTime(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			declaration.setArrivalDepartureTime(FormatData.parseStringToDate(((ShipSecurityNotification) object).getETA()));
			declaration.setRemainingTime(ConvertUtil.CheckRemainingTime(FormatData.parseStringToDate(header.getSubject().getSendDate()),
					FormatData.parseStringToDate(((ShipSecurityNotification) object).getETA())));
			declaration.setRemarks(Validator.isNotNull(dmGTBusinessType.getRemarks()) ? dmGTBusinessType.getRemarks() : remark);
			declaration.setLatestSend(declaration.getLatestSend() + 1);
			declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
if(declaration.getRequestState() == 9) {
				
			} else {
				declaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			}
			try {
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(declaration);
			} catch (Exception e) {
				
				e.printStackTrace();
			log.error(e.getMessage());
			}
		}
	}
	
	private static void checkShipDateFormXacBaoThongBaoNCvaQC(Header header, Object object) throws Exception {
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header
				.getSubject().getDocumentYear());
		log.info("==checkXacBaoThongBaoNhapCanhQuaCanhShipDateForm==" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkXacBaoThongBaoNhapCanhQuaCanhShipDateForm==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
			if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) ||
				document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
				
				if (header != null && object != null) {
					// 1. LDD; 2. XB; 3. TB; 4. BKAN, 5. BKC
					if (object instanceof InlandNoticeOfArrival) {
						log.info("==object instanceof NoticeOfArrival==");
						try {
							if (object != null) {
								InlandNoticeOfArrival noticeOfArrival = (InlandNoticeOfArrival) object;
								if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null
										&& noticeOfArrival.getDocumentName().length() > 0) {
									// 2. XB
									if ((header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG)
											|| (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH)) {
										log.info("==object instanceof NoticeOfArrival==XAC_BAO_TAU_DEN_CANG");
										// truong hop gui XB
										// Ton tai lenh dieu dong cuoi cung o trang thai PageType.DUYET_PHE_CHUAN
										IssueShiftingOrder shiftingOrder = IssueShiftingOrderLocalServiceUtil
												.getVersionNoMaxByDocumentYearAndDocumentYear(
														FormatData.convertToLong(noticeOfArrival.getDocumentName()),
														FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
										// List<IssueShiftingOrder> lstissueShiftingOrder =
										// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(noticeOfArrival.getDocumentName()),
										// FormatData.convertToInt(noticeOfArrival.getDocumentYear()), "VersionNo", true);
										// if (Validator.isNotNull(lstissueShiftingOrder) && lstissueShiftingOrder.size() > 0 &&
										// (Validator.isNotNull(issueShiftingOrder != null) && issueShiftingOrder.getIsApproval() ==
										// PageType.DUYET_PHE_CHUAN)) {
										if (Validator.isNotNull(shiftingOrder) && shiftingOrder.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
											log.info("==NHAP_CANH QUA CANH==XAC BAO noticeOfArrival==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
										} else {
											log.info("==NHAP_CANH QUA CANH==Check xac bao==lenh dieu dong co the chua co, hoac ko o trang thai DUYET_PHE_CHUAN");
											// TempNoticeShipMessage xacBao =
											// TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(FormatData.convertToLong(noticeOfArrival.getDocumentName()),
											// FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
											// if (xacBao != null) {
											if (document != null) {
												try {
													document.setShipDateFrom(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
											// }
										}
										// }
										
										// 3. TB
									} else if ((header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG)
											|| (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH)) {
										
										log.info("==object instanceof NoticeOfArrival==Type==" + header.getSubject().getType());
										
										IssueShiftingOrder shiftingOrder = IssueShiftingOrderLocalServiceUtil
												.getVersionNoMaxByDocumentYearAndDocumentYear(
														FormatData.convertToLong(noticeOfArrival.getDocumentName()),
														FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
										// List<IssueShiftingOrder> lstissueShiftingOrder =
										// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(noticeOfArrival.getDocumentName()),
										// FormatData.convertToInt(noticeOfArrival.getDocumentYear()), "VersionNo", true);
										// if (Validator.isNotNull(lstissueShiftingOrder) && lstissueShiftingOrder.size() > 0 &&
										// (Validator.isNotNull(issueShiftingOrder != null) && issueShiftingOrder.getIsApproval() ==
										// PageType.DUYET_PHE_CHUAN)) {
										if (Validator.isNotNull(shiftingOrder) && shiftingOrder.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
											log.info("==NHAP_CANH QUA CANH==THONG BAO noticeOfArrival==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
										} else {
											log.info("==NHAP_CANH QUA CANH==THONB BAO = Check thu tu uu tien Xac bao==lenh dieu dong co the chua co, hoac ko o trang thai DUYET_PHE_CHUAN");
											TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil
													.getXacBaoLastByDocumentNameAndDocumentYear(
															FormatData.convertToLong(noticeOfArrival.getDocumentName()),
															FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
											if (xacBao != null) {
												if (document != null) {
													try {
														document.setShipDateFrom(xacBao.getArrivalDate());
														TempDocumentLocalServiceUtil.updateTempDocument(document);
													} catch (Exception e) {
														e.printStackTrace();
			log.error(e.getMessage());
													}
												}
											} else {
												log.info("==NHAP_CANH QUA CANH==THONB BAO==check Thong bao==");
												TempNoticeShipMessage thongBao = TempNoTiceShipMessageLocalServiceUtil
														.getThongBaoLastByDocumentNameAndDocumentYear(
																FormatData.convertToLong(noticeOfArrival.getDocumentName()),
																FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
												if (thongBao != null) {
													if (document != null) {
														try {
															document.setShipDateFrom(thongBao.getArrivalDate());
															TempDocumentLocalServiceUtil.updateTempDocument(document);
														} catch (Exception e) {
															e.printStackTrace();
			log.error(e.getMessage());
														}
													}
												}
											}
										}
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
										// ko lam
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
				}
			}
		}
		
	}
	
	//inland
	
	private static void checkShipDateFormXacBaoThongBaoNCvaQCInland(Header header, Object object) throws Exception {
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header
				.getSubject().getDocumentYear());
		log.info("==checkXacBaoThongBaoNhapCanhQuaCanhShipDateForm==" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkXacBaoThongBaoNhapCanhQuaCanhShipDateForm==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
			if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) ||
				document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
				
				if (header != null && object != null) {
					// 1. LDD; 2. XB; 3. TB; 4. BKAN, 5. BKC
					if (object instanceof InlandNoticeOfArrival) {
						log.info("==object instanceof NoticeOfArrival==");
						try {
							if (object != null) {
								InlandNoticeOfArrival noticeOfArrival = (InlandNoticeOfArrival) object;
								if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null
										&& noticeOfArrival.getDocumentName().length() > 0) {
									// 2. XB
									if ((header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG)
											|| (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH)) {
										log.info("==object instanceof NoticeOfArrival==XAC_BAO_TAU_DEN_CANG");
										// truong hop gui XB
										// Ton tai lenh dieu dong cuoi cung o trang thai PageType.DUYET_PHE_CHUAN
										IssueShiftingOrder shiftingOrder = IssueShiftingOrderLocalServiceUtil
												.getVersionNoMaxByDocumentYearAndDocumentYear(
														FormatData.convertToLong(noticeOfArrival.getDocumentName()),
														FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
										// List<IssueShiftingOrder> lstissueShiftingOrder =
										// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(noticeOfArrival.getDocumentName()),
										// FormatData.convertToInt(noticeOfArrival.getDocumentYear()), "VersionNo", true);
										// if (Validator.isNotNull(lstissueShiftingOrder) && lstissueShiftingOrder.size() > 0 &&
										// (Validator.isNotNull(issueShiftingOrder != null) && issueShiftingOrder.getIsApproval() ==
										// PageType.DUYET_PHE_CHUAN)) {
										if (Validator.isNotNull(shiftingOrder) && shiftingOrder.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
											log.info("==NHAP_CANH QUA CANH==XAC BAO noticeOfArrival==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
										} else {
											log.info("==NHAP_CANH QUA CANH==Check xac bao==lenh dieu dong co the chua co, hoac ko o trang thai DUYET_PHE_CHUAN");
											// TempNoticeShipMessage xacBao =
											// TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(FormatData.convertToLong(noticeOfArrival.getDocumentName()),
											// FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
											// if (xacBao != null) {
											if (document != null) {
												try {
													document.setShipDateFrom(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
											// }
										}
										// }
										
										// 3. TB
									} else if ((header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG)
											|| (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH)) {
										
										log.info("==object instanceof NoticeOfArrival==Type==" + header.getSubject().getType());
										
										IssueShiftingOrder shiftingOrder = IssueShiftingOrderLocalServiceUtil
												.getVersionNoMaxByDocumentYearAndDocumentYear(
														FormatData.convertToLong(noticeOfArrival.getDocumentName()),
														FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
										// List<IssueShiftingOrder> lstissueShiftingOrder =
										// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(noticeOfArrival.getDocumentName()),
										// FormatData.convertToInt(noticeOfArrival.getDocumentYear()), "VersionNo", true);
										// if (Validator.isNotNull(lstissueShiftingOrder) && lstissueShiftingOrder.size() > 0 &&
										// (Validator.isNotNull(issueShiftingOrder != null) && issueShiftingOrder.getIsApproval() ==
										// PageType.DUYET_PHE_CHUAN)) {
										if (Validator.isNotNull(shiftingOrder) && shiftingOrder.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
											log.info("==NHAP_CANH QUA CANH==THONG BAO noticeOfArrival==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
										} else {
											log.info("==NHAP_CANH QUA CANH==THONB BAO = Check thu tu uu tien Xac bao==lenh dieu dong co the chua co, hoac ko o trang thai DUYET_PHE_CHUAN");
											TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil
													.getXacBaoLastByDocumentNameAndDocumentYear(
															FormatData.convertToLong(noticeOfArrival.getDocumentName()),
															FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
											if (xacBao != null) {
												if (document != null) {
													try {
														document.setShipDateFrom(xacBao.getArrivalDate());
														TempDocumentLocalServiceUtil.updateTempDocument(document);
													} catch (Exception e) {
														e.printStackTrace();
			log.error(e.getMessage());
													}
												}
											} else {
												log.info("==NHAP_CANH QUA CANH==THONB BAO==check Thong bao==");
												TempNoticeShipMessage thongBao = TempNoTiceShipMessageLocalServiceUtil
														.getThongBaoLastByDocumentNameAndDocumentYear(
																FormatData.convertToLong(noticeOfArrival.getDocumentName()),
																FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
												if (thongBao != null) {
													if (document != null) {
														try {
															document.setShipDateFrom(thongBao.getArrivalDate());
															TempDocumentLocalServiceUtil.updateTempDocument(document);
														} catch (Exception e) {
															e.printStackTrace();
			log.error(e.getMessage());
														}
													}
												}
											}
										}
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
										// ko lam
									}
								}
							}
						} catch (Exception e) {
							
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
				}
			}
		}
		
	}
	//
	private static void checkShipDateFormShipSecurityNotificationNCvaQC(Header header, Object object) throws Exception {
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header
				.getSubject().getDocumentYear());
		log.info("==checkShipSecurityMessageNhapCanhQuaCanhShipDateForm==" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkShipSecurityMessageNhapCanhQuaCanhShipDateForm==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type=="
					+ header.getSubject().getType());
			if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) || document.getDocumentTypeCode()
					.equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
				if (header != null && object != null) {
					// 1. LDD; 2. XB; 3. TB; 4. BKAN, 5. BKC
					// --------------Truong hop thay doi ShipDateFrom gui ban khai an ninh 4. BKAN
					if (object instanceof ShipSecurityNotification) {
						log.info("==NHAP_CANH QUA CANH==GUI BAN KHAI AN NINH ==ShipSecurityNotification==lam thay doi thoi gian den cua tau==");
						try {
							ShipSecurityNotification shipSecurity = (ShipSecurityNotification) object;
							if (shipSecurity != null && shipSecurity.getDocumentName() != null && shipSecurity.getDocumentName().length() > 0) {
								log.info("==NHAP_CANH QUA CANH==ShipSecurityNotification==DocumentName==" + shipSecurity.getDocumentName()
										+ "==DocumentYear==" + shipSecurity.getDocumentYear());
								
								// Ton tai lenh dieu dong cuoi cung o trang thai PageType.DUYET_PHE_CHUAN
								IssueShiftingOrder shiftingOrder = IssueShiftingOrderLocalServiceUtil.getVersionNoMaxByDocumentYearAndDocumentYear(
										FormatData.convertToLong(shipSecurity.getDocumentName()),
										FormatData.convertToInt(shipSecurity.getDocumentYear()));
								// List<IssueShiftingOrder> lstIssueShiftingOrder =
								// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(shipSecurity.getDocumentName()),
								// FormatData.convertToInt(shipSecurity.getDocumentYear()), "VersionNo", true);
								
								/**
								 * Check co ban khai chung ko Th1: BKAN gá»­i trÆ°á»›c khi cáº¥p LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3.
								 * TB; 4. BKAN; 5. BKC
								 */
								// if (Validator.isNotNull(lstIssueShiftingOrder) && lstIssueShiftingOrder.size() > 0) {
								if (Validator.isNotNull(shiftingOrder) && shiftingOrder.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
									log.info("==NHAP_CANH QUA CANH==ShipSecurityNotification==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
								} else {
									log.info("==NHAP_CANH QUA CANH==ShipSecurityNotification==check XAC BAO==lenh dieu dong co the chua co, hoac ko o trang thai DUYET_PHE_CHUAN");
									TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(
											FormatData.convertToLong(shipSecurity.getDocumentName()),
											FormatData.convertToInt(shipSecurity.getDocumentYear()));
									if (xacBao != null) {
										if (document != null) {
											try {
												document.setShipDateFrom(xacBao.getArrivalDate());
												TempDocumentLocalServiceUtil.updateTempDocument(document);
											} catch (Exception e) {
												e.printStackTrace();
			log.error(e.getMessage());
											}
										}
									} else {
										log.info("==NHAP_CANH QUA CANH==ShipSecurityNotification==check Thong bao==");
										TempNoticeShipMessage thongBao = TempNoTiceShipMessageLocalServiceUtil
												.getThongBaoLastByDocumentNameAndDocumentYear(
														FormatData.convertToLong(shipSecurity.getDocumentName()),
														FormatData.convertToInt(shipSecurity.getDocumentYear()));
										if (thongBao != null) {
											if (document != null) {
												try {
													document.setShipDateFrom(thongBao.getArrivalDate());
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
										} else {
											log.info("==NHAP_CANH QUA CANH==ShipSecurityNotification==check Ban Khai An Ninh==");
											if (document != null) {
												try {
													document.setShipDateFrom(FormatData.parseStringToDate(shipSecurity.getETA()));
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
										}
									}
								}
								// } else {
								// }
							}
						} catch (Exception e) {
							
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
				}
			}
		}
	}
	
	/** ham check cap nhap thoi gian den cang cua tau, */
	private static void checkShipDateFormGeneralDeclarationNCvaQC(Header header, Object object) throws Exception {
		
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header
				.getSubject().getDocumentYear());
		
		log.info("==checkGeneralDeclarationNHapCanhQuaCanhShipDateForm==" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkGeneralDeclarationNHapCanhQuaCanhShipDateForm==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type=="
					+ header.getSubject().getType());
			// ----------Truong hop nhap canh, va xuat canh
			if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) || document.getDocumentTypeCode()
					.equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
				if (header != null && object != null) {
					
					// --------------Thu tu uu tien------------- 5.BKC
					log.info("==NHAP_CANH QUA CANH==GUI BAN KHAI CHUNG ==GeneralDeclaration==lam thay doi thoi gian den cua tau==");
					GeneralDeclaration generalDeclaration = (GeneralDeclaration) object;
					if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
							&& generalDeclaration.getDocumentName().length() > 0) {
						log.info("==NHAP_CANH QUA CANH==GeneralDeclaration==DocumentName==" + generalDeclaration.getDocumentName()
								+ "==DocumentYear==" + generalDeclaration.getDocumentYear());
						
						// Ton tai lenh dieu dong cuoi cung o trang thai PageType.DUYET_PHE_CHUAN
						IssueShiftingOrder shiftingVersionMax = IssueShiftingOrderLocalServiceUtil.getVersionNoMaxByDocumentYearAndDocumentYear(
								FormatData.convertToLong(generalDeclaration.getDocumentName()),
								FormatData.convertToInt(generalDeclaration.getDocumentYear()));
						// List<IssueShiftingOrder> lstShifting =
						// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(generalDeclaration.getDocumentName()),FormatData.convertToInt(generalDeclaration.getDocumentYear()),
						// "VersionNo", true);
						
						/**
						 * Check co ban khai chung ko Th1: BKC gá»­i trÆ°á»›c khi cáº¥p LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3. TB; 4.
						 * BKAN; 5. BKC
						 */
						
						// if (Validator.isNotNull(lstShifting) && lstShifting.size() > 0) {
						if (Validator.isNotNull(shiftingVersionMax) && shiftingVersionMax.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
							log.info("==NHAP_CANH QUA CANH==GeneralDeclaration==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
							
							/**
							 * Th2: BKC gá»­i sau LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. BKC; 2. LDD; 3. XB; 4. TB; 5. BKAN.
							 */
							log.info("==NHAP_CANH QUA CANH==Th2: BKC gá»­i sau LDD lam thay Doi ShipDateFrom==TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. BKC==");
							log.info("==NHAP_CANH QUA CANH==Th2: BKC==Document===" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
							if (document != null) {
								try {
									log.info("==GeneralDeclaration==DateOfArrival==" + generalDeclaration.getDateOfArrival());
									document.setShipDateFrom(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
									try {
										TempDocument updateTempDocument = TempDocumentLocalServiceUtil.updateTempDocument(document);
										log.info("==GeneralDeclaration==ShipDateFrom==" + updateTempDocument.getShipDateFrom());
									} catch (Exception e) {
										e.printStackTrace();
			log.error(e.getMessage());
									}
								} catch (Exception e) {
									e.printStackTrace();
			log.error(e.getMessage());
								}
							}
						} else {
							log.info("==NHAP_CANH QUA CANH==check xac bao==");
							TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(
									FormatData.convertToLong(generalDeclaration.getDocumentName()),
									FormatData.convertToInt(generalDeclaration.getDocumentYear()));
							if (xacBao != null) {
								if (document != null) {
									try {
										document.setShipDateFrom(xacBao.getArrivalDate());
										TempDocumentLocalServiceUtil.updateTempDocument(document);
									} catch (Exception e) {
										e.printStackTrace();
			log.error(e.getMessage());
									}
								}
							} else {
								log.info("==NHAP_CANH QUA CANH==check Thong bao==");
								TempNoticeShipMessage thongBao = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(
										FormatData.convertToLong(generalDeclaration.getDocumentName()),
										FormatData.convertToInt(generalDeclaration.getDocumentYear()));
								if (thongBao != null) {
									if (document != null) {
										try {
											document.setShipDateFrom(thongBao.getArrivalDate());
											TempDocumentLocalServiceUtil.updateTempDocument(document);
										} catch (Exception e) {
											e.printStackTrace();
			log.error(e.getMessage());
										}
									}
								} else {
									log.info("==NHAP_CANH QUA CANH==check Ban Khai An Ninh==");
									TempShipSecurityMessage shipSecurityMessage = TempShipSecurityMessageLocalServiceUtil
											.getLastByDocumentNameAndDocumentYear(FormatData.convertToLong(generalDeclaration.getDocumentName()),
													FormatData.convertToInt(generalDeclaration.getDocumentYear()));
									if (shipSecurityMessage != null) {
										if (document != null) {
											try {
												document.setShipDateFrom(shipSecurityMessage.getArrivalDate());
												TempDocumentLocalServiceUtil.updateTempDocument(document);
											} catch (Exception e) {
												e.printStackTrace();
			log.error(e.getMessage());
											}
										}
									} else {
										// cac ban khai 1. LDD; 2. XB; 3. TB; 4. BKAN; updae ShipDateFrom tu 5. BKC
										if (document != null) {
											try {
												document.setShipDateFrom(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
												TempDocumentLocalServiceUtil.updateTempDocument(document);
											} catch (Exception e) {
												e.printStackTrace();
			log.error(e.getMessage());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//
private static void checkShipDateFormGeneralDeclarationNCvaQCInland(Header header, Object object) throws Exception {
		
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header
				.getSubject().getDocumentYear());
		
		log.info("==checkGeneralDeclarationNHapCanhQuaCanhShipDateForm==" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkGeneralDeclarationNHapCanhQuaCanhShipDateForm==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type=="
					+ header.getSubject().getType());
			// ----------Truong hop nhap canh, va xuat canh
			if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) || document.getDocumentTypeCode()
					.equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
				if (header != null && object != null) {
					
					// --------------Thu tu uu tien------------- 5.BKC
					log.info("==NHAP_CANH QUA CANH==GUI BAN KHAI CHUNG ==GeneralDeclaration==lam thay doi thoi gian den cua tau==");
					InlandGeneralDeclaration generalDeclaration = (InlandGeneralDeclaration) object;
					if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
							&& generalDeclaration.getDocumentName().length() > 0) {
						log.info("==NHAP_CANH QUA CANH==GeneralDeclaration==DocumentName==" + generalDeclaration.getDocumentName()
								+ "==DocumentYear==" + generalDeclaration.getDocumentYear());
						
						// Ton tai lenh dieu dong cuoi cung o trang thai PageType.DUYET_PHE_CHUAN
						IssueShiftingOrder shiftingVersionMax = IssueShiftingOrderLocalServiceUtil.getVersionNoMaxByDocumentYearAndDocumentYear(
								FormatData.convertToLong(generalDeclaration.getDocumentName()),
								FormatData.convertToInt(generalDeclaration.getDocumentYear()));
						// List<IssueShiftingOrder> lstShifting =
						// IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(FormatData.convertToLong(generalDeclaration.getDocumentName()),FormatData.convertToInt(generalDeclaration.getDocumentYear()),
						// "VersionNo", true);
						
						/**
						 * Check co ban khai chung ko Th1: BKC gá»­i trÆ°á»›c khi cáº¥p LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3. TB; 4.
						 * BKAN; 5. BKC
						 */
						
						// if (Validator.isNotNull(lstShifting) && lstShifting.size() > 0) {
						if (Validator.isNotNull(shiftingVersionMax) && shiftingVersionMax.getIsApproval() == PageType.DUYET_PHE_CHUAN) {
							log.info("==NHAP_CANH QUA CANH==GeneralDeclaration==luc nay LDD da cap nhap thoi gian den o ben ke hoach, khong lam j ca?");
							
							/**
							 * Th2: BKC gá»­i sau LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. BKC; 2. LDD; 3. XB; 4. TB; 5. BKAN.
							 */
							log.info("==NHAP_CANH QUA CANH==Th2: BKC gá»­i sau LDD lam thay Doi ShipDateFrom==TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. BKC==");
							log.info("==NHAP_CANH QUA CANH==Th2: BKC==Document===" + (Validator.isNotNull(document) ? " != NULL" : " NULL"));
							if (document != null) {
								try {
									log.info("==GeneralDeclaration==DateOfArrival==" + generalDeclaration.getDateOfArrival());
									document.setShipDateFrom(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
									try {
										TempDocument updateTempDocument = TempDocumentLocalServiceUtil.updateTempDocument(document);
										log.info("==GeneralDeclaration==ShipDateFrom==" + updateTempDocument.getShipDateFrom());
									} catch (Exception e) {
										e.printStackTrace();
			log.error(e.getMessage());
									}
								} catch (Exception e) {
									e.printStackTrace();
			log.error(e.getMessage());
								}
							}
						} else {
							log.info("==NHAP_CANH QUA CANH==check xac bao==");
							TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(
									FormatData.convertToLong(generalDeclaration.getDocumentName()),
									FormatData.convertToInt(generalDeclaration.getDocumentYear()));
							if (xacBao != null) {
								if (document != null) {
									try {
										document.setShipDateFrom(xacBao.getArrivalDate());
										TempDocumentLocalServiceUtil.updateTempDocument(document);
									} catch (Exception e) {
										e.printStackTrace();
			log.error(e.getMessage());
									}
								}
							} else {
								log.info("==NHAP_CANH QUA CANH==check Thong bao==");
								TempNoticeShipMessage thongBao = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(
										FormatData.convertToLong(generalDeclaration.getDocumentName()),
										FormatData.convertToInt(generalDeclaration.getDocumentYear()));
								if (thongBao != null) {
									if (document != null) {
										try {
											document.setShipDateFrom(thongBao.getArrivalDate());
											TempDocumentLocalServiceUtil.updateTempDocument(document);
										} catch (Exception e) {
											e.printStackTrace();
			log.error(e.getMessage());
										}
									}
								} else {
									log.info("==NHAP_CANH QUA CANH==check Ban Khai An Ninh==");
									TempShipSecurityMessage shipSecurityMessage = TempShipSecurityMessageLocalServiceUtil
											.getLastByDocumentNameAndDocumentYear(FormatData.convertToLong(generalDeclaration.getDocumentName()),
													FormatData.convertToInt(generalDeclaration.getDocumentYear()));
									if (shipSecurityMessage != null) {
										if (document != null) {
											try {
												document.setShipDateFrom(shipSecurityMessage.getArrivalDate());
												TempDocumentLocalServiceUtil.updateTempDocument(document);
											} catch (Exception e) {
												e.printStackTrace();
			log.error(e.getMessage());
											}
										}
									} else {
										// cac ban khai 1. LDD; 2. XB; 3. TB; 4. BKAN; updae ShipDateFrom tu 5. BKC
										if (document != null) {
											try {
												document.setShipDateFrom(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
												TempDocumentLocalServiceUtil.updateTempDocument(document);
											} catch (Exception e) {
												e.printStackTrace();
			log.error(e.getMessage());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	//
	
	
	private static void checkShipDateToXuatCanh(Header header, Object object) {
		
		// ------------loat thuc tuc xuat canh
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header.getSubject().getDocumentYear());
		log.info("==checkXuaCanhShipDateTo==document==" + (Validator.isNull(document) ? "  NULL  " : "  != NULL  "));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkXuaCanhShipDateTo==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
			
			if (document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
				if (header != null && object != null) {
					/**
					 * Thá»�i gian rá»�i (XC): BKC gá»­i sau TB: Æ¯u tiÃªn 1. BKC, 2. TB
					 */
					if (object instanceof GeneralDeclaration) {
						log.info("==checkXuaCanhShipDateTo==GeneralDeclaration==");
						try {
							if (object != null) {
								GeneralDeclaration generalDeclaration = (GeneralDeclaration) object;
								if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
										&& generalDeclaration.getDocumentName().length() > 0) {
									//
									log.info("==XUAT CANH==Thá»�i gian rá»�i (XC): *  BKC gá»­i sau TB: Æ¯u tiÃªn 1. BKC,==GeneralDeclaration==DocumentName=="
											+ generalDeclaration.getDocumentName() + "==DocumentYear==" + generalDeclaration.getDocumentYear());
									if (document != null) {
										try {
											log.info("==XUAT CANH==DateOfArrival==" + generalDeclaration.getDateOfArrival());
											document.setShipDateTo(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
											TempDocument updateTempDocument = TempDocumentLocalServiceUtil.updateTempDocument(document);
											log.info("==XUAT CANH==ShipDateTo==" + updateTempDocument.getShipDateTo());
											log.info("");
										} catch (Exception e) {
											e.printStackTrace();
			log.error(e.getMessage());
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
					
					/**
					 * Thá»�i gian rá»�i (XC): - BKC gá»­i trÆ°á»›c TB: Cáº­p nháº­t theo 1.TB; 2. BKC
					 */
					if (object instanceof NoticeOfArrival) {
						log.info("==checkXuaCanhShipDateTo==NoticeOfArrival==Subject==Type==");
						try {
							if (object != null) {
								NoticeOfArrival noticeOfArrival = (NoticeOfArrival) object;
								log.info("==XUAT CANH==GUI thong bao den sau thi thoi gian rá»�i (XC) lay nguoc tu ban khai chung update nguoc lai ");
								log.info("  BKC gá»­i trÆ°á»›c TB: Cáº­p nháº­t theo 1.TB,==NoticeOfArrival==DocumentName=="
										+ noticeOfArrival.getDocumentName() + "==DocumentYear==" + noticeOfArrival.getDocumentYear());
								log.info("==Subject==Type==" + header.getSubject().getType());
								
								if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null
										&& noticeOfArrival.getDocumentName().length() > 0 && noticeOfArrival.getDocumentYear() != null
										&& noticeOfArrival.getDocumentYear().length() > 0) {
									if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
										// Lam truong hop nay
										TempGeneralDeclaration general = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(
												FormatData.convertToLong(noticeOfArrival.getDocumentName()),
												FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
										if (Validator.isNotNull(general)) {
											if (document != null) {
												try {
													document.setShipDateTo((general.getDateOfArrival()));
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
										}
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
										/*
										 * if (document != null) { try {
										 * document.setShipDateTo(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
										 * TempDocumentLocalServiceUtil.updateTempDocument(document); } catch (Exception e) { e.printStackTrace();
			log.error(e.getMessage()); } }
										 */
									}
									// else { object.setNoticeShipType(String.valueOf(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG))); }
									
								}
							}
						} catch (Exception e) {
							
							e.printStackTrace();
			log.error(e.getMessage());
						}
					} else if (object instanceof ConfirmationOfArrival) {
						log.info("==checkXuaCanhShipDateTo==ConfirmationOfArrival==Subject==Type==");
						try {
							if (object != null) {
								ConfirmationOfArrival confirmationOfArrival = (ConfirmationOfArrival) object;
								log.info("==XUAT CANH==GUI thong bao den sau thi thoi gian rá»�i (XC) lay nguoc tu ban khai chung update nguoc lai ");
								log.info("  BKC gá»­i trÆ°á»›c TB: Cáº­p nháº­t theo 1.TB,==NoticeOfArrival==DocumentName=="
										+ confirmationOfArrival.getDocumentName() + "==DocumentYear==" + confirmationOfArrival.getDocumentYear());
								log.info("==Subject==Type==" + header.getSubject().getType());
								
								if (confirmationOfArrival != null && confirmationOfArrival.getDocumentName() != null
										&& confirmationOfArrival.getDocumentName().length() > 0 && confirmationOfArrival.getDocumentYear() != null
										&& confirmationOfArrival.getDocumentYear().length() > 0) {
									if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
										// Lam truong hop nay
										TempGeneralDeclaration general = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(
												FormatData.convertToLong(confirmationOfArrival.getDocumentName()),
												FormatData.convertToInt(confirmationOfArrival.getDocumentYear()));
										if (Validator.isNotNull(general)) {
											if (document != null) {
												try {
													document.setShipDateTo((general.getDateOfArrival()));
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
										}
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
										/*
										 * if (document != null) { try {
										 * document.setShipDateTo(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
										 * TempDocumentLocalServiceUtil.updateTempDocument(document); } catch (Exception e) { e.printStackTrace();
			log.error(e.getMessage()); } }
										 */
									}
									// else { object.setNoticeShipType(String.valueOf(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG))); }
									
								}
							}
						} catch (Exception e) {
							
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
				}
			}
		}
	}
	
private static void checkShipDateToXuatCanhInland(Header header, Object object) {
		
		// ------------loat thuc tuc xuat canh
		TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getReference(), header.getSubject().getDocumentYear());
		log.info("==checkXuaCanhShipDateTo==document==" + (Validator.isNull(document) ? "  NULL  " : "  != NULL  "));
		
		if (Validator.isNotNull(document)) {
			log.info("==checkXuaCanhShipDateTo==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
			
			if (document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
				if (header != null && object != null) {
					/**
					 * Thá»�i gian rá»�i (XC): BKC gá»­i sau TB: Æ¯u tiÃªn 1. BKC, 2. TB
					 */
					if (object instanceof InlandGeneralDeclaration) {
						log.info("==checkXuaCanhShipDateTo==GeneralDeclaration==");
						try {
							if (object != null) {
								InlandGeneralDeclaration generalDeclaration = (InlandGeneralDeclaration) object;
								if (generalDeclaration != null && generalDeclaration.getDocumentName() != null
										&& generalDeclaration.getDocumentName().length() > 0) {
									//
									log.info("==XUAT CANH==Thá»�i gian rá»�i (XC): *  BKC gá»­i sau TB: Æ¯u tiÃªn 1. BKC,==GeneralDeclaration==DocumentName=="
											+ generalDeclaration.getDocumentName() + "==DocumentYear==" + generalDeclaration.getDocumentYear());
									if (document != null) {
										try {
											log.info("==XUAT CANH==DateOfArrival==" + generalDeclaration.getDateOfArrival());
											document.setShipDateTo(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
											TempDocument updateTempDocument = TempDocumentLocalServiceUtil.updateTempDocument(document);
											log.info("==XUAT CANH==ShipDateTo==" + updateTempDocument.getShipDateTo());
											log.info("");
										} catch (Exception e) {
											e.printStackTrace();
			log.error(e.getMessage());
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
					
					/**
					 * Thá»�i gian rá»�i (XC): - BKC gá»­i trÆ°á»›c TB: Cáº­p nháº­t theo 1.TB; 2. BKC
					 */
					if (object instanceof NoticeOfArrival) {
						log.info("==checkXuaCanhShipDateTo==NoticeOfArrival==Subject==Type==");
						try {
							if (object != null) {
								NoticeOfArrival noticeOfArrival = (NoticeOfArrival) object;
								log.info("==XUAT CANH==GUI thong bao den sau thi thoi gian rá»�i (XC) lay nguoc tu ban khai chung update nguoc lai ");
								log.info("  BKC gá»­i trÆ°á»›c TB: Cáº­p nháº­t theo 1.TB,==NoticeOfArrival==DocumentName=="
										+ noticeOfArrival.getDocumentName() + "==DocumentYear==" + noticeOfArrival.getDocumentYear());
								log.info("==Subject==Type==" + header.getSubject().getType());
								
								if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null
										&& noticeOfArrival.getDocumentName().length() > 0 && noticeOfArrival.getDocumentYear() != null
										&& noticeOfArrival.getDocumentYear().length() > 0) {
									if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
										// Lam truong hop nay
										TempGeneralDeclaration general = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(
												FormatData.convertToLong(noticeOfArrival.getDocumentName()),
												FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
										if (Validator.isNotNull(general)) {
											if (document != null) {
												try {
													document.setShipDateTo((general.getDateOfArrival()));
													TempDocumentLocalServiceUtil.updateTempDocument(document);
												} catch (Exception e) {
													e.printStackTrace();
			log.error(e.getMessage());
												}
											}
										}
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) {
										
									} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
										/*
										 * if (document != null) { try {
										 * document.setShipDateTo(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
										 * TempDocumentLocalServiceUtil.updateTempDocument(document); } catch (Exception e) { e.printStackTrace();
			log.error(e.getMessage()); } }
										 */
									}
									// else { object.setNoticeShipType(String.valueOf(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG))); }
									
								}
							}
						} catch (Exception e) {
							
							e.printStackTrace();
			log.error(e.getMessage());
						}
					}
				}
			}
		}
	}
	
	private static boolean checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC(Header header, Object object) {
		boolean resultMethod = true;
		try {
			// ------------loat thuc tuc KE HOACH NHAP CANH, QUA CANH - 1. XB, 2. TB, 3. BKAN má»›i nháº¥t
			TempDocument document = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(header.getSubject().getReference(), header.getSubject().getDocumentYear());
			log.info("==checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC==document==" + (Validator.isNull(document) ? "  NULL  " : "  != NULL  "));
			if (Validator.isNotNull(document)) {
				log.info("==checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
				
				//check Ho so ben KE HOACH
				if (document.getRequestState() > 0 && (Validator.isNull(document.getDocumentStatusCode()) || document.getDocumentStatusCode() == 0)) {
					//check Nhap canh, Qua canh
					if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) ||
						document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
						
						if (header != null && object != null) {
							if (object instanceof NoticeOfArrival) {
								log.info("==object instanceof NoticeOfArrival==");
								try {
									if (object != null) {
										NoticeOfArrival noticeOfArrival = (NoticeOfArrival) object;
										if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null && noticeOfArrival.getDocumentName().length() > 0) {
											// 1. XB
											if ((header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) ||
												(header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH)) {
												log.info("==object instanceof NoticeOfArrival==TYPE==" + header.getSubject().getType() + "=XAC_BAO_TAU_DEN_CANG or XAC_BAO_TAU_QUA_CANH");
												
												if (document != null) {
													try {
														if (Validator.isNotNull(noticeOfArrival.getNameOfShip())) {
															document.setShipName(noticeOfArrival.getNameOfShip());
														}
														if (Validator.isNotNull(noticeOfArrival.getFlagStateOfShip())) {
															document.setStateCode(noticeOfArrival.getFlagStateOfShip());
														}
														
														if (Validator.isNotNull(noticeOfArrival.getCallSign())) {
															document.setCallSign(noticeOfArrival.getCallSign());
														}
														
														if (Validator.isNotNull(noticeOfArrival.getIMONumber())) {
															document.setImo(noticeOfArrival.getIMONumber());
														}
														TempDocumentLocalServiceUtil.updateTempDocument(document);
													} catch (Exception e) {
														
														e.printStackTrace();
			log.error(e.getMessage());
													}
												}
												
											// 2. TB
											} else if ((header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG) ||
														(header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH)) {
												
												log.info("==object instanceof NoticeOfArrival==Type==" + header.getSubject().getType() + "==THONG_BAO_TAU_DEN_CANG or THONG_BAO_TAU_QUA_CANH");
												TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(
														FormatData.convertToLong(noticeOfArrival.getDocumentName()), FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
												if (xacBao != null) {
													//khong lam j,
												} else {
													//Update Thong bao vao bang TempDocument
													if (document != null) {
														try {
															if (Validator.isNotNull(noticeOfArrival.getNameOfShip())) {
																document.setShipName(noticeOfArrival.getNameOfShip());
															}
															if (Validator.isNotNull(noticeOfArrival.getFlagStateOfShip())) {
																document.setStateCode(noticeOfArrival.getFlagStateOfShip());
															}
															
															if (Validator.isNotNull(noticeOfArrival.getCallSign())) {
																document.setCallSign(noticeOfArrival.getCallSign());
															}
															
															if (Validator.isNotNull(noticeOfArrival.getIMONumber())) {
																document.setImo(noticeOfArrival.getIMONumber());
															}
															TempDocumentLocalServiceUtil.updateTempDocument(document);
														} catch (Exception e) {
															
															e.printStackTrace();
			log.error(e.getMessage());
														}
													}
												}
												
											} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
												
											}
										}
									}
								} catch (Exception e) {
									
									e.printStackTrace();
			log.error(e.getMessage());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultMethod = false;
		}
		return resultMethod;
	}
	
	private static boolean checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQCInland(Header header, Object object) {
		boolean resultMethod = true;
		try {
			// ------------loat thuc tuc KE HOACH NHAP CANH, QUA CANH - 1. XB, 2. TB, 3. BKAN má»›i nháº¥t
			TempDocument document = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(header.getSubject().getReference(), header.getSubject().getDocumentYear());
			log.info("==checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC==document==" + (Validator.isNull(document) ? "  NULL  " : "  != NULL  "));
			if (Validator.isNotNull(document)) {
				log.info("==checkTempDocumentFromXacBaoThongBaoKeHoachNCvaQC==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
				
				//check Ho so ben KE HOACH
				if (document.getRequestState() > 0 && (Validator.isNull(document.getDocumentStatusCode()) || document.getDocumentStatusCode() == 0)) {
					//check Nhap canh, Qua canh
					if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) ||
						document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
						
						if (header != null && object != null) {
							if (object instanceof InlandNoticeOfArrival) {
								log.info("==object instanceof NoticeOfArrival==");
								try {
									if (object != null) {
										InlandNoticeOfArrival noticeOfArrival = (InlandNoticeOfArrival) object;
										if (noticeOfArrival != null && noticeOfArrival.getDocumentName() != null && noticeOfArrival.getDocumentName().length() > 0) {
											// 1. XB
											if ((header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) ||
												(header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH)) {
												log.info("==object instanceof NoticeOfArrival==TYPE==" + header.getSubject().getType() + "=XAC_BAO_TAU_DEN_CANG or XAC_BAO_TAU_QUA_CANH");
												
												if (document != null) {
													try {
														if (Validator.isNotNull(noticeOfArrival.getNameOfShip())) {
															document.setShipName(noticeOfArrival.getNameOfShip());
														}
//														if (Validator.isNotNull(noticeOfArrival.getFlagStateOfShip())) {
//															document.setStateCode(noticeOfArrival.getFlagStateOfShip());
//														}
														
														if (Validator.isNotNull(noticeOfArrival.getCallSign())) {
															document.setCallSign(noticeOfArrival.getCallSign());
														}
														
//														if (Validator.isNotNull(noticeOfArrival.getIMONumber())) {
//															document.setImo(noticeOfArrival.getIMONumber());
//														}
														TempDocumentLocalServiceUtil.updateTempDocument(document);
													} catch (Exception e) {
														e.printStackTrace();
			log.error(e.getMessage());
													}
												}
												
											// 2. TB
											} else if ((header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG) ||
														(header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH)) {
												
												log.info("==object instanceof NoticeOfArrival==Type==" + header.getSubject().getType() + "==THONG_BAO_TAU_DEN_CANG or THONG_BAO_TAU_QUA_CANH");
												TempNoticeShipMessage xacBao = TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(
														FormatData.convertToLong(noticeOfArrival.getDocumentName()), FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
												if (xacBao != null) {
													//khong lam j,
												} else {
													//Update Thong bao vao bang TempDocument
													if (document != null) {
														try {
															if (Validator.isNotNull(noticeOfArrival.getNameOfShip())) {
																document.setShipName(noticeOfArrival.getNameOfShip());
															}
//															if (Validator.isNotNull(noticeOfArrival.getFlagStateOfShip())) {
//																document.setStateCode(noticeOfArrival.getFlagStateOfShip());
//															}
															
															if (Validator.isNotNull(noticeOfArrival.getCallSign())) {
																document.setCallSign(noticeOfArrival.getCallSign());
															}
															
//															if (Validator.isNotNull(noticeOfArrival.getIMONumber())) {
//																document.setImo(noticeOfArrival.getIMONumber());
//															}
															TempDocumentLocalServiceUtil.updateTempDocument(document);
														} catch (Exception e) {
															e.printStackTrace();
			log.error(e.getMessage());
														}
													}
												}
												
											} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
												
											}
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
			log.error(e.getMessage());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			log.error(e.getMessage());
			resultMethod = false;
		}
		return resultMethod;
	}
	
	private static boolean checkTempDocumentFromShipSecurityNotificationKeHoachNCvaQC(Header header, Object object) {
		boolean resultMethod = true;
		try {
			// ------------loat thuc tuc KE HOACH NHAP CANH, QUA CANH - 1. XB, 2. TB, 3. BKAN má»›i nháº¥t
			TempDocument document = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(header.getSubject().getReference(), header.getSubject().getDocumentYear());
			log.info("==checkTempDocumentFromShipSecurityMessageKeHoachNCvaQC==document==" + (Validator.isNull(document) ? "  NULL  " : "  != NULL  "));
			if (Validator.isNotNull(document)) {
				log.info("==checkTempDocumentFromShipSecurityMessageKeHoachNCvaQC==DocumentTypeCode==" + document.getDocumentTypeCode() + "==Type==" + header.getSubject().getType());
				
				//check Ho so ben KE HOACH
				if (document.getRequestState() > 0 && (Validator.isNull(document.getDocumentStatusCode()) || document.getDocumentStatusCode() == 0 )) {
					//check Nhap canh, Qua canh
					if ((document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) ||
						document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH))) {
						if (header != null && object != null) {
							if (object instanceof ShipSecurityNotification) {
								
								log.info("==object instanceof NoticeOfArrival==");
								try {
									if (object != null) {
										ShipSecurityNotification shipSecurityNotification = (ShipSecurityNotification) object;
										if (shipSecurityNotification != null && shipSecurityNotification.getDocumentName() != null && shipSecurityNotification.getDocumentName().length() > 0) {
											log.info("==object instanceof NoticeOfArrival==Type==" + header.getSubject().getType() + "==THONG_BAO_TAU_DEN_CANG or THONG_BAO_TAU_QUA_CANH");
											
											TempNoticeShipMessage xacBaoLast = TempNoTiceShipMessageLocalServiceUtil.getXacBaoLastByDocumentNameAndDocumentYear(
													FormatData.convertToLong(shipSecurityNotification.getDocumentName()), FormatData.convertToInt(shipSecurityNotification.getDocumentYear()));
											if (xacBaoLast != null) {
												//khong lam j,
											} else {
												TempNoticeShipMessage thongBaoLast = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(
													FormatData.convertToLong(shipSecurityNotification.getDocumentName()), FormatData.convertToInt(shipSecurityNotification.getDocumentYear()));
												if (thongBaoLast != null) {
													//khong lam j
												} else {
													//Update Thong bao vao bang TempDocument
													if (document != null) {
														try {
															if (Validator.isNotNull(shipSecurityNotification.getNameOfShip())) {
																document.setShipName(shipSecurityNotification.getNameOfShip());
															}
															if (Validator.isNotNull(shipSecurityNotification.getFlagStateOfShip())) {
																document.setStateCode(shipSecurityNotification.getFlagStateOfShip());
															}
															
															if (Validator.isNotNull(shipSecurityNotification.getCallSign())) {
																document.setCallSign(shipSecurityNotification.getCallSign());
															}
															
															if (Validator.isNotNull(shipSecurityNotification.getIMONumber())) {
																document.setImo(shipSecurityNotification.getIMONumber());
															}
															TempDocumentLocalServiceUtil.updateTempDocument(document);
														} catch (Exception e) {
															e.printStackTrace();
			log.error(e.getMessage());
														}
													}
												}
											}
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
			log.error(e.getMessage());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultMethod = false;
		}
		return resultMethod;
	}
}
