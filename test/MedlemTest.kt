import no.nav.aap.avro.medlem.v1.ErMedlem
import no.nav.aap.avro.medlem.v1.Medlem
import no.nav.aap.avro.medlem.v1.Request
import no.nav.aap.avro.medlem.v1.Response
import org.junit.Test
import java.time.LocalDate
import java.util.*
import kotlin.test.assertFails

internal class MedlemTest {

    @Test
    fun `happy path`() {
        defaultMedlem()
        defaultMedlem().withDefaultRequest()
        defaultMedlem().withDefaultRequest().withResponse()
        defaultMedlem().withDefaultRequest().withResponse(ErMedlem.UAVKLART, "en begrunnelse")
    }

    @Test
    fun `id required`() {
        assertFails { Medlem.newBuilder().setPersonident("123").build() }
    }

    @Test
    fun `personident required`() {
        assertFails { Medlem.newBuilder().setId("ABC").build() }
    }

    @Test
    fun `arbeidetUtlands required`() {
        assertFails {
            defaultMedlem().apply {
                request = Request.newBuilder()
                    .setYtelse("AAP")
                    .setMottattDato(LocalDate.now())
                    .build()
            }
        }
    }

    @Test
    fun `yelse required`() {
        assertFails {
            defaultMedlem().apply {
                request = Request.newBuilder()
                    .setArbeidetUtenlands(false)
                    .setMottattDato(LocalDate.now())
                    .build()
            }
        }
    }

    @Test
    fun `mottatt dato required`() {
        assertFails {
            defaultMedlem().apply {
                request = Request.newBuilder()
                    .setArbeidetUtenlands(false)
                    .setYtelse("AAP")
                    .build()
            }
        }
    }

    @Test
    fun `erMedlem required`() {
        assertFails {
            defaultMedlem().apply {
                response = Response.newBuilder()
                    .setBegrunnelse("begrunnelse")
                    .build()
            }
        }
    }
}

private fun defaultMedlem() =
    Medlem.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setPersonident("12345678910")
        .build()

private fun Medlem.withDefaultRequest() = apply {
    request = Request.newBuilder()
        .setArbeidetUtenlands(false)
        .setYtelse("AAP")
        .setMottattDato(LocalDate.now())
        .build()
}

private fun Medlem.withResponse(svar: ErMedlem = ErMedlem.JA, begrunnelse: String? = null) = apply {
    response = Response.newBuilder()
        .setErMedlem(svar)
        .setBegrunnelse(begrunnelse)
        .build()
}
