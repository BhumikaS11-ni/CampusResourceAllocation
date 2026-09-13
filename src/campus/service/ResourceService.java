package campus.service;

import campus.model.Resource;
import java.util.ArrayList;
import java.util.List;

public class ResourceService {

    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        resources.add(resource);
        System.out.println("Resource added successfully.");
    }

    public Resource findResourceById(int resourceId) {
        for (Resource resource : resources) {
            if (resource.getResourceId() == resourceId) {
                return resource;
            }
        }
        return null;
    }

    public void displayAllResources() {
        if (resources.isEmpty()) {
            System.out.println("No resources found.");
            return;
        }

        for (Resource resource : resources) {
            resource.displayResource();
            System.out.println("-------------------------");
        }
    }
}
