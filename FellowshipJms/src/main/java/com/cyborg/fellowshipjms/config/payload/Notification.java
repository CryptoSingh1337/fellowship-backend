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
public class Notification {

    private String title;
    private String description;
    private String url;
}
