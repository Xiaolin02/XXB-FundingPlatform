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
public class Project {

    private String userName;
    private String projectName;
    private Integer money;
    private String explain;
    private String status;
    private Integer getMoney;

    public Project(String userName, String projectName, Integer money, String explain) {

        this.userName = userName;
        this.projectName = projectName;
        this.money = money;
        this.explain = explain;
        this.status = "wait";
        this.getMoney = 0;

    }
}
