package github.com.emreisler.simplebanking.controller;

import java.util.UUID;

public class TransactionStatus {

    private String status;
    private UUID approvalCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(UUID approvalCode) {
        this.approvalCode = approvalCode;
    }

}
