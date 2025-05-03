package com.example.driveremote.controllers;
class NotFoundException extends RuntimeException {
    NotFoundException(String obj) {
        super("Could not find " + obj);
    }
}