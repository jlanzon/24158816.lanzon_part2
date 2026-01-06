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
            "REF010", "PAT010", "DOC010", "DOC010", "FAC010", "FAC010",
            LocalDate.now(), "HIGH", "Checkup", "Summary", "Blood Test", "PENDING",
            "APP010", "Notes", LocalDate.now(), LocalDate.now()
        );

        manager.addReferral(r1);
        System.out.println("Added referral. Count: " + manager.getReferralCount());

        String email = manager.generateReferralEmail(r1);
        System.out.println("\nGenerated Email:\n" + email);

        Referral processed = manager.processNextReferral();
        if (processed != null && processed.getReferralID().equals("REF010")) {
            System.out.println("\nSUCCESS: Processed referral matches added referral.");
        } else {
            System.out.println("\nFAILURE: Processed referral mismatch.");
        }
        
        System.out.println("Final Count: " + manager.getReferralCount());
    }
}
