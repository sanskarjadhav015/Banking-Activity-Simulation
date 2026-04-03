-- Create the database
CREATE DATABASE IF NOT EXISTS bankSystem;
USE bankSystem;

-- Table for user login credentials
CREATE TABLE IF NOT EXISTS login (
    formno VARCHAR(30),
    card_number VARCHAR(30),
    pin VARCHAR(30)
);

-- Table for personal details (Signup Page 1)
CREATE TABLE IF NOT EXISTS signup (
    formno VARCHAR(30),
    name VARCHAR(30),
    fname VARCHAR(30),
    dob VARCHAR(30),
    gender VARCHAR(30),
    email VARCHAR(50),
    marital VARCHAR(30),
    address VARCHAR(100),
    city VARCHAR(30),
    pincode VARCHAR(30),
    state VARCHAR(30)
);

-- Table for additional details (Signup Page 2)
CREATE TABLE IF NOT EXISTS signuptwo (
    formno VARCHAR(30),
    religion VARCHAR(30),
    category VARCHAR(30),
    income VARCHAR(30),
    education VARCHAR(30),
    occupation VARCHAR(30),
    pan VARCHAR(30),
    aadhar VARCHAR(30),
    seniorcitizen VARCHAR(30),
    existing_account VARCHAR(30)
);

-- Table for account details (Signup Page 3)
CREATE TABLE IF NOT EXISTS signupthree (
    formno VARCHAR(30),
    account_type VARCHAR(30),
    card_number VARCHAR(30),
    pin VARCHAR(30),
    facility VARCHAR(100)
);

-- Table for bank transactions (Deposits/Withdrawals)
CREATE TABLE IF NOT EXISTS bank (
    pin VARCHAR(30),
    date VARCHAR(50),
    type VARCHAR(30),
    amount VARCHAR(30)
);
