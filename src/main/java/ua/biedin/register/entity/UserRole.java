package ua.biedin.register.entity;

import javax.persistence.Table;

@Table(name = "roles")
public enum UserRole {
    RESERVED,USER, ADMIN
}
