package com.ms.main.constants;


public class Constants {

    private Constants(){
        throw new IllegalStateException("Constants utility class!");
    }

    private static final String[] EDITED_IMAGE_FILE_NAMES = {"Chris_edited.jpg", "paul walker_edited.jpg", "Sherlock_edited.jpg", "tom_edited.jpg", "tony_edited.jpg" };

    public static final String EDITED_IMAGE_FOLDER = "/dummyResponseImages/";

    public static final Integer FAILURE_USER_ID = -1;

    public static final Integer FAILURE_CANDIDATE_ID = -1;

    public static final Integer FAILURE_LOCATION_ID = -1;

    public static final Integer FAILURE_INSTITUTION_ID = -1;

    public static final String EMPTY_RESPONSE_STRING = "Nil";

    public static final String LOCATION_DUPLICATE_MESSAGE = "Location is already present!";

    public static final String LOCATION_ADD_SUCCESS_MESSAGE = "Location successfully added!";

    public static final String GET_LOCATIONS_SUCCESS_MESSAGE = "Get all locations successful!";

    public static final String GET_LOCATIONS_FAILURE_MESSAGE = "Get all locations failed!";

    public static final String GET_INSTITUTIONS_SUCCESS_MESSAGE = "Get all institutions successful!";

    public static final String GET_INSTITUTIONS_FAILURE_MESSAGE = "Get all institutions failed!";

    public static final String ADD_CANDIDATE_FAILURE_LOCATION_MESSAGE = "Candidate location invalid!";

    public static final String ADD_CANDIDATE_FAILURE_INSTITUTION_MESSAGE = "Candidate institution invalid!";

    public static final String ADD_CANDIDATE_FAILURE_USER_MESSAGE = "Candidate created by user is invalid!";

    public static final String ADD_CANDIDATE_FAILURE_DUPLICATE_MESSAGE = "Candidate email is duplicate!";

    public static final String ADD_CANDIDATE_SUCCESS_MESSAGE = "Candidate added successfully!";

    public static final String GET_CANDIDATES_FAILURE_MESSAGE = "No candidates found!";

    public static final String GET_CANDIDATES_SUCCESS_MESSAGE = "Get all candidates success!";

    public static final String DELETE_CANDIDATE_FAILURE_MESSAGE = "Candidate couldn't be deleted!";

    public static final String DELETE_CANDIDATE_SUCCESS_MESSAGE = "Candidate deleted successfully!";

    public static final String UPDATE_CANDIDATE_SUCCESS_MESSAGE = "Candidate update success!";

    public static final String UPDATE_CANDIDATE_FAILURE_MESSAGE = "Candidate update failed!";

    public static final String GET_USERS_SUCCESS_MESSAGE = "Get all users success!";

    public static final String GET_USERS_FAILURE_MESSAGE = "Get all users failed!";

    public static final int BOUND_FOR_DUMMY_IMAGES = 4;

    public static final int BOUND_OFFSET = 1;

    public static final String MANAGER_ROLE = "manager";

    public static final String EMPLOYEE_ROLE = "employee";


    public static final String SIGNUP_SUCCESS_MESSAGE = "Sign up successful!";

    public static final String SIGNUP_EMAIL_DUPLICATE_MESSAGE = "Email is duplicate";

    public static final String SIGN_IN_SUCCESS_MESSAGE = "Sign In Success!";

    public static final String SIGN_IN_FAILED_MESSAGE = "Sign In failed!, Invalid credentials";

    public static final String LOGIN_FAILED_ROLE = "Invalid!";

    public static final String SUBORDINATE_SUCCESS_MESSAGE = "Subordinates fetch success!";

    public static final String SUBORDINATE_FAILURE_MESSAGE = "Subordinates fetch failed!";

    public static final String IMAGE_SAVED_MESSAGE = "Received image saved!";

    public static final String INVALID_USER_MESSAGE = "User invalid!";

    public static final String IMAGE_EDIT_FAILED_MESSAGE = "Couldn't edit image";

    public static final String DUMMY_IMAGE_TYPE = "image/jpeg";

    public static final String BULK_IMAGE_FAILURE_MESSAGE = "Process couldn't be completed!";

    public static final String BULK_IMAGE_SUCCESS_MESSAGE = "Process successfully completed!";

    public static final String ADMIN_GET_ALL_DATA_SUCCESS_MESSAGE = "All employee data fetched successfully!";

    public static final String ADMIN_GET_ALL_DATA_FAILURE_MESSAGE = "All employee data fetch failed!!";

    public static final String ASSIGN_MANAGER_SUCCESS_MESSAGE = "Manager successfully assigned to employee!";

    public static final String ASSIGN_MANAGER_FAILURE_MESSAGE = "Failed to assign manager to employee!";

    public static String[] getEditedImageFileNames(){
        return EDITED_IMAGE_FILE_NAMES;
    }

}
