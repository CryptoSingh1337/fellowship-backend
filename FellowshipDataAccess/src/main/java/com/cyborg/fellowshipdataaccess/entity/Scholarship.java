package com.cyborg.fellowshipdataaccess.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
    private List<String> country;
    private List<Degree> degrees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @TextScore
    private Float score;
}
