/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.conf;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author boniface
 */
@Configuration
@ComponentScan("zm.hashcode.mshengu")
@EnableMongoRepositories(basePackages = "zm.hashcode.mshengu.repository")
public class RepositoryConfig extends AbstractMongoConfiguration {
    

    @Override
    protected String getDatabaseName() {
        return "mshengu";
    }

    @Override
    public Mongo mongo() throws Exception {
        Mongo mongo = new Mongo();
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
    }

    @Override
    protected String getMappingBasePackage() {
        return "zm.hashcode.mshengu.repository";
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }
}
