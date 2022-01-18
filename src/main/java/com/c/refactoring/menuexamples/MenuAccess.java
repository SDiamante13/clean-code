package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItems, Role[] roles) {
        if (roles == null) return;

        menuItems.forEach(menuItem -> setMenuItemAccess(roles, menuItem));
        }

    private void setMenuItemAccess(Role[] roles, MenuItem menuItem) {
        if (hasAccessRole(roles, menuItem.getReadAccessRole())) {
                menuItem.setAccess(Constants.READ);
                menuItem.setVisible(true);
            }

        if (hasAccessRole(roles, menuItem.getWriteAccessRole())) {
                menuItem.setAccess(Constants.WRITE);
                menuItem.setVisible(true);
        }
    }

    private boolean hasAccessRole(Role[] roles, String writeAccessRole) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(writeAccessRole));
    }

}
