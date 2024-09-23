//package vn.gt.tichhop.schedule;
//
////import com.liferay.portal.kernel.messaging.Message;
////import com.liferay.portal.kernel.messaging.MessageListener;
////import com.liferay.portal.kernel.messaging.MessageListenerException;
////import com.liferay.portal.kernel.util.PropsUtil;
//import com.fds.flex.common.ultility.Validator;
//
//import vn.gt.dao.asw.service.AswmsgMessageQueueLocalServiceUtil;
//
//public class SchedulerMessageQueue implements MessageListener {
//    @Override
//    public void receive(Message message) throws MessageListenerException {
//    	String receivers = PropsUtil.get("vn.gt.queue.reset.receiver.reset");
//    	if(Validator.isNotNull(receivers)) {
//    		AswmsgMessageQueueLocalServiceUtil.updateListMessageQueuePending(receivers);
//    	}
//    }
//}
//todo Message listener