package com.dss.storage.repository.util.impl;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dss.storage.repository.util.FileUtil;
import com.dss.util.log.LogUtil;
import com.dss.util.mongo.MongoDbUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;

@Component("fileManager")
public class MongoFileUtil implements FileUtil {	
    
    @Resource(name="mongoUtil")
    private MongoDbUtil util;
    public MongoFileUtil(){
    }
	
	public String saveFile(String fileName, String location, byte[] in){
		GridFS gfs= new GridFS(util.getDB(),location);
		LogUtil.debug(getClass(),"inputstream: "+ in);		
		GridFSInputFile file= gfs.createFile(in);
		file.setFilename(fileName);
		file.save();	
		return file.getMD5();
	}
	
	public InputStream getFile(String md5,String location){
		GridFS gfs= new GridFS(util.getDB(),location);
		DBObject query= new BasicDBObject("md5", md5);
		return gfs.findOne(query).getInputStream();
	}

    @Override
    public void removeFile(String name, String location)
    {
        GridFS gfs= new GridFS(util.getDB(),location);
        gfs.remove(name);
    }	
	

}
