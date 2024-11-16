package com.Rusya2054.bot.web.manager.models.management;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "specialists_images")
public class SpecialistImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "contentType")
    private String contentType;

    // данной поле будет храниться в blob (@Lob - иной вариант)
    // @Column(columnDefinition = "LONGBLOB") -- не робит
    @Lob
    private byte[] bytes;
    // fetch - способ загрузки -- тут почитай подробнее
//    @OneToOne(cascade =  CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private Specialist specialist;
}
