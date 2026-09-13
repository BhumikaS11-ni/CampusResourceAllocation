package campus.service;

import campus.model.ResourceRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityAllocationService {

    public List<ResourceRequest> prioritizeRequests(
            List<ResourceRequest> requests) {

        List<ResourceRequest> sortedRequests =
                new ArrayList<>(requests);

        sortedRequests.sort(
            Comparator.comparingInt(ResourceRequest::getPriority)
                      .reversed()
        );

        return sortedRequests;
    }

    public void displayPriorityOrder(List<ResourceRequest> requests) {

        List<ResourceRequest> sorted =
                prioritizeRequests(requests);

        System.out.println("\n--- Priority Allocation Order ---");

        for (ResourceRequest request : sorted) {
            System.out.println(
                "Request ID: " + request.getRequestId()
                + " | Priority: " + request.getPriority()
                + " | User: " + request.getUser().getName()
            );
        }
    }
}
