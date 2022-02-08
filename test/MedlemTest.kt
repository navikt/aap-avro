import no.nav.aap.avro.medlem.v1.ErMedlem
import no.nav.aap.avro.medlem.v1.Medlem
import no.nav.aap.avro.medlem.v1.Request
import no.nav.aap.avro.medlem.v1.Response
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.util.*

internal class MedlemTest {

    @Test
    fun `example usage`() {
        val incomming = Medlem.newBuilder().apply {
            id = UUID.randomUUID().toString()
            personident = "12345678910"
            request = Request.newBuilder().apply {
                arbeidetUtenlands = true
                ytelse = "AAP"
                mottattDato = LocalDate.now()
            }.build()
        }.build()

        assertNull(incomming.response)

        val outgoing = incomming.apply {
            response = Response.newBuilder().apply {
                erMedlem = ErMedlem.JA
            }.build()
        }

        when (incomming.request.ytelse == "AAP") {
            false -> fail("ugyldig ytelse")
            true -> assertEquals(ErMedlem.JA, outgoing.response.erMedlem)
        }
    }
}
