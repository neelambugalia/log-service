package com.fable.logservice.repo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private long logId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private long unixTs;

    @Override
    public String toString() {
        return String.format("Log: {id: %d, logId: %d, user: %s, event: %s, unixTs: %s}", id, logId, userId, eventName, unixTs);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Log other = (Log) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (id != other.id)
            return false;
        if (eventName == null) {
            if (other.eventName != null)
                return false;
        } else if (!eventName.equals(other.eventName))
            return false;
        return true;
    }
}
