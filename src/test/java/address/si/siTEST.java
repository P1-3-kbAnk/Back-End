package address.si;


import com.kbank.backend.config.AppConfig;
import com.kbank.backend.domain.address.si.service.SiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;


@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
public class siTEST {

    private final SiService siService;

    @Autowired
    public siTEST(SiService service) {
        this.siService = service;
    }


    @Test
    void TEST() {
        siService.addSi("서울특별시");
    }
}
