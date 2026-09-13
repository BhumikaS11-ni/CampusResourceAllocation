# Campus Resource Allocation & Conflict Resolution System

## Project Overview

The Campus Resource Allocation & Conflict Resolution System is a Java-based application designed to manage shared campus resources such as laboratories, classrooms, seminar halls, and equipment.

The system processes resource requests, checks availability, detects scheduling conflicts, prioritizes requests, and allocates resources accordingly.

It demonstrates Object-Oriented Programming, Java Collections, Exception Handling, Multithreading, Synchronization, JDBC, and MySQL.

## Problem Statement

Campus resources are shared by students, faculty members, and other users. Manual resource allocation can result in double booking, scheduling conflicts, and difficulty in tracking requests.

This project provides an automated Java-based solution for managing resource requests and resolving conflicts using priority-based allocation.

## Objectives

- Manage campus users and resources.
- Accept and process resource requests.
- Detect overlapping resource bookings.
- Prioritize requests.
- Allocate resources without double booking.
- Process multiple requests using multithreading.
- Store records using MySQL.
- Maintain conflict and audit records.

## Main Features

- User Management
- Resource Management
- Resource Request Management
- Conflict Detection
- Priority-Based Allocation
- Concurrent Request Processing
- Database Persistence
- Audit Logging

## Requirements

- JDK 17 or later
- MySQL Server 8.0 or later
- MySQL Connector/J
- Visual Studio Code or any Java-compatible IDE

## Technologies Used

- Java 17
- MySQL
- JDBC
- Java Collections Framework
- Multithreading and Synchronization
- Exception Handling
- Git and GitHub
- Visual Studio Code

## Java Concepts Used

- Classes and Objects
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces
- Method Overriding
- Constructors
- Enums
- ArrayList
- Comparator and Sorting
- Exception Handling
- Multithreading
- Synchronization
- JDBC
- CRUD Operations

## Project Modules

### 1. User & Resource Management

Maintains information about campus users and available resources.

### 2. Resource Request Management

Creates and manages requests containing user, resource, date, time, purpose, and priority.

### 3. Conflict Detection Engine

Checks whether a requested resource has an overlapping approved booking.

### 4. Priority & Allocation Engine

Processes requests according to their priority and allocates available resources.

### 5. Concurrent Request Processor

Uses Java threads and synchronization to safely process multiple resource requests.

### 6. Database & Audit Management

Uses JDBC and MySQL to store users, resources, requests, allocations, conflicts, and audit records.

## System Workflow

User Request  
→ Request Validation  
→ Conflict Detection  
→ Priority Processing  
→ Resource Allocation  
→ Database Storage  
→ Audit Logging

## Example Execution

Two requests are made for the same Programming Lab:

- Request 301 — Priority 3
- Request 302 — Priority 5

Request 302 is processed first because it has higher priority and is successfully allocated.

Request 301 overlaps with the approved booking and is rejected because of the resource conflict.

## Project Structure

```text
CampusResourceAllocation/
│
├── lib/
│   └── mysql-connector-j-26.7.0.jar
│
├── src/
│   └── campus/
│       ├── model/
│       ├── service/
│       ├── dao/
│       ├── exception/
│       ├── util/
│       └── main/
│
├── .vscode/
└── README.md