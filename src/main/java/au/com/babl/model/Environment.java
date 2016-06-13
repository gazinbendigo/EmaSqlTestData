package main.java.au.com.babl.model;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class Environment
{
    private static final long serialVersionUID = -6566597737760462671L;
    private Long id;
    private String name;
    private String description;
    private String labelName;


    public Environment()
    {
        id = new Long(0);
        name = "";
        description = "";
        labelName = "";
    }

    public Environment(Long id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.labelName = "";
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     *
     * @return the display name
     */
    public String getLabelName()
    {
        return getName() + " - " + getDescription();
    }

    /**
     *
     * @param labelName
     */
    public void setLabelName(String labelName)
    {
        this.labelName = labelName;
    }


    @Override
    public boolean equals(Object other)
    {
        return (other != null && getClass() == other.getClass() && id != null)
                ? id.equals(((Environment) other).id)
                : (other == this);
    }

    @Override
    public int hashCode()
    {
        return (id != null)
                ? (getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }
}
