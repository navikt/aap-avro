import no.nav.aap.avro.manuell.v1.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class BackwardsCompatible {

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun manuell() {
        Manuell.newBuilder().build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_2() {
        Losning_11_2.newBuilder().setErMedlem("JA").build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_3() {
        Losning_11_3.newBuilder().setErOppfylt(true).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_4_l2_l3() {
        Losning_11_4_l2_l3.newBuilder().setErOppfylt(true).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_5() {
        Losning_11_5.newBuilder().setGrad(1).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_6() {
        Losning_11_6.newBuilder().setErOppfylt(true).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_12_l1() {
        Losning_11_12_l1.newBuilder().setErOppfylt(true).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losning_11_29() {
        Losning_11_29.newBuilder().setErOppfylt(true).build()
    }

    /**
     * Failing test = breaking changes = major release
     */
    @Test
    fun losningVurderingAvBeregningsdato() {
        LosningVurderingAvBeregningsdato.newBuilder().setBeregningsdato(LocalDate.now()).build()
    }
}
