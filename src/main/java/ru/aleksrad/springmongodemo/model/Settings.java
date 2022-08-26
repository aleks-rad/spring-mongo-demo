package ru.aleksrad.springmongodemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "settings")
public class Settings {

    @Id
    private String id;
    private String userName;
    private String type;
    private boolean isActive;

}
