package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        store.add(new Role("2", "Programmer"));
        store.add(new Role("3", "Plumber"));
        Role result = store.findById("2");
        assertThat(result.getPosition()).isEqualTo("Programmer");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getPosition()).isEqualTo("Cook");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getPosition()).isEqualTo("Programmer");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        store.replace("2", new Role("2", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getPosition()).isEqualTo("Cook");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getPosition()).isEqualTo("Cook");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        boolean result = store.replace("1", new Role("1", "Programmer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cook"));
        boolean result = store.replace("2", new Role("2", "Programmer"));
        assertThat(result).isFalse();
    }
}