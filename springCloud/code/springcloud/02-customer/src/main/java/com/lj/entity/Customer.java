package com.lj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/24 13:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private Integer id;

    private String name;

    private Integer age;
}
