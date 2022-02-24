import no.nav.aap.avro.inntekter.v1.Inntekt
import no.nav.aap.avro.inntekter.v1.Inntekter
import no.nav.aap.avro.inntekter.v1.Request
import no.nav.aap.avro.inntekter.v1.Response
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class BackwardsCompatible {

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun inntekt() {
        Inntekt.newBuilder()
            .setArbeidsgiver("NAV")
            .setInntektsmaned(LocalDate.now())
            .setBelop(2.0)
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun request() {
        Request.newBuilder()
            .setFom(LocalDate.now())
            .setTom(LocalDate.now())
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun response() {
        Response.newBuilder()
            .setInntekter(listOf())
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun inntekter() {
        Inntekter.newBuilder()
            .setPersonident("123")
            .build()
    }
}