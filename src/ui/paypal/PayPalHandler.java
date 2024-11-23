package ui.paypal;

import com.paypal.base.rest.APIContext;

public class PayPalHandler {
    private APIContext apiContext;

    public PayPalHandler() {
        // Replace these with your own credentials from PayPal Developer Dashboard
        String clientId = "YOUR_CLIENT_ID";
        String clientSecret = "YOUR_CLIENT_SECRET";

        // Create a context for PayPal API
        this.apiContext = new APIContext(clientId, clientSecret, "sandbox");
    }

    // Getter for the API context
    public APIContext getApiContext() {
        return apiContext;
    }
}
