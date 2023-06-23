package com.stackroute.expertservice.constants;

public class ExpertConstants {

    public static final String COLON = ": ";
    public static final String NUM_0 = "0";
    public static final String PARAM_EXPERT_EMAIL = "expertEmail";
    public static final String PARAM_SERVICE_ID = "serviceId";
    public static final String PARAM_SERVICE_NAME = "serviceName";
    public static final String PARAM_SLOTS = "slots";
    public static final String PARAM_SLOT_START_TIME = "slotStartTime";
    public static final String PARAM_SLOT_END_TIME = "slotEndTime";
    public static final String PARAM_SLOT_ID = "slotId";
    public static final String PARAM_SLOT_STATUS = "slotStatus";
    public static final String PARAM_SLOT_DATE = "slotDate";
    public static final String PARAM_AVAILABLE_DATES = "availableDates";
    public static final String PARAM_AVAILABLE_DATE = "availableDate";
    public static final String PARAM_PRICE = "price";

    public static final String INVALID = "Invalid ";
    public static final String CANT_BE_NULL = ", it can't be null";
    public static final String CANT_BE_BLANK = ", it can't be blank";
    public static final String CANT_BE_EMPTY = ", it can't be empty";
    public static final String CANT_BE_NULL_OR_BLANK = ", it can't be null/blank";
    public static final String CANT_BE_LESS_THAN = ", it can't be less than ";
    public static final String LENGTH_BETWEEN = ", the length must be between 2 and 15";
    public static final String EXPERT_AVAILABILITY_NOT_FOUND = "Expert Availability not found with ";
    public static final String AVAILABLE_DATE_ALREADY_EXIST = "Available Date already exist with ";

    public static final String SERVICE_NOT_FOUND = "Service not found with ";

    public static final String SLOT_NOT_FOUND = "Slot not found with ";
    public static final String DATE_CANT_BE_PAST = ", it can't be a past date";

    public static final String TIME_CANT_BE_PAST = ", it can't be a past time";

    public static final String TIME_CANT_BE_PAST_OR_EQUAL = ", it can't be a past time or equal to ";
    public static final String ALREADY_EXIST = ", it already exists";

    public static final String MESSAGE_DELETE_SLOT = "Slot has been successfully deleted with id :";
    public static final String MESSAGE_DELETE_SERVICE = "Service has been successfully deleted with id :";

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String MESSAGE_MISSING_TOKEN = "Authorization token is missing in request";
    public static final String MESSAGE_INVALID_TOKEN = "Authorization token is invalid";
    public static final String MESSAGE_EXPIRED_TOKEN = "Authorization token has expired";
    public static final String MESSAGE_USER_UNAUTHORIZED = "User is not authorized to do this action";


    private ExpertConstants() {
    }
}
