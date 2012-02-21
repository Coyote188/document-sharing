package com.dss.storage.ui.control;

public class EditRootDocumentDirectoryException
        extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -8439652961439338514L;

    @Override
    public String getMessage()
    {
        return "根目录不能修改";
    }

    @Override
    public String toString()
    {
        return "编辑目录错误： " + getMessage() + " ！";
    }
    
   
}
