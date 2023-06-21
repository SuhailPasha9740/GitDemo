package pojo;



public class getCourse  {

    private String instructor;
    private String url;
    private String services;
    private String expertise;


// data type is courses bcoz in response courses section having nested data but not string as the above variables have
    private courses courses;
    private String linkedIn;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    public pojo.courses getCourses() {
        return courses;
    }
//datatype is pojo.courses
    public void setCourses(pojo.courses courses) {
        this.courses = courses;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }



}
