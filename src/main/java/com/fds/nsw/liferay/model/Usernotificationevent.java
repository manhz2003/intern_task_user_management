package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usernotificationevent", schema = "dvc_hanghai_liferay_clone")
public class Usernotificationevent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userNotificationEventId", nullable = false)
    private Long id;

    @Column(name = "uuid_", length = 75)
    private String uuid;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "type_", length = 75)
    private String type;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "deliverBy")
    private Long deliverBy;

    @Lob
    @Column(name = "payload")
    private String payload;

    @Column(name = "archived")
    private Byte archived;

}