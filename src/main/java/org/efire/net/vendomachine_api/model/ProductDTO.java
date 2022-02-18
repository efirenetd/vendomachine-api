package org.efire.net.vendomachine_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String code;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double amount;

    private Integer quantity;

    private Long products;

}
