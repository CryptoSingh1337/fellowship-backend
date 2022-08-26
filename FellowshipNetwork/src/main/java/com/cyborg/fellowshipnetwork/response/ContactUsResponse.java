package com.cyborg.fellowshipnetwork.response;

import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactUsResponse implements Response {

    private String message;
}
