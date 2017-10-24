package aiidc.com.cn.portal.util;

/**
 * Created by Zhangx on 2017/9/15 at 11:27.
 */
public enum SolutionType
{
    总体("总体解决方案", "1"),
    专项("专项解决方案", "2"),
    ;
    private String name;
    private String value;

    SolutionType(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }
}
