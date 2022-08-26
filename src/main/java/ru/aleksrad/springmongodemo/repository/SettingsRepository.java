package ru.aleksrad.springmongodemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aleksrad.springmongodemo.model.Settings;

public interface SettingsRepository extends MongoRepository<Settings, String> {

    Settings findByUserName(String userName);

    void removeByType(String type);

}
