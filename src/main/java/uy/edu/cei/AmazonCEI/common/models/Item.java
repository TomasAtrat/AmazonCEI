package uy.edu.cei.AmazonCEI.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    private Long id;
    private String uuid;
    private Integer stock;
    private String name;
    private Float cost;
}
