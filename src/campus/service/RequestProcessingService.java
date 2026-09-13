package campus.service;

import campus.exception.ResourceUnavailableException;
import campus.model.Conflict;
import campus.model.RequestStatus;
import campus.model.ResourceRequest;
import campus.dao.ConflictDAO;
import campus.dao.AuditLogDAO;

import java.util.List;

public class RequestProcessingService {

    private ConflictDetectionService conflictService;
    private AllocationService allocationService;
    private ConflictDAO conflictDAO;
    private AuditLogDAO auditLogDAO;

    public RequestProcessingService(
            ConflictDetectionService conflictService,
            AllocationService allocationService) {

        this.conflictService = conflictService;
        this.allocationService = allocationService;
        this.conflictDAO = new ConflictDAO();
        this.auditLogDAO = new AuditLogDAO();
    }

    public void processRequest(
            ResourceRequest request,
            List<ResourceRequest> existingRequests) {

        try {
            validateRequest(request);

            ResourceRequest conflictingRequest =
                    conflictService.findConflictingRequest(
                            request, existingRequests);

            if (conflictingRequest != null) {

                request.setStatus(RequestStatus.REJECTED);

                Conflict conflict = new Conflict(
                        generateConflictId(),
                        request,
                        conflictingRequest,
                        "Resource and time overlap"
                );

                conflictDAO.saveConflict(conflict);

                auditLogDAO.saveLog(
                        "CONFLICT_DETECTED",
                        request.getRequestId(),
                        "Conflict detected with Request "
                                + conflictingRequest.getRequestId()
                );

                System.out.println(
                        "Request " + request.getRequestId()
                                + " rejected: Resource conflict detected.");

                System.out.println(
                        "Conflict recorded between Request "
                                + request.getRequestId()
                                + " and Request "
                                + conflictingRequest.getRequestId());

                return;
            }

            allocationService.allocate(request);

            auditLogDAO.saveLog(
                    "REQUEST_APPROVED",
                    request.getRequestId(),
                    "Request approved and resource allocated."
            );

        } catch (ResourceUnavailableException e) {

            request.setStatus(RequestStatus.REJECTED);

            auditLogDAO.saveLog(
                    "REQUEST_REJECTED",
                    request.getRequestId(),
                    e.getMessage()
            );

            System.out.println(
                    "Request " + request.getRequestId()
                            + " rejected: " + e.getMessage());
        }
    }

    private int generateConflictId() {
        return (int) (System.currentTimeMillis() % 100000);
    }

    private void validateRequest(
            ResourceRequest request)
            throws ResourceUnavailableException {

        if (request.getResource() == null) {
            throw new ResourceUnavailableException(
                    "Resource does not exist.");
        }

        if (request.getStartTime() == null
                || request.getEndTime() == null
                || !request.getStartTime()
                        .isBefore(request.getEndTime())) {

            throw new ResourceUnavailableException(
                    "Invalid time range.");
        }
    }
}