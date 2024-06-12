import org.example.Utils.ClientService;
import org.example.Utils.PaymentRequest;
import org.example.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentRequestTest {

    @Autowired
    private ClientRepository repository;

    @Test
    public void toStringTest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(777);
        paymentRequest.setItemId("String");
        paymentRequest.setDiscount(1000.0);
        Assert.assertEquals("UserId: '777', ItemId: 'String', Discount: '1000.0'", paymentRequest.toString());
    }

    @Test
    public void callInterface() {
        ClientService client = null;

    }
}
