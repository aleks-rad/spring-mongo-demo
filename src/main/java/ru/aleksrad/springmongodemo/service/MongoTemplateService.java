package ru.aleksrad.springmongodemo.service;

import com.mongodb.client.result.DeleteResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.aleksrad.springmongodemo.model.Settings;

import java.util.List;

@Service
@AllArgsConstructor
public class MongoTemplateService {

    private MongoTemplate mongoTemplate;

    private static final String SETTINGS = "settings";

    public void saveSettings(Settings settings) {
        mongoTemplate.save(settings, SETTINGS);
    }

    public Settings findSettingsByUserName(String userName) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("userName").is(userName)), Settings.class
        );
    }

    public List<Settings> findAllSettings() {
        return mongoTemplate.findAll(Settings.class, SETTINGS);
    }

    public List<Settings> findAllSettingsByCriteria(CriteriaDefinition criteria) {
        return mongoTemplate.find(Query.query(criteria), Settings.class);
    }

    public DeleteResult removeSettingsWithNotActive() {
        return mongoTemplate.remove(
                Query.query(Criteria.where("isActive").is(false)), SETTINGS
        );
    }

}
