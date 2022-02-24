import no.nav.aap.avro.medlem.v1.ErMedlem
import no.nav.aap.avro.medlem.v1.Medlem
import no.nav.aap.avro.medlem.v1.Request
import no.nav.aap.avro.medlem.v1.Response
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class BackwardsCompatible {

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun medlem() {
        Medlem.newBuilder()
            .setPersonident("123")
            .setId("ABC")
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun response() {
        Response.newBuilder()
            .setErMedlem(ErMedlem.JA)
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun request() {
        Request.newBuilder()
            .setMottattDato(LocalDate.now())
            .setYtelse("AAP")
            .setArbeidetUtenlands(true)
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun `er medlem`() {
        ErMedlem.JA
        ErMedlem.NEI
        ErMedlem.UAVKLART
    }
}
