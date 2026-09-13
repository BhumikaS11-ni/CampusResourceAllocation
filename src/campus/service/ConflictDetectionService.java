package campus.service;

import campus.model.RequestStatus;
import campus.model.ResourceRequest;
import java.util.List;

public class ConflictDetectionService {

    public ResourceRequest findConflictingRequest(
            ResourceRequest newRequest,
            List<ResourceRequest> existingRequests) {

        for (ResourceRequest existing : existingRequests) {

            if (existing.getRequestId() == newRequest.getRequestId()) {
                continue;
            }

            if (existing.getStatus() != RequestStatus.APPROVED) {
                continue;
            }

            if (existing.getResource().getResourceId()
                    != newRequest.getResource().getResourceId()) {
                continue;
            }

            if (!existing.getDate().equals(newRequest.getDate())) {
                continue;
            }

            boolean timeOverlap =
                    newRequest.getStartTime().isBefore(existing.getEndTime())
                    && newRequest.getEndTime().isAfter(existing.getStartTime());

            if (timeOverlap) {
                return existing;
            }
        }

        return null;
    }

    public boolean hasConflict(
            ResourceRequest newRequest,
            List<ResourceRequest> existingRequests) {

        return findConflictingRequest(
                newRequest, existingRequests) != null;
    }
}