package campus.service;

import campus.model.ResourceRequest;
import java.util.ArrayList;
import java.util.List;

public class ResourceRequestService {

    private List<ResourceRequest> requests = new ArrayList<>();

    public void addRequest(ResourceRequest request) {
        requests.add(request);
        System.out.println("Resource request added successfully.");
    }

    public ResourceRequest findRequestById(int requestId) {
        for (ResourceRequest request : requests) {
            if (request.getRequestId() == requestId) {
                return request;
            }
        }
        return null;
    }

    public void displayAllRequests() {
        if (requests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }

        for (ResourceRequest request : requests) {
            request.displayRequest();
            System.out.println("-------------------------");
        }
    }

    public List<ResourceRequest> getRequests() {
        return requests;
    }
}
