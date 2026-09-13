package campus.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResourceRequest {

    private int requestId;
    private User user;
    private Resource resource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String purpose;
    private int priority;
    private RequestStatus status;

    public ResourceRequest(int requestId, User user, Resource resource,
                           LocalDate date, LocalTime startTime,
                           LocalTime endTime, String purpose,
                           int priority) {

        this.requestId = requestId;
        this.user = user;
        this.resource = resource;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
        this.priority = priority;
        this.status = RequestStatus.PENDING;
    }

    public int getRequestId() {
        return requestId;
    }

    public User getUser() {
        return user;
    }

    public Resource getResource() {
        return resource;
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

    public String getPurpose() {
        return purpose;
    }

    public int getPriority() {
        return priority;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void displayRequest() {
        System.out.println("Request ID : " + requestId);
        System.out.println("User       : " + user.getName());
        System.out.println("Resource   : " + resource.getResourceName());
        System.out.println("Date       : " + date);
        System.out.println("Time       : " + startTime + " - " + endTime);
        System.out.println("Purpose    : " + purpose);
        System.out.println("Priority   : " + priority);
        System.out.println("Status     : " + status);
    }
}