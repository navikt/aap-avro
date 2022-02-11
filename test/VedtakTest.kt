import no.nav.aap.avro.vedtak.v1.Sak
import no.nav.aap.avro.vedtak.v1.Tilstand
import org.junit.Test

internal class VedtakTest {

    @Test
    fun `vurderingsdato kan være null`() {
        Sak.newBuilder()
            .setVurderingsdato(null)
            .setTilstand(Tilstand.BEREGN_INNTEKT)
            .setVilkårsvurderinger(listOf())
            .build()
    }
}