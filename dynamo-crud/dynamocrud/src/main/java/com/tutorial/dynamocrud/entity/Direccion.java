package com.tutorial.dynamocrud.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBDocument
public class Direccion {
    @DynamoDBAttribute
    private String ciudad;
    @DynamoDBAttribute
    private String calle;
    @DynamoDBAttribute
    private long cp;
}
