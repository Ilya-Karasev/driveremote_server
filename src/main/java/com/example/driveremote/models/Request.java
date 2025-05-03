package com.example.driveremote.models;

import jakarta.persistence.*;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "senderId", nullable = false)
    private Integer sender;

    @Column(name = "receiverId", nullable = false)
    private Integer receiver;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSender() { return sender; }
    public void setSender(Integer senderId) { this.sender = senderId; }

    public Integer getReceiver() { return receiver; }
    public void setReceiver(Integer receiverId) { this.receiver = receiverId; }
}