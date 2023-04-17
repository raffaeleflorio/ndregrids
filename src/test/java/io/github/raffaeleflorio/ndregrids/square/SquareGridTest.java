package io.github.raffaeleflorio.ndregrids.square;

import io.github.raffaeleflorio.ndregrids.log.LogGridMedia;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SquareGridTest {

    @Test
    void shows_itself_with_cells_in_correct_position() {
        var grid = new SquareGrid(
                List.of(
                        "a",
                        "b",
                        "c",
                        "d"
                )
        );

        var log = grid.show(new LogGridMedia()).output();

        assertThat(log)
                .isEqualTo("0,0=a 0,1=b 1,0=c 1,1=d");
    }

    @Test
    void does_not_show_itself_when_cells_size_is_incompatible() {
        var grid = new SquareGrid(
                List.of(
                        "a",
                        "b"
                )
        );

        assertThatThrownBy(() -> grid.show(new LogGridMedia()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Unable to show the grid because cells size is not a square number");
    }
}