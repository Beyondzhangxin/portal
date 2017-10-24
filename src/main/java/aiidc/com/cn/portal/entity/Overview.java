package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 公司首页综述性文字
 */
@Entity
@Table(name = "overview")
public class Overview extends BaseEntity
{
    /**
     * 综述类型
     */
    private String type;
    /**
     * 文字内容
     */
    @NotBlank
    private String content;

    @Column(name = "type")
    public String getType()
    {
        return type;
    }


    public void setType(String type)
    {
        this.type = type;
    }

    @Column(name = "content")
    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
