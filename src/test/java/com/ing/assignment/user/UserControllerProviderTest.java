package com.ing.assignment.user;

import java.util.Optional;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.ing.assignment.user.model.Address;
import com.ing.assignment.user.model.User;
import com.ing.assignment.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=8888"
})
@Provider("userservice")
@PactFolder("C:\\Development\\user-service\\src\\test\\pacts")
public class UserControllerProviderTest {

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8888, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State({"provider accepts a new user",
            "person 11 exists"})
    public void toCreateUserState() {
        User user = User.builder()
                .id(11L)
                .firstName("Bill").lastName("Clinton").emailId("bill.clinton@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();

        when(userRepository.findById(eq(11L))).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
    }

}