package io.javabrains.bean;

import java.util.List;

public class UserCatalog {
    private String userId;
    private List<CatalogInfo> catalogInfoList;

    public UserCatalog(String userId, List<CatalogInfo> catalogInfoList) {
        this.userId = userId;
        this.catalogInfoList = catalogInfoList;
    }

    public UserCatalog() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CatalogInfo> getCatalogInfoList() {
        return catalogInfoList;
    }

    public void setCatalogInfoList(List<CatalogInfo> catalogInfoList) {
        this.catalogInfoList = catalogInfoList;
    }
}
