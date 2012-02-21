package com.dss.storage.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.dss.core.IRepository;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.util.hibernate.HibernateEntity;

public abstract class DirectoryStructure
        extends HibernateEntity
{
    private String name;
    private DirectoryStructure parent;
    private List<DirectoryStructure> subDirectory;

    public DirectoryStructure() {
        subDirectory = new ArrayList<DirectoryStructure>();
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    protected DirectoryStructure getParent()
    {
        return parent;
    }

    protected void setParent(DirectoryStructure parent)
    {
//        if (parent != null)
//            parent.addChild(this);
        this.parent = parent;
    }

    public List getChildren()
    {
        return subDirectory;
    }

    public void addChild(DirectoryStructure child)
    {
        if (!subDirectory.contains(child)) {
            subDirectory.add(child);
            child.setParent(this);
            // save();
        }
    }

    protected void removeChild(DirectoryStructure child)
    {
        if (subDirectory.contains(child)) {
            subDirectory.remove(child);
            // save();
        }
    }

    public void remove()
    {
        if (parent != null) {
            parent.removeChild(this);
        }
        // getRepository().delete(this);
        super.remove();
    }

    // public void save()
    // {
    // getRepository().saveOrUpdate(this);
    // }

}