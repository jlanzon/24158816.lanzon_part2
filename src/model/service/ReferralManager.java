package model.service;

import model.domain.Referral;
import java.util.LinkedList;
import java.util.Queue;

public class ReferralManager {
    private static ReferralManager instance = new ReferralManager();
    private Queue<Referral> queue;

    private ReferralManager() {
        queue = new LinkedList<>();
    }

    public static ReferralManager getInstance() {
        return instance;
    }

    public void addReferral(Referral r) {
        queue.add(r);
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
