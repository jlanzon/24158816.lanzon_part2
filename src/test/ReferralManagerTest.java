package test;

import model.domain.Referral;
import model.service.ReferralManager;
import java.time.LocalDate;

public class ReferralManagerTest {
    public static void main(String[] args) {
        System.out.println("Testing ReferralManager Singleton...");

        ReferralManager manager = ReferralManager.getInstance();
        if (manager != null) {
            System.out.println("SUCCESS: ReferralManager instance obtained.");
        } else {
            System.out.println("FAILURE: ReferralManager instance is null.");
            return;
        }

        Referral r1 = new Referral(
            "REF001", "PAT001", "DOC001", "DOC002", "FAC001", "FAC002",
            LocalDate.now(), "HIGH", "Checkup", "Summary", "Blood Test", "PENDING",
            "APP001", "Notes", LocalDate.now(), LocalDate.now()
        );

        manager.addReferral(r1);
        System.out.println("Added referral. Count: " + manager.getReferralCount());

        String email = manager.generateReferralEmail(r1);
        System.out.println("\nGenerated Email:\n" + email);

        Referral processed = manager.processNextReferral();
        if (processed != null && processed.getReferralID().equals("REF001")) {
            System.out.println("\nSUCCESS: Processed referral matches added referral.");
        } else {
            System.out.println("\nFAILURE: Processed referral mismatch.");
        }
        
        System.out.println("Final Count: " + manager.getReferralCount());
    }
}
