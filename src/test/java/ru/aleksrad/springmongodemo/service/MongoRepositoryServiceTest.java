package ru.aleksrad.springmongodemo.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aleksrad.springmongodemo.model.Settings;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MongoRepositoryServiceTest {

    @Autowired
    private MongoRepositoryService mongoRepositoryService;

    private static final String REPOSITORY_TYPE = "repository";

    @BeforeEach
    public void beforeEach() {
        mongoRepositoryService.removeAllByType(REPOSITORY_TYPE);
    }

    @Test
    void saveSettingsTest() {
        Settings settings = new Settings();
        settings.setUserName("Иванов");
        settings.setType(REPOSITORY_TYPE);
        settings.setActive(true);
        mongoRepositoryService.saveSettings(settings);

        Settings settingsFound = mongoRepositoryService.findSettingsById(settings.getId());
        assertThat(settingsFound)
                .as("Проверяем исходный settings и полученный после сохранения")
                .isEqualTo(settings);
    }

    @Test
    void findAllSettingsTest() {
        List<Settings> settingsList = List.of(
            Settings.builder()
                    .userName("Петров Петр")
                    .type(REPOSITORY_TYPE)
                    .isActive(true)
                    .build(),
            Settings.builder()
                    .userName("Сидорова Сидора")
                    .type(REPOSITORY_TYPE)
                    .isActive(true)
                    .build()
        );

        mongoRepositoryService.saveSettings(settingsList.get(0));
        mongoRepositoryService.saveSettings(settingsList.get(1));

        List<Settings> allSettings = mongoRepositoryService.findAllSettings();
        assertThat(allSettings.size())
                .as("Проверяем, что кол-во документов больше 0")
                .isGreaterThan(0);
    }

}