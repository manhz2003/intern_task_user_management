package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_crew_effects_items", schema = "dvc_hanghai_nghiepvu_clone")
public class TempCrewEffectsItems {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "CrewEffectItemCode", length = 20)
    private String crewEffectItemCode;

    @Column(name = "FamilyName")
    private String familyName;

    @Column(name = "GivenName")
    private String givenName;

    @Column(name = "RankOrRatingCode", length = 20)
    private String rankOrRatingCode;

    @Column(name = "EffectsIneligibleForRelief", length = 500)
    private String effectsIneligibleForRelief;

}