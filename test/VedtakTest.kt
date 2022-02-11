import no.nav.aap.avro.vedtak.v1.Sak
import org.junit.Test

internal class VedtakTest {

    @Test
    fun `vurderingsdato kan være null`() {
        Sak.newBuilder()
            .setVurderingsdato(null)
            .setTilstand("BEREGN_INNTEKT")
            .setVilkårsvurderinger(listOf())
            .build()
    }
}