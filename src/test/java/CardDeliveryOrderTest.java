import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    @Test
    public void shouldTest() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Южно-Сахалинск");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Ибмупраджон Ослоебоевич");
        $("[data-test-id=phone] input").setValue("+88005553535");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(byClassName("notification__title")).should(ownText("Успешно!"), Duration.ofSeconds(15));
        $(byClassName("notification__content")).should(ownText("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));

    }
}