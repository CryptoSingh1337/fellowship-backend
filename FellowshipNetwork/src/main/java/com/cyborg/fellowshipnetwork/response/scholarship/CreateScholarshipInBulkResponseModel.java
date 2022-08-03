package com.cyborg.fellowshipnetwork.response.scholarship;

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
public class CreateScholarshipInBulkResponseModel implements Response {

    private int documentCreated;
}
