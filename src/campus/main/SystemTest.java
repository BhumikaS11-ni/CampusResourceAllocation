package campus.main;

import campus.model.ResourceRequest;
import campus.model.User;
import campus.model.Resource;
import campus.service.RequestProcessingService;
import campus.service.ConflictDetectionService;
import campus.service.AllocationService;
import campus.dao.RequestDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SystemTest {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" CRACS SYSTEM TEST");
        System.out.println("======================================");

        User user = new User(
                501,
                "Test User",
                "test@vitbhopal.ac.in",
                "STUDENT"
        );

        Resource lab = new Resource(
                601,
                "Test Laboratory",
                "LABORATORY",
                50
        );

        ConflictDetectionService conflictService =
                new ConflictDetectionService();

        AllocationService allocationService =
                new AllocationService();

        RequestProcessingService processingService =
                new RequestProcessingService(
                        conflictService,
                        allocationService
                );

         RequestDAO requestDAO = new RequestDAO();



        // Test Case 1: Invalid time range
        System.out.println("\n--- Test Case 1: Invalid Time ---");

        ResourceRequest invalidRequest =
                new ResourceRequest(
                        701,
                        user,
                        lab,
                        LocalDate.of(2026, 9, 20),
                        LocalTime.of(14, 0),
                        LocalTime.of(12, 0),
                        "Invalid Time Test",
                        3
                );

        List<ResourceRequest> requests =
                new ArrayList<>();

        requests.add(invalidRequest);

        processingService.processRequest(
                invalidRequest,
                requests
        );

        System.out.println(
                "Expected Result: Request REJECTED"
        );

        // Test Case 2: Different resources
System.out.println("\n--- Test Case 2: Different Resources ---");

Resource classroom = new Resource(
        602,
        "Test Classroom",
        "CLASSROOM",
        40
);

ResourceRequest labRequest =
        new ResourceRequest(
                702,
                user,
                lab,
                LocalDate.of(2026, 9, 20),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                "Laboratory Test",
                3
        );

ResourceRequest classroomRequest =
        new ResourceRequest(
                703,
                user,
                classroom,
                LocalDate.of(2026, 9, 20),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                "Classroom Test",
                3
        );
       

List<ResourceRequest> differentResourceRequests =
        new ArrayList<>();

        

differentResourceRequests.add(labRequest);

processingService.processRequest(
        labRequest,
        differentResourceRequests
);

differentResourceRequests.add(classroomRequest);

processingService.processRequest(
        classroomRequest,
        differentResourceRequests
);

System.out.println(
        "Expected Result: Both requests APPROVED"
);

// Test Case 3: Normal valid request
System.out.println("\n--- Test Case 3: Normal Request ---");

ResourceRequest normalRequest =
        new ResourceRequest(
                704,
                user,
                lab,
                LocalDate.of(2026, 9, 21),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Normal Resource Test",
                3
        );

List<ResourceRequest> normalRequests =
        new ArrayList<>();

normalRequests.add(normalRequest);

processingService.processRequest(
        normalRequest,
        normalRequests
);

System.out.println(
        "Expected Result: Request APPROVED"
);

// Test Case 4: Priority-based conflict resolution
System.out.println("\n--- Test Case 4: Priority Conflict ---");

Resource priorityLab = new Resource(
        603,
        "Priority Test Lab",
        "LABORATORY",
        50
);

ResourceRequest lowPriority =
        new ResourceRequest(
                705,
                user,
                priorityLab,
                LocalDate.of(2026, 9, 22),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                "Low Priority Request",
                2
        );

ResourceRequest highPriority =
        new ResourceRequest(
                706,
                user,
                priorityLab,
                LocalDate.of(2026, 9, 22),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                "High Priority Request",
                5
        );
        requestDAO.saveRequest(highPriority);
        requestDAO.saveRequest(lowPriority);

List<ResourceRequest> priorityRequests =
        new ArrayList<>();

priorityRequests.add(highPriority);
processingService.processRequest(
        highPriority,
        priorityRequests
);

priorityRequests.add(lowPriority);
processingService.processRequest(
        lowPriority,
        priorityRequests
);

System.out.println(
        "Expected Result: High-priority request APPROVED, "
        + "low-priority request REJECTED"
);

        System.out.println("\n======================================");
        System.out.println(" TEST COMPLETED");
        System.out.println("======================================");
    }
}
