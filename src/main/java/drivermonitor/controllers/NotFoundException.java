package drivermonitor.controllers;

public class NotFoundException extends RuntimeException {
    NotFoundException(String obj) {
        super("Не удалось найти " + obj);
    }
}