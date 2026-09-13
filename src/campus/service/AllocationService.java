package campus.service;

import campus.model.Allocation;
import campus.model.RequestStatus;
import campus.model.ResourceRequest;
import java.util.ArrayList;
import java.util.List;

public class AllocationService {

    private List<Allocation> allocations = new ArrayList<>();
    private int nextAllocationId = 1;

    public synchronized Allocation allocate(ResourceRequest request) {

        Allocation allocation =
                new Allocation(nextAllocationId++, request);

        allocations.add(allocation);
        request.setStatus(RequestStatus.APPROVED);
        request.getResource().setAvailable(false);

        System.out.println("Request allocated successfully.");

        return allocation;
    }

    public void displayAllAllocations() {

        if (allocations.isEmpty()) {
            System.out.println("No allocations found.");
            return;
        }

        for (Allocation allocation : allocations) {
            allocation.displayAllocation();
            System.out.println("-------------------------");
        }
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }
}