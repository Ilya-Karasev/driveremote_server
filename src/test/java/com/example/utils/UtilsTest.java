package com.example.utils;
import drivermonitor.models.*;
import drivermonitor.services.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class UtilsTest {
    private UserServiceImpl userService;
    private TestUserRepository userRepository;
    private ResultsServiceImpl resultsService;
    private TestResultsRepository resultsRepository;
    private RequestServiceImpl requestService;
    private TestRequestRepository requestRepository;
    private DriverServiceImpl driverService;
    private TestDriverRepository driverRepository;
    private ManagerServiceImpl managerService;
    private TestManagerRepository managerRepository;

    @BeforeEach
    void setUp() {
        userRepository = new TestUserRepository();
        userService = new UserServiceImpl(userRepository);
        resultsRepository = new TestResultsRepository();
        resultsService = new ResultsServiceImpl(resultsRepository);
        requestRepository = new TestRequestRepository();
        requestService = new RequestServiceImpl(requestRepository);
        driverRepository = new TestDriverRepository();
        driverService = new DriverServiceImpl(driverRepository);
        managerRepository = new TestManagerRepository();
        managerService = new ManagerServiceImpl(managerRepository);
    }

    @Test
    void testCreateUser() {
        User user = createTestUser();
        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals("Иванов", savedUser.getSurName());
    }

    @Test
    void testGetAllUsers() {
        userService.createUser(createTestUser());
        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    void testGetUserByEmailAndPasswordSuccess() {
        User user = createTestUser();
        userService.createUser(user);
        Optional<User> result = userService.getUserByEmailAndPassword("ivanov@example.com", "123456");
        assertTrue(result.isPresent());
        assertEquals("Иванов", result.get().getSurName());
    }

    @Test
    void testGetUserByEmailAndPasswordFail() {
        assertThrows(ResponseStatusException.class, () ->
                userService.getUserByEmailAndPassword("wrong@example.com", "wrongpass")
        );
    }

    @Test
    void testGetUserById() {
        User user = createTestUser();
        User saved = userService.createUser(user);
        Optional<User> result = userService.getUserById(saved.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void testGetUsersByIds() {
        User user1 = userService.createUser(createTestUser());
        User user2 = userService.createUser(createTestUser("Петров", "petrov@example.com"));
        List<User> result = userService.getUsersByIds(List.of(user1.getId(), user2.getId()));
        assertEquals(2, result.size());
    }

    @Test
    void testSaveResult() {
        User user = userService.createUser(createTestUser());
        Results result = createTestResult(user);
        Results saved = resultsService.saveResult(result);
        assertNotNull(saved);
        assertEquals(user.getEmail(), saved.getUser().getEmail());
    }

    @Test
    void testFindAllResults() {
        User user = userService.createUser(createTestUser());
        resultsService.saveResult(createTestResult(user));
        List<Results> results = resultsService.findAll();
        assertEquals(1, results.size());
    }

    @Test
    void testGetResultsByUserId() {
        User user = userService.createUser(createTestUser());
        resultsService.saveResult(createTestResult(user));
        resultsService.saveResult(createTestResult(user));
        List<Results> results = resultsService.getResultsByUserId(user.getId());
        assertEquals(2, results.size());
    }

    @Test
    void testGetLastResultByUserId() {
        User user = userService.createUser(createTestUser());
        Results first = createTestResult(user);
        first.setTestDate(LocalDateTime.now().minusDays(1));
        Results second = createTestResult(user);
        second.setTestDate(LocalDateTime.now());

        resultsService.saveResult(first);
        resultsService.saveResult(second);

        Results last = resultsService.getLastResultByUserId(user.getId());
        assertEquals(second.getTestDate(), last.getTestDate());
    }

    @Test
    void testCreateRequest() {
        Request request = requestService.createRequest(createTestRequest(1, 2));
        Request saved = requestService.createRequest(request);
        assertNotNull(saved);
        assertEquals(1, saved.getSender());
        assertEquals(2, saved.getReceiver());
    }

    @Test
    void testDeleteRequest() {
        Request request = requestService.createRequest(createTestRequest(1, 2));
        Integer id = request.getId();
        requestService.deleteRequest(id);
        List<Request> all = requestService.getAllRequests();
        assertTrue(all.isEmpty());
    }

    @Test
    void testGetAllRequests() {
        requestService.createRequest(createTestRequest(1, 2));
        requestService.createRequest(createTestRequest(3, 4));
        List<Request> all = requestService.getAllRequests();
        assertEquals(2, all.size());
    }

    @Test
    void testGetRequestsForReceiver() {
        requestService.createRequest(createTestRequest(1, 2));
        requestService.createRequest(createTestRequest(3, 2));
        requestService.createRequest(createTestRequest(4, 5));
        List<Request> forReceiver = requestService.getRequestsForReceiver(2);
        assertEquals(2, forReceiver.size());
    }

    @Test
    void testGetRequestsForSender() {
        requestService.createRequest(createTestRequest(1, 2));
        requestService.createRequest(createTestRequest(1, 3));
        requestService.createRequest(createTestRequest(4, 5));
        List<Request> forSender = requestService.getRequestsForSender(1);
        assertEquals(2, forSender.size());
    }

    @Test
    void testSaveDriver() {
        Driver driver = createTestDriver(1);
        Driver saved = driverService.saveDriver(driver);
        assertNotNull(saved);
        assertEquals(1, saved.getId());
    }

    @Test
    void testGetDriverById() {
        Driver driver = createTestDriver(1);
        driverService.saveDriver(driver);
        Optional<Driver> result = driverService.getDriverById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testUpdateCompletionStatus() {
        Driver driver = createTestDriver(1);
        driverService.saveDriver(driver);
        driverService.updateCompletionStatus(1, false);
        assertFalse(driverService.getDriverById(1).get().getIsCompleted());
    }

    @Test
    void testUpdateQuantityValid() {
        Driver driver = createTestDriver(1);
        driverService.saveDriver(driver);
        driverService.updateQuantity(1, 2);
        assertEquals(2, driverService.getDriverById(1).get().getQuantity());
    }

    @Test
    void testUpdateQuantityInvalid() {
        Driver driver = createTestDriver(1);
        driverService.saveDriver(driver);
        assertThrows(IllegalArgumentException.class, () -> {
            driverService.updateQuantity(1, 3);
        });
    }

    @Test
    void testUpdateStatus() {
        Driver driver = createTestDriver(1);
        driverService.saveDriver(driver);
        driverService.updateStatus(1, "Усталость");
        assertEquals("Усталость", driverService.getDriverById(1).get().getStatus());
    }

    @Test
    void testSaveManager() {
        Manager manager = new Manager(1, List.of(101, 102));
        Manager saved = managerService.saveManager(manager);
        assertNotNull(saved);
        assertEquals(2, saved.getEmployeesList().size());
    }

    @Test
    void testGetManagerByIdFound() {
        Manager manager = new Manager(2, List.of(103));
        managerService.saveManager(manager);
        Optional<Manager> found = managerService.getManagerById(2);
        assertTrue(found.isPresent());
        assertEquals(103, found.get().getEmployeesList().get(0));
    }

    @Test
    void testGetManagerByIdNotFound() {
        Optional<Manager> found = managerService.getManagerById(999);
        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateEmployeesList() {
        Manager manager = new Manager(3, List.of(201, 202));
        managerService.saveManager(manager);

        List<Integer> updatedList = List.of(301, 302, 303);
        managerService.updateEmployeesList(3, updatedList);

        Optional<Manager> updated = managerService.getManagerById(3);
        assertTrue(updated.isPresent());
        assertEquals(3, updated.get().getEmployeesList().size());
        assertEquals(301, updated.get().getEmployeesList().get(0));
    }

    @Test
    void testUpdateEmployeesListManagerNotFound() {
        managerService.updateEmployeesList(999, List.of(1, 2, 3));
        Optional<Manager> manager = managerService.getManagerById(999);
        assertFalse(manager.isPresent());
    }

    private User createTestUser() {
        return createTestUser("Иванов", "ivanov@example.com");
    }

    private User createTestUser(String surName, String email) {
        return new User(
                surName,
                "Иван",
                "Иванович",
                35,
                Post.ВОДИТЕЛЬ,
                email,
                "123456"
        );
    }

    private Results createTestResult(User user) {
        return new Results(
                user,
                LocalDateTime.now(),
                40, 20, 30, 90
        );
    }

    private Request createTestRequest(Integer senderId, Integer receiverId) {
        Request request = new Request();
        request.setSender(senderId);
        request.setReceiver(receiverId);
        return request;
    }

    private Driver createTestDriver(int id) {
        return new Driver(
                id,
                true,
                List.of("08:00", "16:00"),
                1,
                "Норма"
        );
    }
}