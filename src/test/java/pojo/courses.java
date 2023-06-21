package pojo;

import java.util.List;

public class courses {


// here Data type is WebAutomation bcoz it has nested data / having data inside it & it is enclosed with List<> array
    //bcoz in all the below there are list of data/ more than one array
    // same as in getters & setters method
    private List<WebAutomation> webAutomation;
    private List<Api> API;

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

    private List<Mobile> mobile;



    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Api> getAPI() {
        return API;
    }

    public void setAPI(List<Api> API) {
        this.API = API;
    }




}
