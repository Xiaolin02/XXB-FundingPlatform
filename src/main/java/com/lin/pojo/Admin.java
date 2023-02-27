package com.lin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 林炳昌
 * @date 2023年01月25日 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private String adminName;
    private Integer password;

}