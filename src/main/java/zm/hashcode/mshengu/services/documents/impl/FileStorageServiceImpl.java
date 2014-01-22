/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.documents.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import java.io.InputStream;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author Luckbliss
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private GridFsOperations gridOperation;

    @Override
    public String save(InputStream inputStream, String contentType, String filename) {
        DBObject metaData = new BasicDBObject();
        metaData.put("filename", filename);
        metaData.put("contentType", contentType);
        GridFSFile file = gridOperation.store(inputStream, filename, metaData);
        return file.getId().toString();
    }

    @Override
    public GridFSDBFile getById(String id) {
        return gridOperation.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))));
    }

    @Override
    public GridFSDBFile getByFilename(String filename) {
        return gridOperation.findOne(new Query(Criteria.where("filename").is(filename)));
    }

    @Override
    public void removeById(String id) {
        gridOperation.delete(new Query(Criteria.where("_id").is(new ObjectId(id))));
    }
}
