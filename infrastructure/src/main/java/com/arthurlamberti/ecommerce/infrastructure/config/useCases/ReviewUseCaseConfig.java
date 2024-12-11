package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.review.create.CreateReviewUseCase;
import com.arthurlamberti.ecommerce.application.review.create.DefaultCreateReviewUseCase;
import com.arthurlamberti.ecommerce.application.review.retrieve.get.DefaultGetReviewUseCase;
import com.arthurlamberti.ecommerce.application.review.retrieve.get.GetReviewUseCase;
import com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item.DefaultGetReviewsByItemUseCase;
import com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item.GetReviewsByItemUseCase;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewUseCaseConfig {

    private final ItemGateway itemGateway;
    private final ReviewGateway reviewGateway;
    private final CustomerGateway customerGateway;


    public ReviewUseCaseConfig(ItemGateway itemGateway, ReviewGateway reviewGateway, CustomerGateway customerGateway) {
        this.itemGateway = itemGateway;
        this.reviewGateway = reviewGateway;
        this.customerGateway = customerGateway;
    }

    @Bean
    public CreateReviewUseCase createReviewUseCase(){
        return new DefaultCreateReviewUseCase(reviewGateway,customerGateway,itemGateway);
    }

    @Bean
    public GetReviewUseCase getReviewUseCase(){
        return new DefaultGetReviewUseCase(reviewGateway);
    }

    @Bean
    public GetReviewsByItemUseCase getReviewsByItemUseCase(){
        return new DefaultGetReviewsByItemUseCase(reviewGateway);
    }
}
