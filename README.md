# Campus Resource Allocation & Conflict Resolution System

## Project Overview

The Campus Resource Allocation & Conflict Resolution System is a Java-based command-line application developed to manage shared campus resources such as laboratories, classrooms, seminar halls, and equipment.

The system accepts resource requests, validates them, checks for scheduling conflicts, processes requests according to priority, allocates available resources, and stores important records in a MySQL database.

The project demonstrates Java Object-Oriented Programming, Collections, Exception Handling, Multithreading, Synchronization, JDBC, and File/Database-based data management.

## Problem Statement

Campus resources are shared by multiple students and faculty members. When multiple users request the same resource for overlapping time periods, manual allocation can result in double booking, scheduling conflicts, and difficulty in tracking requests.

This project provides a Java-based solution that validates requests, detects conflicts, processes requests according to priority, and records allocation and conflict information.

## Objectives

- Manage campus users and resources.
- Accept and validate resource requests.
- Detect overlapping resource bookings.
- Process requests according to priority.
- Allocate available resources.
- Prevent conflicting resource allocations.
- Process multiple requests using multithreading.
- Store project records using MySQL and JDBC.
- Maintain conflict and audit records.

## Main Features

- User Management
- Resource Management
- Resource Request Management
- Request Validation
- Conflict Detection
- Priority-Based Request Processing
- Resource Allocation
- Concurrent Request Processing
- MySQL Database Persistence
- Conflict and Audit Logging

## Technologies Used

- Java 17
- MySQL
- JDBC
- Java Collections Framework
- Multithreading
- Synchronization
- Exception Handling
- Git and GitHub
- Visual Studio Code

## Java Concepts Used

- Classes and Objects
- Encapsulation
- Constructors
- Enums
- ArrayList
- Comparator and Sorting
- Exception Handling
- Multithreading
- Synchronization
- JDBC
- SQL CRUD-related operations

## Project Modules

### 1. User & Resource Management
Manages campus users and shared resources.

### 2. Resource Request Management
Creates, stores, validates, and manages resource requests.

### 3. Conflict Detection
Checks whether a request overlaps with an already approved request for the same resource and time period.

### 4. Priority & Allocation
Processes requests according to priority and allocates available resources.

### 5. Concurrent Request Processing
Uses multiple threads to process pending resource requests while synchronizing shared request processing.

### 6. Database & Audit Management
Uses JDBC and MySQL to store users, resources, requests, allocations, conflicts, and audit logs.

## System Workflow

User Request  
↓  
Request Validation  
↓  
Priority Processing  
↓  
Conflict Detection  
↓  
Resource Allocation / Rejection  
↓  
Database Update  
↓  
Audit Logging

## Example Execution

Two requests are submitted for the same Programming Lab:

- Request 301 — Priority 3
- Request 302 — Priority 5

The higher-priority Request 302 is processed first and successfully allocated.

Request 301 overlaps with the approved booking and is rejected because of the detected resource conflict.

## Project Structure

```text
CampusResourceAllocation
│
├── .gitignore
├── README.md
├── statement.md
├── schema.sql
│
├── .vscode
│   ├── launch.json
│   └── settings.json
│
├── lib
│   └── mysql-connector-j-26.7.0.jar
│
└── src
    └── campus
        ├── dao
        ├── exception
        ├── main
        ├── model
        ├── service
        └── util