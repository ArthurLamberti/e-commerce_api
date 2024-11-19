package com.arthurlamberti.ecommerce.infrastructure.api;

import com.arthurlamberti.ecommerce.ControllerTest;
import com.arthurlamberti.ecommerce.application.address.create.CreateAddressCommand;
import com.arthurlamberti.ecommerce.application.address.create.CreateAddressOutput;
import com.arthurlamberti.ecommerce.application.address.create.CreateAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.delete.DeleteAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.get.GetAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.list.ListAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.update.UpdateAddressUsecase;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest
public class AddressAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateAddressUsecase createAddressUsecase;

    @MockBean
    private ListAddressUsecase listAddressUsecase;

    @MockBean
    private GetAddressUsecase getAddressUsecase;
    @MockBean
    private DeleteAddressUsecase deleteAddressUsecase;
    @MockBean
    private UpdateAddressUsecase updateAddressUsecase;

    @Test
    public void givenAValidCommand_whenCallsCreateAddress_shouldReturnAddressId() throws Exception {
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var input = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        when(createAddressUsecase.execute(any())).thenReturn(CreateAddressOutput.from(expectedAddress));

        final var aRequest = post("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(input));

        final var response = this.mockMvc.perform(aRequest)
                .andDo(print());

        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/addresses/" + expectedAddress.getId().getValue()))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(expectedAddress.getId().getValue())));
    }

    @Test
    public void givenAnInvalidField_whenCallsCreateAddress_shouldReturnAnException() throws Exception{
        final var anAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorMessage = "'name' should not be null";

        final var input = CreateAddressCommand.with(
                null,
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumber(),
                anAddress.getComplement()
        );
        final var notification = Notification.create();
        notification.append(new Error(expectedErrorMessage));
        when(createAddressUsecase.execute(any()))
                .thenThrow(new NotificationException("Error", notification));

        final var aRequest = post("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(input));

        final var response = this.mockMvc.perform(aRequest)
                .andDo(print());

        response.andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Location", nullValue()))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0].message", equalTo(expectedErrorMessage)));
    }

}