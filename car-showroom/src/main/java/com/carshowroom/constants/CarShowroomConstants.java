package com.carshowroom.constants;

public class CarShowroomConstants {

    // ─── Status values ───────────────────────────────────────────────────────
    public static       String STATUS_SUCCESS                   = "SUCCESS";
    public static       String STATUS_FAILURE                   = "FAILED";
    public static final String SUCCESS                          = "Success";
    public static final String FAILURE                          = "Failure";
    public static       String ACTIVE                           = "active";
    public static       String IN_ACTIVE                        = "inActive";
    public static       String PENDING                          = "PENDING";

    // ─── Generic keys ────────────────────────────────────────────────────────
    public static       String MESSAGE                          = "message: ";
    public static final String DATA                             = "Data";
    public static final String NOT_FOUND                        = "NOT_FOUND";

    // ─── Auth / Login ────────────────────────────────────────────────────────
    public static final String LOGIN_SUCCESS                    = "Login successful";
    public static final String LOGIN_FAILED                     = "Login failed";
    public static final String INVALID_CREDENTIALS              = "Invalid credentials";
    public static final String EMAIL_REQUIRED                   = "Email is required";
    public static final String PASSWORD_REQUIRED                = "Password is required";

    // ─── Car ─────────────────────────────────────────────────────────────────
    public static final String CAR_ADDED_SUCCESSFULLY           = "Car added successfully";
    public static final String CAR_UPDATED_SUCCESSFULLY         = "Car updated successfully";
    public static final String CAR_DELETED_SUCCESSFULLY         = "Car deleted successfully";
    public static final String CAR_FETCHED_SUCCESSFULLY         = "Car fetched successfully";
    public static final String CARS_FETCHED_SUCCESSFULLY        = "Cars fetched successfully";
    public static final String CAR_NOT_FOUND                    = "Car not found";
    public static final String NO_CARS_FOUND                    = "No cars found";
    public static       String CAR_NOT_FOUND_WITH_ID            = "Car not found with id: ";
    public static final String INVALID_CAR_ID                   = "Invalid car id";
    public static final String CAR_ALREADY_SOLD                 = "Car is already sold";
    public static final String CAR_VIN_EXISTS                   = "Car with this VIN already exists";

    // ─── Car Status ──────────────────────────────────────────────────────────────
    public static final String CAR_STATUS_AVAILABLE             = "AVAILABLE";
    public static final String CAR_STATUS_SOLD                  = "SOLD";
    public static final String CAR_STATUS_RESERVED              = "RESERVED";
    public static final String CAR_STATUS_UNDER_MAINTENANCE     = "UNDER_MAINTENANCE";
    public static final String CAR_STATUS_UPDATED_SUCCESSFULLY  = "Car status updated successfully";
    public static final String INVALID_CAR_STATUS               = "Invalid car status. Valid values: AVAILABLE, SOLD, RESERVED, UNDER_MAINTENANCE";
    public static final String CAR_ID_CANNOT_BE_NULL_OR_NEGATIVE = "Car id cannot be null or negative";
    public static final String CAR_SEARCH_RESULTS_FETCHED       = "Car search results fetched successfully";

    // ─── Customer ────────────────────────────────────────────────────────────
    public static       String CUSTOMER_REGISTERED_SUCCESSFULLY = "Customer registered successfully";
    public static final String CUSTOMER_UPDATED_SUCCESSFULLY    = "Customer updated successfully";
    public static final String CUSTOMER_DELETED_SUCCESSFULLY    = "Customer deleted successfully";
    public static final String CUSTOMER_FETCHED_SUCCESSFULLY    = "Customer fetched successfully";
    public static final String CUSTOMERS_FETCHED_SUCCESSFULLY   = "Customers fetched successfully";
    public static final String CUSTOMER_NOT_FOUND               = "Customer not found";
    public static       String CUSTOMER_NOT_FOUND_WITH_ID       = "Customer not found with id: ";
    public static final String INVALID_CUSTOMER_ID              = "Invalid customer id";
    public static final String CUSTOMER_EMAIL_EXISTS            = "Customer with this email already exists";

    // ─── Employee ────────────────────────────────────────────────────────────
    public static final String EMPLOYEE_ADDED_SUCCESSFULLY      = "Employee added successfully";
    public static final String EMPLOYEE_UPDATED_SUCCESSFULLY    = "Employee updated successfully";
    public static final String EMPLOYEE_DELETED_SUCCESSFULLY    = "Employee deleted successfully";
    public static final String EMPLOYEE_FETCHED_SUCCESSFULLY    = "Employee fetched successfully";
    public static final String EMPLOYEES_FETCHED_SUCCESSFULLY   = "Employees fetched successfully";
    public static final String EMPLOYEE_NOT_FOUND               = "Employee not found";
    public static       String EMPLOYEE_NOT_FOUND_WITH_ID       = "Employee not found with id: ";
    public static final String INVALID_EMPLOYEE_ID              = "Invalid employee id";

    // ─── Sale ────────────────────────────────────────────────────────────────
    public static final String SALE_RECORDED_SUCCESSFULLY       = "Sale recorded successfully";
    public static final String SALE_FETCHED_SUCCESSFULLY        = "Sale fetched successfully";
    public static final String SALES_FETCHED_SUCCESSFULLY       = "Sales fetched successfully";
    public static final String SALE_NOT_FOUND                   = "Sale not found";
    public static       String SALE_NOT_FOUND_WITH_ID           = "Sale not found with id: ";
    public static final String INVALID_SALE_ID                  = "Invalid sale id";

    // ─── Validation / Request ────────────────────────────────────────────────
    public static final String INVALID_REQUEST                  = "Invalid request";
    public static       String REQUEST_BODY_IS_MISSING_OR_CONTAINS_INVALID_DATA = "Required body is missing or contains invalid data";
    public static       String BAD_REQUEST                      = "BAD REQUEST";

    // ─── Errors / Misc ───────────────────────────────────────────────────────
    public static final String SOMETHING_WENT_WRONG             = "Something went wrong";
    public static final String INTERNAL_SERVER_ERROR            = "Internal Server Error";
    public static final String NO_DATA_FOUND                    = "No data found";

}
