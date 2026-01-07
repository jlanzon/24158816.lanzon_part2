package model.service;

import model.domain.Referral;
import java.util.LinkedList;
import java.util.Queue;

public class ReferralManager {
    private static ReferralManager instance = new ReferralManager();
    private Queue<Referral> queue;
    private java.util.Set<String> processedIds;

    private ReferralManager() {
        queue = new LinkedList<>();
        processedIds = new java.util.HashSet<>();
    }

    public static ReferralManager getInstance() {
        return instance;
    }

    public boolean addReferral(Referral r) {
        if (processedIds.contains(r.getReferralID())) {
            return false;
        }
        queue.add(r);
        processedIds.add(r.getReferralID());
        auditLog(r);
        return true;
    }

    private void auditLog(Referral r) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("output/referral_audit.txt", true))) {
            writer.write("Referral Created: " + r.getReferralID() + " at " + java.time.LocalDateTime.now());
            writer.newLine();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public Referral processNextReferral() {
        return queue.poll();
    }

    public int getReferralCount() {
        return queue.size();
    }

    public String generateReferralEmail(Referral r) {
        String s = "";
        s += "========================================\n";
        s += "NHS REFERRAL NOTIFICATION\n";
        s += "========================================\n";
        s += "Referral ID: " + r.getReferralID() + "\n";
        s += "Date: " + r.getReferralDate() + "\n";
        s += "Priority: " + r.getUrgencyLevel() + "\n\n";
        
        s += "From: " + r.getReferringClinicianID() + " at " + r.getReferringFacilityID() + "\n";
        s += "To: " + r.getReferredToClinicianID() + " at " + r.getReferredToFacilityID() + "\n\n";
        
        s += "Patient: " + r.getPatientID() + "\n";
        s += "Reason: " + r.getReferralReason() + "\n";
        s += "Clinical Summary: " + r.getClinicalSummary() + "\n";
        s += "Investigations: " + r.getRequestedInvestigations() + "\n\n";
        
        s += "Status: " + r.getStatus() + "\n";
        s += "========================================";
        
        return s;
    }
}
