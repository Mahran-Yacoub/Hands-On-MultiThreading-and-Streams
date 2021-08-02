package exalt.company.SpringBootrRestAPI.models;

/**
 * This Enum Contains Two Status for a server
 * either is ON or OFF
 */
public enum Active {

    ON("Active"),
    OFF("InActive");

    private String status;

    private Active(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
