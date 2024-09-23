package com.fds.nsw.liferay.model;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.DefaultFullNameGenerator;
import com.fds.nsw.liferay.service.OrganizationLocalServiceUtil;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_", schema = "dvc_hanghai_liferay_clone")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "uuid_", length = 75)
    private String uuid;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "defaultUser")
    private Byte defaultUser;

    @Column(name = "contactId")
    private Long contactId;

    @Column(name = "password_", length = 75)
    private String password;

    @Column(name = "passwordEncrypted")
    private Byte passwordEncrypted;

    @Column(name = "passwordReset")
    private Byte passwordReset;

    @Column(name = "passwordModifiedDate")
    private Date passwordModifiedDate;

    @Column(name = "digest")
    private String digest;

    @Column(name = "reminderQueryQuestion", length = 75)
    private String reminderQueryQuestion;

    @Column(name = "reminderQueryAnswer", length = 75)
    private String reminderQueryAnswer;

    @Column(name = "graceLoginCount")
    private Integer graceLoginCount;

    @Column(name = "screenName", length = 75)
    private String screenName;

    @Column(name = "emailAddress", length = 75)
    private String emailAddress;

    @Column(name = "facebookId")
    private Long facebookId;

    @Column(name = "openId", length = 1024)
    private String openId;

    @Column(name = "portraitId")
    private Long portraitId;

    @Column(name = "languageId", length = 75)
    private String languageId;

    @Column(name = "timeZoneId", length = 75)
    private String timeZoneId;

    @Column(name = "greeting")
    private String greeting;

    @Lob
    @Column(name = "comments")
    private String comments;

    @Column(name = "firstName", length = 75)
    private String firstName;

    @Column(name = "middleName", length = 75)
    private String middleName;

    @Column(name = "lastName", length = 75)
    private String lastName;

    @Column(name = "jobTitle", length = 100)
    private String jobTitle;

    @Column(name = "loginDate")
    private Date loginDate;

    @Column(name = "loginIP", length = 75)
    private String loginIP;

    @Column(name = "lastLoginDate")
    private Date lastLoginDate;

    @Column(name = "lastLoginIP", length = 75)
    private String lastLoginIP;

    @Column(name = "lastFailedLoginDate")
    private Date lastFailedLoginDate;

    @Column(name = "failedLoginAttempts")
    private Integer failedLoginAttempts;

    @Column(name = "lockout")
    private Byte lockout;

    @Column(name = "lockoutDate")
    private Date lockoutDate;

    @Column(name = "agreedToTermsOfUse")
    private Byte agreedToTermsOfUse;

    @Column(name = "emailAddressVerified")
    private Byte emailAddressVerified;

    @Column(name = "status")
    private Integer status;

    @Transient
    @Getter(value = AccessLevel.NONE)
    private List<Organization> organizations;

    @Transient
    @Getter(value = AccessLevel.NONE)
    private List<Role> roles;


    public List<Organization> getOrganizations() {
        long idUser = this.getUserId();
        List<Organization> results;
        try {
            results =  OrganizationLocalServiceUtil.getUserOrganizations(idUser);
        } catch (Exception e) {
            results = new ArrayList<>();
        }

        setOrganizations(results);

        return results;
    }

    public List<Role> getRoles() {
        long idUser = this.getUserId();
        List<Role> results;
        try {
            results =   UserLocalServiceUtil.getRoles(idUser, -1, -1, null);
        } catch (Exception e) {
            results = new ArrayList<>();
        }

        setRoles(results);

        return results;
    }


    public String getFullName() {
        return DefaultFullNameGenerator.getFullName(this.getFirstName(), this.getMiddleName(), this.getLastName());
    }

}