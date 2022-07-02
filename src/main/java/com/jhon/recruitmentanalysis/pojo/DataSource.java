package com.jhon.recruitmentanalysis.pojo;

import lombok.Data;

@Data
public class DataSource {

    private Integer id;
    private String dataUrl;
    private String tableName;
    private String username;
    private String password;
    private String driver;
    private String dataType;
    private String fileType;
    private Integer status;

}
