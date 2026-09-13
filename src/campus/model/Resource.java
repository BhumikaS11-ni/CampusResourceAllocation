package campus.model;

public class Resource {

    private int resourceId;
    private String resourceName;
    private String resourceType;
    private int capacity;
    private boolean available;

    public Resource(int resourceId, String resourceName,
                    String resourceType, int capacity) {

        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.capacity = capacity;
        this.available = true;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayResource() {
        System.out.println("Resource ID   : " + resourceId);
        System.out.println("Resource Name : " + resourceName);
        System.out.println("Resource Type : " + resourceType);
        System.out.println("Capacity      : " + capacity);
        System.out.println("Available     : " + (available ? "Yes" : "No"));
    }
}
    
