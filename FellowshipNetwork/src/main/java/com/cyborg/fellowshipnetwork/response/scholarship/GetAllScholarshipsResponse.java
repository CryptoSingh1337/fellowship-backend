package com.cyborg.fellowshipnetwork.response.scholarship;

import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;

import java.util.List;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllScholarshipsResponse implements Response {

    private List<ScholarshipResponseModel> scholarships;
}
