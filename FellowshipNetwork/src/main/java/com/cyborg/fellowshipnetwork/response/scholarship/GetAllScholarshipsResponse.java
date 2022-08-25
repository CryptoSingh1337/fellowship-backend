package com.cyborg.fellowshipnetwork.response.scholarship;

import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;
import org.springframework.data.domain.Page;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllScholarshipsResponse implements Response {

    private Page<ScholarshipResponseModel> scholarships;
}
