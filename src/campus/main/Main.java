package campus.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import campus.model.*;
import campus.service.*;
import campus.dao.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" CAMPUS RESOURCE ALLOCATION SYSTEM");
        System.out.println("======================================");

        // ==============================
        // USERS
        // ==============================

        User student = new User(
                101,
                "Rahul",
                "rahul@vitbhopal.ac.in",
                "STUDENT"
        );

        User faculty = new User(
                102,
                "Dr. Sharma",
                "sharma@vitbhopal.ac.in",
                "FACULTY"
        );

        // ==============================
        // RESOURCES
        // ==============================

        Resource lab = new Resource(
                201,
                "Programming Lab",
                "LABORATORY",
                60
        );

        Resource seminarHall = new Resource(
                202,
                "Seminar Hall",
                "HALL",
                150
        );

        // ==============================
        // SERVICES
        // ==============================

        UserService userService = new UserService();
        ResourceService resourceService = new ResourceService();
        ResourceRequestService requestService =
                new ResourceRequestService();

        ConflictDetectionService conflictService =
                new ConflictDetectionService();

        AllocationService allocationService =
                new AllocationService();

        RequestProcessingService processingService =
                new RequestProcessingService(
                        conflictService,
                        allocationService
                );

        ConcurrentRequestProcessor concurrentProcessor =
                new ConcurrentRequestProcessor(
                        processingService
                );

        PriorityAllocationService priorityService =
                new PriorityAllocationService();

        // ==============================
        // DATABASE OBJECTS
        // ==============================

        UserDAO userDAO = new UserDAO();
        ResourceDAO resourceDAO = new ResourceDAO();
        RequestDAO requestDAO = new RequestDAO();
        AllocationDAO allocationDAO = new AllocationDAO();
        AuditLogDAO auditLogDAO = new AuditLogDAO();

        // ==============================
        // ADD USERS
        // ==============================

        userService.addUser(student);
        userService.addUser(faculty);

        System.out.println("\n--- Users ---");
        userService.displayAllUsers();

        // ==============================
        // ADD RESOURCES
        // ==============================

        resourceService.addResource(lab);
        resourceService.addResource(seminarHall);

        System.out.println("\n--- Resources ---");
        resourceService.displayAllResources();

        // ==============================
        // DATABASE SAVE
        // ==============================

        userDAO.saveUser(student);
        userDAO.saveUser(faculty);

        resourceDAO.saveResource(lab);
        resourceDAO.saveResource(seminarHall);

        // ==============================
        // CREATE REQUESTS
        // ==============================

        ResourceRequest request1 =
                new ResourceRequest(
                        301,
                        student,
                        lab,
                        LocalDate.of(2026, 9, 15),
                        LocalTime.of(10, 0),
                        LocalTime.of(12, 0),
                        "Java Practical",
                        3
                );

        ResourceRequest request2 =
                new ResourceRequest(
                        302,
                        faculty,
                        lab,
                        LocalDate.of(2026, 9, 15),
                        LocalTime.of(11, 0),
                        LocalTime.of(13, 0),
                        "Faculty Workshop",
                        5
                );

        requestService.addRequest(request1);
        requestService.addRequest(request2);

        requestDAO.saveRequest(request1);
        requestDAO.saveRequest(request2);

        auditLogDAO.saveLog(
                "REQUEST_CREATED",
                request1.getRequestId(),
                "New resource request created."
        );

        auditLogDAO.saveLog(
                "REQUEST_CREATED",
                request2.getRequestId(),
                "New resource request created."
        );

        // ==============================
        // REQUEST LIST
        // ==============================

        List<ResourceRequest> requests =
                new ArrayList<>();

        requests.add(request1);
        requests.add(request2);

        // ==============================
        // DISPLAY REQUESTS
        // ==============================

        System.out.println("\n--- Resource Requests ---");
        requestService.displayAllRequests();

        // ==============================
        // PRIORITY ORDER
        // ==============================


        priorityService.displayPriorityOrder(requests);

        // ==============================
        // CONCURRENT PROCESSING
        // ==============================

        System.out.println(
                "\n--- Concurrent Request Processing ---"
        );

        concurrentProcessor.processConcurrently(
                requests,
                requestService.getRequests()
                
        );

         requestDAO.updateRequestStatus(request1);
         requestDAO.updateRequestStatus(request2);

        // ==============================
        // RESULTS
        // ==============================

        System.out.println("\n--- Final Request Status ---");

        requestService.displayAllRequests();

        // ==============================
        // ALLOCATIONS
        // ==============================

        System.out.println("\n--- Allocations ---");

        allocationService.displayAllAllocations();

        // ==============================
        // SAVE ALLOCATIONS
        // ==============================

        for (var allocation :
                allocationService.getAllocations()) {

            allocationDAO.saveAllocation(allocation);
        }

        // ==============================
        // COMPLETION
        // ==============================

        System.out.println("\n======================================");
        System.out.println(" PROJECT EXECUTION COMPLETED");
        System.out.println("======================================");
    }
}