package com.resume.userservice.vertical.mapper;

import com.resume.userservice.vertical.entity.Vertical;
import com.resume.userservice.vertical.response.VerticalResponse;

import java.util.List;
import java.util.stream.Collectors;

public class VerticalMapper {
    public static List<VerticalResponse> toVerticalResponseList(List<Vertical> verticals) {
        return verticals.stream().map(vertical -> {
            return VerticalResponse.builder()
                    .verticalId(vertical.getVerticalId())
                    .name(vertical.getName())
                    .build();
            })
            .collect(Collectors.toList());
    }
}
