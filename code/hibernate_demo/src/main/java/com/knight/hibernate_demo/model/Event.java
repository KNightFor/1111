package com.knight.hibernate_demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Column(name = "EVENT_ID")
    private long id;

    private String title;

}