package org.efire.net.vendomachine_api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
public class VendingMachine {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String type;

    @Column
    private String status;

    @OneToMany(mappedBy = "products", targetEntity = Product.class,
            fetch = FetchType.LAZY)
    private Set<Product> productProductss;


}
