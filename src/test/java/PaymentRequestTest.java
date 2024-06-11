import ru.practice.Utils.PaymentRequest;
import org.junit.Assert;
import org.junit.Test;

public class PaymentRequestTest {
    @Test
    public void toStringTest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(777);
        paymentRequest.setItemId("String");
        paymentRequest.setDiscount(1000.0);
        Assert.assertEquals("UserId: '777', ItemId: 'String', Discount: '1000.0'", paymentRequest.toString());
    }
}
