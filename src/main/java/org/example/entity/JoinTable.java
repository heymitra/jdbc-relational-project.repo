package org.example.entity;

import java.util.Arrays;

public class JoinTable {
    long shareholderId;
    long brandId;
    Shareholder shareholder;
    Brand brand;

    public JoinTable(long shareholderId, long brandId) {
        this.shareholderId = shareholderId;
        this.brandId = brandId;
    }

    public JoinTable(Shareholder shareholder, Brand brand) {
        this.shareholder = shareholder;
        this.brand = brand;
    }

    public long getShareholderId() {
        return shareholderId;
    }

    public void setShareholderId(long shareholderId) {
        this.shareholderId = shareholderId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "\n------------------------------" + shareholder + brand;
    }
}
