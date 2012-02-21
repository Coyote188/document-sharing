package com.dss.util.mongo;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;


//@Configuration
@Component("mongoUtil")
public class MongoDbUtil {
	@Resource(name="mongo")
	private Mongo mongo ;	
    private String dbName;
	private Morphia morphia;
	private String username;
	private String password;
	
//	public Mongo getMongo()
//    {
//        return mongo;
//    }
//    public void setMongo(Mongo mongo)
//    {
//        this.mongo = mongo;
//    }
    
//	@Bean(name="mongoDB")
	public DB getDB(){
		return mongo.getDB(dbName);
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	@Bean(name="mongoDataStore")
//	public Datastore getDataStore(){
//		if(morphia==null)
//			morphia = new Morphia();
//		return morphia.createDatastore(mongo, getDbName(), getUsername(), getPassword().toCharArray());
//	}

}
