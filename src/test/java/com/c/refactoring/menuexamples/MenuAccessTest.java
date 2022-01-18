package com.c.refactoring.menuexamples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MenuAccessTest {

    @Test
    void testSetAuthorizationsInEachMenus() {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("A", "MenuARead", "MenuAWrite"),
                new MenuItem("B", "MenuBRead", "MenuBWrite"),
                new MenuItem("C", "MenuCRead", "MenuCWrite"),
                new MenuItem("D", "MenuDRead", "MenuDWrite")
        );
        MenuAccess menuAccess = new MenuAccess();
        Role[] userRoles = {new Role("MenuARead"), new Role("MenuBWrite"),
                new Role("MenuCRead"), new Role("MenuCWrite")};
        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertThat(menuItems)
                .extracting("access", "visible")
                .contains(tuple(Constants.READ, true),
                        tuple(Constants.WRITE, true),
                        tuple(Constants.WRITE, true),
                        tuple(null, false)
                );
    }
}
