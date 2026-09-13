CREATE DATABASE IF NOT EXISTS campus_allocation;
USE campus_allocation;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS resources (
    resource_id INT PRIMARY KEY,
    resource_name VARCHAR(100) NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    available BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS resource_requests (
    request_id INT PRIMARY KEY,
    user_id INT NOT NULL,
    resource_id INT NOT NULL,
    request_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    purpose VARCHAR(255) NOT NULL,
    priority INT NOT NULL,
    status VARCHAR(30) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (resource_id) REFERENCES resources(resource_id)
);

CREATE TABLE IF NOT EXISTS allocations (
    allocation_id INT PRIMARY KEY,
    request_id INT NOT NULL,
    allocation_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    FOREIGN KEY (request_id) REFERENCES resource_requests(request_id)
);

CREATE TABLE IF NOT EXISTS conflicts (
    conflict_id INT PRIMARY KEY,
    request_id_1 INT NOT NULL,
    request_id_2 INT NOT NULL,
    reason VARCHAR(255) NOT NULL,
    resolution VARCHAR(100) NOT NULL,
    FOREIGN KEY (request_id_1) REFERENCES resource_requests(request_id),
    FOREIGN KEY (request_id_2) REFERENCES resource_requests(request_id)
);

CREATE TABLE IF NOT EXISTS audit_logs (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    action VARCHAR(100) NOT NULL,
    request_id INT,
    details VARCHAR(255),
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (request_id) REFERENCES resource_requests(request_id)
);