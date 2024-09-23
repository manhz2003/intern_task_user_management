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
@Table(name = "temp_ship_stores_items", schema = "dvc_hanghai_nghiepvu_clone")
public class TempShipStoresItems {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "ShipsStoreItemCode", length = 12)
    private String shipsStoreItemCode;

    @Column(name = "NameOfArticle", length = 250)
    private String nameOfArticle;

    @Column(name = "Quantity", length = 50)
    private String quantity;

    @Column(name = "QuantityUnit", length = 100)
    private String quantityUnit;

    @Column(name = "LocationOnBoat", length = 250)
    private String locationOnBoat;

    @Column(name = "UseInBoat")
    private Integer useInBoat;

}