package model.domain;

public class Facility {
    private final String facilityID;
    private final String facilityName;
    private final String facilityType;
    private final String address;
    private final String postcode;
    private final String phoneNumber;
    private final String email;
    private final String openingHours;
    private final String managerName;
    private final int capacity;
    private final String specialitiesOffered;

    public Facility(String facilityID, String facilityName, String facilityType, String address, 
                    String postcode, String phoneNumber, String email, String openingHours, 
                    String managerName, int capacity, String specialitiesOffered) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.capacity = capacity;
        this.specialitiesOffered = specialitiesOffered;
    }

    public String getFacilityID() { return facilityID; }
    public String getFacilityName() { return facilityName; }
    public String getFacilityType() { return facilityType; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getOpeningHours() { return openingHours; }
    public String getManagerName() { return managerName; }
    public int getCapacity() { return capacity; }
    public String getSpecialitiesOffered() { return specialitiesOffered; }

    // Domain methods
    public void getAvailableAppointments() {
        // TODO: Implement getAvailableAppointments
    }

    public void checkCapacity() {
        // TODO: Implement checkCapacity
    }

    public void getSpecialities() {
        // TODO: Implement getSpecialities
    }
}
