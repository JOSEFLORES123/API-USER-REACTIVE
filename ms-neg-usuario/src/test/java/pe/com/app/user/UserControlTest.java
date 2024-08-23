package pe.com.app.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import pe.com.app.user.control.UserControl;
import pe.com.app.user.control.request.UserRequest;
import pe.com.app.user.control.response.UserResponse;
import pe.com.app.user.exception.BusinessException;
import pe.com.app.user.servicio.UserService;
import pe.com.app.user.servicio.UserServiceImpl;
import pe.com.app.user.util.ConstProperties;
import pe.com.app.user.util.ErrorResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserControl.class)
@Import({UserServiceImpl.class, ConstProperties.class})
class UserControlTest {

	@MockBean
	private UserService userService;

	@Autowired
	private ConstProperties constProperties;

	@Autowired
	public WebTestClient webTestClient;

	@Test
	void save_ok() {
		UserRequest request = RptUtil.generateUserRequest();

		given(userService.add(any())).willReturn( Mono.just(RptUtil.generateUserResponse()) );

		webTestClient.post()
				.uri("/users")
				.body(BodyInserters.fromValue(request))
				.accept(APPLICATION_JSON).exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBody(UserResponse.class);
	}

	@Test
	void save_invalid_fields() {
		UserRequest request = RptUtil.generateUserRequestWithInvalidFields();

		given(userService.add(any())).willReturn( Mono.just(RptUtil.generateUserResponse()) );

		webTestClient.post()
				.uri("/users")
				.body(BodyInserters.fromValue(request))
				.accept(APPLICATION_JSON).exchange()
				.expectStatus().is4xxClientError()
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBody(ErrorResponse.class)
				.value(errorResponse -> {
					assertTrue(errorResponse.getMessage().contains("no es valido"));

				});
	}

	@Test
	void getById_User_NotExist() {

		given(userService.getById(any())).willReturn( Mono.error(new BusinessException("Usuario no existe")) );

		webTestClient.get()
				.uri("/users/{id}", "9c44021f-986c-4f5e-b09f-382ad14ea2db")
				.exchange()
				.expectStatus().is4xxClientError()
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBody(ErrorResponse.class)
				.value(errorResponse -> {
					assertEquals("Usuario no existe", errorResponse.getMessage());

				});
	}

	@Test
	void getAll_ok() {
		given(userService.getAll()).willReturn( Flux.fromIterable(RptUtil.generateUserResponseList()) );

		webTestClient.get()
				.uri("/users")
				.accept(APPLICATION_JSON).exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBodyList(UserResponse.class);
	}
}
