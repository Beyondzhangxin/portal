package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created by Zhangx on 2017/10/17 at 9:54.
 */
@Entity
@Table(name = "log")
public class Log extends BaseEntity
{

    private static final long serialVersionUID = -914160797849435836L;
    private Calendar time;
    private String name;
    private String ip;
    private String event;


    @Basic
    @Column(name = "time", nullable = false)
    public Calendar getTime()
    {
        return time;
    }

    public void setTime(Calendar time)
    {
        this.time = time;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 128)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 32)
    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    @Basic
    @Column(name = "event", nullable = true, length = 256)
    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (id != null ? !id.equals(log.id) : log.id != null) return false;
        if (time != null ? !time.equals(log.time) : log.time != null) return false;
        if (name != null ? !name.equals(log.name) : log.name != null) return false;
        if (ip != null ? !ip.equals(log.ip) : log.ip != null) return false;
        if (event != null ? !event.equals(log.event) : log.event != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
