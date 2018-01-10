package com.qcc.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_image")
public class Image extends BaseEntity{
    private String url;
    private String description;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
