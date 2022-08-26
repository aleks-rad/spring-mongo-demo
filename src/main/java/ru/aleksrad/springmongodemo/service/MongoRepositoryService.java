package ru.aleksrad.springmongodemo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aleksrad.springmongodemo.model.Settings;
import ru.aleksrad.springmongodemo.repository.SettingsRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MongoRepositoryService {

    private SettingsRepository settingsRepository;

    public void saveSettings(Settings settings) {
        settingsRepository.save(settings);
    }

    public Settings findSettingsById(String id){
        return settingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Settings not found by id = " + id));
    }

    public List<Settings> findAllSettings() {
        return settingsRepository.findAll();
    }

    public void removeAllByType(String type) {
        settingsRepository.removeByType(type);
    }

}
