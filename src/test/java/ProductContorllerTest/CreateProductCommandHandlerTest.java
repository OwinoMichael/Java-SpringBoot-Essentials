package ProductContorllerTest;


import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.products.Model.Product;
import com.example.demo.products.ProductRepository;
import com.example.demo.products.commandHandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class CreateProductCommandHandlerTest {

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    //MethodName_StateUnderBehaviour_ExpectedBehaviour
    @Test
    public void createProductCommandHandler_validProduct_returnSuccess(){
        //Given, When, Then
        //Arrange, Act, Assert

        //1.Given
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        //2.When
        ResponseEntity response = createProductCommandHandler.execute(product);

        //3.Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException(){

        //Given
        Product product = new Product();
        product.setId(1);
        product.setPrice(-9.99);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        //When /Then
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));

        //Then
        assertEquals("Product price cannot be negative", exception.getSimpleResponse().getMessage());
    }
}
