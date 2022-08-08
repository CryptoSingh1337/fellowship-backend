package com.cyborg.fellowshipjms.config.payload;

import lombok.*;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ScholarshipNotification {

    private String title;
    private String description;
    private String url;
}
