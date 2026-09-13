package campus.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Allocation {

    private int allocationId;
    private ResourceRequest request;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;

    public Allocation(int allocationId, ResourceRequest request) {
        this.allocationId = allocationId;
        this.request = request;
        this.date = request.getDate();
        this.startTime = request.getStartTime();
        this.endTime = request.getEndTime();
        this.status = "CONFIRMED";
    }

    public int getAllocationId() {
        return allocationId;
    }

    public ResourceRequest getRequest() {
        return request;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void cancelAllocation() {
        status = "CANCELLED";
    }

    public void displayAllocation() {
        System.out.println("Allocation ID : " + allocationId);
        System.out.println("Request ID    : " + request.getRequestId());
        System.out.println("User          : " + request.getUser().getName());
        System.out.println("Resource      : " + request.getResource().getResourceName());
        System.out.println("Date          : " + date);
        System.out.println("Time          : " + startTime + " - " + endTime);
        System.out.println("Status        : " + status);
    }
}
