import no.nav.aap.avro.sokere.v1.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class BackwardsCompatible {

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun inntekt() {
        Inntekt.newBuilder()
            .setArbeidsgiver("LOL")
            .setInntektsmaned(LocalDate.now())
            .setBelop(2.0)
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun sak() {
        Sak.newBuilder()
            .setSakstyper(listOf())
            .setVurderingsdato(null)
            .setTilstand("YEY")
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun soker() {
        Soker.newBuilder()
            .setPersonident("123")
            .setFodselsdato(LocalDate.now())
            .setSaker(listOf())
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun soknad() {
        Soknad.newBuilder()
            .setPersonident("123")
            .setArbeidetUtenlands(true)
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun vedtak() {
        InntektsgrunnlagForAr.newBuilder()
            .setAr(LocalDate.now())
            .setInntekter(listOf())
            .setBelopForJustering(2.0)
            .setBelopJustertFor6G(1.0)
            .setErBelopJustertFor6G(false)
            .setGrunnlagsfaktor(3.0)
            .build()

        val inntektsgrunnlag = Inntektsgrunnlag.newBuilder()
            .setBeregningsdato(LocalDate.now())
            .setInntekterSiste3Kalenderar(listOf())
            .setFodselsdato(LocalDate.now())
            .setSisteKalenderar(LocalDate.now())
            .setGrunnlagsfaktor(2.0)
            .build()

        Vedtak.newBuilder()
            .setInnvilget(true)
            .setInntektsgrunnlag(inntektsgrunnlag)
            .setSoknadstidspunkt(LocalDateTime.now())
            .setVedtaksdato(LocalDate.now())
            .setVirkningsdato(LocalDate.now())
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun vilkarsvurdering() {
        Vilkarsvurdering.newBuilder()
            .setParagraf("PARA")
            .setLedd(listOf())
            .setTilstand("NICE")
            .setMaVurderesManuelt(true)
            .build()

        Losning_11_2.newBuilder().setErMedlem("JA").build()
        Losning_11_3.newBuilder().setErOppfylt(true).build()
        Losning_11_4_l2_l3.newBuilder().setErOppfylt(true).build()
        Losning_11_5.newBuilder().setGrad(1).build()
        Losning_11_6.newBuilder().setErOppfylt(true).build()
        Losning_11_12_l1.newBuilder().setErOppfylt(true).build()
        Losning_11_29.newBuilder().setErOppfylt(true).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun sakstype() {
        Sakstype.newBuilder()
            .setType("STANDARD")
            .setVilkarsvurderinger(listOf())
            .build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun `vurdering av beregningsdato`() {
        VurderingAvBeregningsdato.newBuilder()
            .setTilstand("SWEET")
            .build()

        LosningVurderingAvBeregningsdato.newBuilder()
            .setBeregningsdato(LocalDate.now())
            .build()
    }
}