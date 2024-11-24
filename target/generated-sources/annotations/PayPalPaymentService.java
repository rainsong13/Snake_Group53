package ui.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PayPalPaymentService {
    private PayPalHandler payPalHandler;

    public PayPalPaymentService(PayPalHandler payPalHandler) {
        this.payPalHandler = payPalHandler;
    }

    public void createPayment(String amount, String currency) {
        // Create amount object
        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency(currency);
        paymentAmount.setTotal(amount);

        // Create transaction object
        Transaction transaction = new Transaction();
        transaction.setAmount(paymentAmount);
        transaction.setDescription("Simple Payment Description");

        // Add transaction to list
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Set payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Create payment object
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Set redirect URLs for PayPal approval
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://example.com/cancel");
        redirectUrls.setReturnUrl("https://example.com/return");
        payment.setRedirectUrls(redirectUrls);

        try {
            // Create payment on PayPal
            Payment createdPayment = payment.create(payPalHandler.getApiContext());

            // Find the approval link and open it in the browser
            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    // Open in default browser
                    Desktop.getDesktop().browse(new URI(link.getHref()));
                    break;
                }
            }

            System.out.println("Payment created successfully");
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
