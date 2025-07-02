CREATE SCHEMA library;

USE library;

CREATE TABLE `CATEGORY` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE `USER` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    login VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    birth_date DATE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE `BOOK` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    page_count INT NOT NULL,
    published_date DATE,
    category_id BIGINT,
    CONSTRAINT chk_page_count CHECK (page_count > 0),
    CONSTRAINT fk_book_category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE `LOAN` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    return_date DATE,
    CONSTRAINT fk_loan_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES book(id)
);
