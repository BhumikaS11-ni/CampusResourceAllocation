package campus.model;

public class Conflict {

    private int conflictId;
    private ResourceRequest request1;
    private ResourceRequest request2;
    private String reason;
    private String resolution;

    public Conflict(int conflictId, ResourceRequest request1,
                    ResourceRequest request2, String reason) {

        this.conflictId = conflictId;
        this.request1 = request1;
        this.request2 = request2;
        this.reason = reason;
        this.resolution = "PENDING";
    }

    public int getConflictId() {
        return conflictId;
    }

    public ResourceRequest getRequest1() {
        return request1;
    }

    public ResourceRequest getRequest2() {
        return request2;
    }

    public String getReason() {
        return reason;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void displayConflict() {
        System.out.println("Conflict ID : " + conflictId);
        System.out.println("Request 1   : " + request1.getRequestId());
        System.out.println("Request 2   : " + request2.getRequestId());
        System.out.println("Reason      : " + reason);
        System.out.println("Resolution  : " + resolution);
    }
}
