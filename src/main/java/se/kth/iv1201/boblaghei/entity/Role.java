package se.kth.iv1201.boblaghei.entity;

import org.springframework.security.core.GrantedAuthority;


/**
 * O/R Mapping of the table Role in the database.
 */
public enum Role implements GrantedAuthority {

    ROLE_APPLICANT,
    ROLE_RECRUITER;

    @Override
    public String getAuthority() {
        return name();
    }
}