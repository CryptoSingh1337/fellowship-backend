package com.cyborg.fellowshipdataaccess.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "scholarships")
public class Scholarship {

    @Id
    private String id;
    @TextIndexed(weight = 2.0f)
    private String title;
    @Indexed(name = "url_idx", unique = true)
    private String url;
    private String grant;
    @TextIndexed
    private String description;
    private String deadline;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
