package com.iei.ratallert.services.drugsService.database;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String unitStrength;
    private Integer quantity;
    private Unit units;

    public enum Unit{
        PILLS,
        PACK,
        DROPS
    }
}
