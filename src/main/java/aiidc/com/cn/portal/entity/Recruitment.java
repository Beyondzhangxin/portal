package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;


/**
 * 人才招聘
 */
@Entity
@Table(name = "recruitment")
public class Recruitment extends BaseEntity
{
    /**
     *
     */
    private static final long serialVersionUID = -8643112777912611727L;
    /**
     * 工作岗位
     */
    @NotBlank
    private String job;
    /**
     * 职位职能
     */
    @NotBlank
    private String jobFunction;
    /**
     * 工作地点
     */
    @NotBlank
    private String workPlace;
    /**
     * 招聘人数
     */
    @NotBlank
    private String hireNumber;
    /**
     * 工作年限
     */
    @NotBlank
    private String workYear;
    /**
     * 语言要求
     */
    private String language;
    /**
     * 学历
     */
    @NotBlank
    private String record;
    /**
     * 薪水范围
     */
    @NotBlank
    private String salary;
    /**
     * 工作职责
     */
    @NotBlank
    private String jobResponse;
    /**
     * 任职条件
     */
    @NotBlank
    private String qualifications;
    @NotBlank
    private String toEmail;
    @NotBlank
    private String remark;
    /**
     * 发布时间
     */
    private Calendar createTime;
    /**
     * 发布人
     */
    private AclUser creator;
    /**
     * 是否置顶 1置顶
     */
    private String isTop = "0";
    /**
     * 审核状态：未审核，通过，不通过 0,1,2
     */
    private String status = "0";

    @Column(name = "to_email")
    public String getToEmail()
    {
        return toEmail;
    }

    public void setToEmail(String toEmail)
    {
        this.toEmail = toEmail;
    }

    @Column(name = "remark")
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Column(name = "is_top")
    public String getIsTop()
    {
        return isTop;
    }

    public void setIsTop(String isTop)
    {
        this.isTop = isTop;
    }

    @Column(name = "status")
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Column(name = "job")
    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    @Column(name = "job_function")
    public String getJobFunction()
    {
        return jobFunction;
    }

    public void setJobFunction(String jobFunction)
    {
        this.jobFunction = jobFunction;
    }

    @Column(name = "work_place")
    public String getWorkPlace()
    {
        return workPlace;
    }

    public void setWorkPlace(String workPlace)
    {
        this.workPlace = workPlace;
    }

    @Column(name = "hire_number")
    public String getHireNumber()
    {
        return hireNumber;
    }

    public void setHireNumber(String hireNumber)
    {
        this.hireNumber = hireNumber;
    }

    @Column(name = "work_year")
    public String getWorkYear()
    {
        return workYear;
    }

    public void setWorkYear(String workYear)
    {
        this.workYear = workYear;
    }

    @Column(name = "language")
    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    @Column(name = "record")
    public String getRecord()
    {
        return record;
    }

    public void setRecord(String record)
    {
        this.record = record;
    }

    @Column(name = "salary")
    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    @Column(name = "job_response")
    public String getJobResponse()
    {
        return jobResponse;
    }

    public void setJobResponse(String jobResponse)
    {
        this.jobResponse = jobResponse;
    }

    @Column(name = "qualifications")
    public String getQualifications()
    {
        return qualifications;
    }

    public void setQualifications(String qualifications)
    {
        this.qualifications = qualifications;
    }

    @Column(name = "create_time")
    public Calendar getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Calendar createTime)
    {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "creator")
    public AclUser getCreator()
    {
        return creator;
    }

    public void setCreator(AclUser creator)
    {
        this.creator = creator;
    }


}