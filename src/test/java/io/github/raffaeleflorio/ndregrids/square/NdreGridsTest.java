package io.github.raffaeleflorio.ndregrids.square;

import io.github.raffaeleflorio.ndregrids.log.LogGridsMedia;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


class NdreGridsTest {

    @Test
    void shows_itself_randomly() {
        var grids = new NdreGrids(1, new Random(0L));

        var first = grids.show(new LogGridsMedia()).output();
        var second = grids.show(new LogGridsMedia()).output();

        assertThat(first)
                .isNotEqualTo(second);
    }

    @Test
    void shows_3x3_square_grids_according_the_count_with_three_empty_cells() {
        var grids = new NdreGrids(2, new Random(0L));

        var log = grids.show(new LogGridsMedia()).output();

        assertThat(log)
                .isEqualTo("[0,0=/ 0,1= 0,2=/ 1,0= 1,1= 1,2=/ 2,0=\\ 2,1=/ 2,2=/] [0,0=\\ 0,1=/ 0,2=/ 1,0=/ 1,1= 1,2= 2,0=\\ 2,1=\\ 2,2=]");
    }
}