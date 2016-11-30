package com.softserve.edu.delivery.domain;

public enum Role {
    CUSTOMER("Customer", "/#/orders/open"),
    DRIVER("Driver", "/#/driver"),
    ADMIN("Admin", "/#/users"),
    MODERATOR("Moderator", "/#/feedback"),
    MANAGER("Manager", "/#/users");

    private String roleName;

    private String pageUrl;

    Role(String name, String url) {
        roleName = name;
        pageUrl = url;
    }


    public static Role getRoleByString(String role) {
        Role result = null;
        Role[] roles = Role.values();
        for (Role r : roles) {
            if (role.equalsIgnoreCase(r.getName())) {
                result = r;
            }
        }
        return result;
    }

    public String getName() {
        return roleName;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
