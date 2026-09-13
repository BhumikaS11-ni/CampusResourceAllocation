package campus.service;

import campus.model.RequestStatus;
import campus.model.ResourceRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConcurrentRequestProcessor {

    private RequestProcessingService processingService;

    public ConcurrentRequestProcessor(
            RequestProcessingService processingService) {
        this.processingService = processingService;
    }

    public void processConcurrently(
            List<ResourceRequest> requests,
            List<ResourceRequest> existingRequests) {

        List<ResourceRequest> pendingRequests =
                new ArrayList<>(requests);

        Thread[] threads = new Thread[pendingRequests.size()];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(() -> {

                synchronized (existingRequests) {

                    // Select the highest-priority pending request
                    ResourceRequest selectedRequest =
                            pendingRequests.stream()
                                    .filter(r -> r.getStatus()
                                            == RequestStatus.PENDING)
                                    .max(Comparator.comparingInt(
                                            ResourceRequest::getPriority))
                                    .orElse(null);

                    if (selectedRequest != null) {

                        processingService.processRequest(
                                selectedRequest,
                                existingRequests);
                    }
                }
            });

            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {

            try {
                thread.join();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        "Request processing interrupted.");
            }
        }
    }
}