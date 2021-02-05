package com.developer.assignment.service.order;

import com.developer.assignment.repository.second.OrderSecondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderSearchService {

    private final OrderSecondRepository orderSecondRepository;

}
