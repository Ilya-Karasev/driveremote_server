package drivermonitor.services;
import drivermonitor.models.Request;
import java.util.List;
public interface RequestService {
    Request createRequest(Request request);
    void deleteRequest(Integer id);
    List<Request> getAllRequests();
    List<Request> getRequestsForReceiver(Integer receiverId);
    List<Request> getRequestsForSender(Integer senderId);
}