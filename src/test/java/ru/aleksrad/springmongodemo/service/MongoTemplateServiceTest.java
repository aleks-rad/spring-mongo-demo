package ru.aleksrad.springmongodemo.service;

import com.mongodb.client.result.DeleteResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import ru.aleksrad.springmongodemo.model.Settings;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MongoTemplateServiceTest {

    @Autowired
    private MongoTemplateService mongoTemplateService;

    @Test
    void saveSettingsTest() {
        Settings settings = new Settings();
        settings.setUserName("Пупов");
        settings.setLang("ru");
        settings.setActive(true);
        mongoTemplateService.saveSettings(settings);

        Settings settingsFound = mongoTemplateService.findSettingsByUserName(settings.getUserName());
        assertThat(settingsFound)
                .as("Проверяем найденный документ")
                .isEqualTo(settings);
    }

    @Test
    void findAllSettingsTest() {
        Settings settings = new Settings();
        settings.setUserName("Пупкина");
        settings.setLang("ru");
        settings.setActive(true);
        mongoTemplateService.saveSettings(settings);

        List<Settings> allSettings = mongoTemplateService.findAllSettings();
        assertThat(allSettings.size())
                .as("Кол-во найденных документов должно быть больше 0")
                .isGreaterThan(0);
    }

    @Test
    void removeSettingsTest() {
        Settings settings = new Settings();
        settings.setUserName("Удалите меня");
        settings.setLang("ru");
        settings.setActive(false);
        mongoTemplateService.saveSettings(settings);

        List<Settings> notActiveSettings = mongoTemplateService.findAllSettingsByCriteria(Criteria.where("isActive").is(false));
        assertThat(notActiveSettings.size())
                .as("Кол-во найденных документов должно быть больше 0")
                .isGreaterThan(0);

        DeleteResult deleteResult = mongoTemplateService.removeSettingsWithNotActive();
        assertThat(deleteResult.getDeletedCount())
                .as("Проверяем кол-во удаленных документов")
                .isEqualTo(notActiveSettings.size());
    }
}